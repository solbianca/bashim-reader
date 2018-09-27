package com.example.solbianca.bashim.ViewHolders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.example.solbianca.bashim.R;

public class LoaderViewHolder extends RecyclerView.ViewHolder {

    public ProgressBar mProgressBar;

    public LoaderViewHolder(View itemView) {
        super(itemView);
        mProgressBar = (ProgressBar) itemView.findViewById(R.id.loader_item);
    }
}
