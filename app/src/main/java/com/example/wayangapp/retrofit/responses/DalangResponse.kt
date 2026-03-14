package com.example.wayangapp.retrofit.responses

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class DalangResponse(

    @field:SerializedName("msg")
    val msg: String,

    @field:SerializedName("data")
    val data: ArrayList<DataItem>,

    @field:SerializedName("error")
    val error: String
)

@Parcelize
data class DataItem(

//	@field:SerializedName("image")
//	val image: String,

    @field:SerializedName("image")
    val image: Int,

    @field:SerializedName("born")
    val born: String,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("biography")
    val biography: String,
) : Parcelable
