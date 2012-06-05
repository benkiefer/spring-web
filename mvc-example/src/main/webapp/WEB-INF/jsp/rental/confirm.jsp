<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
 <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<head>
    <title>Review Your Cart</title>
</head>

<body>

    <div class="contentArea">
	    <h1>Review Your Cart</h1>

        <c:choose>
        <c:when test="${fn:length(cart.rentals) gt 0}">
            <p>You are renting ${cart.itemCount} item(s).</p>

                <table id="movies" class="dataTable">
                    <tr>
                        <td class="dataTableColumnHeading">Title:</td>
                        <td class="dataTableColumnHeading">Rating:</td>
                    <tr>


                    <c:forEach var="rental" items="${rentals}">

                        <tr class="dataTableRow">
                                <td class="dataTableText">${rental.title}</td>
                            <td class="dataTableText">${rental.rating}</td>
                        </tr>

                    </c:forEach>
                </table>
        </c:when>

        <c:otherwise>
            <p>Your cart is empty.</p>
        </c:otherwise>
        </c:choose>


    </div>

</body>
