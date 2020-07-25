package com.festive.pushnotificationtest

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RegisterWorker(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {
    private val TAG = "RegisterWorker"
    override fun doWork(): Result {
        val token = inputData.getString("token")
        val retrofit = Retrofit.Builder().baseUrl("http://10.0.2.2:8080/")
            .addConverterFactory(GsonConverterFactory.create()).build()
        val registerService:RegisterService = retrofit.create(RegisterService::class.java)
        registerService.register(7,token).enqueue(object : Callback<MyResponse> {
            override fun onFailure(call: Call<MyResponse>, t: Throwable) {
                t.printStackTrace()
            }

            override fun onResponse(call: Call<MyResponse>, response: Response<MyResponse>) {
                Log.i(TAG,"response registering token: ${response.body()}")
            }
        })
        return Result.success()
    }
}