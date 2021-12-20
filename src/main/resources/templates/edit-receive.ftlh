<!DOCTYPE html>
<html>
<head>
    <title>My Shop</title>
</head>
<body>
<h1>My Shop</h1><hr>
<h4>Edit receive</h4>
<form action="/admin/editReceive/${receive.getId}" method="post">
    <b>Name: </b> <input type="text" name="receive_Method" value="${receive.getReceiveMethod}"><br>
    <b>Address: </b> <input type="text" name="address" value="${receive.address}"><br>

    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <input type="submit" value="Edit"/>
</form>

</body>
</html>