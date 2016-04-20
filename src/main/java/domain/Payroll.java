package domain;

import java.math.BigDecimal;
import java.time.Month;

public class Payroll {
    private Month month;
    private BigDecimal grossPay;
    private BigDecimal netPay;
    private BigDecimal pensionInsurance;
    private BigDecimal disabilityInsurance;
    private BigDecimal healtcareInsurance;
    private BigDecimal sicknessInsurance;
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

    public BigDecimal getDisabilityInsurance() {
        return disabilityInsurance;
    }

    public void setDisabilityInsurance( BigDecimal disabilityInsurance ) {
        this.disabilityInsurance = disabilityInsurance;
    }

    public BigDecimal getHealtcareInsurance() {
        return healtcareInsurance;
    }

    public void setHealtcareInsurance( BigDecimal healtcareInsurance ) {
        this.healtcareInsurance = healtcareInsurance;
    }

    public BigDecimal getSicknessInsurance() {
        return sicknessInsurance;
    }

    public void setSicknessInsurance( BigDecimal sicknessInsurance ) {
        this.sicknessInsurance = sicknessInsurance;
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