<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Справочник</title>
    <!--<link href="/css/styles.css" rel="stylesheet" type="text/css">-->
</head>
<body>
<div class="form-style-2">
    <div class="form-style-2-heading">
        Введите контрагента
    </div>
    <form method="post" action="/contractors"><br>
        <label for="name">Наименование
            <input class="input-field" type="text" id="name" name="name" placeholder="до 20 символов"><br><br>
        </label>
        <label for="inn">ИНН
        <input class="input-field" type="text" id="inn" name="inn" placeholder="10 или 12 цифр"><br><br>
        </label>
        <label for="inn">КПП
        <input class="input-field" type="text" name="kpp" placeholder="9 цифр"><br><br>
        </label>
        <label for="inn">БИК
        <input class="input-field" type="text" name="bik" placeholder="9 цифр"><br><br>
        </label>
        <label for="inn">Счет
        <input class="input-field" type="text" name="account" placeholder="20 цифр"><br><br>
        </label>
        <input type="submit" value="Add contractor">
    </form>
</div>

<div class="form-style-2">
    <div class="form-style-2-heading">
        Справочник контрагентов
    </div>
    <table>
        <tr>
            <th>Наименование</th>
            <th>ИНН</th>
            <th>КПП</th>
            <th>БИК</th>
            <th>Счет</th>
        </tr>
        <c:forEach items="${contractorList}" var="contractor">
            <tr>
                <td>${contractor.name}</td>
                <td>${contractor.inn}</td>
                <td>${contractor.kpp}</td>
                <td>${contractor.bik}</td>
                <td>${contractor.account}</td>
            </tr>
        </c:forEach>
    </table>
</div>

</body>
</html>