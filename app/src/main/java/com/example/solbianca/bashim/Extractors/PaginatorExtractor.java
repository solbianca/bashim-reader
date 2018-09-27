package com.example.solbianca.bashim.Extractors;

import android.util.Log;

import com.example.solbianca.bashim.Components.QuotesPager;
import com.example.solbianca.bashim.Services.BashImApi;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

public class PaginatorExtractor {

//    private String url;
//
//    public QuotesPager extract(String url) {
//        String page = this.loadPage(url);
//        return new QuotesPager(10,0);
//    }
//
//    private String loadPage(String url) {
//        Retrofit retrofit = new Retrofit.Builder().baseUrl(url).build();
//        BashImApi bashIm = retrofit.create(BashImApi.class);
//        Call<ResponseBody> call = bashIm.index();
//
//        try {
//            Response<ResponseBody> response = call.execute();
//            String body = response.body().string();
//            Log.i("dump", "pgination body: " + body);
//            return body;
//        } catch (IOException e) {
//            return null;
//        }
//    }
}
