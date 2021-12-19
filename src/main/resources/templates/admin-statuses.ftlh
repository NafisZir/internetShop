<!DOCTYPE html>
<html>
<head>
    <title>My Shop</title>
</head>
<body>
<h1>My Shop</h1><hr>
<h4>Receiving</h4><hr>

<form action="/admin/create-status" method="post">
    <b>Write name: </b> <input type="text" name="status_Name"><br>

    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <input type="submit" value="Create"/>
</form>

<#if message??>
    <h5 style="color: red">"This status has orders!"</h5>
</#if>

<#list statuses as status>
    <div>
        <p><b>${status.status_Name}</b>
        <form action="/admin/delete-status/${status.status_Name}" method="post">
            <input type="hidden" name="_csrf" value="${_csrf.token}">
            <input type="submit" value="Delete"/>
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