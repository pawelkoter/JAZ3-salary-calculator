package domain;

import java.math.BigDecimal;

public abstract class SocialInsuredPayrollCalculator extends PayrollCalculator {

    protected BigDecimal pensionInsurance;
    protected BigDecimal disabilityInsurance;
    protected BigDecimal sicknessInsurance;
    protected BigDecimal healthInsuranceCalculationBasis;
    protected BigDecimal healthcareInsurance;
    protected BigDecimal healthcareInsuranceTaxDeduction;


    public SocialInsuredPayrollCalculator( Contract contract ) {
        super(contract);
    }

    @Override
    public Payroll calculate() {
        calculatePayrollFields();
        return buildPayroll();
    }

    protected void calculatePayrollFields() {
        calculateGrossPay();
        calculatePensionInsurance();
        calculateDisabilityInsurance();
        calculateSicknessInsurance();
        calculateHealthInsuranceCalculationBasis();
        calculateHealtcareInsuranceTaxDeduction();
        calculateHealthcareInsurance();
        calculateTaxBasis();
        calculatePitAdvancePayment();
        calculateNetPay();
    }

    protected Payroll buildPayroll() {
        Payroll payroll = new Payroll();

        payroll.setMonth( null );
        payroll.setGrossPay( grossPay );
        payroll.setPensionInsurance( pensionInsurance );
        payroll.setDisabilityInsurance( disabilityInsurance );
        payroll.setSicknessInsurance( sicknessInsurance );
        payroll.setHealtcareInsurance( healthcareInsurance );
        payroll.setTaxBasis( taxBasis );
        payroll.setPitAdvancePayment( pitAdvancePayment );
        payroll.setNetPay( netPay );

        return payroll;
    }

    protected void calculateGrossPay() {
        grossPay = contract.getIncome();
    }

    protected void calculatePensionInsurance() {
        pensionInsurance = grossPay
                .multiply( taxScheme.getPensionInsurance() )
                .setScale( 2, BigDecimal.ROUND_HALF_UP )
        ;
    }

    protected void calculateDisabilityInsurance() {
        disabilityInsurance = grossPay
                .multiply( taxScheme.getDisabilityInsurance() )
                .setScale( 2, BigDecimal.ROUND_HALF_UP )
        ;
    }

    protected void calculateSicknessInsurance() {
        sicknessInsurance = grossPay
                .multiply( taxScheme.getSicnkessInsurance() )
                .setScale( 2, BigDecimal.ROUND_HALF_UP )
        ;
    }

    protected void calculateHealthInsuranceCalculationBasis() {
        healthInsuranceCalculationBasis = grossPay
                .subtract( pensionInsurance )
                .subtract( disabilityInsurance )
                .subtract( sicknessInsurance )
        ;
    }

    protected void calculateHealthcareInsurance() {
        healthcareInsurance = healthInsuranceCalculationBasis
                .multiply( taxScheme.getHealtcareInsurance() )
                .setScale( 2, BigDecimal.ROUND_HALF_UP )
        ;
    }

    protected void calculateHealtcareInsuranceTaxDeduction() {
        healthcareInsuranceTaxDeduction = healthInsuranceCalculationBasis
                .multiply( taxScheme.getHealthcareInsuranceTaxDeduction() )
                .setScale( 2, BigDecimal.ROUND_HALF_UP )
        ;
    }

    protected void calculateTaxBasis() {
        taxBasis = grossPay
                .subtract( pensionInsurance )
                .subtract( sicknessInsurance )
                .subtract( disabilityInsurance )
        ;
    }

    protected void calculatePitAdvancePayment() {
        pitAdvancePayment = taxBasis
                .multiply( taxScheme.getTaxValue( 1 ) )
                .subtract( healthcareInsuranceTaxDeduction )
        ;
    }

    protected void calculateNetPay() {
        netPay = healthInsuranceCalculationBasis
                .subtract( healthcareInsurance )
                .subtract( pitAdvancePayment )
        ;
    }
}