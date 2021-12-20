<!DOCTYPE html>
<html>
<head>
    <title>My Shop</title>
</head>
<body>
<h1>My Shop</h1><hr>
<h4>Ordering</h4>

<#if message??>
    <h5 style="color: red">Wrong count</h5>
    <br>
</#if>

<form action="/order/${good.getId}" method="post">
    Count: <input type="text" name="count"><br><br>
    Receive method:
    <#list receivings as receive>
    <div>
        <input type="radio" id="${receive.getId()}"
               name="receiveID" value="${receive.getId()}">
        <label for="receiving_method">${receive.getReceiveMethod()}-----${receive.getAddress()}</label>
    </div>
    </#list>
    <br><br>

    Payment method:
    <#list payments as payment>
        <div>
            <input type="radio" id="${payment.getPayMethod()}"
                   name="payMethod" value="${payment.getPayMethod()}">
            <label for="payment_method">${payment.getPayMethod()}</label>
        </div>
    </#list>
    <br><br>

    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <input type="submit" value="Order!"/>
</form>
</body>
</html>