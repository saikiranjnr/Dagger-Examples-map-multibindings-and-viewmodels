package com.codingwithmitch.daggerpractice.ui.auth;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import dagger.android.support.DaggerAppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.bumptech.glide.RequestManager;
import com.codingwithmitch.daggerpractice.BaseActivity;
import com.codingwithmitch.daggerpractice.R;
import com.codingwithmitch.daggerpractice.models.User;
import com.codingwithmitch.daggerpractice.viewmodels.ViewModelProviderFactory;

import javax.inject.Inject;

public class AuthActivity extends DaggerAppCompatActivity {

    private static final String TAG = "AuthActivity";
    private AuthViewModel viewModel;
    @Inject
    ViewModelProviderFactory providerFactory;
    @Inject
    Drawable logo;
    @Inject
    RequestManager requestManager;
    EditText userIdInput;
    Button loginButton;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        viewModel = ViewModelProviders.of(this, providerFactory).get(AuthViewModel.class);
        userIdInput = (EditText) findViewById(R.id.user_id_input);
        progressBar=(ProgressBar)findViewById(R.id.progress_bar);
        loginButton = (Button) findViewById(R.id.login_button);

        viewModel.observeUserData().observe(this, new Observer<AuthResource<User>>() {
            @Override
            public void onChanged(AuthResource<User> userAuthResource) {

                if(userAuthResource!=null){
                    switch (userAuthResource.status){
                        case LOADING:
                            hideProgressBar(false);
                            break;
                        case AUTHENTICATED:
                            hideProgressBar(true);
                            Toast.makeText(AuthActivity.this,"EmailId: " +userAuthResource.data.getEmail()+" UserName: "+userAuthResource.data.getName(),Toast.LENGTH_LONG).show();
                            navMainScreen();
                            break;
                        case ERROR:
                            hideProgressBar(true);
                            Toast.makeText(AuthActivity.this,userAuthResource.message,Toast.LENGTH_LONG).show();
                            break;
                        case NOT_AUTHENTICATED:
                            hideProgressBar(true);
                            break;


                    }
                }

            }
        });


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (userIdInput.getText().toString().trim().length() != 0) {
                    viewModel.authenticateWithId(Integer.parseInt(userIdInput.getText().toString()));
                }
            }
        });

        setLogo();
    }

    private void navMainScreen() {
    startActivity(new Intent(this,MainActivity.class));
    finish();
    }



    public void hideProgressBar(boolean isVisible){

        if(isVisible){
            progressBar.setVisibility(View.GONE);
        }
        else{
            progressBar.setVisibility(View.VISIBLE);
        }
    }

    private void setLogo() {
        requestManager
                .load(logo)
                .into((ImageView) findViewById(R.id.login_logo));
    }
}
