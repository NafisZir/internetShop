<!DOCTYPE html>
<html>
<head>
    <title>My Shop</title>
</head>
<body>
<h1>My Shop</h1><hr>
<h4>Information</h4>
<b>Name: </b>${good.name}<br>
<b>Price: </b>${good.price}<br>
<b>Producer: </b>${good.getProducerName}<br>
<b>Category: </b>${goodCategory.descr}<br>
<b>Count: </b>${good.availability}<br>
<div>
    <img src="${good.image}" height="500px">
</div>

<#if user.isAdmin()>
    <form action="/admin/goods" method="get">
        <input type="submit" value="To goods"/>
    </form>
<#else>
    <form action="/order/${good.getId}" method="get">
        <input type="submit" value="Order"/>
    </form>
</#if>
</body>
</html>