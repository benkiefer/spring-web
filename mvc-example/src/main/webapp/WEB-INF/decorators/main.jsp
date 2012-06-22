<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" lang="en-US">
  <head>
    <link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/css/screen/main.css"/>"/>
    <link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/css/screen/navigation.css"/>"/>

    <script type="text/javascript" src="<c:url value="/js/jquery-1.7.min.js"/>"></script>

    <title><decorator:title default="White Box"/></title>

    <decorator:head/>
  </head>
  <body>

    <div class="pageWidth">

        <div id="header">

            <div id="navigation">
                <ul>
                    <li>
                        <a id="Home" href="<c:url value="/options.do"/>">Home</a>
                    </li>
                </ul>
                <c:if test="${cart != null}">
                    <ul id="right">
                        <li>
                            <a id="Cart" href="<c:url value="/rental/cart.do"/>">Cart (<span id="cartCount">${cart.itemCount}</span>)</a>
                        </li>
                    </ul>
                </c:if>
            </div>

        </div>

      <decorator:body/>

    </div>

  </body>
</html>