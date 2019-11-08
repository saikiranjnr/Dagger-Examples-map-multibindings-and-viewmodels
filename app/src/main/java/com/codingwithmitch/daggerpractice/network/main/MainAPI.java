package com.codingwithmitch.daggerpractice.network.main;


import com.codingwithmitch.daggerpractice.models.Posts;

import java.util.List;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface MainAPI {

    @GET("posts")
    Flowable<List<Posts>> getPosts(@Query("userId") int id);
 }
