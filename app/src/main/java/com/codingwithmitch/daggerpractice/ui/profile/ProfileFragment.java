package com.codingwithmitch.daggerpractice.ui.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.codingwithmitch.daggerpractice.R;
import com.codingwithmitch.daggerpractice.models.User;
import com.codingwithmitch.daggerpractice.ui.auth.AuthResource;
import com.codingwithmitch.daggerpractice.viewmodels.ViewModelProviderFactory;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;


public class ProfileFragment extends DaggerFragment {

    @Inject
    ViewModelProviderFactory providerFactory;

     ProfileViewModel profileViewModel;

     TextView email;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        email=view.findViewById(R.id.email);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        profileViewModel = ViewModelProviders.of(this, providerFactory).get(ProfileViewModel.class);
       // textView.setText(profileViewModel.getAuthData().getValue().data.getEmail());
        subscribeObservers();
    }

    private void subscribeObservers() {
    profileViewModel.getAuthData().removeObservers(getViewLifecycleOwner());
    profileViewModel.getAuthData().observe(getViewLifecycleOwner(), new Observer<AuthResource<User>>() {
        @Override
        public void onChanged(AuthResource<User> userAuthResource) {
            if(userAuthResource!=null){

                switch (userAuthResource.status){

                    case AUTHENTICATED:
                        email.setText(userAuthResource.data.getEmail());
                        break;

                    case ERROR:
                        break;

                    case LOADING:
                        break;

                    case NOT_AUTHENTICATED:
                        break;

                }
            }
        }
    });
    }
}
