package com.codingwithmitch.daggerpractice;


import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;

import com.codingwithmitch.daggerpractice.models.User;
import com.codingwithmitch.daggerpractice.ui.auth.AuthResource;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class SessionManager {

    private static final String TAG="session manager";

    private MediatorLiveData<AuthResource<User>> cachedUser=new MediatorLiveData<>();


    public MediatorLiveData<AuthResource<User>> getCachedUser() {
        return cachedUser;
    }

    @Inject
    public SessionManager(){

    }



    public void authenticateWithId(final LiveData<AuthResource<User>> source){
        Log.d("test","seesion manager user id is executed");
        if(cachedUser!=null){
            cachedUser.setValue(AuthResource.loading((User)null));
            cachedUser.addSource(source, new Observer<AuthResource<User>>() {
                @Override
                public void onChanged(AuthResource<User> userAuthResource) {
                    cachedUser.setValue(userAuthResource);
                    cachedUser.removeSource(source);
                }
            });

        }
        else{
            Log.d("test","Authenticate with previous session detected. Retriving user from Cache..");
        }
    }

    public void logout(){
        cachedUser.setValue(AuthResource.<User>logout());
    }
}
