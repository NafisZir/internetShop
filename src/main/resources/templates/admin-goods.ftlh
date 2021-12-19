<!DOCTYPE html>
<html>
<head>
    <title>My Shop</title>
</head>
<body>
<h1>My Shop</h1><hr>
<h4>Goods</h4><hr>

<form action="/admin/create-good" method="get">
    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <input type="submit" value="Create new good"/>
</form>

<form action="/admin/goods" method="get">
    Search with good's name: <input type="text" name="name"><br>
    <input type="submit" value="Search"/>
</form>

<#if message??>
    <h5 style="color: red">message</h5>
</#if>

<#list goods as good>
    <div>
        <p><b>${good.name}</b> ${good.price} rub. | <a href="/good/${good.goods_ID}">More...</a>
        <form action="/admin/delete-good/${good.goods_ID}" method="post">
            <input type="hidden" name="_csrf" value="${_csrf.token}">
            <input type="submit" value="Delete"/>
        </form>
        <form action="/admin/edit-good/${good.goods_ID}" method="get">
            <input type="hidden" name="_csrf" value="${_csrf.token}">
            <input type="submit" value="Edit"/>
        </form>
        </p>
    </div>
<#else>
    <h3>Empty</h3>
</#list>

<form action="/admin" method="get">
    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <input type="submit" value="To panel"/>
</form>

</body>
</html>