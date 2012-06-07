<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<head>
    <title>Pick a Movie</title>
    <script type="text/javascript">
        function add(id) {
                var url = "<c:url value="/rental/add.do"/>";
                updateCart(id, url);
                stageForRemoval(id);
        }

        function remove(id) {
                var url = "<c:url value="/rental/remove.do"/>";
                updateCart(id, url);
                stageForAddition(id);
        }

        function updateCart(id, url){
            $.getJSON(url, {movieId: id}, function(data){
                  $('#cartCount').text(data);
            });
        }

        function stageForRemoval(id){
            $('#remove_' + id).show();
            $('#add_' + id).hide();
        }

        function stageForAddition(id){
            $('#add_' + id).show();
            $('#remove_' + id).hide();
        }

        window.onload = function(){
            <c:forEach var="rental" items="${movies.movieRentals}">
                <c:choose>
                    <c:when test="${!rental.selected}">
                        stageForAddition("${rental.id}");
                    </c:when>
                    <c:otherwise>
                        stageForRemoval("${rental.id}");
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        }

    </script>

</head>

<body onload="updateButtons()">

    <div class="contentArea">
	    <h1>Select a Movie</h1>

            <table id="movies" class="dataTable">
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
                    <td class="dataTableText">
                        <input id="add_${rental.id}"
                               type="button"
                               onClick="add(${rental.id});"
                               value="add"/>
                        <input id="remove_${rental.id}"
                               type="button"
                               onClick="remove(${rental.id});"
                               value="remove"/>
                    </td>
                    <td>
                    <img src="<c:url value="/rental/image.do?id=${rental.id}"/>" border="0">
                    </td>
                </tr>

                </c:forEach>

            </table>

            <br/>

            <a id="viewCart" href="<c:url value="/rental/cart.do"/>">View Cart</a>

            <br/>

            <div class="shoppingCartCount">
            	<p>Cart: <span id="cartCount">${cart.itemCount}</span></p></div>
            </div>
    </div>

</body>
