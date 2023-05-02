<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>


<form action="/cart/add/" method="post">
    кол-во <input type="number" name="quantity">
    <input type="hidden"  value="${product.id}" name="productId" >
    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <button type="submit">Save</button>
</form>

</body>
</html>