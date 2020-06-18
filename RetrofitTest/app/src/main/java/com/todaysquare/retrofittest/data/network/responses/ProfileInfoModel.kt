package com.todaysquare.retrofittest.data.network.responses

import com.google.gson.annotations.Expose


data class ProfileInfoModel(
    @field:Expose var gender: String,
    @field:Expose var nickName: String,
    @field:Expose var weight: Int,
    @field:Expose var height: Int

)