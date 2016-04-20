package domain;

import java.math.BigDecimal;

public class MandatePayrollCalculator extends SocialInsuredPayrollCalculator {

    protected BigDecimal taxDeductibleExpenses;

    public MandatePayrollCalculator( MandateContract contract ) {
        super(contract);
    }

    @Override
    protected void calculatePayrollFields() {
        calculateGrossPay();
        calculatePensionInsurance();
        calculateDisabilityInsurance();
        calculateSicknessInsurance();
        calculateHealthInsuranceCalculationBasis();
        calculateHealtcareInsuranceTaxDeduction();
        calculateHealthcareInsurance();
        calculateTaxDeductibleExpenses();
        calculateTaxBasis();
        calculatePitAdvancePayment();
        calculateNetPay();
    }

    @Override
    protected Payroll buildPayroll() {
        Payroll payroll = super.buildPayroll();
        payroll.setTaxDeductibleExpenses( taxDeductibleExpenses );
        return payroll;
    }

    @Override
    protected void calculatePensionInsurance() {
        if ( ((MandateContract) contract).hasPensionInsurance() ) {
            super.calculatePensionInsurance();
        } else {
            pensionInsurance = BigDecimal.ZERO.setScale( 2 );
        }
    }

    @Override
    protected void calculateDisabilityInsurance() {
        if ( ((MandateContract) contract).hasDisabilityInsurance() ) {
            super.calculateDisabilityInsurance();
        } else {
            disabilityInsurance = BigDecimal.ZERO.setScale( 2 );
        }
    }

    @Override
    protected void calculateSicknessInsurance() {
        if ( ((MandateContract) contract).hasSicknessInsurance() ) {
            super.calculateSicknessInsurance();
        } else {
            sicknessInsurance = BigDecimal.ZERO.setScale( 2 );
        }
    }

    @Override
    protected void calculateHealthcareInsurance() {
        if ( ((MandateContract) contract).hasHealthcareInsurance() ) {
            super.calculateHealthcareInsurance();
        } else {
            healthcareInsurance = BigDecimal.ZERO.setScale( 2 );
        }
    }

    @Override
    protected void calculateHealtcareInsuranceTaxDeduction() {
        if ( ((MandateContract) contract).hasHealthcareInsurance() ) {
            super.calculateHealtcareInsuranceTaxDeduction();
        } else {
            healthcareInsuranceTaxDeduction = BigDecimal.ZERO.setScale( 2 );
        }
    }

    @Override
    protected void calculateTaxBasis() {
        super.calculateTaxBasis();
        taxBasis = taxBasis.subtract( taxDeductibleExpenses )
                            .setScale( 0, BigDecimal.ROUND_HALF_UP )
                            .setScale( 2 )
        ;
    }

    private void calculateTaxDeductibleExpenses() {
        MandateContract mandateContract = (MandateContract) contract;
        BigDecimal minContractValueForTaxDeductibleExpenses = new BigDecimal( "200.00" );

        if ( grossPay.compareTo( minContractValueForTaxDeductibleExpenses ) > 0 ) {
            // tax deductible expenses allowed
            BigDecimal maxExpensesAllowed = new BigDecimal( "42764.00" );
            taxDeductibleExpenses = grossPay
                    .subtract( pensionInsurance )
                    .subtract( disabilityInsurance )
                    .subtract( sicknessInsurance )
                    .multiply( mandateContract.getTaxDeduction() )
                    .min( maxExpensesAllowed )
                    .setScale( 2, BigDecimal.ROUND_UP )
            ;
        } else {
            // tax deductible expenses not allowed
            taxDeductibleExpenses = BigDecimal.ZERO.setScale( 2 );
        }
    }
}