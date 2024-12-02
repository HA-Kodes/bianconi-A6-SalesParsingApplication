package com.coderscampus;

import java.time.YearMonth;

public class SalesTotal {
    private final YearMonth yearMonth;
    private final Integer sales;

    public SalesTotal(YearMonth yearMonth, Integer sales) {
        this.yearMonth = yearMonth;
        this.sales = sales;
    }

    public YearMonth getYearMonth() {
        return yearMonth;
    }

    public Integer getSales() {
        return sales;
    }

    @Override
    public String toString() {
        return "SalesTotal [yearMonth=" + yearMonth + ", sales=" + sales + "]";
    }
}