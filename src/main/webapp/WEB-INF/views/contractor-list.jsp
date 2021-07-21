<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Справочник контрагентов</title>
    <style type="text/css">
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
        /*.tg .tg-4eph {*/
        /*    background-color: #f9f9f9*/
        /*}*/
    </style>
</head>
<body>
<div class="form-style-2">
    <p><a href="/contractor-create">Добавить контрагента</a></p>
    <p><a href="/contractor-search">Поиск контрагента по наименованию</a></p>
    <p><a href="/contractor-search">Поиск контрагента по БИК и номеру счета</a></p>
    <br>
    <h2>Справочник контрагентов</h2>
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
</div>
</body>
</html>