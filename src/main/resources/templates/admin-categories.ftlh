<!DOCTYPE html>
<html>
<head>
    <title>My Shop</title>
</head>
<body>
<h1>My Shop</h1><hr>
<h4>Categories</h4><hr>

<form action="/admin/create-category" method="post">
    <b>Write: </b> <input type="text" name="descr"><br>

    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <input type="submit" value="Create"/>
</form>

<#if message??>
    <h5 style="color: red">This category has goods!</h5>
</#if>

<#list categories as category>
    <div>
        <p><b>${category.descr}</b>
        <form action="/admin/delete-category/${category.getId}" method="post">
            <input type="hidden" name="_csrf" value="${_csrf.token}">
            <input type="submit" value="Delete"/>
        </form>
        <form action="/admin/edit-category/${category.getId}" method="get">
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