<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Product Creation</title>
    </head>
    <body>
        <header>
            <h1>Product Edition</h1>
        </header>
        <main>
            <form action="updateProduct" method="post">
                <div>
                    <!--on peut ajouter un parametre au id : hidden -->
                    <label hidden for="idProduct">Product Id : </label>
                    <input hidden type="text" id="idProduct" name="idProduct" value="${productJsp.idProduct}">
                </div>
                <div>
                    <label for="nameProduct">Product Name : </label>
                    <input type="text" id="nameProduct" name="nameProduct" value="${productJsp.nameProduct}">
                </div>
                <div>
                    <label for="priceProduct">Price : </label>
                    <input type="text" id="priceProduct" name="priceProduct" value="${productJsp.priceProduct}">
                </div>
                <div>
                    <label for="dateCreate">Creation Date : </label>
                    <fmt:formatDate pattern ="YYYY-MM-dd" value="${productJsp.dateCreate}" var="formatDate"/>
                    <input type="date" id="dateCreate" name="dateJsp" value="${formatDate}">
                </div>
                <div>
                    <input type="submit" value="update" action="updateProduct">
                </div>
            </form>
        </main>
        <footer>
            <a href="productsList">Products List</a>
        </footer>
    </body>
</html>