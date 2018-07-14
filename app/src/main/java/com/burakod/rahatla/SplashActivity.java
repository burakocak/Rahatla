package com.burakod.rahatla;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashActivity extends AppCompatActivity {
    Fragment selectedFragment = null;
    // Bu Ekrandan 2 saniyelik bir bekleme süresi ile Home activity geçişi sağlıyoruz.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // Bekleme işlemi için yeni tread tanımlıyoruz.
        final Thread splashTread = new Thread(){
            public void run(){
                try{
                    sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                finally {
                    // Beklemeden sonra ana sayfaya geçiş
                    startActivity(new Intent(SplashActivity.this,HomeActivity.class));

                    //-----Favori ve Kitaplık Verilerini çekiyoruz.
                    new FetchFavorite().execute();
                    new FetchBookShelf().execute();


                }

            }
        };
            splashTread.start();
    }
}
