package com.example.solbianca.bashim.Components.Pager;

public class BestByRatingQuotesPager implements PagerInterface {

    public static final int BEST_QUOTES_BY_RATING_START_PAGE = 1;

    private int current;

    private int min;

    private int max;

    public BestByRatingQuotesPager(int min, int max, int current ) {
        this.min = min;
        this.max = max;
        this.current = current;
    }

    public int getNext() {
        if (this.current < this.min) {
            return this.min;
        } else if (this.current >= this.min && this.current < this.max) {
            return this.current + 1;
        } else if (this.current >=this.max) {
            return this.max;
        }

        return this.min;
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
