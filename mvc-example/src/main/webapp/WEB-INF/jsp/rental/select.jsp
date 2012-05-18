<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<head>
    <title>Pick a Movie</title>
</head>

<body>

    <div class="contentArea">
	    <h1>Select a Movie</h1>

        <form:form method="POST" action="rental/select.do">
            <form:errors path="*" cssClass="errorblock" element="div" />

            <table class="dataTable">
                <tr>
                    <td class="dataTableColumnHeading">Title:</td>
                    <td class="dataTableColumnHeading">Rating:</td>
                    <td class="dataTableColumnHeading">&nbsp;</td>
                <tr>

	            <c:forEach var="movie" items="${movies}" varStatus="status">
                    <tr class="dataTableRow">
                        <td class="dataTableText">${movie.title}</td>
                        <td class="dataTableText">${movie.rating}</td>
                         <td class="dataTableText"><input type="submit"/></td>
                    </tr>
                </c:forEach>
            </table>

        </form:form>

    </div>

</body>
