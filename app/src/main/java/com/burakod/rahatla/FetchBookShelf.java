package com.burakod.rahatla;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.ArrayList;

public class FetchBookShelf extends AsyncTask<Void,Void,Void> {

    String url = "http://burakocak.net/bookshelf.html";
    public static ArrayList<BookShelf> bookShelfArrayList = new ArrayList<BookShelf>();


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
            if(bookShelfArrayList.isEmpty()) {

                Document doc = Jsoup.connect(url).get();
                Element el = doc.getElementById("jsonData1");
                String jsonStr = el.html();
                JSONArray jsonarray = new JSONArray(jsonStr);

                for (int i = 0; i < jsonarray.length(); i++) {
                    JSONObject jsonobject = jsonarray.getJSONObject(i);
                    BookShelf cBookShelf = new BookShelf();
                    // Json Listesi String Dizilerle çekiyoruz.
                    cBookShelf.bookShelfID = jsonobject.getString("id");
                    cBookShelf.bookShelfName = jsonobject.getString("bookShelfName");
                    cBookShelf.bookShelfImage = jsonobject.getString("img");
                    bookShelfArrayList.add(cBookShelf);

                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void onPostExecute(Void aVoid) {


    }
}
