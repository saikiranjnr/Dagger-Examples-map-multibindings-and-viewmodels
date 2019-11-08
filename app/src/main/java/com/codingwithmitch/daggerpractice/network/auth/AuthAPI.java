package com.codingwithmitch.daggerpractice.network.auth;

import com.codingwithmitch.daggerpractice.models.User;

import io.reactivex.Flowable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface AuthAPI {

    @GET("users/{id}")
    Flowable<User> getUserData(@Path("id") int id);


 }
