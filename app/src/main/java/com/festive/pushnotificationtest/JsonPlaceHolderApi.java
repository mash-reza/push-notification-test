package com.festive.pushnotificationtest;

import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;
import java.util.Map;

public interface JsonPlaceHolderApi {
    @GET("posts")
    Call<List<Post>> getPosts(@Query("userId") Integer[] userId, @Query("_sort") String sort, @Query("_order") String order);

    @GET("posts")
    Call<List<Post>> getPosts(@QueryMap Map<String,String> params);

    @GET("posts/{id}/comments")
    Call<List<Comment>> getComments(@Path("id") int psotId);

    @GET()
    Call<List<Comment>> getComments(@Url String url);
}
