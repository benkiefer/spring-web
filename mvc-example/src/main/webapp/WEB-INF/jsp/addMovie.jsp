<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<head>
    <title>Add a Movie</title>
</head>
<body>
<h2>Add a Movie</h2>
<form:form method="post" action="addMovie.do">

    <table>
        <tr>
            <td><form:label path="title">First Name</form:label></td>
            <td><form:input path="title" /></td>
        </tr>
    </table>

</form:form>