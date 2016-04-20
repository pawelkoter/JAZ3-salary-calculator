package web;

import domain.*;
import service.ContractFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@WebServlet( "/calculate" )
public class SalaryCalculationDispatcherServlet extends HttpServlet {
    @Override
    protected void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        String contractType = request.getParameter( "contract_type" );

        switch ( contractType ) {
            case "employment" : processEmploymentContract( request, response );
                break;
            case "mandate" : processMandateContract( request, response );
                break;
            case "commission": processCommissionContract( request, response );
        }
    }

    private void processEmploymentContract( HttpServletRequest request, HttpServletResponse response ) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        EmploymentContract contract = (EmploymentContract) ContractFactory.create( parameterMap );
        EmploymentPayrollCalculator calculator = new EmploymentPayrollCalculator( contract );
        Payroll payroll = calculator.calculate();

        List<Payroll> payrollList = new ArrayList<>(  );

        for (int i = 0; i <12; i++) {
            payrollList.add( payroll );
        }

        request.setAttribute( "payroll_list", payrollList );
        try {
            request.getRequestDispatcher( "/WEB-INF/employment_contract.jsp" ).forward( request, response );
        } catch ( ServletException e ) {
            e.printStackTrace();
        } catch ( IOException e ) {
            e.printStackTrace();
        }
    }

    private void processMandateContract( HttpServletRequest request, HttpServletResponse response ) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        MandateContract contract = (MandateContract) ContractFactory.create( parameterMap );
        MandatePayrollCalculator calculator = new MandatePayrollCalculator( contract );
        Payroll payroll = calculator.calculate();

        request.setAttribute( "payroll", payroll );
        try {
            request.getRequestDispatcher( "/WEB-INF/mandate_contract.jsp" ).forward( request, response );
        } catch ( ServletException e ) {
            e.printStackTrace();
        } catch ( IOException e ) {
            e.printStackTrace();
        }
    }

    private void processCommissionContract( HttpServletRequest request, HttpServletResponse response ) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        CommissionContract contract = (CommissionContract) ContractFactory.create( parameterMap );
        CommissionPayrollCalculator calculator = new CommissionPayrollCalculator( contract );
        Payroll payroll = calculator.calculate();

        request.setAttribute( "payroll", payroll );
        try {
            request.getRequestDispatcher( "/WEB-INF/commission_contract.jsp" ).forward( request, response );
        } catch ( ServletException e ) {
            e.printStackTrace();
        } catch ( IOException e ) {
            e.printStackTrace();
        }
    }
}