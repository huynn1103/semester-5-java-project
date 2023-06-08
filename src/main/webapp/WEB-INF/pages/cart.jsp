<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8">
		<title>My cart</title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<meta name="description" content="">
		<!--[if ie]><meta content='IE=8' http-equiv='X-UA-Compatible'/><![endif]-->
		<!-- bootstrap -->
		<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">      
		<link href="bootstrap/css/bootstrap-responsive.min.css" rel="stylesheet">		
		<link href="themes/css/bootstrappage.css" rel="stylesheet"/>
		<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.14.0/css/all.min.css">
		
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
			<script src="themes/js/respond.min.js"></script>
		<![endif]-->
		<style>
			#checkout {
				background-color: #eb4800;
				color: white;
				padding: 7.5px 15px;
				border-radius: 5px;
				font-size: 14px;
				border-style: none;
			}
			#checkout:hover {
				background-color: #ff6f21;
			}
			.delete {
				display: none;
			}
			.fas, .far {
				cursor: pointer;
			}
		</style>
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
						</ul>
					</nav>
				</div>
			</section>				
			<section class="main-content">				
				<div class="row">
					<div class="span12">					
						<h4 class="title"><span class="text"><strong>My</strong> Cart</span></h4>
						<table class="table table-striped">
							<thead>
								<tr>
									<th><input type="checkbox" value="select-all" name="select-all" id="select-all" checked="checked"/></th>
									<th>Image</th>
									<th>Product Name</th>
									<th>Minus</th>
									<th>Quantity</th>
									<th>Plus</th>
									<th>Unit Price</th>
									<th>Total</th>
									<th>Remove</th>
								</tr>
							</thead>
							<tbody>
							<c:forEach items="${lstCarts}" var="cart">
								<tr id="OrderDetailID=${cart.getOrderDetailID()}">
									<td><input type="checkbox" name="select" value="${cart.getOrderDetailID()}"checked="checked"/></td>
									<td><a href=""><img width="100px" alt="" src="${cart.getImage()}"></a></td>
									<td>${cart.getFoodName()}</td>
									<td><i class="fas fa-minus"></i></td>
									<td class="amount">${cart.getAmount()}</td>
									<td><i class="fas fa-plus"></i></td>
									<td>${cart.getPrice()}</td>
									<td>${cart.getTotal()}</td>
									<td><i class="far fa-trash-alt"></i></td>
								</tr>
							</c:forEach>
								<tr>
									<td colspan="7"></td>
									<td id="total" colspan="2">Total: <strong>${totalMoneyOfCart} VND</strong></td>
								</tr>	  
							</tbody>
						</table>
						<p class="buttons center">				
							<button id="checkout">Checkout</button>
						</p>					
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
			$('#checkout').on('click', function(e) {
				let queryString = "";
				$("input[name='select']:checked").each(function() {
					if (queryString == "") {
						queryString += "OrderDetailID[]=" + $(this).val();
					} else {
						queryString += "&OrderDetailID[]=" + $(this).val();
					}
				})
				window.location.assign("http://localhost:8080/RestaurantManagement/Checkout?" + queryString);
			})
		</script>
		<script>
			function updateMoneyOfCart() {
				const orderDetailIDs = [];
				$("input[name='select']:checked").each(function() {
					orderDetailIDs.push($(this).val());
				})
				var jsonOrderDetailIDs = JSON.stringify(orderDetailIDs);
				
				$.ajax({
		            type: "POST",
		            url: "UpdateMoneyOfCart",
		            data: "jsonOrderDetailIDs=" + jsonOrderDetailIDs,
		            dataType: "json",
		            success: function(responseText) {
		            	$('#total > strong').text(responseText);
	                }
	            });
			}
		</script>
		<script>
			$('#select-all').click(function(event) {   
			    if (this.checked) {
			        $(':checkbox').each(function() {
			            this.checked = true;
			        });
			        updateMoneyOfCart();
			    } else {
			        $(':checkbox').each(function() {
			            this.checked = false;
			        });
			        $('#total > strong').text('0 VND');
			    }
			});
			$("input[name='select']").click(updateMoneyOfCart);
		</script>
		<script>
			$('.fa-plus').click(function(event) {
				let amount = $(this).parent().prev();
				let total = $(this).parent().next().next();
				let id = $(this).parent().parent().attr('id');
				let row = $(this).parent().parent();
				let orderDetailID = id.substring(id.indexOf('=') + 1);
				$.ajax({
		            type: "POST",
		            url: "IncreaseAmount",
		            data: "orderDetailID=" + orderDetailID,
		            success: function(responseText) {
		            	const response = responseText.split(" ");
		            	$('.user-menu > li > #cart').text('Cart (' + response[0] + ')');
		            	amount.text(response[1]);
		            	total.text(response[2]);
		            	updateMoneyOfCart();
		            }
		    	});
			});
		</script>
		<script>
			$('.fa-minus').click(function(event) {
				let amount = $(this).parent().next();
				let total = $(this).parent().next().next().next().next();
				let id = $(this).parent().parent().attr('id');
				let row = $(this).parent().parent();
				let orderDetailID = id.substring(id.indexOf('=') + 1);
				$.ajax({
		            type: "POST",
		            url: "DecreaseAmount",
		            data: "orderDetailID=" + orderDetailID,
		            success: function(responseText) {
		            	if (responseText != "") {
		            		const response = responseText.split(" ");
			            	$('.user-menu > li > #cart').text('Cart (' + response[0] + ')');
			            	amount.text(response[1]);
			            	total.text(response[2]);
			            	updateMoneyOfCart();
		            	}
		            }
		    	});
			});
		</script>
		<script>
			$('.fa-trash-alt').click(function(event) {
				let id = $(this).parent().parent().attr('id');
				let row = $(this).parent().parent();
				row.children().first().children().prop('checked', false);
				let orderDetailID = id.substring(id.indexOf('=') + 1);
				row.addClass("delete");
				$.ajax({
		            type: "POST",
		            url: "DeleteOrderDetail",
		            data: "orderDetailID=" + orderDetailID,
		            success: function(responseText) {
		            	$('.user-menu > li > #cart').text('Cart (' + responseText + ')');
		            	updateMoneyOfCart();
		            }
		    	});
			});
		</script>
    </body>
</html>