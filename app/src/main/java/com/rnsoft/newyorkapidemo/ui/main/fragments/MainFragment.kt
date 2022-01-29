package com.rnsoft.newyorkapidemo

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rnsoft.newyorkapidemo.databinding.MainFragmentLayoutBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment() , AdapterClickListener {

    private val viewModel: MainViewModel by activityViewModels()

    private lateinit var apiResultAdapter: ApiResultAdapter
    private  var responseResultListing: ArrayList<ResponseResult> = ArrayList()

    private var _binding: MainFragmentLayoutBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MainFragmentLayoutBinding.inflate(inflater, container, false)
        val view = binding.root
        setupUI()
        observeData()
        return view
    }

    private fun setupUI(){
        binding.recycleView.apply {
            this.layoutManager = LinearLayoutManager(requireContext())
            this.setHasFixedSize(true)
            apiResultAdapter = ApiResultAdapter(responseResultListing , this@MainFragment)  // set the custom adapter to the RecyclerView
            this.adapter = apiResultAdapter
        }
    }

    private fun observeData(){
        viewModel.fetchNewYorkApiModel("all-sections", 7)
        viewModel.itemList.observe(viewLifecycleOwner) {
            Log.e("response = ", it.toString())
            val resultList = it.results
            val lastSize = resultList?.size
            Log.e("lastSize = ", lastSize.toString())

            resultList?.let { newResultList->
                responseResultListing = newResultList
                apiResultAdapter.updateResultListUsingDiffUtil(newResultList)
            }
        }
    }

    override fun clickOnImageView(position: Int) {}
    override fun navigateTo(position: Int) {
        viewModel.updateItem(responseResultListing[position])
        findNavController().navigate(R.id.nav_to_detail_fragment , null)
    }

    //override fun onActivityCreated(savedInstanceState: Bundle?) { super.onActivityCreated(savedInstanceState) }

}