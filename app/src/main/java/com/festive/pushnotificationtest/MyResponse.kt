package com.festive.pushnotificationtest

import com.google.gson.annotations.SerializedName

data class MyResponse(@SerializedName("message") val message: String)