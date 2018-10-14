package com.example.solbianca.bashim.Extractors.Parser;

import com.example.solbianca.bashim.Components.Pager.EmptyPager;
import com.example.solbianca.bashim.Components.Pager.PagerInterface;

public class EmptyPaginationParser implements PaginationParserInterface {

    @Override
    public PaginationParserInterface parse(String page) {
        return this;
    }

    @Override
    public PagerInterface getPager() {
        return new EmptyPager();
    }
}
