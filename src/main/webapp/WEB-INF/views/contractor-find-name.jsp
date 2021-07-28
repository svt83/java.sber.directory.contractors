<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Справочник контрагентов</title>
    <style>
        .tg {
            border-collapse: collapse;
            border-spacing: 0;
            border-color: #ccc;
        }
        .tg td {
            font-family: Arial, sans-serif;
            font-size: 14px;
            padding: 10px 5px;
            border-style: solid;
            border-width: 1px;
            overflow: hidden;
            word-break: normal;
            border-color: #ccc;
            color: #333;
            background-color: #fff;
        }
        .tg th {
            font-family: Arial, sans-serif;
            font-size: 14px;
            font-weight: normal;
            padding: 10px 5px;
            border-style: solid;
            border-width: 1px;
            overflow: hidden;
            word-break: normal;
            border-color: #ccc;
            color: #333;
            background-color: #f0f0f0;
        }
    </style>
</head>
<body>
<div>
    <h2>Введите реквизиты поиска</h2>
</div>
<form method="post" action="/contractor-find-name"><br>
    <label for="name">Наименование
        <input type="text" id="name" name="name" placeholder="Часть или целиком (до 20 знаков)"
               minlength="1" maxlength="20" size="30" required autocomplete="off"><br><br>
    </label>
    <input type="submit" value="Найти контрагента">
</form>
<div>
    <h2>Результат поиска:</h2>
    <table class="tg">
        <thead>
        <tr>
            <th style="width: 200px">Наименование</th>
            <th style="width: 100px">ИНН</th>
            <th style="width: 100px">КПП</th>
            <th style="width: 100px">БИК</th>
            <th style="width: 100px">Счет</th>
            <th style="width: 60px"></th>
            <th style="width: 60px"></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${contractorList}" var="contractor">
        <tr>
            <td>${contractor.name}</td>
            <td>${contractor.inn}</td>
            <td>${contractor.kpp}</td>
            <td>${contractor.bik}</td>
            <td>${contractor.account}</td>
            <td><a href="contractor-update/${contractor.id}">Изменить</a></td>
            <td><a href="contractor-delete/${contractor.id}">Удалить</a></td>
        </tr>
        </tbody>
        </c:forEach>
    </table>
    <p><a href="/contractor-list">Назад к справочнику</a></p>
</div>
</body>
</html>