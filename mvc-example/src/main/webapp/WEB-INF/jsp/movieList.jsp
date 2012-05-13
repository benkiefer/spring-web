<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<head>
    <title>Movie List</title>
</head>
<body class="contentArea">
    <div id="content">
	    <h1>Movie List</h1>
	    <p>Here are the current movies:</p>

	    <ul>
	            <c:forEach var="movie" items="${movies}" varStatus="status">
	                    <li>${movie.title}</li>
                </c:forEach>
	    </ul>

    </div>
</body>