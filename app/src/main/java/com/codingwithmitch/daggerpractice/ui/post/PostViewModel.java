package com.codingwithmitch.daggerpractice.ui.post;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.codingwithmitch.daggerpractice.SessionManager;
import com.codingwithmitch.daggerpractice.models.Posts;
import com.codingwithmitch.daggerpractice.network.main.MainAPI;
import com.codingwithmitch.daggerpractice.ui.auth.AuthResource;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class PostViewModel extends ViewModel {


    SessionManager sessionManager;
    MainAPI mainAPI;
    private MediatorLiveData<Resource<List<Posts>>> posts;

    @Inject
    public PostViewModel(SessionManager sessionManager, MainAPI mainAPI) {
        this.sessionManager = sessionManager;
        this.mainAPI = mainAPI;
        Log.d("test", "postview model is working");
    }


    public LiveData<Resource<List<Posts>>> getPosts() {

        if (posts == null) {

            posts = new MediatorLiveData<>();
            posts.setValue(Resource.loading((List<Posts>)null));

            final LiveData<Resource<List<Posts>>> source = LiveDataReactiveStreams.fromPublisher(mainAPI.getPosts(sessionManager.getCachedUser().getValue().data.getId()).onErrorReturn(new Function<Throwable, List<Posts>>() {
                @Override
                public List<Posts> apply(Throwable throwable) throws Exception {
                    Posts errorPost = new Posts();
                    errorPost.setId(-1);
                    ArrayList<Posts> listOfPosts = new ArrayList<>();
                    listOfPosts.add(errorPost);
                    return listOfPosts;
                }
            }).map(new Function<List<Posts>, Resource<List<Posts>>>() {
                @Override
                public Resource<List<Posts>> apply(List<Posts> posts) throws Exception {
                    if (posts.size() != 0) {

                        if (posts.get(0).getId() == -1) {
                            return Resource.error("Unable to retrieve posts", null);
                        }
                        return Resource.success(posts);
                    } else {
                        return Resource.error("Unable to retrieve posts", null);
                    }
                }
            }).subscribeOn(Schedulers.io()));


            posts.addSource(source, new Observer<Resource<List<Posts>>>() {
                @Override
                public void onChanged(Resource<List<Posts>> listResource) {
                    posts.setValue(listResource);
                    posts.removeSource(source);
                }
            });
        }
return posts;
    }
}
