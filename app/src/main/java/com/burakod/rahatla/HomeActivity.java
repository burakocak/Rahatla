package com.burakod.rahatla;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.Random;


public class HomeActivity extends AppCompatActivity{

    private static final String TAG = "HomeActivity";
    private BottomNavigationView mBottomNavigationView;
    FragmentTransaction mFragmentTransaction;
    FragmentManager mFragmentManager;
    Fragment selectedFragment = null;
    Random random;
    ProgressDialog progress;
    Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Log.d(TAG,"onCreate: Starting");



        showProgress();
        mBottomNavigationView = findViewById(R.id.NavBot);


        selectedFragment = FavoriteFragment.newInstance();
        getSupportFragmentManager().beginTransaction().replace(R.id.content, selectedFragment).commit();



        mBottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.icon_favorite :

                        showProgress();
                        selectedFragment = FavoriteFragment.newInstance();
                        getSupportFragmentManager().beginTransaction().replace(R.id.content, selectedFragment).commit();

                        return true;
                    case R.id.icon_bookshelf:

                        showProgress();
                        selectedFragment = BookShelfFragment.newInstance();
                        getSupportFragmentManager().beginTransaction().replace(R.id.content, selectedFragment).commit();

                        return true;
                }
                return false;
            }
        });






    }




    public void showProgress() {
        progress=new ProgressDialog(this);
        progress.setMessage("Loading...");
        progress.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progress.setIndeterminate(true);
        progress.setProgress(0);
        progress.show();

        final int totalProgressTime = 100;
        final Thread t = new Thread() {
            @Override
            public void run() {
                int jumpTime = 0;

                while(jumpTime < totalProgressTime) {
                    try {
                        sleep(1000);
                        jumpTime += 5;
                        progress.setProgress(jumpTime);
                        progress.dismiss();
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
        };
        t.start();

    }



}

