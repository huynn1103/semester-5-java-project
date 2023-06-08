<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8">
		<title>Foods</title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<meta name="description" content="">
		<!--[if ie]><meta content='IE=8' http-equiv='X-UA-Compatible'/><![endif]-->
		<!-- bootstrap -->
		<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">      
		<link href="bootstrap/css/bootstrap-responsive.min.css" rel="stylesheet">		
		<link href="themes/css/bootstrappage.css" rel="stylesheet"/>
		
		<!-- global styles -->
		<link href="themes/css/flexslider.css" rel="stylesheet"/>
		<link href="themes/css/main.css" rel="stylesheet"/>

		<!-- scripts -->
		<script src="themes/js/jquery-1.7.2.min.js"></script>
		<script src="bootstrap/js/bootstrap.min.js"></script>				
		<script src="themes/js/superfish.js"></script>	
		<script src="themes/js/jquery.scrolltotop.js"></script>
		
		<script src="https://apis.google.com/js/platform.js" async defer></script>
		
		<meta name="google-signin-client_id" content="784148845704-r77nsdh30glq39g9spb2nvvjqabtlfcg.apps.googleusercontent.com">
		<!--[if lt IE 9]>			
			<script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
			<script src="js/respond.min.js"></script>
		<![endif] -->
		<style>
			.product-box a button {
				background-color: #eb4800;
				color: white;
				padding: 7.5px 15px;
				border-radius: 5px;
				font-size: 14px;
				border-style: none;
			}
			
			.product-box:hover a button {
 				background-color: white;
 			}
			
 			.product-box:hover a button {
 				color: #eb4800 !important;
 			}

			.product-box:hover .price {
				color: white;
			}
			.search_form input{
				color: #777;
			}
		</style>
	</head>
    <body>		
		<div id="top-bar" class="container">
			<div class="row">
				<div class="span4">
					<form method="POST" class="search_form">
						<input onkeyup="searchFunction()" type="text" id="search" class="input-block-level search-query" Placeholder="eg. Macaron">
					</form>
				</div>
				<div class="span8">
					<div class="account pull-right">
						<ul class="user-menu">				
							<c:choose>
					            <c:when test = "${sessionScope.ctm == null}">
					            	<li><a href="Login">Log In</a></li>	
					            </c:when>
					            <c:otherwise>
					            	<li><a href="#">${sessionScope.ctm.getCustomerName()}</a></li>
					            	<li><a id="cart" href="Cart">Cart (${numberOfCartItems})</a></li>
					            	<li><a href="Purchase">My Purchase</a></li>
					            	<c:choose>
							            <c:when test = "${sessionScope.method == null}">
							            	<li><a href="#" onclick="signOut();">Log Out</a></li>
							            </c:when>
							            <c:otherwise>
											<li><a href="Logout">Log Out</a></li>
							            </c:otherwise>
									</c:choose>
					            </c:otherwise>
							</c:choose>	
						</ul>
					</div>
				</div>
			</div>
		</div>
		<div id="wrapper" class="container">
			<section class="navbar main-menu">
				<div class="navbar-inner main-menu">				
					<a href="Welcome" class="logo pull-left"><img src="themes/images/logo_2.png" class="site_logo" alt=""></a>
					<nav id="menu" class="pull-right">
						<ul>
							<li><a href="AllFoods">All foods</a></li>
							<li><a href="AllFoods?Country=Japan">Japan</a></li>
							<li><a href="AllFoods?Country=China">China</a></li>
							<li><a href="AllFoods?Country=Korea">Korea</a></li>
							<li><a href="AllFoods?Country=Vietnam">Vietnam</a></li>
							<li><a href="AllFoods?Country=France">France</a></li>																			
							<li><a href="AllFoods?Country=USA">USA</a></li>
						</ul>
					</nav>
				</div>
			</section>
			<section class="main-content">
				<div class="row">
					<div class="span12">													
						<div class="row">
							<div class="span12">
								<h4 class="title">
									<span class="pull-left"><span class="text"><span class="line"><strong>All</strong> Foods</span></span></span>
									<span class="pull-right">
										<a class="left button" href="#myCarousel" data-slide="prev"></a><a class="right button" href="#myCarousel" data-slide="next"></a>
									</span>
								</h4>
								<div id="myCarousel" class="myCarousel carousel slide">
									<div class="carousel-inner">	
									<c:forEach items="${lstFoods}" var="food" varStatus="loop">
										<c:if test = "${loop.index % 4 == 0}">
											<c:choose>
									            <c:when test = "${loop.index == 0}">
						            	<div class="active item">
											<ul class="thumbnails">
									            </c:when>
									            <c:otherwise>
						            	<div class="item">
											<ul class="thumbnails">
									            </c:otherwise>
								            </c:choose>
										</c:if>
												<li class="span3">
													<div class="product-box">
														<span class="sale_tag"></span>
														<p><a href="product_detail.html"><img src="${food.getImage()}" alt="" /></a></p>
														<a href="product_detail.html" class="title">${food.getFoodName()}</a><br/>
														<a href="products.html" class="category">Amount: ${food.getAmount()}</a>
														<p class="price">Price: ${food.getPrice()} VND</p>
														<a class="order" id="Order?FoodID=${food.getFoodID()}">
															<button type="button">Order</button>
														</a>
													</div>
												</li>
										<c:if test = "${loop.index % 4 == 3 || loop.index == lstFoods.size() - 1}">
											</ul>
										</div>
										</c:if>
									</c:forEach>								
									</div>							
								</div>
							</div>						
						</div>
						<br/>
						<div class="row feature_box">						
							<div class="span4">
								<div class="service">
									<div class="responsive">	
										<img src="themes/images/feature_img_2.png" alt="" />
										<h4>MODERN <strong>DESIGN</strong></h4>
										<p>Lorem Ipsum is simply dummy text of the printing and printing industry unknown printer.</p>									
									</div>
								</div>
							</div>
							<div class="span4">	
								<div class="service">
									<div class="customize">			
										<img src="themes/images/feature_img_1.png" alt="" />
										<h4>FREE <strong>SHIPPING</strong></h4>
										<p>Lorem Ipsum is simply dummy text of the printing and printing industry unknown printer.</p>
									</div>
								</div>
							</div>
							<div class="span4">
								<div class="service">
									<div class="support">	
										<img src="themes/images/feature_img_3.png" alt="" />
										<h4>24/7 LIVE <strong>SUPPORT</strong></h4>
										<p>Lorem Ipsum is simply dummy text of the printing and printing industry unknown printer.</p>
									</div>
								</div>
							</div>	
						</div>		
					</div>				
				</div>
			</section>
			<section id="footer-bar">
				<div class="row">
					<div class="span3">
						<h4>Navigation</h4>
						<ul class="nav">
							<li><a href="./index.html">Home Page</a></li>  
							<li><a href="./about.html">About Us</a></li>
							<li><a href="./contact.html">Contact Us</a></li>
							<li><a href="./cart.html">Your Cart</a></li>
							<li><a href="./register.html">Login</a></li>							
						</ul>					
					</div>
					<div class="span4">
						<h4>My Account</h4>
						<ul class="nav">
							<li><a href="#">My Account</a></li>
							<li><a href="#">Order History</a></li>
							<li><a href="#">Wish List</a></li>
							<li><a href="#">Newsletter</a></li>
						</ul>
					</div>
					<div class="span5">
						<p class="logo"><img src="themes/images/logo_1.png" class="site_logo" alt=""></p>
						<p>Lorem Ipsum is simply dummy text of the printing and typesetting industry. the  Lorem Ipsum has been the industry's standard dummy text ever since the you.</p>
						<br/>
						<span class="social_icons">
							<a class="facebook" href="#">Facebook</a>
							<a class="twitter" href="#">Twitter</a>
							<a class="skype" href="#">Skype</a>
							<a class="vimeo" href="#">Vimeo</a>
						</span>
					</div>					
				</div>	
			</section>
			<section id="copyright">
				<span>Copyright 2013 bootstrap page template  All right reserved.</span>
			</section>
		</div>
		<script src="themes/js/common.js"></script>
		<script>
			$(function() {
				gapi.load('auth2', function() {
		       		gapi.auth2.init();
		    	});
			});
		
			function signOut() {
			    $.ajax({
		            type: "POST",
		            url: "Logout",
		            success: function(responseText) {
						var auth2 = gapi.auth2.getAuthInstance();
					    auth2.signOut().then(function() {
					    	console.log('User signed out.');
					    });
		            	window.location.assign("http://localhost:8080/RestaurantManagement/Welcome");
	                }
	            });
			}
		</script>
		<script>
			function order(e) {
				e.preventDefault();
				console.log(e.currentTarget.id);
				let foodID = e.currentTarget.id.substring(e.currentTarget.id.indexOf("=") + 1);
				console.log(foodID);
			<%	if (session.getAttribute("ctm") == null) {	%>
					alert("Please login to order.");
			<%	} else {	%>
					$.ajax({
			            type: "POST",
			            url: "Order",
			            data: "foodID=" + foodID,
			            success: function(responseText) {
			            	$('.user-menu > li > #cart').text('Cart (' + responseText + ')');
		                }
		            });
			<%	}	%>
			}
		</script>
		<script>
			$('.order').on('click', order);
		</script>
		<script>
			function searchFunction() {
				$.ajax({
		            type: "POST",
		            url: "Search",
		            data: "key=" + $('#search').val(),
		            success: function(responseText) {
		            	const foods = responseText;
						let newFoods = "";
						for (let i = 0; i < foods.length; i++) {
							if (i % 4 == 0) {
								if (i == 0) {
									newFoods += '<div class="active item">';
									newFoods += '<ul class="thumbnails">';
								} else {
									newFoods += '<div class="item">';
									newFoods += '<ul class="thumbnails">';
								}
							}
							newFoods += '<li class="span3">';
							newFoods += '<div class="product-box">';
							newFoods += '<span class="sale_tag"></span>'
							newFoods += '<p><a href="product_detail.html"><img src="' + foods[i].image + '" alt="" /></a></p>';
							newFoods += '<a href="product_detail.html" class="title">' + foods[i].foodName + '</a><br/>';
							newFoods += '<a href="products.html" class="category">Amount: ' + foods[i].amount + '</a>';
							newFoods += '<p class="price">Price: ' + foods[i].price + ' VND</p>';
							newFoods += '<a class="order" id="Order?FoodID=' + foods[i].foodID + '">';
							newFoods += '<button type="button">Order</button>';
							newFoods += '</a>';
							newFoods += '</div>';
							newFoods += '</li>';
							if (i % 4 == 3 || i == foods.length - 1) {
								newFoods += '</ul>';
								newFoods += '</div>';
							}
						}
	            		$('#myCarousel > .carousel-inner').html(newFoods);
	            		$('.order').on('click', order);
	                }
	            });
			}
		</script>
    </body>
</html>