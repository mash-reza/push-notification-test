package com.festive.pushnotificationtest;

import retrofit2.Call;
import retrofit2.http.GET;

import java.util.List;

public interface JsonPlaceHolderApi {
    @GET("posts")
    Call<List<Post>> getPosts();
}
