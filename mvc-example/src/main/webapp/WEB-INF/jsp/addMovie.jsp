<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<head>
    <title>Add a Movie</title>
</head>
<body>
    <div class="contentArea">

        <h1>Add a Movie</h1>

        <form:form method="POST" action="add.do" encType="multipart/form-data">
		    <form:errors path="*" cssClass="errorblock" element="div" />
		    <table>
                <tr>
                    <td><form:label path="title">Title:</form:label></td>
                    <td><form:input id="title" path="title"/></td>
                </tr>
                <tr>
                    <td><form:label path="rating">Rating:</form:label></td>
                    <td>
                        <form:select id="rating" path="rating">
                            <form:option value="NONE" label="--- Select ---"/>
                            <form:options items="${ratings}"/>
                        </form:select>
                    </td>
                </tr>
                <tr>
                    <td><form:label path="image">Image:</form:label></td>
                    <td>
                        <form:input id="image" path="image" type="file"/>
                    </td>
                </tr>
            </table>
            <br/>
            <input id="submit" type="submit" value="Add"/>
	    </form:form>

	    <c:if test="${success}">
	        <p>Movie added: ${title}<p/>
	    </c:if>

    </div>
</body>

