<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>

<img src="/images/${product.image.id}" height="600px" weight="600px"/><br><br>
${product.title} <br><br>
${product.description} <br><br>
<form action="/product/delete/${product.id}" method="post">
    <input type="submit" value="удалить"/>
    <input type="hidden" name="_csrf" value="${_csrf.token}">
</form>
<form action="/logout" method="post">
    <input type="submit" value="Sign Out"/>
    <input type="hidden" name="_csrf" value="${_csrf.token}">
</form>

</body>
</html>