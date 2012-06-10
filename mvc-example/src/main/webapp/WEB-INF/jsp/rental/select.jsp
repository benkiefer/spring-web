<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<head>
    <title>Pick a Movie</title>
   <script type="text/javascript">
         function update(id) {
            var selected = $('#movie_' + id).hasClass("selected");
            if (selected){
               remove(id);
            } else{
               add(id);
            }
        }

        function add(id) {
                var url = "<c:url value="/rental/add.do"/>";
                updateCart(id, url);
                stageForRemoval(id);
        }

        function remove(id) {
                var url = "<c:url value="/rental/remove.do"/>";
                updateCart(id, url);
                stageForAddition(id);
        }

        function updateCart(id, url){
            $.getJSON(url, {movieId: id}, function(data){
                  $('#cartCount').text(data);
            });
        }

        function stageForRemoval(id){
            $('#movie_' + id).addClass("selected");
            $('#movie_' + id).removeClass("unselected");
        }

        function stageForAddition(id){
            $('#movie_' + id).removeClass("selected");
            $('#movie_' + id).addClass("unselected");
        }

        window.onload = function(){
            <c:forEach var="group" items="${movies}">
                <c:forEach var="rental" items="${group}">
                    <c:choose>
                        <c:when test="${!rental.selected}">
                            stageForAddition("${rental.id}");
                        </c:when>
                        <c:otherwise>
                            stageForRemoval("${rental.id}");
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </c:forEach>
        }

    </script>

</head>

<body onload="updateButtons()">

    <div class="contentArea">
               <p>Want a movie? Click it. Changed your mind? Click it again.</p>

                <c:forEach var="group" items="${movies}">
                    <ul class="movieRentalList">
                        <c:forEach var="rental" items="${group}">
                            <li class="movieRentalOption">
                                <input class="movieRentalOptionImage" id="movie_${rental.id}" type="image" onClick="update(${rental.id});"
                                src="<c:url value="/rental/image.do?id=${rental.id}"/>"/>
                            </li>
                        </c:forEach>
                    </ul>
                    <br/>
                </c:forEach>

            <br/>

            <a id="viewCart" href="<c:url value="/rental/cart.do"/>">View Cart</a>

            <br/>

            <div class="shoppingCartCount">
            	<p>Cart: <span id="cartCount">${cart.itemCount}</span></p></div>
            </div>
    </div>

</body>
