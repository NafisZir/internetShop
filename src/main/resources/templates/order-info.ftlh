<!DOCTYPE html>
<html>
<head>
    <title>My Shop</title>
</head>
<body>
<h1>My Shop</h1><hr>
<h4>Information about order</h4>
<br>
<b>Good's name: </b>${good.name}<br>
<b>Price: </b>${order.price}<br>
<b>Producer: </b>${good.getProducerName}<br>
<b>Count: </b>${order.count}<br>
<b>Status: </b>${order.status}<br>
<b>Payment: </b>${order.payMethod}<br>
<b>Receive method: </b>${receive.getReceiveMethod()}<br>
<b>Receive address: </b>${receive.getAddress()}<br>
<div>
    <img src="${good.image}" height="500px">
</div>

<#if user.isAdmin()>
    <form action="/admin/orders" method="get">
        <input type="submit" value="To orders"/>
    </form>
<#else>
    <form action="/" method="get">
        <input type="submit" value="To main page"/>
    </form>
</#if>

</body>
</html>