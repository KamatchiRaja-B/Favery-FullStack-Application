<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.favery.model.Menu, com.favery.model.Restaurant, java.util.List" %>
	<head>
	    <meta charset="UTF-8">
	    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	    <link rel="stylesheet" type="text/css" href="css/style.css">
	    <link rel="preconnect" href="https://fonts.googleapis.com">
	    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
	    <link
	        href="https://fonts.googleapis.com/css2?family=Poppins:wght@100;200;300;400;500;600;700;800;900&family=Roboto:wght@700&display=swap"
	        rel="stylesheet">
	    <title>Menu - Favery</title>
	</head>
	
	<body>
		<% 
		    List<Menu> menusList = (List<Menu>)session.getAttribute("menusList");
			Restaurant restaurant = (Restaurant)session.getAttribute("restaurant");
		%>
	    <nav>
	        <ul class="navbar">
	            <div class="leftend">
	                <li><img src="assets/logo.png" alt="Logo" width="75px" height="75px"></li>
	                <li>Favery</li>
	            </div>
	            <div class="center">
	                <li><a href="home.jsp">Home</a></li>
	                <li><a href="#">Cart</a></li>
	                <li><a href="#">Order History</a></li>
	            </div>
	            <div class="rightend">
	                <a href="index.jsp"><button class="btn">Logout</button></a>
	            </div>
	        </ul>
	    </nav>
	
	    <header>
	        <h1>Menus available in 
		        <span class="yellow">
		        	<% out.println(restaurant.getRestaurantName()); %>
		        </span>
	        </h1>
	    </header>
	
	    <div class="menu-container">
		    <% for(Menu menu : menusList) {%>
		        <div class="menu-card">
		            <div class="menu-image-container">
		                <img src="images/<% menu.getImgPath(); %>" 
		                alt="Menu Image of <% out.println(menu.getMenuName()); %> ">
		            </div>
		            <div class="menu-content">
		                <div class="menu-title"><% out.println(menu.getMenuName()); %></div>
		                <div class="menu-description"><% out.println(menu.getDescription()); %></div>
		                <div class="menu-price">$<% out.println(menu.getPrice()); %></div>
		                <div class="quantity-container">
		                    <button class="quantity-btn minus">-</button>
		                    <input type="text" value="0" class="quantity-number" readonly>
		                    <button class="quantity-btn plus">+</button>
		                </div>
		                <button class="btn add-to-cart">Add to Cart</button>
		            </div>
		        </div>
		 	<% } %>      
	    </div>
	    
	    <script src="js/script.js"></script>
	</body>

</html>