package com.example.loginform;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ApiService {
    @POST("auth/login")
    Call<AuthResponse> login(@Body Credentials credentials);

    @POST("login")
    Call <AuthResponse> loginUser(@Body Credentials loginRequest);
    @POST("MouldingData/PostMouldingData")
    Call<Void> postMouldingData(@Body MouldingData data);


}