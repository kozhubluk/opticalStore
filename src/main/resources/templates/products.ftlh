<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<form action="/" method="get">
    Поиск по товарам: <input type="text" name="title"><br/>
    <input type="submit" value="Поиск">
</form>
<#list products as product>
    <div>
        <p>${product.title} | <a href="/product/${product.id}">Подробнее</a></p>
    </div>
    <#else>
    <h3>товаров нет</h3>
</#list>
<form action="/logout" method="post">
    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <input type="submit" value="Sign Out"/>
</form>
</body>
</html>