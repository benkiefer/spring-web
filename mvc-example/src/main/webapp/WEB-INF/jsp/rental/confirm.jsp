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

                    <table id="movies" class="confirmationTable">
                        <tr>
                            <td class="firstColumnOnLeft confirmationTableColumnHeading confirmationTableImageColumn">&nbsp;</td>
                            <td class="confirmationTableColumnHeading confirmationTableTitleColumn">Title:</td>
                            <td class="confirmationTableColumnHeading confirmationTableRatingColumn">Rating:</td>
                            <td class="confirmationTableColumnHeading confirmationTableCheckboxColumn">Remove:</td>
                        <tr>


                        <c:forEach var="rental" items="${rentals}" varStatus="index">
                            <tr class="confirmationTableRow">
                                <td class="firstColumnOnLeft confirmationTableImageColumn">
                                    <img class="smallImage" src="<c:url value="/rental/image.do?id=${rental.id}"/>"/>
                                </td>
                                <td class="confirmationTableTitleColumn">${rental.title}</td>
                                <td class="confirmationTableRatingColumn">${rental.rating}</td>
                                <td class="confirmationTableCheckboxColumn"><input type="checkbox"/></td>
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
