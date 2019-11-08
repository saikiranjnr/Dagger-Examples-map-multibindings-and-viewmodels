package com.codingwithmitch.daggerpractice.di.auth;

import com.codingwithmitch.daggerpractice.network.auth.AuthAPI;
import com.codingwithmitch.daggerpractice.network.main.MainAPI;
import com.codingwithmitch.daggerpractice.ui.post.PostRecyclerAdapter;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class MainModule {

    @Provides
    static MainAPI provideMainAPI(Retrofit retrofit){
        return retrofit.create(MainAPI.class);
    }

    @Provides
    static PostRecyclerAdapter provideAdapter(){return new PostRecyclerAdapter();}
}
