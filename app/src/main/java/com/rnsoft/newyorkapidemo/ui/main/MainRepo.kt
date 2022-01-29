package com.rnsoft.newyorkapidemo

import javax.inject.Inject

class MainRepo @Inject constructor(private val mainDataSource: MainDataSource)  {

    suspend fun fetchNewYorkApiModel(section:String, period:Int):Result<NewYorkApiModel>{

        return mainDataSource.fetchNewYorkApiModel(section, period)
    }
}