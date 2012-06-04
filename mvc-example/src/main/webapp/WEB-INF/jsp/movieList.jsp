<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<head>
    <title>Movie List</title>
</head>

<body>
    <div class="contentArea">
	    <h1>Movie List</h1>

	    <c:choose>

            <c:when test="${fn:length(movies) gt 0}">

                <p>Here are the current movies:</p>

                <table id="movies" class="dataTable">
                        <tr>
                            <td class="dataTableColumnHeading">Title:</td>
                            <td class="dataTableColumnHeading">Rating:</td>
                        <tr>
                        <c:forEach var="movie" items="${movies}" varStatus="status">
                            <tr class="dataTableRow">
                                <td class="dataTableText">${movie.title}</td>
                                <td class="dataTableText">${movie.rating}</td>
                            </tr>
                        </c:forEach>
                </table>

            </c:when>

            <c:otherwise>
                <p>There are currently no movies. Go add one.</p>
            </c:otherwise>

	    </c:choose>

    </div>
</body>