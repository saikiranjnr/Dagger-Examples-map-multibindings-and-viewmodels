package com.codingwithmitch.daggerpractice.di.main;

import com.codingwithmitch.daggerpractice.ui.post.PostsFragment;
import com.codingwithmitch.daggerpractice.ui.profile.ProfileFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class MainFragmentBuilderModule {

    @ContributesAndroidInjector
    abstract ProfileFragment provideProfileFragment();

    @ContributesAndroidInjector
    abstract PostsFragment providePostsFragment();

}
