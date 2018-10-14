package com.example.solbianca.bashim.Components.Pager;

import com.example.solbianca.bashim.Components.Pager.PagerInterface;

public class QuotesPager implements PagerInterface {

    public static final int NEW_QUOTES_START_PAGE = 0;

    private int current;

    private int min;

    private int max;

    public QuotesPager(int min, int max, int current ) {
        this.min = min;
        this.max = max;
        this.current = current;
    }

    public int getNext() {
        if (this.current > this.max) {
            return this.max;
        } else if (this.current > this.min && this.current <= this.max) {
            return this.current - 1;
        } else if (this.current <= this.min){
            return this.min;
        }

        return this.max;
    }

    public void setCurrent(int currentNew) {
        this.current = currentNew;
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }
}
