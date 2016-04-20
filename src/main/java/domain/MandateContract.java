package domain;

import java.math.BigDecimal;

public class MandateContract extends Contract {

    private BigDecimal taxDeduction;
    private boolean pensionInsurance;
    private boolean disabilityInsurance;
    private boolean sicknessInsurance;
    private boolean healthcareInsurance;


    public BigDecimal getTaxDeduction() {
        return taxDeduction;
    }

    public void setTaxDeduction( BigDecimal taxDeduction ) {
        this.taxDeduction = taxDeduction;
    }

    public boolean hasPensionInsurance() {
        return pensionInsurance;
    }

    public void setPensionInsurance( boolean pensionInsurance ) {
        this.pensionInsurance = pensionInsurance;
    }

    public boolean hasDisabilityInsurance() {
        return disabilityInsurance;
    }

    public void setDisabilityInsurance( boolean disabilityInsurance ) {
        this.disabilityInsurance = disabilityInsurance;
    }

    public boolean hasSicknessInsurance() {
        return sicknessInsurance;
    }

    public void setSicknessInsurance( boolean sicknessInsurance ) {
        this.sicknessInsurance = sicknessInsurance;
    }

    public boolean hasHealthcareInsurance() {
        return healthcareInsurance;
    }

    public void setHealthcareInsurance( boolean healthcareInsurance ) {
        this.healthcareInsurance = healthcareInsurance;
    }
}