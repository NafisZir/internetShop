<!DOCTYPE html>
<html>
<head>
    <title>My Shop</title>
</head>
<body>
<h1>My Shop</h1><hr>
<h4>Edit category</h4>
<form action="/admin/editCategory/${category.getId}" method="post">
    <b>Name: </b> <input type="text" name="descr" value="${category.descr}"><br>

    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <input type="submit" value="Edit"/>
</form>

</body>
</html>