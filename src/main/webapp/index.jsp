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
    <div style="margin-top:50px;">
        <h1 id="title" class="text-center">Kalkulator wynagrodzeń</h1>
        <c:if test="${error_msg != null}">
        <div class="row">
            <div class="alert alert-danger col-sm-offset-4 col-sm-6" role="alert">
                <p>
                    <c:out value="${error_msg}" />
                </p>
            </div>
        </div>
        </c:if>
        <form class="form-horizontal" role="form" action="">
            <div class="form-group">
                <label class="control-label col-sm-offset-1 col-sm-3">Rodzaj umowy:</label>
                <div class="col-sm-4">
                    <div class="col-sm-4">
                        <label class="radio-inline"><input type="radio" id="" name="contract_type" value="employment" checked onclick="showOnlyEmploymentContractFields()"/>o&nbsp;pracę</label>
                    </div>
                    <div class="col-sm-4">
                        <label class="radio-inline"><input type="radio" id="" name="contract_type" value="mandate" onclick="showMandateContractFields()"/>zlecenie</label>
                    </div>
                    <div class="col-sm-4">
                        <label class="radio-inline"><input type="radio" id="" name="contract_type" value="commission" onclick="showCommissionContractFields()"/>o&nbsp;dzieło</label>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-offset-1 col-sm-3" for="salary">Rok podatkowy:</label>
                <div class="col-sm-4">
                    <select class="form-control" type="text" id="tax_yar" name="tax_year">
                        <option>2016</option>
                    </select>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-offset-1 col-sm-3" for="salary">Wysokość wynagrodzenia:</label>
                <div class="col-sm-4">
                    <input class="form-control" type="text" id="salary" name="salary"/>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-offset-1 col-sm-3">Płaca: </label>
                <div class="col-sm-4">
                    <label class="radio-inline"><input type="radio" id="" name="income" value="gross" checked/>brutto</label>
                    <label class="radio-inline"><input type="radio" id="" name="income" value="net"/>netto</label>
                </div>
            </div>
            <!-- UMOWA ZLECENIE -->
            <div id="pension_insurance_group" class="form-group hidden">
                <label class="control-label col-sm-offset-1 col-sm-3">Składka emerytalna:</label>
                <div class="col-sm-4">
                    <label class="radio-inline"><input type="radio" id="" name="pension_insurance" value="true" checked/>Tak</label>
                    <label class="radio-inline"><input type="radio" id="" name="pension_insurance" value="false"/>Nie</label>
                </div>
            </div>
            <div id="disability_insurance_group" class="form-group hidden">
                <label class="control-label col-sm-offset-1 col-sm-3">Składka rentowa:</label>
                <div class="col-sm-4">
                    <label class="radio-inline"><input type="radio" id="" name="disability_insurance" value="true" checked/>Tak</label>
                    <label class="radio-inline"><input type="radio" id="" name="disability_insurance" value="false"/>Nie</label>
                </div>
            </div>
            <div id="healthcare_insurance_group" class="form-group hidden">
                <label class="control-label col-sm-offset-1 col-sm-3">Składka zdrowotna:</label>
                <div class="col-sm-4">
                    <label class="radio-inline"><input type="radio" id="" name="healthcare_insurance" value="true" checked/>Tak</label>
                    <label class="radio-inline"><input type="radio" id="" name="healthcare_insurance" value="false"/>Nie</label>
                </div>
            </div>
            <div id="sickness_insurance_group" class="form-group hidden">
                <label class="control-label col-sm-offset-1 col-sm-3">Składka chorobowa:</label>
                <div class="col-sm-4">
                    <label class="radio-inline"><input type="radio" id="" name="sickness_insurance" value="true" checked/>Tak</label>
                    <label class="radio-inline"><input type="radio" id="" name="sickness_insurance" value="false"/>Nie</label>
                </div>
            </div>
            <!-- UMOWA O DZIEŁO -->
            <div id="tax_deduction_group" class="form-group">
                <label class="control-label col-sm-offset-1 col-sm-3">Koszt uzyskania przychodu:</label>
                <div class="col-sm-4">
                    <label class="radio-inline"><input type="radio" id="" name="tax_deduction" value="20" checked/>20%</label>
                    <label class="radio-inline"><input type="radio" id="" name="tax_deduction" value="50"/>50%</label>
                </div>
            </div>
            <!-- KONIEC UMOWY O DZIEŁO -->
            <!-- KONIEC UMOWY ZLECENIE -->
            <div class="form-group">
                <div class="col-sm-offset-4 col-sm-4">
                    <input class="btn btn-primary" type="submit" value="Oblicz"/>
                </div>
            </div>
        </form>
    </div>
</div>
<script>
    function showOnlyEmploymentContractFields() {
        hideElement("pension_insurance_group");
        hideElement("disability_insurance_group");
        hideElement("healthcare_insurance_group");
        hideElement("sickness_insurance_group");
        hideElement("tax_deduction_group");
    }

    function showMandateContractFields() {
        showElement("pension_insurance_group");
        showElement("disability_insurance_group");
        showElement("healthcare_insurance_group");
        showElement("sickness_insurance_group");
        showElement("tax_deduction_group");
    }

    function showCommissionContractFields() {
        hideElement("pension_insurance_group");
        hideElement("disability_insurance_group");
        hideElement("healthcare_insurance_group");
        hideElement("sickness_insurance_group");
        showElement("tax_deduction_group");
    }

    function showElement( elementId ) {
        document.getElementById( elementId ).className =
                document.getElementById( elementId ).className
                        .replace( /(?:^|\s)hidden(?!\S)/g , '');
    }

    function hideElement( elementId ) {
        document.getElementById( elementId ).className += " hidden";
    }
</script>
</body>
</html>