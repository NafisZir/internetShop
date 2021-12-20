<!DOCTYPE html>
<html>
<head>
    <title>My Shop</title>
</head>
<body>
<h1>My Shop</h1><hr>
<h4>Clients</h4><hr>

<table border="1">
    <tr>
        <th>Name</th>
        <th>Phone</th>
        <th>E-mail</th>
        <th>Deleting user</th>
    </tr>
    <#list users as user>
        <tr>
            <th>${user.name}</th>
            <th>${user.phone}</th>
            <th>${user.email}</th>
            <th><form action="/admin/delete-user/${user.getId}" method="post">
                    <input type="hidden" name="_csrf" value="${_csrf.token}">
                    <input type="submit" value="Delete"/>
                </form></th>
        </tr>
    <#else>
        <h3>No clients</h3>
    </#list>
</table>
<hr>

<form action="/admin" method="get">
    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <input type="submit" value="To panel"/>
</form>

</body>
</html>