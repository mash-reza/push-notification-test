package com.festive.pushnotificationtest;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RegisterService {
    @GET("register/{id}/{token}")
    Call<MyResponse> register(@Path("id") int id, @Path("token") String token);
}
