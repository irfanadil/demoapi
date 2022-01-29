package com.rnsoft.newyorkapidemo

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

interface AdapterClickListener {
    fun clickOnImageView(position: Int)
    fun navigateTo(position: Int)
}

class ApiResultAdapter
internal constructor(
    passedNewYorkApiModel: List<ResponseResult>, onAdapterClickListener: MainFragment
) :  RecyclerView.Adapter<ApiResultAdapter.ResponseResultViewHolder>() {

    private val resultList = mutableListOf<ResponseResult>()
    private var classScopedItemClickListener: AdapterClickListener = onAdapterClickListener

    init {
        resultList.addAll(passedNewYorkApiModel)
        this.classScopedItemClickListener = onAdapterClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResponseResultViewHolder {
        val holder: ResponseResultViewHolder
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        holder = ResponseResultViewHolder(inflater.inflate(R.layout.result_view_holder, parent, false))
        return holder

    }

    override fun onBindViewHolder(holder: ResponseResultViewHolder, position: Int) {
        val result  = resultList[position]
        val media = result.media
        media?.let { it ->
            if(it.size>0) {
                val metaDataSize =it[0].meteDataList
                metaDataSize?.let {
                        if(it.size>0){
                            val firstImageUrl  = it[0].url
                            if(firstImageUrl.isNotEmpty() && firstImageUrl.isNotBlank()){
                                Log.e("bingo ", firstImageUrl)
                                Glide.with(holder.imageView.context).load(firstImageUrl).circleCrop().into(holder.imageView)
                            }
                        }
                }
            }
        }
        holder.firstString.text = result.abstractString
        holder.secondString.text = result.byline
    }

    override fun getItemCount(): Int { return resultList.size }

    inner class ResponseResultViewHolder(itemView: View ) : RecyclerView.ViewHolder(itemView) {
        var firstString: TextView = itemView.findViewById(R.id.first_string)
        var secondString: TextView = itemView.findViewById(R.id.second_string)
        var imageView: ImageView = itemView.findViewById(R.id.imageView3)
        private var cardViewConstraintLayout: ConstraintLayout = itemView.findViewById(R.id.docCardView)

       init {
           imageView.setOnClickListener {
               classScopedItemClickListener.clickOnImageView(adapterPosition)
           }
           cardViewConstraintLayout.setOnClickListener{
               classScopedItemClickListener.navigateTo(adapterPosition)
           }
       }
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    fun updateResultListUsingDiffUtil(resultList: List<ResponseResult>) {
        val diffCallback = ApiResultDiffUtil(this.resultList, resultList)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        this.resultList.clear()
        this.resultList.addAll(resultList)
        diffResult.dispatchUpdatesTo(this)
    }
}