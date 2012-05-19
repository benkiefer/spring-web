<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<head>
    <title>Review Your Cart</title>
</head>

<body>

    <div class="contentArea">
	    <h1>Review Your Cart</h1>

	    <p>You are renting ${cart.itemCount} item(s).</p>

        <table class="dataTable">
            <tr>
                <td class="dataTableColumnHeading">Title:</td>
                <td class="dataTableColumnHeading">Rating:</td>
            <tr>


            <c:forEach var="rental" items="${cart.rentals}">

                <tr class="dataTableRow">
                        <td class="dataTableText">${rental.title}</td>
                    <td class="dataTableText">${rental.rating}</td>
                </tr>

            </c:forEach>
        </table>
    </div>

</body>
