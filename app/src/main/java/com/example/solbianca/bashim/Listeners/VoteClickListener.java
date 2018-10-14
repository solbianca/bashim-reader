package com.example.solbianca.bashim.Listeners;

import android.util.Log;
import android.view.View;

import com.example.solbianca.bashim.Components.RetrofitFactory;
import com.example.solbianca.bashim.Services.BashImApi;
import com.example.solbianca.bashim.ViewHolders.QuoteViewHolder;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

public abstract class VoteClickListener implements View.OnClickListener {

    protected QuoteViewHolder quoteViewHolder;
    protected String toastText;

    public VoteClickListener(QuoteViewHolder quoteViewHolder, String toastText) {
        this.quoteViewHolder = quoteViewHolder;
        this.toastText = toastText;
    }

    @Override
    public void onClick(View v) {
        this.quoteViewHolder.showToast(this.toastText);
        BashImApi bashIm = RetrofitFactory.factory().create(BashImApi.class);
        Call<ResponseBody> call = this.vote(bashIm);
        try {
            call.execute();
            return;
        } catch (IOException e) {
            return;
        }
    }

    protected abstract Call<ResponseBody> vote(BashImApi bashImApi);
}
