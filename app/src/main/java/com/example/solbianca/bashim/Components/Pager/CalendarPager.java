package com.example.solbianca.bashim.Components.Pager;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class CalendarPager {

    /**
     * Год с которого доступны цитаты
     */
    private static final int START_YEAR = 2004;

    /**
     * Месяц первого года с которого доступны цитаты
     */
    private static final int START_MONTH = 8;

    private List years;

    private List months;

    private List firstYearMonths;

    public CalendarPager() {
        this.years = new ArrayList<String>();
        this.months = new ArrayList<String>();
        this.firstYearMonths = new ArrayList<String>();
    }

    public List getYears() {
        if (!this.years.isEmpty()) {
            return this.years;
        }

        Integer currentYear = CalendarPager.getCurrentYear();
        this.years.add("");
        for (Integer year = CalendarPager.START_YEAR; year <= currentYear; year++) {
            this.years.add(year.toString());
        }

        return this.years;
    }

    public List getMonths() {
        if (!this.months.isEmpty()) {
            return this.months;
        }

        this.months.add("");
        for (Integer month = 1; month <= 12; month++) {
            this.months.add(month.toString());
        }

        return this.months;
    }

    public List getMonthForFirstYear()
    {
        if (!this.firstYearMonths.isEmpty()) {
            return this.firstYearMonths;
        }

        for (Integer month = CalendarPager.START_MONTH; month <= 12; month++) {
            this.firstYearMonths.add(month);
        }

        return this.firstYearMonths;
    }

    public static Integer getCurrentYear() {
        return Calendar.getInstance().get(Calendar.YEAR);
    }
}
