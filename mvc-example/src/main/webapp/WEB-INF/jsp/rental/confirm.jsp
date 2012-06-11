<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
 <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<head>
    <title>Review Your Cart</title>
    <script type="text/javascript">

        function remove(id){
            var url = "<c:url value="/rental/remove.do"/>";
            $.getJSON(url, {movieId: id}, function(data){
                if(data > 0){
                  $('#cartCount').text(data);
                  $('#rental_' + id).remove();
                } else{
                  $('#rental_' + id).remove();
                  $('#cartCountText').replaceWith('<p>Your cart is empty.</p>');
                  $('#movies').hide()
                }
            });
        }

    </script>
</head>

<body>

    <div class="contentArea">
	    <h1>Review Your Cart</h1>

        <c:choose>
            <c:when test="${fn:length(cart.rentals) gt 0}">
                <p id="cartCountText">You are renting <span id="cartCount">${cart.itemCount}</span> item(s).</p>

                    <table id="movies" class="confirmationTable">
                        <tr>
                            <td class="firstColumnOnLeft confirmationTableColumnHeading confirmationTableImageColumn">&nbsp;</td>
                            <td class="confirmationTableColumnHeading confirmationTableTitleColumn">Title:</td>
                            <td class="confirmationTableColumnHeading confirmationTableRatingColumn">Rating:</td>
                            <td class="confirmationTableColumnHeading confirmationTableCheckboxColumn">Remove:</td>
                        <tr>


                        <c:forEach var="rental" items="${rentals}" varStatus="index">
                            <tr id="rental_${rental.id}" class="confirmationTableRow">
                                <td class="firstColumnOnLeft confirmationTableImageColumn">
                                    <img class="smallImage" src="<c:url value="/rental/image.do?id=${rental.id}"/>"/>
                                </td>
                                <td class="confirmationTableTitleColumn">${rental.title}</td>
                                <td class="confirmationTableRatingColumn">${rental.rating}</td>
                                <td class="confirmationTableCheckboxColumn"><input id="remove_${rental.id}" type="submit" onClick="remove(${rental.id})" value="Remove"/></td>
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
