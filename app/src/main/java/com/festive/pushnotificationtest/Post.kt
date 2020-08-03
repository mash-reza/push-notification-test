package com.festive.pushnotificationtest

import com.google.gson.annotations.SerializedName

data class Post(var id: Int, var userId: Int, var title: String, @SerializedName("body") var text: String)