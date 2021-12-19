<!DOCTYPE html>
<html>
<head>
    <title>My Shop</title>
</head>
<body>
<h1>My Shop</h1><hr>
<h4>Registration</h4>
<form action="/registration" method="post">
    Name: <input type="text" name="name"><br><br>
    E-mail: <input type="email" name="email"><br><br>
    Phone: <input type="text" name="phone"><br><br>
    Password: <input type="password" name="password"><br><br>
    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <input type="submit" value="Sign Up"/>
</form>
<#if errorMessage??>
    <h2 style="color: red">$(errorMessage)</h2>
</#if>
</body>
</html>