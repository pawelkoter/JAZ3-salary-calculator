package domain;

import org.assertj.core.api.SoftAssertions;
import org.junit.Test;

import java.math.BigDecimal;

public class TaxSchemeTest {
    @Test
    public void getTaxValue() throws Exception {

        TaxScheme scheme = new TaxScheme();

        BigDecimal belowFirstTreshold = scheme.getTaxValue( new BigDecimal( "3000" ) );
        BigDecimal aboveFirstTreshold = scheme.getTaxValue( new BigDecimal( "4000" ) );
        BigDecimal aboveSecondTreshold = scheme.getTaxValue( new BigDecimal( "90000" ) );

        SoftAssertions softly = new SoftAssertions();

        softly.assertThat( belowFirstTreshold ).as( "Tax value below first treshold" ).isEqualTo( BigDecimal.ZERO );
        softly.assertThat( aboveFirstTreshold ).as( "Tax value above first treshold" ).isEqualTo( new BigDecimal( "0.18" ) );
        softly.assertThat( aboveSecondTreshold ).as( "Tax value above second treshold" ).isEqualTo( new BigDecimal( "0.32" ) );
        softly.assertAll();
    }

}