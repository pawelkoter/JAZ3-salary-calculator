package domain;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.NavigableMap;
import java.util.TreeMap;

public class TaxScheme {

    private List< BigDecimal > taxTresholds = Arrays.asList(
            BigDecimal.ZERO,
            new BigDecimal( "3091" ),
            new BigDecimal( "85528" )
    );
    private List< BigDecimal > taxValues = Arrays.asList(
            BigDecimal.ZERO,
            new BigDecimal( "0.18" ),
            new BigDecimal( "0.32" )
    );
    private BigDecimal pensionInsurance = new BigDecimal( "0.0976" );
    private BigDecimal disabilityInsurance = new BigDecimal( "0.015" );
    private BigDecimal healtcareInsurance = new BigDecimal( "0.09" );
    private BigDecimal healthcareInsuranceTaxDeduction = new BigDecimal( "0.0775" );
    private BigDecimal sicnkessInsurance = new BigDecimal( "0.0245" );
    private BigDecimal taxDeductibleExpenses = new BigDecimal( "111.25" );

    public int getTaxTresholdCount() {
        return taxTresholds.size();
    }

    public BigDecimal getTaxValue( BigDecimal income ) {
        NavigableMap< BigDecimal, BigDecimal > taxes = new TreeMap<>();
        for ( int i = 0; i < getTaxTresholdCount(); i++ ) {
            taxes.put( taxTresholds.get( i ), taxValues.get( i ) );
        }

        return taxes.floorEntry( income ).getValue();
    }

    public BigDecimal getPensionInsurance() {
        return pensionInsurance;
    }

    public BigDecimal getDisabilityInsurance() {
        return disabilityInsurance;
    }

    public BigDecimal getHealtcareInsurance() {
        return healtcareInsurance;
    }

    public BigDecimal getSicnkessInsurance() {
        return sicnkessInsurance;
    }

    public BigDecimal getHealthcareInsuranceTaxDeduction() {
        return healthcareInsuranceTaxDeduction;
    }

    public BigDecimal getTaxDeductibleExpenses() {
        return taxDeductibleExpenses;
    }
}