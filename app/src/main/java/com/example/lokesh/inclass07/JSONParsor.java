package com.example.lokesh.inclass07;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;


/**
 * Created by Lokesh on 23/10/2017.
 */

public class JSONParsor {
    static ArrayList<itunesApp> parseApps(String jsonString) {
        ArrayList<itunesApp> listOfItunesApps = new ArrayList<>();
        try {
            JSONObject root = new JSONObject(jsonString);
            JSONObject feedsObject = root.getJSONObject("feed");
            JSONArray feedItems = feedsObject.getJSONArray("entry");
            Log.d("Length",feedItems.length()+"");
            for (int i=0; i<feedItems.length(); i++) {
                JSONObject feedObject = feedItems.getJSONObject(i);

                JSONObject labelObject = feedObject.getJSONObject("title");
                String title = labelObject.getString("label");

                JSONArray imageList = feedObject.getJSONArray("im:image");

                String thumbImage="";
                JSONObject imageObject = imageList.getJSONObject(0);
                thumbImage = imageObject.getString("label");

                String thumbImageLarge="";
                JSONObject imageObjectLrg = imageList.getJSONObject(2);
                thumbImageLarge = imageObjectLrg.getString("label");

                JSONObject priceObject = feedObject.getJSONObject("im:price");
                String price = priceObject.getString("label");

                itunesApp itunesApp = new itunesApp(title,price,thumbImage,thumbImageLarge);
                Log.d("demo2", "parseApps: "+thumbImageLarge);

                listOfItunesApps.add(itunesApp);
            }
            return listOfItunesApps;
        } catch (Exception e) {
            Log.d("Exception", e.getLocalizedMessage());
        }

        return null;
    }
}
