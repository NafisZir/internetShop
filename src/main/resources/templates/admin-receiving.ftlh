<!DOCTYPE html>
<html>
<head>
    <title>My Shop</title>
</head>
<body>
<h1>My Shop</h1><hr>
<h4>Receiving</h4><hr>

<form action="/admin/create-receive" method="post">
    <b>Write name: </b> <input type="text" name="receive_Method"><br>
    <b>Address: </b> <input type="text" name="address"><br>

    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <input type="submit" value="Create"/>
</form>

<#if message??>
    <h5 style="color: red">"This receiving has orders!"</h5>
</#if>

<#list receiving as receive>
    <div>
        <p><b>${receive.getReceiveMethod} | ${receive.address}</b>
        <form action="/admin/delete-receive/${receive.getId}" method="post">
            <input type="hidden" name="_csrf" value="${_csrf.token}">
            <input type="submit" value="Delete"/>
        </form>
        <form action="/admin/edit-receive/${receive.getId}" method="get">
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