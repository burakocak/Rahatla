package com.burakod.rahatla;


import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;



public class HomeActivity extends AppCompatActivity{

    private static final String TAG = "HomeActivity";
    private BottomNavigationView mBottomNavigationView;
    Fragment fragment = null;
    FragmentTransaction fragmentTransaction;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Log.d(TAG,"onCreate: Starting");

        mBottomNavigationView = findViewById(R.id.NavBot);
        //mBottomNavigationView.setHasTransientState(true);
        fragment = new FavoriteFragment();
        switchFragment(fragment);

        mBottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.icon_favorite :
                        fragment = new FavoriteFragment();
                        switchFragment(fragment);
                        return true;
                    case R.id.icon_bookshelf:
                        fragment = new BookShelfFragment();
                        switchFragment(fragment);
                        return true;
                }
                return false;
            }
        });

       




    }
    private void switchFragment(Fragment fragment) {
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content, fragment);
        fragmentTransaction.commit();
    }


}

