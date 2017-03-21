package com.gmail.berezin.serg.imagesearch;


import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Link {
    String CX = "002600642033321958554%3Al12i1nf1fz8";
    String KEY = "AIzaSyAzfrd2ZJPFyjhUUkkp32PWCLJF7Am4hqQ";
    String QUERY = null;


    @GET("/customsearch/v1")
    Response customSearch(@Query("key") String key, @Query("cx") String cx, @Query("q") String query);
//    @POST("/v1?q="+QUERY+"&cx=" + CX + "&fileType=jpg%2C+png%2C+jpeg&key=" + KEY)
//    Call<Object> search(@FieldMap Map<String, String> map);

}
