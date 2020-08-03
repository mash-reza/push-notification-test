package com.festive.pushnotificationtest;

import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;
import java.util.Map;

public interface JsonPlaceHolderApi {
    @GET("posts")
    Call<List<Post>> getPosts(@Query("userId") Integer[] userId, @Query("_sort") String sort, @Query("_order") String order);

    @GET("posts")
    Call<List<Post>> getPosts(@QueryMap Map<String, String> params);

    @GET("posts/{id}/comments")
    Call<List<Comment>> getComments(@Path("id") int psotId);

    @GET()
    Call<List<Comment>> getComments(@Url String url);


    @POST("posts")
    Call<Post> createPost(@Body Post post);

    @FormUrlEncoded
    @POST("posts")
    Call<Post> createPost(@Field("userId") int userId,
                          @Field("title") String title,
                          @Field("body") String text);

    @FormUrlEncoded
    @POST("posts")
    Call<Post> createPost(@FieldMap Map<String,String> params);

}
