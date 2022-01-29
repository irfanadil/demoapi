package com.rnsoft.newyorkapidemo

import android.util.Log
import java.io.IOException
import javax.inject.Inject


class MainDataSource @Inject constructor(private val serverApi: ServerApi) {



    suspend fun fetchNewYorkApiModel(section:String, period:Int):Result<NewYorkApiModel> {
        return try {
            val response = serverApi.fetchNewYorkApiModel(
                section = section, period = period, apikey = AppConstant.API_KEY
            )
            Log.e("response-", response.toString())
            Result.Success(response)
        } catch (e: Throwable) {
                Result.Error(IOException("Error notification -", e))
        }
    }
}