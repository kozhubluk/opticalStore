<!doctype html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>
<body>

<#assign categories = ['Солнцезащитные очки', 'Очки для чтения', 'Очки для компьютера', 'Линзы', 'Офисные очки', 'Аксессуары для очков']>

<form action="/product/edit" method="post" enctype="multipart/form-data">
    название: <input type="text" name="title" value="${product.title}">
    описание: <input type="text" name="description" value="${product.description}"/><br><br>
    цена: <input type="number" name="price" value="${product.price}"/><br><br>
    <select name="category">
        <#list categories as category>
            <option value="${category}" <#if (category == product.category)> selected="selected"</#if>>${category}</option>
        </#list>
    </select>
    <input type="hidden" value="${product.id}" name="productId">
    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <input type="submit" value="добавить"/>
</form>
</body>
</html>