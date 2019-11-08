package com.codingwithmitch.daggerpractice.ui.profile;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.codingwithmitch.daggerpractice.SessionManager;
import com.codingwithmitch.daggerpractice.models.User;
import com.codingwithmitch.daggerpractice.ui.auth.AuthResource;

import javax.inject.Inject;

public class ProfileViewModel extends ViewModel {
    private static final String TAG="profileViewModel";
    SessionManager sessionManager;

    @Inject
    public ProfileViewModel(SessionManager sessionManager) {
        Log.d("test","Profile View model is working");
        this.sessionManager=sessionManager;

    }

    public LiveData<AuthResource<User>> getAuthData(){
        return sessionManager.getCachedUser();
    }

}

