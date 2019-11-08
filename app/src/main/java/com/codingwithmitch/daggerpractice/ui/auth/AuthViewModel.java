package com.codingwithmitch.daggerpractice.ui.auth;

import android.util.Log;

import androidx.databinding.ObservableInt;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.codingwithmitch.daggerpractice.SessionManager;
import com.codingwithmitch.daggerpractice.models.User;
import com.codingwithmitch.daggerpractice.network.auth.AuthAPI;

import javax.inject.Inject;

import io.reactivex.Scheduler;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

public class AuthViewModel extends ViewModel {

    private static final String TAG = "AuthViewModel";
    AuthAPI authAPI;
    SessionManager sessionManager;


    @Inject
    public AuthViewModel(AuthAPI authAPI,SessionManager sessionManager) {
        this.authAPI = authAPI;
        this.sessionManager=sessionManager;
    }

    public void authenticateWithId(int id){
Log.d("test","attempting login");
       sessionManager.authenticateWithId(queryUserId(id));

     }

     public LiveData<AuthResource<User>>  queryUserId(int id){
        Log.d("test","Id is executed");

         final LiveData<AuthResource<User>> source= LiveDataReactiveStreams.fromPublisher(authAPI.getUserData(id)
                 .onErrorReturn(new Function<Throwable, User>() {
                     @Override
                     public User apply(Throwable throwable) throws Exception {
                         User errorUser=new User();
                         errorUser.setId(-1);
                         return errorUser;
                     }
                 })
                 .map(new Function<User, AuthResource<User>>() {
                     @Override
                     public AuthResource<User> apply(User user) throws Exception {
                         if(user.getId() == -1){
                             return AuthResource.error("Could not authenticate", null);
                         }
                         return AuthResource.authenticated(user);
                     }
                 })
                 .subscribeOn(Schedulers.io()));
         return source;


     }

     public LiveData<AuthResource<User>> observeUserData(){
        return sessionManager.getCachedUser();
     }


}









