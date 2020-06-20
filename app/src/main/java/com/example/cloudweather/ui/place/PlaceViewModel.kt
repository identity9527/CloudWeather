package com.example.cloudweather.ui.place

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.cloudweather.logic.Respository
import com.example.cloudweather.logic.model.Place

/**
 *Build time：2020/6/20
 *Author：Tang
 *Des:
 */
class PlaceViewModel :ViewModel(){

    /**注册一个对数据query地址进行监听的LiveData*/
    private val searchLiveData = MutableLiveData<String>()

    /**声明集合对天气结果进行缓存，避免屏幕翻转组件重启问题*/
    val placeList = ArrayList<Place>()

    /**监听searchLiveData变化，监听到变化时调用Respository.searchPlaces，并返回请求结果*/
    val placeLiveData = Transformations.switchMap(searchLiveData){query ->
        Respository.searchPlaces(query)
    }

    /**为直接调用仓库层的search方法，而是通过数据监听间接调用*/
    fun searchPlaces(query:String){
        searchLiveData.value = query
    }
}