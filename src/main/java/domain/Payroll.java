package domain;

import java.math.BigDecimal;
import java.time.Month;

public class Payroll {
    private Month month;
    private BigDecimal grossPay;
    private BigDecimal netPay;
    private BigDecimal pensionInsurance;
    private BigDecimal diasbilityInsurance;
    private BigDecimal healtcareInsurance;
    private BigDecimal sicnessInsurance;
    private BigDecimal taxDeductibleExpenses;
    private BigDecimal taxBasis;
    private BigDecimal pitAdvancePayment;

    public Month getMonth() {
        return month;
    }

    public void setMonth( Month month ) {
        this.month = month;
    }

    public BigDecimal getGrossPay() {
        return grossPay;
    }

    public void setGrossPay( BigDecimal grossPay ) {
        this.grossPay = grossPay;
    }

    public BigDecimal getNetPay() {
        return netPay;
    }

    public void setNetPay( BigDecimal netPay ) {
        this.netPay = netPay;
    }

    public BigDecimal getPensionInsurance() {
        return pensionInsurance;
    }

    public void setPensionInsurance( BigDecimal pensionInsurance ) {
        this.pensionInsurance = pensionInsurance;
    }

    public BigDecimal getDiasbilityInsurance() {
        return diasbilityInsurance;
    }

    public void setDiasbilityInsurance( BigDecimal diasbilityInsurance ) {
        this.diasbilityInsurance = diasbilityInsurance;
    }

    public BigDecimal getHealtcareInsurance() {
        return healtcareInsurance;
    }

    public void setHealtcareInsurance( BigDecimal healtcareInsurance ) {
        this.healtcareInsurance = healtcareInsurance;
    }

    public BigDecimal getSicnessInsurance() {
        return sicnessInsurance;
    }

    public void setSicnessInsurance( BigDecimal sicnessInsurance ) {
        this.sicnessInsurance = sicnessInsurance;
    }

    public BigDecimal getTaxDeductibleExpenses() {
        return taxDeductibleExpenses;
    }

    public void setTaxDeductibleExpenses( BigDecimal taxDeductibleExpenses ) {
        this.taxDeductibleExpenses = taxDeductibleExpenses;
    }

    public BigDecimal getTaxBasis() {
        return taxBasis;
    }

    public void setTaxBasis( BigDecimal taxBasis ) {
        this.taxBasis = taxBasis;
    }

    public BigDecimal getPitAdvancePayment() {
        return pitAdvancePayment;
    }

    public void setPitAdvancePayment( BigDecimal pitAdvancePayment ) {
        this.pitAdvancePayment = pitAdvancePayment;
    }
}