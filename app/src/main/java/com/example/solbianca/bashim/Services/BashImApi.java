package com.example.solbianca.bashim.Services;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface BashImApi {

    public static final String HOST = "http://bash.im";

    public static final String QUOTES_NEW = "index";
    public static final String QUOTES_RANDOM = "random";
    public static final String QUOTES_BEST_OF_THE_DAY = "best";
    public static final String QUOTES_BEST_BY_RATING = "byrating";
    public static final String QUOTES_ABYSS = "abyss";
    public static final String QUOTES_ABYSS_TOP = "abysstop";
    public static final String QUOTES_ABYSS_BEST = "abyssbest";

    @GET("index")
    Call<ResponseBody> index();

    @GET("index/{pageid}")
    Call<ResponseBody> loadPage(@Path("pageid") String pageId);

    @GET("/quote/{quoteId}/rulez")
    Call<ResponseBody> voteUp(@Path("quoteId") String quoteId);

    @GET("/quote/{quoteId}/sux")
    Call<ResponseBody> voteDown(@Path("quoteId") String quoteId);

    @GET("/quote/{quoteId}/bayan")
    Call<ResponseBody> voteBayan(@Path("quoteId") String quoteId);
}
