<!DOCTYPE html>
<html lang="en">
    <head>

        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Product Creation</title>
    </head>
    <body>


        <form action= "helloMethod" method="post">
        <div> HELLO FRIENDS </div>
        <label for="nameProduct">Product Name : </label>
        <div><input type="text" id="nameProduct" name="nameProduct" ></div>
        <label for="priceProduct">Product Price : </label>
        <div><input type="number" step="0.01" id="priceProduct" name="priceProduct"></div>
        <div><input type="date" id="dateCreate" name="dateJsp" value="${now}"></div>

        <input type="submit" value="TEST">

        ${message}

        </form>
    </body>
</html>