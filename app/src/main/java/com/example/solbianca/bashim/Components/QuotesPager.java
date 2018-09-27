package com.example.solbianca.bashim.Components;

public class QuotesPager {

    public static final int NEW_QUOTES_START_PAGE = 0;
    public static final int BEST_QUOTES_BY_RATING_START_PAGE = 1;
    
    private int currentNew;

    private int currentBestByRating;

    private int min;

    private int max;

    public QuotesPager(int min, int max, int currentNew, int currentBestByRating ) {
        this.min = min;
        this.max = max;
        this.currentNew = currentNew;
        this.currentBestByRating = currentBestByRating;
    }

    public int getNextForNew() {
        if (this.currentNew > this.max) {
            return this.max;
        } else if (this.currentNew > this.min && this.currentNew <= this.max) {
            return this.currentNew - 1;
        } else if (this.currentNew <= this.min){
            return this.min;
        }

        return this.max;
    }

    public int getNextForBestByRating() {
        if (this.currentBestByRating < this.min) {
            return this.min;
        } else if (this.currentBestByRating >= this.min && this.currentBestByRating < this.max) {
            return this.currentBestByRating + 1;
        } else if (this.currentBestByRating >=this.max) {
            return this.max;
        }

        return this.min;
    }

    public void setCurrentNew(int currentNew) {
        this.currentNew = currentNew;
    }

    public void setCurrentBestByRating(int currentBestByRating) {
        this.currentBestByRating = currentBestByRating;
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }
}
