<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8">
		<title>Welcome</title>
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
		<![endif]-->
		
		<style>
			html {
				scroll-behavior: smooth;
			}
			
			.product-box:hover .price {
				color: white;
			}
		</style>
		<script>
			if (window.location.href != "http://localhost:8080/RestaurantManagement/Welcome")
				window.location.assign("http://localhost:8080/RestaurantManagement/Welcome");
		</script>
	</head>
    <body>		
		<div id="top-bar" class="container">
			<div class="row">
				<div class="span4">
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
					            	<c:choose>
							            <c:when test = "${numberOfCartItems == null}">
							    	<li><a href="Cart">Cart (0)</a></li>
							            </c:when>
							            <c:otherwise>
							    	<li><a href="Cart">Cart (${numberOfCartItems})</a></li>
							            </c:otherwise>
									</c:choose>
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
							<li><a href="#best-seller">Best seller</a></li>
							<li><a href="#best-price">Best price</a></li>
						</ul>
					</nav>
				</div>
			</section>
			<section  class="homepage-slider" id="home-slider">
				<div class="flexslider">
					<ul class="slides">
						<li class="google_map">
							<iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3826.3057301453778!2d107.58116881417493!3d16.46004983321913!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x3141a137de72dcfd%3A0x8b5ead90ff77bf71!2zVHLGsOG7nW5nIFRIUFQgY2h1ecOqbiBRdeG7kWMgSOG7jWMgSHXhur8!5e0!3m2!1svi!2s!4v1640427444904!5m2!1svi!2s" width="100%" height="450" style="border:0;" allowfullscreen="" loading="lazy"></iframe>
						</li>
<!-- 						<li> -->
<!-- 							<img src="themes/images/carousel/banner-1.jpg" alt="" /> -->
<!-- 						</li> -->
<!-- 						<li> -->
<!-- 							<img src="themes/images/carousel/banner-2.jpg" alt="" /> -->
<!-- 							<div class="intro"> -->
<!-- 								<h1>Mid season sale</h1> -->
<!-- 								<p><span>Up to 50% Off</span></p> -->
<!-- 								<p><span>On selected items online and in stores</span></p> -->
<!-- 							</div> -->
<!-- 						</li> -->
					</ul>
				</div>			
			</section>
			<section class="header_text">
				Lorem Ipsum is simply dummy text of the printing and printing industry unknown printer.
			</section>
			<section class="main-content">
				<div class="row">
					<div class="span12">													
						<div id="best-seller" class="row">
							<div class="span12">
								<h4 class="title">
									<span class="pull-left"><span class="text"><span class="line"><strong>BEST</strong> SELLER</span></span></span>
									<span class="pull-right">
										<a class="left button" href="#myCarousel" data-slide="prev"></a><a class="right button" href="#myCarousel" data-slide="next"></a>
									</span>
								</h4>
								<div id="myCarousel" class="myCarousel carousel slide">
									<div class="carousel-inner">
									<c:forEach items="${lstBestSellerFoods}" var="food" varStatus="loop">
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
														<p class="price">Price: ${food.getPrice()} VND</p>
													</div>
												</li>
										<c:if test = "${loop.index % 4 == 3 || loop.index == lstBestSellerFoods.size() - 1}">
											</ul>
										</div>
										</c:if>
									</c:forEach>	
									</div>							
								</div>
							</div>						
						</div>
						<br/>
						<div id="best-price" class="row">
							<div class="span12">
								<h4 class="title">
									<span class="pull-left"><span class="text"><span class="line">BEST <strong>PRICE</strong></span></span></span>
									<span class="pull-right">
										<a class="left button" href="#myCarousel-2" data-slide="prev"></a><a class="right button" href="#myCarousel-2" data-slide="next"></a>
									</span>
								</h4>
								<div id="myCarousel-2" class="myCarousel carousel slide">
									<div class="carousel-inner">
									<c:forEach items="${lstBestPriceFoods}" var="food" varStatus="loop">
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
														<p class="price">Price: ${food.getPrice()} VND</p>
													</div>
												</li>
										<c:if test = "${loop.index % 4 == 3 || loop.index == lstBestPriceFoods.size() - 1}">
											</ul>
										</div>
										</c:if>
									</c:forEach>
									</div>							
								</div>
							</div>						
						</div>
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
			<section class="our_client">
				<h4 class="title"><span class="text">Countries</span></h4>
				<div class="row">					
					<div class="span2">
						<a href="#"><img alt="" src="themes/images/Japan.png"></a>
					</div>
					<div class="span2">
						<a href="#"><img alt="" src="themes/images/China.png"></a>
					</div>
					<div class="span2">
						<a href="#"><img alt="" src="themes/images/Korea.png"></a>
					</div>
					<div class="span2">
						<a href="#"><img alt="" src="themes/images/Vietnam.png"></a>
					</div>
					<div class="span2">
						<a href="#"><img alt="" src="themes/images/France.png"></a>
					</div>
					<div class="span2">
						<a href="#"><img alt="" src="themes/images/USA.png"></a>
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
						<p>Lorem Ipsum is simply dummy text of the printing and type setting industry. the  Lorem Ipsum has been the industry's standard dummy text ever since the you.</p>
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
		<script src="themes/js/jquery.easing.min.js"></script>
		
		<!-- For home page-slider -->
		<script src="themes/js/jquery.flexslider-min.js"></script>
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
		<script type="text/javascript">
			$(function() {
				$(document).ready(function() {
					$('.flexslider').flexslider({
						animation: "fade",
						slideshowSpeed: 4000,
						animationSpeed: 600,
						controlNav: false,
						directionNav: true,
						controlsContainer: ".flex-container" // the container that holds the flexslider
					});
				});
			});
		</script>
    </body>
</html>