package com.codingwithmitch.daggerpractice.ui.post;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.codingwithmitch.daggerpractice.R;
import com.codingwithmitch.daggerpractice.models.Posts;
import com.codingwithmitch.daggerpractice.ui.profile.ProfileViewModel;
import com.codingwithmitch.daggerpractice.viewmodels.ViewModelProviderFactory;

import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

public class PostsFragment extends DaggerFragment {

    @Inject
    ViewModelProviderFactory providerFactory;
    PostViewModel postViewModel;
    @Inject
    PostRecyclerAdapter adapter;
    RecyclerView mRecyclerView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_posts, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
       postViewModel= ViewModelProviders.of(this,providerFactory).get(PostViewModel.class);
       mRecyclerView=view.findViewById(R.id.mRecyclerView);
       subscribeObservers();
initRecyclerAdapter();
    }

    private void subscribeObservers() {

        postViewModel.getPosts().removeObservers(getViewLifecycleOwner());
        postViewModel.getPosts().observe(getViewLifecycleOwner(), new Observer<Resource<List<Posts>>>() {
            @Override
            public void onChanged(Resource<List<Posts>> listResource) {

                if(listResource!=null){

                    switch (listResource.status){

                        case LOADING:
                            break;

                        case ERROR:
                            break;

                        case SUCCESS:
                            Log.d("test","data size is "+listResource.data);
                            adapter.setPosts(listResource.data);
                            break;

                    }

                }

            }
        });

    }

    private void initRecyclerAdapter(){
    mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    mRecyclerView.setAdapter(adapter);
    }
}
