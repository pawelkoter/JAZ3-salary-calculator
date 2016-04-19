package domain;

import java.math.BigDecimal;

public class CommissionPayrollCalculator extends PayrollCalculator {

    protected BigDecimal taxDeductibleExpenses;

    protected CommissionPayrollCalculator( CommissionContract contract ) {
        super( contract );
    }

    @Override
    public Payroll calculate() {
        calculatePayrollFields();
        return buildPayroll();
    }

    private void calculatePayrollFields() {
        calculateGrossPay();
        calculateTaxDeductibleExpenses();
        calculateTaxBasis();
        calculatePitAdvancePayment();
        calculateNetPay();
    }

    private void calculateGrossPay() {

        if ( contract.getIncomeType() == Contract.IncomeType.GROSS ) {
            grossPay = contract.getIncome();
        } else {
            grossPay = convertNetToGross();
        }
    }

    private BigDecimal convertNetToGross() {

        if ( contract.getIncome().compareTo( new BigDecimal( "164.00" ) ) <= 0 ) {
            // tax deductible expenses may not be included
            return contract.getIncome()
                    .divide( BigDecimal.ONE.subtract( taxScheme.getTaxValue( 1 ) ),
                            BigDecimal.ROUND_UP )
            ;
        } else if ( contract.getIncome().compareTo( new BigDecimal( "77830.00" ) ) <= 0 ) {
            // tax deductible expenses may be included
            CommissionContract commissionContract = (CommissionContract) contract;
            return contract.getIncome()
                    .divide( BigDecimal.ONE
                                    .subtract( taxScheme.getTaxValue( 1 ) )
                                    .add( taxScheme.getTaxValue( 1 )
                                            .multiply( commissionContract.getTaxDeduction() )
                                    ),
                            BigDecimal.ROUND_UP )
            ;
        } else {
            // tax deductible expenses are constrained up to 42674.00
            return contract.getIncome()
                    .subtract( new BigDecimal( "42764.00" ) )
                    .divide(
                            BigDecimal.ONE.subtract( taxScheme.getTaxValue( 1 ) ),
                            BigDecimal.ROUND_HALF_UP )
                    .add( new BigDecimal( "42764.00" ) )
                    .setScale( 0, BigDecimal.ROUND_DOWN )
                    .setScale( 2 )
            ;
        }
    }

    private void calculateTaxDeductibleExpenses() {
        CommissionContract commissionContract = (CommissionContract) contract;
        BigDecimal minContractValueForTaxDeductibleExpenses = new BigDecimal( "200.00" );

        if ( grossPay.compareTo( minContractValueForTaxDeductibleExpenses ) > 0 ) {
            // tax deductible expenses allowed
            BigDecimal maxExpensesAllowed = new BigDecimal( "42764.00" );
            taxDeductibleExpenses = grossPay.multiply( commissionContract.getTaxDeduction() )
                    .min( maxExpensesAllowed );
        } else {
            // tax deductible expenses not allowed
            taxDeductibleExpenses = BigDecimal.ZERO.setScale( 2 );
        }
    }

    private void calculateTaxBasis() {
        taxBasis = grossPay.subtract( taxDeductibleExpenses )
                .setScale( 0, BigDecimal.ROUND_HALF_UP )
                .setScale( 2 )
        ;
    }

    private void calculatePitAdvancePayment() {
        pitAdvancePayment = taxBasis.multiply( taxScheme.getTaxValue( 1 ) )
                .setScale( 0, BigDecimal.ROUND_HALF_UP )
                .setScale( 2 )
        ;
    }

    private void calculateNetPay() {
        netPay = grossPay.subtract( pitAdvancePayment );
    }

    private Payroll buildPayroll() {
        Payroll payroll = new Payroll();

        payroll.setGrossPay( grossPay );
        payroll.setTaxDeductibleExpenses( taxDeductibleExpenses );
        payroll.setTaxBasis( taxBasis );
        payroll.setPitAdvancePayment( pitAdvancePayment );
        payroll.setNetPay( netPay );

        return payroll;
    }
}