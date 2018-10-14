package com.example.solbianca.bashim.Components;

import com.example.solbianca.bashim.Services.BashImApi;

import retrofit2.Retrofit;

public class RetrofitFactory {

    public static Retrofit factory() {
        return new Retrofit.Builder().baseUrl(BashImApi.HOST).build();
    }

    public static Retrofit factory(String url) {
        return  new Retrofit.Builder().baseUrl(url).build();
    }
}
