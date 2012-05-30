<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<head>
    <title>Pick a Movie</title>
    <script type="text/javascript">
        function add(id) {
                var url = "<c:url value="/rental/add.do"/>";
                updateCart(id, url);
        }

        function remove(id) {
                var url = "<c:url value="/rental/remove.do"/>";
                updateCart(id, url);
        }

        function updateCart(id, url){
            $.getJSON(url, {movieId: id}, function(data){
                  $('#cartCount').text(data);
            });
        }
    </script>

</head>

<body>

    <div class="contentArea">
	    <h1>Select a Movie</h1>

            <table class="dataTable">
                <tr>
                    <td class="dataTableColumnHeading">Title:</td>
                    <td class="dataTableColumnHeading">Rating:</td>
                    <td class="dataTableColumnHeading">&nbsp;</td>
                    <td class="dataTableColumnHeading">&nbsp;</td>
                <tr>


                <c:forEach var="rental" items="${movies.movieRentals}" varStatus="status">

                <tr class="dataTableRow">
                    <td class="dataTableText">${rental.title}</td>
                    <td class="dataTableText">${rental.rating}</td>
                    <td class="dataTableText"><input type="button" onClick="add(${rental.id});" value="add"/></td>
                    <td class="dataTableText"><input type="button" onClick="remove(${rental.id});" value="remove"/></td>
                </tr>

                </c:forEach>

            </table>

            <br/>

            <a href="<c:url value="/rental/cart.do"/>">View Cart</a>

            <br/>

            <div class="shoppingCartCount">
            	<p>Cart: <span id="cartCount">${cart.itemCount}</span></p></div>
            </div>
    </div>

</body>
