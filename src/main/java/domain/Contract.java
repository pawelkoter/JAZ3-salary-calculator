package domain;

import java.math.BigDecimal;

public abstract class Contract {

    public enum IncomeType {
        GROSS,
        NET
    }

    private String taxYear;
    private BigDecimal income;
    private IncomeType incomeType;


    public Contract(){}

    public Contract( BigDecimal income, IncomeType incomeType ) {
        this.income = income;
        this.incomeType = incomeType;
    }

    public BigDecimal getIncome() {
        return income;
    }

    public void setIncome( BigDecimal income ) {
        this.income = income;
    }

    public IncomeType getIncomeType() {
        return incomeType;
    }

    public void setIncomeType( IncomeType incomeType ) {
        this.incomeType = incomeType;
    }

    public void setTaxYear( String taxYear ) {
        this.taxYear = taxYear;
    }

    public String getTaxYear() {
        return taxYear;
    }
}