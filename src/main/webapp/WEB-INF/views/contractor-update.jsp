<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Изменение контрагента</title>
</head>
<body>
<div class="form-style-2">
    <div class="form-style-2-heading">
        Введите реквизиты контрагента
    </div>
    <!--action="/contractor-update"-->
    <form method="post"><%--@declare id="kpp"--%><%--@declare id="bik"--%><%--@declare id="account"--%><br>
        <label for="name">Наименование
            <input type="text" value="${contractor.name}" id="name" name="name" placeholder="до 20 символов"
                   minlength="1" maxlength="20" size="21" required><br><br>
        </label>
        <label for="inn">ИНН
            <input type="text" value="${contractor.inn}" id="inn" name="inn" placeholder="10 или 12 цифр"
                   pattern="\d{10}|\d{12}" minlength="10" maxlength="12" required><br><br>
        </label>
        <label for="kpp">КПП
            <input type="text" value="${contractor.kpp}" name="kpp" placeholder="9 цифр"
                   pattern="[0-9]*" minlength="9" maxlength="9" required><br><br>
        </label>
        <label for="bik">БИК
            <input type="text" value="${contractor.bik}" name="bik" placeholder="9 цифр"
                   pattern="[0-9]*" minlength="9" maxlength="9" required><br><br>
        </label>
        <label for="account">Счет
            <input type="text" value="${contractor.account}" name="account" placeholder="20 цифр"
                   pattern="[0-9]*" minlength="20" maxlength="20" size="21" required autocomplete="off"><br><br>
        </label>
        <input type="submit" value="Внести изменения">
    </form>
</div>
</body>
</html>
