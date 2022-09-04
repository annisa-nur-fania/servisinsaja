package com.servisinsaja.v2.ApiPerbaikan

import com.google.gson.annotations.SerializedName

data class ResponsePerbaikan(

	@field:SerializedName("ResponsePerbaikan")
	val responsePerbaikan: List<ResponsePerbaikanItem>
)

data class ResponsePerbaikanItem(

	@field:SerializedName("harga")
    var harga: String,

	@field:SerializedName("id_jenis")
	val idJenis: String,

	@field:SerializedName("id")
	val id: String,

	@field:SerializedName("jenis_perbaikan")
	val jenisPerbaikan: String
)
