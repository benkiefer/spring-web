<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<head>
    <title>Pick a Movie</title>
</head>

<body>

    <div class="contentArea">
	    <h1>Select a Movie</h1>

            <form:form method="POST" commandName="movies" action="select.do">
                <form:errors path="*" cssClass="errorblock" element="div" />

            <table class="dataTable">
                <tr>
                    <td class="dataTableColumnHeading">Title:</td>
                    <td class="dataTableColumnHeading">Rating:</td>
                    <td class="dataTableColumnHeading">&nbsp;</td>
                <tr>


                <c:forEach var="rental" items="${movies.movieRentals}" varStatus="status">

                <tr class="dataTableRow">
                    <td class="dataTableText">${rental.title}</td>
                    <td class="dataTableText">${rental.rating}</td>
                    <td class="dataTableText"><form:checkbox path="movieRentals[${status.index}].selected"/></td>
                </tr>

                <form:hidden path="movieRentals[${status.index}].title" />
                <form:hidden path="movieRentals[${status.index}].rating" />
                <form:hidden path="movieRentals[${status.index}].id" />

                </c:forEach>

            </table>

            <br/>
            <input type="submit"/>

            </form:form>
    </div>

</body>
