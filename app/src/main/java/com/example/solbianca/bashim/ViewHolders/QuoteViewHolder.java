package com.example.solbianca.bashim.ViewHolders;

import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.solbianca.bashim.Entities.Quote;
import com.example.solbianca.bashim.R;

public class QuoteViewHolder extends RecyclerView.ViewHolder {

    private TextView idTextView;
    private TextView ratingTextView;
    private TextView dateTextView;
    private TextView textTextView;

    public QuoteViewHolder(View itemView) {
        super(itemView);

        idTextView = itemView.findViewById(R.id.quote_id);
        ratingTextView = itemView.findViewById(R.id.quote_rating);
        dateTextView = itemView.findViewById(R.id.quote_date);
        textTextView = itemView.findViewById(R.id.quote_text);
    }

    public void bind(Quote quote) {
        String id = quote.getId() != null ? String.valueOf(quote.getId()) : "";
        idTextView.setText(String.valueOf(quote.getId()));
        String rating = quote.getRating()!= null ? quote.getRating().toString() : "...";
        ratingTextView.setText(rating);
        dateTextView.setText(quote.getDate());

        String body = quote.getText() != null ? quote.getText() : "";
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            textTextView.setText(Html.fromHtml(body,Html.FROM_HTML_MODE_LEGACY));
        } else {
            textTextView.setText(Html.fromHtml(body));
        }
    }
}