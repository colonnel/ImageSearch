package com.gmail.berezin.serg.imagesearch;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public final class JsonUtils {
    String CX = "002600642033321958554%3Al12i1nf1fz8";
    String KEY = "AIzaSyAzfrd2ZJPFyjhUUkkp32PWCLJF7Am4hqQ";
    String fileType = "png,jpg";
    String searchType ="image" ;

    //    private final String URL = "https://www.googleapis.com/customsearch";
//    private Gson gson = new GsonBuilder().create();
//    private Retrofit retrofit = new Retrofit.Builder()
//            .addConverterFactory(GsonConverterFactory.create(gson))
//            .baseUrl(URL)
//            .build();
//    private Link linkf = retrofit.create(Link.class);

    //    public static Map<String, String> getDataFromJson(JSONObject jsonResponse) {
//        final String IMG_NAME = "name";
//        final String IMG_URL = "src";
//
//    }
    public void search(String query) throws IOException {

        URL url = new URL(("https://www.googleapis.com/customsearch/v1?key="
                + KEY
                + "&amp;cx="
                + CX
                + "&amp;q="
                + query +
                "&amp;fileType=" + fileType + "&amp;searchType=" + searchType + "&amp;alt=json"));
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Accept", "application/json");
        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));


    }
}
