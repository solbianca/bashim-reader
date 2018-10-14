package com.example.solbianca.bashim.Extractors.Parser;

import com.example.solbianca.bashim.Components.Pager.PagerInterface;

public interface PaginationParserInterface extends ParserInterface {

    public PaginationParserInterface parse(String page);

    public PagerInterface getPager();
}
