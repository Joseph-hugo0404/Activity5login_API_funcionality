package com.example.project.api;

import com.example.project.pojos.Login;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RequestPlaceHolder {

    @POST("login")
    Call<Login> login(@Body Login Login);
}
