package com.codingwithmitch.daggerpractice.ui.auth;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;
import com.codingwithmitch.daggerpractice.BaseActivity;
import com.codingwithmitch.daggerpractice.R;
import com.codingwithmitch.daggerpractice.SessionManager;
import com.google.android.material.navigation.NavigationView;

import javax.inject.Inject;

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {

    @Inject
    SessionManager sessionManager;
    DrawerLayout mDrawerLayout;
    NavigationView mNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        mNavigationView = (NavigationView) findViewById(R.id.nav_view);
        init();
    }

    private void init() {
        NavController navController= Navigation.findNavController(this,R.id.my_nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this,navController,mDrawerLayout);
        NavigationUI.setupWithNavController(mNavigationView,navController);
        mNavigationView.setNavigationItemSelectedListener(this);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logout:
                sessionManager.logout();
                return true;
            case android.R.id.home:
                if(mDrawerLayout.isDrawerOpen(GravityCompat.START)){
                    mDrawerLayout.closeDrawer(GravityCompat.START);
                return true;
                }
                else{
                    return false;
                }
                default:
                    return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {

            case R.id.profile:
                Navigation.findNavController(this,R.id.my_nav_host_fragment).navigate(R.id.firstFragment);
                break;

            case R.id.posts:
                Navigation.findNavController(this,R.id.my_nav_host_fragment).navigate(R.id.secondFragment);
                break;
        }
        menuItem.setChecked(true);
        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        return NavigationUI.navigateUp(Navigation.findNavController(this,R.id.my_nav_host_fragment),mDrawerLayout);
    }
}
