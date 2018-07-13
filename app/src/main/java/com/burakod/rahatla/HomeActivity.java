package com.burakod.rahatla;


import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;



public class HomeActivity extends AppCompatActivity{

    private static final String TAG = "HomeActivity";
    private BottomNavigationView mBottomNavigationView;
    Fragment selectedFragment = null;
    ProgressDialog progress;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Log.d(TAG,"onCreate: Starting");


        mBottomNavigationView = findViewById(R.id.NavBot);

        mBottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.icon_favorite :

                        showProgress();
                        selectedFragment = FavoriteFragment.newInstance();
                        break;
                    case R.id.icon_bookshelf:

                        showProgress();
                        selectedFragment = BookShelfFragment.newInstance();
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.content, selectedFragment).addToBackStack(null).commit();
                return true;
            }
        });
        // Başlangıçta Bir Yükleme çubuğu ile ilk Fragmenta başlatıyoruz.
        showProgress();
        selectedFragment = FavoriteFragment.newInstance();
        getSupportFragmentManager().beginTransaction().replace(R.id.content, selectedFragment).addToBackStack(null).commit();

        //selectedFragment = FavoriteFragment.newInstance();
        //getSupportFragmentManager().beginTransaction().replace(R.id.content, FavoriteFragment.newInstance()).commit();






    }



    // Belirli bir ilerleme çubuğu progressBar
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

