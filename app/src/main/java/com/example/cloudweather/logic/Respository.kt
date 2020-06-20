package com.example.cloudweather.logic

import androidx.lifecycle.liveData
import com.example.cloudweather.logic.model.Place
import com.example.cloudweather.logic.network.SunnyWeatherNetwork
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception
import java.lang.RuntimeException


/**
 *Build time：2020/6/20
 *Author：Tang
 *Des: 仓库层：类似于数据获取与缓存的中间层，主要判断数据是从本地获取还是网络获取
 */
object Respository {

    /**Dispatchers.IO指定任务运行在子线程中*/
    fun searchPlaces(query:String) = liveData(Dispatchers.IO){
        val result = try {
            /**调用网络层天气请求函数*/
            val placeResponse = SunnyWeatherNetwork.searchPlaces(query)
            if(placeResponse.status=="ok"){
                /**获取天气数据，抛弃状态值*/
                val  places = placeResponse.places
                /**通过生命周期的Result设置结果*/
                Result.success(places)
            }else{
                Result.failure(RuntimeException("response status is ${placeResponse.status}"))
            }
        }catch (e :Exception){
            Result.failure<List<Place>>(e)
        }
        /**
         * 使用emit方法将结果发射出去，类似于LiveData的setValue方法
         * 提供该方法的主要原因是方法内无法获取返回的LiveData对象
         **/
        emit(result as Result<List<Place>>)
    }

}