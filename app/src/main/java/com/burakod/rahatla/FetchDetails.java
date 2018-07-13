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

public class FetchDetails extends AsyncTask<Void,Void,Void> {

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public static String url;

    public FetchDetails() {
    }



    public static ArrayList<Details> detailsArrayList = new ArrayList<Details>();


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }


    @Override
    protected Void doInBackground(Void... voids) {
        SaveData();
        url = null;
        return null;
    }


    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        detailsArrayList.clear();
    }

    public void SaveData() {

        if(detailsArrayList.isEmpty()){

            try {
                Document document = Jsoup.connect(url).get();
                Element element = document.getElementById("jsonData");
                String jsonStr = element.html();
                JSONArray jsonArray = new JSONArray(jsonStr);
                for (int i = 0; i <jsonArray.length() ; i++)
                {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    Details cDetails = new Details();
                    cDetails.bookShelfName = jsonObject.getString("bookShelfName");
                    cDetails.musicName = jsonObject.getString("musicName");
                    cDetails.ID = jsonObject.getString("id");
                    detailsArrayList.add(cDetails);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }


        }
    }




}
