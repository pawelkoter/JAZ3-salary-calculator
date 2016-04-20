package domain;

import java.math.BigDecimal;

public class EmploymentPayrollCalculator extends SocialInsuredPayrollCalculator {

    public EmploymentPayrollCalculator( EmploymentContract contract ) {
        super( contract );
    }

    @Override
    protected void calculateTaxBasis() {
        super.calculateTaxBasis();
        taxBasis = taxBasis.subtract( taxScheme.getTaxDeductibleExpenses() )
                            .setScale( 0, BigDecimal.ROUND_HALF_UP )
                            .setScale( 2 )
        ;
    }

    @Override
    protected void calculatePitAdvancePayment() {
        super.calculatePitAdvancePayment();
        pitAdvancePayment = pitAdvancePayment
                .subtract( new BigDecimal( "46.33" ) )
                .setScale( 0, BigDecimal.ROUND_HALF_UP )
                .setScale( 2 )
        ;
    }
}