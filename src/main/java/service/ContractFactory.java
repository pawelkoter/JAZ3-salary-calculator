package service;

import domain.*;

import java.math.BigDecimal;
import java.util.Map;

public class ContractFactory {
    public static Contract create( Map< String, String[] > parameterMap ) {

        Contract contract;
        String contractType[] = parameterMap.get( "contract_type" );

        switch (contractType[0]) {
            case "employment" : contract = createEmploymentContract();
                break;
            case "mandate" : contract = createMandateContract( parameterMap );
                break;
            case "commission" : contract = createCommissionContract( parameterMap );
                break;
            default: throw new UnknowContractTypeException();
        }

        String taxYear[] = parameterMap.get( "tax_year" );
        contract.setTaxYear( taxYear[0] );

        String income[] = parameterMap.get( "income" );
        contract.setIncome( new BigDecimal( income[0] ) );

        String incomeType[] = parameterMap.get( "income_type" );
        switch ( incomeType[0] ) {
            case "gross" : contract.setIncomeType( Contract.IncomeType.GROSS );
                break;
            case "net" : contract.setIncomeType( Contract.IncomeType.NET );
                break;
        }
        return contract;
    }

    private static EmploymentContract createEmploymentContract() {
        return new EmploymentContract();
    }

    private static MandateContract createMandateContract( Map<String, String[]> parameterMap ) {
        MandateContract contract = new MandateContract();

        String taxDeductibleExpenses[] = parameterMap.get( "tax_deduction" );
        contract.setTaxDeduction(
                new BigDecimal( taxDeductibleExpenses[0] )
                        .divide( new BigDecimal( "100" )  )
                        .setScale( 2, BigDecimal.ROUND_HALF_UP )
        );

        String pensionInsurance[] = parameterMap.get( "pension_insurance" );
        contract.setPensionInsurance( Boolean.parseBoolean( pensionInsurance[0] ) );

        String disabilityInsurance[] = parameterMap.get( "disability_insurance" );
        contract.setDisabilityInsurance( Boolean.parseBoolean( disabilityInsurance[0] ) );

        String sicknessInsurance[] = parameterMap.get( "sickness_insurance" );
        contract.setSicknessInsurance( Boolean.parseBoolean( sicknessInsurance[0] ) );

        String healtcareInsurance[] = parameterMap.get( "healthcare_insurance" );
        contract.setHealthcareInsurance( Boolean.parseBoolean( healtcareInsurance[0] ) );

        return contract;
    }

    private static CommissionContract createCommissionContract( Map<String, String[]> parameterMap ) {
        CommissionContract contract = new CommissionContract( );

        String taxDeductibleExpenses[] = parameterMap.get( "tax_deduction" );
        contract.setTaxDeduction(
                new BigDecimal( taxDeductibleExpenses[0] )
                        .divide( new BigDecimal( "100" )  )
                        .setScale( 2, BigDecimal.ROUND_HALF_UP )
        );
        return contract;
    }
}