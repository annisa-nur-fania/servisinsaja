package com.servisinsaja.v2.Api

import com.google.gson.annotations.SerializedName

data class ResponseServis(

	@field:SerializedName("data")
	val data: List<DataItem?>?,

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("status")
	val status: Int
)

data class DataItem(

	@field:SerializedName("telp")
	val telp: String,

	@field:SerializedName("nama_bengkel")
	val namaBengkel: String,

	@field:SerializedName("id_kategori")
	val idKategori: String,

	@field:SerializedName("latitude")
	val latitude: String,

	@field:SerializedName("longtitude")
	val longtitude: String,

	@field:SerializedName("id")
	val id: String,

	@field:SerializedName("gambar")
	val gambar: Any
)
