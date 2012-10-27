<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<head>
    <title>Movie List</title>
    <script type="text/javascript">
        function deleteMovie(id){
            var url = "<c:url value="/movie/delete.do"/>";
            $.getJSON(url, {movieId: id}, function(data){
                $('#movie_' + id).hide("fast");
            });
        }
    </script>

</head>

<body>
    <div class="contentArea">
	    <h1>Movie List</h1>

	    <c:choose>

            <c:when test="${fn:length(movies) gt 0}">

                <p>Here are the current movies:</p>

                <table id="movies" class="dataTable">
                        <tr class="dataTableRow">
                            <td class="dataTableColumnHeading dataTableIdColumn firstColumnOnLeft">Id:</td>
                            <td class="dataTableColumnHeading dataTableImageColumn">Image:</td>
                            <td class="dataTableColumnHeading dataTableTitleColumn">Title:</td>
                            <td class="dataTableColumnHeading dataTableRatingColumn">Rating:</td>
                            <td class="dataTableColumnHeading dataTableInputColumn">&nbsp;</td>
                        <tr>
                        <c:forEach var="movie" items="${movies}" varStatus="status">
                            <tr id="movie_${movie.id}" class="dataTableRow">
                                <td class="dataTableText dataTableIdColumn firstColumnOnLeft">${movie.id}</td>
                                <td class="dataTableText dataTableImageColumn">
                                    <img class="smallImage" src="<c:url value="/rental/image.do?id=${movie.id}"/>"/>
                                </td>
                                <td class="dataTableText dataTableTitleColumn">${movie.title}</td>
                                <td class="dataTableText dataTableRatingColumn">${movie.rating}</td>
                                <td class="dataTableText dataTableInputColumn"><input id="delete_${movie.id}" onClick="deleteMovie(${movie.id});" value="Delete" type="submit"/></td>
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