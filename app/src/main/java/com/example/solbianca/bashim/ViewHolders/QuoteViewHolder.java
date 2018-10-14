package com.example.solbianca.bashim.ViewHolders;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.solbianca.bashim.Components.RetrofitFactory;
import com.example.solbianca.bashim.Entities.Quote;
import com.example.solbianca.bashim.Listeners.VoteBayanClickListener;
import com.example.solbianca.bashim.Listeners.VoteClickListener;
import com.example.solbianca.bashim.Listeners.VoteDownClickListener;
import com.example.solbianca.bashim.Listeners.VoteUpClickListener;
import com.example.solbianca.bashim.R;
import com.example.solbianca.bashim.Services.BashImApi;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

public class QuoteViewHolder extends RecyclerView.ViewHolder {

    private TextView idTextView;
    private TextView ratingTextView;
    private TextView dateTextView;
    private TextView textTextView;

    private Context context;

    public QuoteViewHolder(View itemView, Context context) {
        super(itemView);

        this.context = context;

        this.idTextView = itemView.findViewById(R.id.quote_id);
        this.ratingTextView = itemView.findViewById(R.id.quote_rating);
        this.dateTextView = itemView.findViewById(R.id.quote_date);
        this.textTextView = itemView.findViewById(R.id.quote_text);

        TextView voteUp = itemView.findViewById(R.id.quote_vote_up);
        voteUp.setOnClickListener(new VoteUpClickListener(this, "rulez"));

        TextView voteDown = itemView.findViewById(R.id.quote_vote_down);
        voteDown.setOnClickListener(new VoteDownClickListener(this, "sux"));

        TextView voteBayan = itemView.findViewById(R.id.quote_vote_bayan);
        voteBayan.setOnClickListener(new VoteBayanClickListener(this, "bayan"));
    }

    public void bind(Quote quote) {
        String id = quote.getId() != null ? String.valueOf(quote.getId()) : "";
        idTextView.setText(String.valueOf(quote.getId()));
        String rating = quote.getRating() != null ? quote.getRating().toString() : "...";
        ratingTextView.setText(rating);
        dateTextView.setText(quote.getDate());

        String body = quote.getText() != null ? quote.getText() : "";
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            textTextView.setText(Html.fromHtml(body, Html.FROM_HTML_MODE_LEGACY));
        } else {
            textTextView.setText(Html.fromHtml(body));
        }
    }

    public String getQuoteId() {
        return idTextView.getText().toString();
    }

    public void showToast(String text) {
        Toast toast = Toast.makeText(this.context, text, Toast.LENGTH_LONG);
        toast.show();
    }
}