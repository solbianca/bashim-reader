package com.example.solbianca.bashim.Fragments;

import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.solbianca.bashim.R;

abstract public class QuotesFragment extends Fragment {

    public void hideCalendarPager(View view, RecyclerView recyclerView) {
        ConstraintLayout layout = (ConstraintLayout)view.findViewById(R.id.calendar_pager);
        layout.setVisibility(View.GONE);

        RelativeLayout.LayoutParams parameters = (RelativeLayout.LayoutParams) recyclerView.getLayoutParams();
        parameters.setMargins(parameters.leftMargin, 0, parameters.rightMargin, parameters.bottomMargin); // left, top, right, bottom
        recyclerView.setLayoutParams(parameters);
    }
}
