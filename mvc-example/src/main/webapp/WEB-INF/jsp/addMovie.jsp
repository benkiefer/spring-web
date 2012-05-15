<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<head>
    <title>Add a Movie</title>
</head>
<body>
    <div class="contentArea">

        <h1>Add a Movie</h1>

        <form:form method="post" action="addMovie.do">

            <table>
                <tr>
                    <td><form:label path="title">Title:</form:label></td>
                    <td><form:input path="title" /></td>
                </tr>
            </table>
        </form:form>
    </div>
</body>
