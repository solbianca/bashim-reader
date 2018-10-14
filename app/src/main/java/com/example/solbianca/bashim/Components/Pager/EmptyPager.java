package com.example.solbianca.bashim.Components.Pager;

public class EmptyPager implements PagerInterface {

    @Override
    public int getNext() {
        return 0;
    }

    @Override
    public void setCurrent(int page) {

    }
}
