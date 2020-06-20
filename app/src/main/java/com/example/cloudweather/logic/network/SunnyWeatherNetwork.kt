package com.example.cloudweather.logic.network

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.await
import retrofit2.http.Query
import java.lang.RuntimeException
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

/**
 *Build time：2020/6/20
 *Author：Tang
 *Des:
 */
object SunnyWeatherNetwork {
    /**获取service实例*/
    private val placeService = ServiceCreator.create<PlaceService>()

    /**得到Call实例，调用扩展方法await*/
    suspend fun searchPlaces(query: String) = placeService.searchPlaces(query).await()

    /**扩展Call，定义可挂起函数*/
    private suspend fun <T> Call<T>.await():T{
        /**
         * suspendCoroutin函数保证立即执行，且将外层携程挂起
         * 当调用resume、resumeWithException方法时，suspendCoroutin将结果返回被挂起携程，并恢复被挂起携程
         * */
        return suspendCoroutine { continuation ->
            enqueue(object :Callback<T>{
                override fun onFailure(call: Call<T>, t: Throwable) {
                    continuation.resumeWithException(t)
                }

                override fun onResponse(call: Call<T>, response: Response<T>) {
                    val body = response.body()
                    if(body!=null) continuation.resume(body)
                    else continuation.resumeWithException(RuntimeException("response body is null"))
                }
            })
        }
    }
}