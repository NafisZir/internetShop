<!DOCTYPE html>
<html>
<head>
    <title>My Shop</title>
</head>
<body>
<h1>My Shop</h1><hr>
<h4>Edit good</h4>
<form action="/admin/createGood" method="post">
    <b>Name: </b> <input type="text" name="name"><br>
    <b>Price: </b> <input type="number" name="price"><br>
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
    <b>Availability: </b> <input type="number" name="availability"><br>
    <b>Image: </b> <input type="text" name="image"><br>

    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <input type="submit" value="Create"/>
</form>

</body>
</html>