package domain;

import org.assertj.core.api.SoftAssertions;
import org.junit.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CommissionPayrollCalculatorTest {

    @Test
    public void given_commissionContractWithGrossPayAboveFirstTaxThreshold_when_calculate_then_commissionPayrollFieldsAreCalculatedProperly() throws Exception {
        //Given
        CommissionContract contract = mock( CommissionContract.class );
        when( contract.getIncome() ).thenReturn( new BigDecimal( "4000" ) );
        when( contract.getTaxDeduction() ).thenReturn( new BigDecimal( "0.20" ) );
        when( contract.getIncomeType() ).thenReturn( Contract.IncomeType.GROSS );

        //When
        CommissionPayrollCalculator calculator = new CommissionPayrollCalculator( contract );
        Payroll payroll = calculator.calculate();

        //Then
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat( payroll.getGrossPay() )
                .as( "gross pay" )
                .isNotNull()
                .isEqualTo( new BigDecimal( "4000" ) );
        softly.assertThat( payroll.getTaxDeductibleExpenses() )
                .as( "tax deductible expenses" )
                .isNotNull()
                .isEqualTo( "800.00" );
        softly.assertThat( payroll.getTaxBasis() )
                .as( "tax basis" )
                .isNotNull()
                .isEqualTo( "3200.00" );
        softly.assertThat( payroll.getPitAdvancePayment() )
                .as( "PIT advance payment" )
                .isNotNull()
                .isEqualTo( "576.00" );
        softly.assertThat( payroll.getNetPay() )
                .as( "net pay" )
                .isNotNull()
                .isEqualTo( "3424.00" );
        softly.assertAll();
    }

    @Test
    public void given_commissionContractWithHighValue_when_calculate_then_taxDeductibleExpensesAreNotExceeded() throws Exception {
        //Given
        CommissionContract contract = mock( CommissionContract.class );
        when( contract.getIncome() ).thenReturn( new BigDecimal( "90000" ) );
        when( contract.getTaxDeduction() ).thenReturn( new BigDecimal( "0.50" ) );
        when( contract.getIncomeType() ).thenReturn( Contract.IncomeType.GROSS );

        //When
        CommissionPayrollCalculator calculator = new CommissionPayrollCalculator( contract );
        Payroll payroll = calculator.calculate();

        //Then
       assertThat( payroll.getTaxDeductibleExpenses() )
                .as( "tax deductible expenses" )
                .isNotNull()
                .isEqualTo( "42764.00" );
    }

    @Test
    public void given_commissionContractWithLowValue_when_calculate_then_taxDeductibleExpensesAreNotEvaluated() throws Exception {
        //Given
        CommissionContract contract = mock( CommissionContract.class );
        when( contract.getIncome() ).thenReturn( new BigDecimal( "200" ) );
        when( contract.getTaxDeduction() ).thenReturn( new BigDecimal( "0.50" ) );
        when( contract.getIncomeType() ).thenReturn( Contract.IncomeType.GROSS );

        //When
        CommissionPayrollCalculator calculator = new CommissionPayrollCalculator( contract );
        Payroll payroll = calculator.calculate();

        //Then
        assertThat( payroll.getTaxDeductibleExpenses() )
                .as( "tax deductible expenses" )
                .isNotNull()
                .isEqualTo( "0.00" );
    }

    @Test
    public void given_commissionContractWithNetPayAboveFirstTaxThreshold_when_calculate_then_commissionPayrollFieldsAreCalculatedProperly() throws Exception {
        //Given
        CommissionContract contract = mock( CommissionContract.class );
        when( contract.getIncome() ).thenReturn( new BigDecimal( "3424" ) );
        when( contract.getTaxDeduction() ).thenReturn( new BigDecimal( "0.20" ) );
        when( contract.getIncomeType() ).thenReturn( Contract.IncomeType.NET );

        //When
        CommissionPayrollCalculator calculator = new CommissionPayrollCalculator( contract );
        Payroll payroll = calculator.calculate();

        //Then
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat( payroll.getGrossPay() )
                .as( "gross pay" )
                .isNotNull()
                .isEqualTo( new BigDecimal( "4000" ) );
        softly.assertThat( payroll.getTaxDeductibleExpenses() )
                .as( "tax deductible expenses" )
                .isNotNull()
                .isEqualTo( "800.00" );
        softly.assertThat( payroll.getTaxBasis() )
                .as( "tax basis" )
                .isNotNull()
                .isEqualTo( "3200.00" );
        softly.assertThat( payroll.getPitAdvancePayment() )
                .as( "PIT advance payment" )
                .isNotNull()
                .isEqualTo( "576.00" );
        softly.assertThat( payroll.getNetPay() )
                .as( "net pay" )
                .isNotNull()
                .isEqualTo( "3424.00" );
        softly.assertAll();
    }

    @Test
    public void given_commissionContractWithLowNetValue_when_calculate_then_convertToGrossProperly() throws Exception {
        //Given
        CommissionContract contract = mock( CommissionContract.class );
        when( contract.getIncome() ).thenReturn( new BigDecimal( "164" ) );
        when( contract.getTaxDeduction() ).thenReturn( new BigDecimal( "0.20" ) );
        when( contract.getIncomeType() ).thenReturn( Contract.IncomeType.NET );

        //When
        CommissionPayrollCalculator calculator = new CommissionPayrollCalculator( contract );
        Payroll payroll = calculator.calculate();

        //Then
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat( payroll.getGrossPay() )
                .as( "gross pay" )
                .isNotNull()
                .isEqualTo( new BigDecimal( "200" ) );
        softly.assertThat( payroll.getTaxDeductibleExpenses() )
                .as( "tax deductible expenses" )
                .isNotNull()
                .isEqualTo( "0.00" );
        softly.assertThat( payroll.getTaxBasis() )
                .as( "tax basis" )
                .isNotNull()
                .isEqualTo( "200.00" );
        softly.assertThat( payroll.getPitAdvancePayment() )
                .as( "PIT advance payment" )
                .isNotNull()
                .isEqualTo( "36.00" );
        softly.assertThat( payroll.getNetPay() )
                .as( "net pay" )
                .isNotNull()
                .isEqualTo( "164.00" );
        softly.assertAll();
    }

    @Test
    public void given_commissionContractWithHighNetValue_when_calculate_then_taxDeductibleExpensesAreNotExceeded() throws Exception {
        //Given
        CommissionContract contract = mock( CommissionContract.class );
        when( contract.getIncome() ).thenReturn( new BigDecimal( "77830" ) );
        when( contract.getTaxDeduction() ).thenReturn( new BigDecimal( "0.50" ) );
        when( contract.getIncomeType() ).thenReturn( Contract.IncomeType.NET );

        //When
        CommissionPayrollCalculator calculator = new CommissionPayrollCalculator( contract );
        Payroll payroll = calculator.calculate();

        //Then
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat( payroll.getGrossPay() )
                .as( "gross pay" )
                .isNotNull()
                .isEqualTo( new BigDecimal( "85528" ) );
        softly.assertThat( payroll.getTaxDeductibleExpenses() )
                .as( "tax deductible expenses" )
                .isNotNull()
                .isEqualTo( "42764.00" );
        softly.assertThat( payroll.getTaxBasis() )
                .as( "tax basis" )
                .isNotNull()
                .isEqualTo( "42764.00" );
        softly.assertThat( payroll.getPitAdvancePayment() )
                .as( "PIT advance payment" )
                .isNotNull()
                .isEqualTo( "7698.00" );
        softly.assertThat( payroll.getNetPay() )
                .as( "net pay" )
                .isNotNull()
                .isEqualTo( "77830.00" );
        softly.assertAll();
    }

    @Test
    public void given_commissionContractWithHigherNetValue_when_calculate_then_taxDeductibleExpensesAreNotExceeded() throws Exception {
        //Given
        CommissionContract contract = mock( CommissionContract.class );
        when( contract.getIncome() ).thenReturn( new BigDecimal( "81498" ) );
        when( contract.getTaxDeduction() ).thenReturn( new BigDecimal( "0.50" ) );
        when( contract.getIncomeType() ).thenReturn( Contract.IncomeType.NET );

        //When
        CommissionPayrollCalculator calculator = new CommissionPayrollCalculator( contract );
        Payroll payroll = calculator.calculate();

        //Then
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat( payroll.getGrossPay() )
                .as( "gross pay" )
                .isNotNull()
                .isEqualTo( new BigDecimal( "90000.00" ) );
        softly.assertThat( payroll.getTaxDeductibleExpenses() )
                .as( "tax deductible expenses" )
                .isNotNull()
                .isEqualTo( "42764.00" );
        softly.assertThat( payroll.getTaxBasis() )
                .as( "tax basis" )
                .isNotNull()
                .isEqualTo( "47236.00" );
        softly.assertThat( payroll.getPitAdvancePayment() )
                .as( "PIT advance payment" )
                .isNotNull()
                .isEqualTo( "8502.00" );
        softly.assertThat( payroll.getNetPay() )
                .as( "net pay" )
                .isNotNull()
                .isEqualTo( "81498.00" );
        softly.assertAll();
    }
}