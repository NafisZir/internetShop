<!DOCTYPE html>
<html>
<head>
    <title>My Shop</title>
</head>
<body>
<h1>My Shop</h1><hr>
<h4>Edit order status</h4>

<form action="/admin/editOrderStatus/${order.id}" method="post">
    <#list statuses as status>
        <div>
            <input type="radio" id="${status.getStatus}"
                   name="status_Name" value="${status.getStatus}">
            <label for="${status.getStatus}">${status.getStatus}</label>
        </div>
    </#list>
    <br>

    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <input type="submit" value="Edit"/>
</form>

</body>
</html>