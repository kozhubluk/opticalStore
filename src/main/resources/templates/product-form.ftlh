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
<h2>Создать товар</h2>
<form action="/product/create" method="post" enctype="multipart/form-data">
    название: <input type="text" name="title"/><br><br>
    описание: <input type="text" name="description"/><br><br>
    цена: <input type="number" name="price"/><br><br>
    фото <input type="file" name="file"/><br><br>
    <select name="category">
        <option value="Очки для чтения">Очки для чтения</option>
        <option value="Солнцезащитные очки">Солнцезащитные очки</option>
        <option value="Линзы">Линзы</option>
        <option value="Офисные очки">Офисные очки</option>
        <option value="Очки для компьютера">Очки для компьютера</option>
        <option value="Аксессуары для очков">Аксессуары для очков</option>
    </select>
    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <input type="submit" value="добавить"/>
</form>
<br><br><br>
</body>
</html>