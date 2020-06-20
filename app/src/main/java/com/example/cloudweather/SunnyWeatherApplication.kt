package com.example.cloudweather

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

/**
 *Build time：2020/6/20
 *Author：Tang
 *Des:
 */
class SunnyWeatherApplication :Application(){
    companion object{
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
        const val TOKEN = ""
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }
}