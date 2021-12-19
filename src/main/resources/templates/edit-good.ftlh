<!DOCTYPE html>
<html>
<head>
    <title>My Shop</title>
</head>
<body>
<h1>My Shop</h1><hr>
<h4>Edit good</h4>
<form action="/admin/editGood/${good.goods_ID}" method="post">
<b>Name: </b> <input type="text" name="name" value="${good.name}"><br>
<b>Price: </b> <input type="number" name="price" value="${good.price}"><br>
<b>Producer: </b>
    <#list producers as producer>
        <div>
            <input type="radio" id="${producer.producer_Name}"
                   name="producer_Name" value="${producer.producer_Name}">
            <label for="${producer.producer_Name}">${producer.producer_Name}</label>
        </div>
    </#list>
    <br>
<b>Category: </b>
    <#list categories as category>
        <div>
            <input type="radio" id="${category.categoryID}"
                   name="categoryID" value="${category.categoryID}">
            <label for="${category.categoryID}">${category.descr}</label>
        </div>
    </#list>
    <br>
<b>Availability: </b> <input type="number" name="availability" value="${good.availability}"><br>
<b>Image: </b> <input type="text" name="image" value="${good.image}"><br>

    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <input type="submit" value="Edit"/>
</form>

</body>
</html>