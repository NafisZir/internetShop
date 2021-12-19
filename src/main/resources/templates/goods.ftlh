<!DOCTYPE html>
<html>
<head>
    <title>My Shop</title>
</head>
<body>
<h1>My Shop</h1><hr>
<#if client.email??>
    <h3>Hello <a href="/client">${client.name}</a></h3>
    <form action="/logout" method="post">
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <input type="submit" value="Log Out"/>
    </form>
<#else>
    <a href="/login">Log In</a></h1>
</#if>
<br>
<#if client.isAdmin()>
    <form action="/admin" method="get">
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <input type="submit" value="Panel of administrator"/>
    </form>
</#if>

<hr>
<h4>Goods from Kazan</h4>
<form action="/" method="get">
    Search with good's name: <input type="text" name="name"><br>
    <input type="submit" value="Search"/>
</form>
<#list goods as good>
    <div>
        <p><b>${good.name}</b> ${good.price} rub. | <a href="/good/${good.goods_ID}">More...</a></p>
    </div>
<#else>
    <h3>Empty</h3>
</#list>
</body>
</html>