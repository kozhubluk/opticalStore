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
<h2>Создать товар</h2>
<form action="/product/create" method="post">
    название: <input type="text" name="title"/><br><br>
    описание: <input type="text" name="description"/><br><br>
    цена: <input type="number" name="price"/><br><br>
    категория: <input type="text" name="category"/><br><br>
    <input type="submit" value="добавить"/>
</form>
</body>
</html>