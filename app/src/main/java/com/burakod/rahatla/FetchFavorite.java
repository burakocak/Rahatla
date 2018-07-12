package com.burakod.rahatla;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;

import com.burakod.rahatla.Favorite;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

class FetchFavorite extends AsyncTask<Void,Void,Void> {

    String url = "http://burakocak.net/favorite.html";
    public static ArrayList<Favorite> favoriteArrayList = new ArrayList<Favorite>();


    @Override
    protected void onPreExecute() {

        super.onPreExecute();
        // ProgressDialog



    }

    @Override
    protected Void doInBackground(Void... voids) {
        SaveData();


        return  null;
    }

    private void SaveData() {

        try {
            if(favoriteArrayList.isEmpty()) {

                Document doc = Jsoup.connect(url).get();

                Element el = doc.getElementById("jsonData");

                String jsonStr = el.html();


                JSONArray jsonarray = new JSONArray(jsonStr);



                for (int i = 0; i < jsonarray.length(); i++) {
                    JSONObject jsonobject = jsonarray.getJSONObject(i);
                    Favorite cFavorite = new Favorite();
                    // Json Listesi String Dizilerle çekiyoruz.
                    cFavorite.setMusicID(jsonobject.getString("id"));

                    cFavorite.setMusicName(jsonobject.getString("musicName"));


                    //musicNameArray[i] = musicName;

                    //favorite[i] = jsonobject.getString("favorite");
                    cFavorite.setMusicUrl(jsonobject.getString("mp3"));
                    favoriteArrayList.add(cFavorite);

                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void onPostExecute(Void aVoid)
    {


    }
}
