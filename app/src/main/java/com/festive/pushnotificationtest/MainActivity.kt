package com.festive.pushnotificationtest

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.GsonBuilder
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

        val Gson = GsonBuilder().serializeNulls().create()

        retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(Gson))
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .build()

//        getPosts()
//        getComments()
//        createPost()
//        updatePost()
        deletePost()
    }

    private fun deletePost() {
        retrofit.create(JsonPlaceHolderApi::class.java).deletePost(
            2
        ).enqueue(object : Callback<Post> {
            override fun onFailure(call: Call<Post>, t: Throwable) {
                textView.text = t.message
            }

            override fun onResponse(call: Call<Post>, response: Response<Post>) {
                textView.text = "status code: ${response.code()}"
            }
        })
    }

    private fun updatePost() {
        retrofit.create(JsonPlaceHolderApi::class.java).patchPost(
            2, Post(null, 13, null, "dsd")
        ).enqueue(object : Callback<Post> {
            override fun onFailure(call: Call<Post>, t: Throwable) {
                textView.text = t.message
            }

            override fun onResponse(call: Call<Post>, response: Response<Post>) {
                if (!response.isSuccessful) {
                    textView.text = "error. code:${response.code()}"
                    return
                }

                val post = response.body()
                textView.append(
                    "code status: ${response.code()}\n" +
                            "ID: ${post?.id}\n" +
                            "title: ${post?.title}\n" +
                            "userId: ${post?.userId}\n" +
                            "text: ${post?.text}\n\n"
                )
            }
        })
    }

    private fun createPost() {
        retrofit.create(JsonPlaceHolderApi::class.java).createPost(
//            Post(id = null, userId = 4, title = "Reza", text = "I am android developer")
//        4,"Reza","I am Java Developer"
            hashMapOf<String, String>(Pair("userId", "78"), Pair("title", "C Developer"))
        ).enqueue(object : Callback<Post> {
            override fun onFailure(call: Call<Post>, t: Throwable) {
                textView.text = t.message
            }

            override fun onResponse(call: Call<Post>, response: Response<Post>) {
                if (!response.isSuccessful) {
                    textView.text = "error. code:${response.code()}"
                    return
                }

                val post = response.body()
                textView.append(
                    "ID: ${post?.id}\n" +
                            "title: ${post?.title}\n" +
                            "userId: ${post?.userId}\n" +
                            "text: ${post?.text}\n\n"
                )
            }
        })
    }

    private fun getPosts() {
        retrofit.create(JsonPlaceHolderApi::class.java).getPosts(
            hashMapOf(Pair("userId", "2"), Pair("_sort", "id"), Pair("_order", "desc"))
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

    private fun getComments() {
        retrofit.create(JsonPlaceHolderApi::class.java)
            .getComments("https://jsonplaceholder.typicode.com/posts/1/comments")
            .enqueue(object : Callback<List<Comment>> {
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
