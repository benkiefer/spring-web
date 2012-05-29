<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<html>
<head>
    <title>Movie Options</title>
</head>

<body>

    <div class="contentArea">

        <h1>Movie Options</h1>

        <ul>
            <li><a href="<c:url value="/add.do"/>">Add Movie</a></li>
            <li><a href="<c:url value="/list.do"/>">List Movies</a></li>
            <li><a href="<c:url value="/rental/select.do"/>">Rent a Movie</a></li>
        </ul>

    </div>

</body>
</html>
