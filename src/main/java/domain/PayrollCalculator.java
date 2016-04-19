package domain;

import java.math.BigDecimal;

public abstract class PayrollCalculator {

    protected Contract contract;
    protected TaxScheme taxScheme;

    protected BigDecimal grossPay;
    protected BigDecimal taxBasis;
    protected BigDecimal pitAdvancePayment;
    protected BigDecimal netPay;

    protected PayrollCalculator( Contract contract ) {
        this.contract = contract;
        taxScheme = new TaxScheme();
    }

    public abstract Payroll calculate();
}