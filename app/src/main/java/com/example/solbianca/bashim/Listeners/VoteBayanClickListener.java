package com.example.solbianca.bashim.Listeners;

import android.util.Log;

import com.example.solbianca.bashim.Services.BashImApi;
import com.example.solbianca.bashim.ViewHolders.QuoteViewHolder;

import okhttp3.ResponseBody;
import retrofit2.Call;

public class VoteBayanClickListener extends VoteClickListener {

    public VoteBayanClickListener(QuoteViewHolder quoteViewHolder, String toastText) {
        super(quoteViewHolder, toastText);
    }

    @Override
    protected Call<ResponseBody> vote(BashImApi bashImApi) {
        return bashImApi.voteBayan(this.quoteViewHolder.getQuoteId());
    }
}
