<!DOCTYPE html>
<html>
<head>
    <title>My Shop</title>
</head>
<body>
<h1>My Shop</h1><hr>
<h4>Hello, ${user.name}</h4>
<br>
<b>Name: </b>${user.name}<br>
<b>Phone: </b>${user.phone}<br>
<b>E-mail: </b>${user.email}<br>

<h4>My orders:</h4>
<#if orderClient??>
    <div>
    <#list orderClient as inf>
        <p><a href="/order-info/${inf.getOrderID()}">${inf.getGoodName()}</a><br></p>
    </#list>
    </div>
<#else >
    <h4>Empty</h4>
</#if>

<form action="/" method="get">
    <input type="submit" value="To main page"/>
</form>

</body>
</html>