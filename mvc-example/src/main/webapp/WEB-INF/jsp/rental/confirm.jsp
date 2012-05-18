<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<head>
    <title>Pick a Movie</title>
</head>

<body>

    <div class="contentArea">
	    <h1>Review Your Cart</h1>

        <form:form method="POST" action="rental/confirm.do">
            <form:errors path="*" cssClass="errorblock" element="div" />

	            <c:forEach var="movie" items="${cart.rentals}" varStatus="status">
	                    <tr class="dataTableRow">
	                        <td class="dataTableText">${movie.title}</td>
	                        <td class="dataTableText">${movie.rating}</td>
                        </tr>
                </c:forEach>

            <td class="dataTableText"><input type="submit"/></td>
        </form:form>

    </div>

</body>
