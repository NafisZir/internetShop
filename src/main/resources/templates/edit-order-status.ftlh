<!DOCTYPE html>
<html>
<head>
    <title>My Shop</title>
</head>
<body>
<h1>My Shop</h1><hr>
<h4>Edit order status</h4>

<form action="/admin/editOrderStatus/${order.order_ID}" method="post">
    <#list statuses as status>
        <div>
            <input type="radio" id="${status.status_Name}"
                   name="status_Name" value="${status.status_Name}">
            <label for="${status.status_Name}">${status.status_Name}</label>
        </div>
    </#list>
    <br>

    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <input type="submit" value="Edit"/>
</form>

</body>
</html>