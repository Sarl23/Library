package com.uptc.models;
//
public enum MembershipType {
    STUDENT(3, 7, 500),
    TEACHER(5, 15, 300),
    EXTERNAL(2, 5, 1000);

    private final int maxLoans;
    private final int loanDays;
    private final int finePerDay;

    MembershipType(int maxLoans, int loanDays, int finePerDay) {
        this.maxLoans = maxLoans;
        this.loanDays = loanDays;
        this.finePerDay = finePerDay;
    }
    //
    public int getMaxLoans() {
        return maxLoans;
    }
    //
    public int getLoanDays() {
        return loanDays;
    }
    //
    public int getFinePerDay() {
        return finePerDay;
    }
}
