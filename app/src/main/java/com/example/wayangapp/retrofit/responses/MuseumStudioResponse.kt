package com.example.wayangapp.retrofit.responses

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class MuseumStudioResponse(

	@field:SerializedName("msg")
	val msg: String,

	@field:SerializedName("data")
	val data: ArrayList<ListMuseumStudioItems>,

	@field:SerializedName("error")
	val error: String
)

@Parcelize
data class ListMuseumStudioItems(

	@field:SerializedName("image")
	val image: Int,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("description")
	val description: String,

	@field:SerializedName("location")
	val location: String,

	@field:SerializedName("id")
	val id: Int
): Parcelable
