package com.example.cloudweather.logic.model

import com.google.gson.annotations.SerializedName

/**
 *Build time：2020/6/20
 *Author：Tang
 *Des:
 */
data class PlaceResponse(val status:String,val places:List<Place>)

/**
 * @SerializedName
 * 解决json的key命名与kotlin不一致问题
*/
data class Place(val name:String,val location:Location,@SerializedName("formatted_address")val address:String)

data class Location(val lng:String,val lat:String)