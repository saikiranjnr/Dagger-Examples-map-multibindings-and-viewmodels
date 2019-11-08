package com.codingwithmitch.daggerpractice;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;

import com.codingwithmitch.daggerpractice.models.User;
import com.codingwithmitch.daggerpractice.ui.auth.AuthActivity;
import com.codingwithmitch.daggerpractice.ui.auth.AuthResource;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public abstract class BaseActivity extends DaggerAppCompatActivity {

    @Inject
    SessionManager sessionManager;
    Context context;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        subscribeObservers();


    }

    private void subscribeObservers() {

        sessionManager.getCachedUser().observe(this, new Observer<AuthResource<User>>() {
            @Override
            public void onChanged(AuthResource<User> userAuthResource) {

                if (userAuthResource != null) {

                    switch (userAuthResource.status) {


                        case LOADING:
                            break;

                        case ERROR:
                            break;

                        case AUTHENTICATED:
                            break;

                        case NOT_AUTHENTICATED: {

                            LogOut();
                        }
                        break;


                    }

                }
            }
        });

    }

    private void LogOut() {
        startActivity(new Intent(this, AuthActivity.class));
        finish();
    }


}
