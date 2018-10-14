package com.example.solbianca.bashim.Components.Pager;

import com.example.solbianca.bashim.Components.Pager.PagerInterface;

public class WantMorePager implements PagerInterface {

    private int nextPage;

    private int current = 0;

    public WantMorePager(int nextPage) {
        this.nextPage = nextPage;
    }

    public int getNext() {
        return this.nextPage;
    }

    public void setCurrent(int page) {
        this.current = page;
    }
}
