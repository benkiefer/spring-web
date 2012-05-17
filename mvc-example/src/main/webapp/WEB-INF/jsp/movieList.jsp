<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<head>
    <title>Movie List</title>
</head>
<body>
    <div class="contentArea">
	    <h1>Movie List</h1>
	    <p>Here are the current movies:</p>

        <table class="dataTable">
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

    </div>
</body>