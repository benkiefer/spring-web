<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="en-US">

  <head>
    <link rel="stylesheet" type="text/css" href="css/main.css"/>
  </head>
  <body>

    <div class="pageWidth">

      <div id="header">
        <br/>
            <p class="headerText">White Box Rentals</p>

        <ul id="list-nav">
            <li><a href="<c:url value="options.do"/>">Home</a></li>
            <li><a href="#">TBD...</a></li>
        </ul>

        <br/>
      </div>

      <decorator:body/>

    </div>

  </body>
</html>