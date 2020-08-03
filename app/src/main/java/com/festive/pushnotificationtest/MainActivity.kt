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

    private lateinit var retrofit: Retrofit
    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .build()

//        getPosts()
        getComments()
    }

    private fun getPosts(){
        retrofit.create(JsonPlaceHolderApi::class.java).getPosts(
            hashMapOf(Pair("userId","2"), Pair("_sort","id"),Pair("_order","desc"))
        ).enqueue(object : Callback<List<Post>> {
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

    private fun getComments(){
        retrofit.create(JsonPlaceHolderApi::class.java).getComments("https://jsonplaceholder.typicode.com/posts/1/comments").enqueue(object : Callback<List<Comment>> {
            override fun onFailure(call: Call<List<Comment>>, t: Throwable) {
                textView.text = t.message
            }

            override fun onResponse(call: Call<List<Comment>>, response: Response<List<Comment>>) {
                if (!response.isSuccessful) {
                    textView.text = "error. code:${response.code()}"
                    return
                }

                val comments = response.body()
                for (comment in comments!!.iterator()) {
                    textView.append(
                        "ID: ${comment.id}\n" +
                                "name: ${comment.name}\n" +
                                "postId: ${comment.postId}\n" +
                                "email: ${comment.email}\n" +
                                "text: ${comment.text}\n\n"
                    )
                }
            }
        })
    }
}
