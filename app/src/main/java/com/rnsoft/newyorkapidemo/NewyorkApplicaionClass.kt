package com.rnsoft.newyorkapidemo

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
open class ApplicationClass : Application()
{
    //override fun onCreate() { super.onCreate() }
}