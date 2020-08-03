package com.festive.pushnotificationtest

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .build()

        retrofit.create(JsonPlaceHolderApi::class.java).posts.enqueue(object : Callback<List<Post>> {
            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                textView.text = t.message
            }

            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                if (!response.isSuccessful) {
                    textView.text = "error. code:${response.code()}"
                    return
                }

                val posts = response.body()
                for (post in posts!!.iterator()) {
                    textView.append(
                        "ID: ${post.id}\n" +
                                "title: ${post.title}\n" +
                                "userId: ${post.userId}\n" +
                                "text: ${post.text}\n\n"
                    )
                }
            }
        })

    }
}
