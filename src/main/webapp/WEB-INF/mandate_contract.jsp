<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet"
          href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"
          integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">
<title>Kalkulator wynagrodzeń</title>
</head>
<body>
<div class="container">
    <h1 class="text-center" style="margin-top:50px;">Umowa zlecenie</h1>
    <div class="table-responsive">
        <table class="table table-stripped table-bordered text-center">
            <tr>
                <th rowspan="2" class="col-md-1 text-center" style="vertical-align: middle">Brutto</th>
                <th colspan="4" class="col-md-4 text-center">Ubezpieczenie</th>
                <th rowspan="2" class="col-md-1 text-center" style="vertical-align: middle">Koszt uzyskania przychodu</th>
                <th rowspan="2" class="col-md-1 text-center" style="vertical-align: middle">Podstawa opodatkowania</th>
                <th rowspan="2" class="col-md-1 text-center" style="vertical-align: middle">Zaliczka na PIT</th>
                <th rowspan="2" class="col-md-1 text-center" style="vertical-align: middle">Netto</th>
            </tr>
            <tr>
                <th class="col-md-1 text-center">emerytalne</th>
                <th class="col-md-1 text-center">rentowe</th>
                <th class="col-md-1 text-center">chorobowe</th>
                <th class="col-md-1 text-center">zdrowotne</th>
            </tr>
            <tr>
                <td><strong>${payroll.grossPay}</strong></td>
                <td>${payroll.pensionInsurance}</td>
                <td>${payroll.disabilityInsurance}</td>
                <td>${payroll.sicknessInsurance}</td>
                <td>${payroll.healtcareInsurance}</td>
                <td>${payroll.taxDeductibleExpenses}</td>
                <td>${payroll.taxBasis}</td>
                <td>${payroll.pitAdvancePayment}</td>
                <td><strong>${payroll.netPay}</strong></td>
            </tr>
        </table>
    </div>
</div>
</body>
</html>