<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>Products List</title>
    </head>
    <body>
        <header>
            <h1>Products List</h1>
        </header>
        <main>
        <div>
              <table>

                <tr>
                  <th>Product Id</th>
                  <th>Product Name</th>
                  <th>Product Price</th>
                  <th>Create Date</th>

                  <c:forEach items= "${productsJsp}" var = "product">
                    <tr>
                        <td>${product.idProduct}</td>
                        <td>${product.nameProduct}</td>
                        <td>${product.priceProduct}</td>
                        <td><fmt:formatDate pattern="d/MM/yyyy" value="${product.dateCreate}"/></td>
                        <td>
                        <a Onclick="return confirm('Are you sure to delete this item?')"
                                href="deleteProduct?id=${product.idProduct}">Delete</a>
                        </td>
                        <td>
                        <a href="showProduct?id=${product.idProduct}">Edit</a>
                        </td>
                        <td>
                            <a href="hello">Hello</a>
                        </td>

                    </tr>
                  </c:forEach>

                </tr>

              </table>


              <table class="table">
                <thead>
                  <tr>
                    <th scope="col">#</th>
                    <th scope="col">First</th>
                    <th scope="col">Last</th>
                    <th scope="col">Handle</th>
                  </tr>
                </thead>
                <tbody>
                  <tr>
                    <th scope="row">1</th>
                    <td>Mark</td>
                    <td>Otto</td>
                    <td>@mdo</td>
                  </tr>
                  <tr>
                    <th scope="row">2</th>
                    <td>Jacob</td>
                    <td>Thornton</td>
                    <td>@fat</td>
                  </tr>
                  <tr>
                    <th scope="row">3</th>
                    <td colspan="2">Larry the Bird</td>
                    <td>@twitter</td>
                  </tr>
                </tbody>
              </table>

              <div class="card" style="width: 18rem;" box-shadow=10px 5px 5px red >
                <img src="https://www.cleverfiles.com/howto/wp-content/uploads/2018/03/minion.jpg" class="card-img-top" alt="...">
                <div class="card-body">
                  <p class="card-text">LES MIGNONS</p>
                </div>
              </div>
              </div>
              <div>${mon_message.nameProduct}</div>
              <div>${mon_message.priceProduct}</div>
              <div>${mon_message.dateCreate}</div>
        </main>
        <footer>
            <a href="createProduct">Product Creation</a>

        </footer>
    </body>
</html>