package com.rnsoft.newyorkapidemo

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltViewModel
class  MainViewModel @Inject constructor(private val mainRepo:MainRepo) : ViewModel() {
    // TODO: Implement the ViewModel

    private  val _itemList:MutableLiveData<NewYorkApiModel> = MutableLiveData()
    val itemList:LiveData<NewYorkApiModel> = _itemList

    private  val _item:MutableLiveData<ResponseResult> = MutableLiveData()
    val item:LiveData<ResponseResult> = _item

    fun fetchNewYorkApiModel(section:String, period:Int){
        viewModelScope.launch(Dispatchers.IO) {
           val result = mainRepo.fetchNewYorkApiModel(section, period)
           if(result is Result.Success) {
               withContext(Dispatchers.Main) {
                   _itemList.value = result.data
               }
           }
           else{
               // post error to the UI....
           }
        }
    }

    fun updateItem(selectedItem:ResponseResult){
        _item.value = selectedItem
    }



}