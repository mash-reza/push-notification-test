package com.festive.pushnotificationtest

import com.google.gson.annotations.SerializedName

data class Comment(
    var postId: Int,
    var id: Int,
    var name: String,
    var email: String,
    @SerializedName("body") var text: String
)