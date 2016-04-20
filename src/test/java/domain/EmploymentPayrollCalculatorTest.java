package domain;

import org.assertj.core.api.SoftAssertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith( MockitoJUnitRunner.class )
public class EmploymentPayrollCalculatorTest {

    @Test
    public void given_contractWithGrossPayOnFirstTaxThreshold_when_calculate_then_allPayrollFieldsAreCalculatedProperly() throws Exception {
        //Given
        EmploymentContract contract = mock( EmploymentContract.class );
        when( contract.getIncome() ).thenReturn( new BigDecimal( "4000" ) );

        //When
        EmploymentPayrollCalculator calculator = new EmploymentPayrollCalculator( contract );
        Payroll payroll = calculator.calculate();

        //Then
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat( payroll.getGrossPay() )
                .as( "gross pay" )
                .isNotNull()
                .isEqualTo( new BigDecimal( "4000" ) );
        softly.assertThat( payroll.getPensionInsurance() )
                .as( "pension insurance" )
                .isNotNull()
                .isEqualTo( "390.40" );
        softly.assertThat( payroll.getDisabilityInsurance() )
                .as( "disability insurance" )
                .isNotNull()
                .isEqualTo( "60.00" );
        softly.assertThat( payroll.getSicknessInsurance() )
                .as( "sickness insurance" )
                .isNotNull()
                .isEqualTo( "98.00" );
        softly.assertThat( payroll.getHealtcareInsurance() )
                .as( "healthcare insurance" )
                .isNotNull()
                .isEqualTo( "310.64" );
        softly.assertThat( payroll.getTaxBasis() )
                .as( "tax basis" )
                .isNotNull()
                .isEqualTo( "3340.00" );
        softly.assertThat( payroll.getPitAdvancePayment() )
                .as( "PIT advance payment" )
                .isNotNull()
                .isEqualTo( "287.00" );
        softly.assertThat( payroll.getNetPay() )
                .as( "net pay" )
                .isNotNull()
                .isEqualTo( "2853.96" );
        softly.assertAll();
    }

    @Test
    public void given_contractWithGrossPayBelowFirstTaxThreshold_when_calculate_then_allPayrollFieldsAreCalculatedProperly() throws Exception {
        //Given
        EmploymentContract contract = mock( EmploymentContract.class );
        when( contract.getIncome() ).thenReturn( new BigDecimal( "2000" ) );

        //When
        EmploymentPayrollCalculator calculator = new EmploymentPayrollCalculator( contract );
        Payroll payroll = calculator.calculate();

        //Then
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat( payroll.getGrossPay() )
                .as( "gross pay" )
                .isNotNull()
                .isEqualTo( new BigDecimal( "2000" ) );
        softly.assertThat( payroll.getPensionInsurance() )
                .as( "pension insurance" )
                .isNotNull()
                .isEqualTo( "195.20" );
        softly.assertThat( payroll.getDisabilityInsurance() )
                .as( "disability insurance" )
                .isNotNull()
                .isEqualTo( "30.00" );
        softly.assertThat( payroll.getSicknessInsurance() )
                .as( "sickness insurance" )
                .isNotNull()
                .isEqualTo( "49.00" );
        softly.assertThat( payroll.getHealtcareInsurance() )
                .as( "healthcare insurance" )
                .isNotNull()
                .isEqualTo( "155.32" );
        softly.assertThat( payroll.getTaxBasis() )
                .as( "tax basis" )
                .isNotNull()
                .isEqualTo( "1615.00" );
        softly.assertThat( payroll.getPitAdvancePayment() )
                .as( "PIT advance payment" )
                .isNotNull()
                .isEqualTo( "111.00" );
        softly.assertThat( payroll.getNetPay() )
                .as( "net pay" )
                .isNotNull()
                .isEqualTo( "1459.48" );
        softly.assertAll();
    }
}