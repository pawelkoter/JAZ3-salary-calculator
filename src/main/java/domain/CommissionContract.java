package domain;

import java.math.BigDecimal;

public class CommissionContract extends Contract {
    private BigDecimal taxDeduction;

    public CommissionContract(){}

    public CommissionContract( BigDecimal income, IncomeType incomeType, BigDecimal taxDeduction ) {
        super( income, incomeType );
        this.taxDeduction = taxDeduction;
    }

    public BigDecimal getTaxDeduction() {
        return taxDeduction;
    }

    public void setTaxDeduction( BigDecimal taxDeduction ) {
        this.taxDeduction = taxDeduction;
    }
}