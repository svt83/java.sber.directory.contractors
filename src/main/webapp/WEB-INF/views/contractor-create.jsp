<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<head>
    <title>Создание контрагента</title>
</head>
<body>
<div class="form-style-2">
    <div class="form-style-2-heading">
        <h3>Введите реквизиты контрагента</h3>
    </div>
    <form method="post" action="/contractor-create"><%--@declare id="kpp"--%><%--@declare id="bik"--%><%--@declare id="account"--%><br>
        <label for="name">Наименование
            <input type="text" id="name" name="name" placeholder="до 20 символов"
                   minlength="1" maxlength="20" size="21" required><br><br>
        </label>
        <label for="inn">ИНН
            <input type="text" id="inn" name="inn" placeholder="10 или 12 цифр" pattern="\d{10}|\d{12}"
                    minlength="10" maxlength="12" required><br><br>
        </label>
        <label for="kpp">КПП
            <input type="text" id="kpp" name="kpp" placeholder="9 цифр" pattern="[0-9]*"
                   minlength="9" maxlength="9" required><br><br>
        </label>
        <label for="bik">БИК
            <input type="text" id="bik" name="bik" placeholder="9 цифр" pattern="[0-9]*"
                   minlength="9" maxlength="9" required><br><br>
        </label>
        <label for="account">Счет
            <input type="text" id="account" name="account" placeholder="20 цифр" pattern="[0-9]*"
                   minlength="20" maxlength="20" size="21" required autocomplete="off"><br><br>
        </label>
        <input type="submit" value="Add contractor">
    </form>
<%--    <form:form method="POST" modelAttribute="contractor">--%>
<%--        <fieldset>--%>
<%--            <form:label path="name">Наименование</form:label>--%>
<%--            <form:input path="name"></form:input><br><br>--%>
<%--            <form:label path="inn">ИНН</form:label>--%>
<%--            <form:input path="inn"/><br><br>--%>
<%--            <form:label path="kpp">КПП</form:label>--%>
<%--            <form:input path="kpp"/><br><br>--%>
<%--            <form:label path="bik">БИК</form:label>--%>
<%--            <form:input path="bik"/><br><br>--%>
<%--            <form:label path="account">Счет</form:label>--%>
<%--            <form:input path="account"/><br><br>--%>
<%--            <input type="submit" value="Добавить контрагента">--%>
<%--        </fieldset>--%>
<%--    </form:form>--%>

    <p><a href="/contractor-list">Назад к справочнику</a></p>
</div>
</body>
</html>