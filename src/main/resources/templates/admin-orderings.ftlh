<!DOCTYPE html>
<html>
<head>
    <title>My Shop</title>
</head>
<body>
<h1>My Shop</h1><hr>
<h4>Orders</h4><hr>

<#list orders as order>
    <div>
        <p><b>ID-${order.id}.</b>
            Client name: ${clients.get(order.clientID).name}.
            Good name: ${goods.get(order.goodsID).name}.
            Status: ${order.status} | <a href="/order-info/${order.id}">More...</a>
        <form action="/admin/delete-order/${order.id}" method="post">
            <input type="hidden" name="_csrf" value="${_csrf.token}">
            <input type="submit" value="Delete"/>
        </form>
        <form action="/admin/edit-order-status/${order.id}" method="get">
            <input type="hidden" name="_csrf" value="${_csrf.token}">
            <input type="submit" value="Edit status"/>
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