<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<head>
    <title>Add a Movie</title>
</head>
<body>
    <div class="contentArea">

        <h1>Add a Movie</h1>

        <form:form method="POST" action="add.do">
		    <form:errors path="*" cssClass="errorblock" element="div" />
		    <table>
                <tr>
                    <td><form:label path="title">Title:</form:label></td>
                    <td><form:input path="title" /></td>
                </tr>
                <tr>
                    <td><form:label path="rating">Rating:</form:label></td>
                    <td>
                        <form:select path="rating">
                            <form:option value="NONE" label="--- Select ---"/>
                            <form:options items="${ratings}"/>
                        </form:select>
                    </td>
                </tr>
            </table>
            <br/>
            <input type="submit"/>
	    </form:form>

    </div>
</body>

