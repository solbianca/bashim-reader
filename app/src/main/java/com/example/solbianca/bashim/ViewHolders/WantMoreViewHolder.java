package com.example.solbianca.bashim.ViewHolders;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.example.solbianca.bashim.R;

public class WantMoreViewHolder extends RecyclerView.ViewHolder {

    public WantMoreViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    public void bind(final View.OnClickListener listener) {
        Button button = itemView.findViewById(R.id.want_more_button);
        button.setOnClickListener(listener);
    }
}
