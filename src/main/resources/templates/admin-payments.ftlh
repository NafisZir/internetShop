<!DOCTYPE html>
<html>
<head>
    <title>My Shop</title>
</head>
<body>
<h1>My Shop</h1><hr>
<h4>Payments</h4><hr>

<form action="/admin/create-payment" method="post">
    <b>Write: </b> <input type="text" name="pay_Method"><br>

    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <input type="submit" value="Create"/>
</form>

<#if message??>
    <h5 style="color: red">"This payment has orders!"</h5>
</#if>

<#list payments as payment>
    <div>
        <p><b>${payment.pay_Method}</b>
        <form action="/admin/delete-payment/${payment.pay_Method}" method="post">
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