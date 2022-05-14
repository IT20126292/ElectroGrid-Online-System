<%@page import="model.Bill"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Bill</title>
<link rel="stylesheet" href="View/bootstrap.min.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css" integrity="sha512-KfkfwYDsLkIlwQp6LFnl8zNdLGxu9YAA1QvwINks4PhcElQSvqcyVLLD9aMhXd13uQjoXtEKNosOWaZqXgel0g==" crossorigin="anonymous" referrerpolicy="no-referrer" />
<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/bill.js"></script>
</head>
<body>
	<div class="container-fluid">
		<div class="jumbotron bg-dark text-light">
			<center>
			<h1 style="font-family:Fantasy; margin-top:5px" class="p-2 mt-2">ElectroGrid Systems</h1>
			<h3 style="font-family:courier;" class="p-2 mt-2">Bill Management</h3>
			</center>
			<div class="navigation">
				<ul class="nav justify-content-center nav-pills nav-fill">
					<li class="nav-item"><a class="nav-link" href="home.jsp">Home</a></li>
					<li class="nav-item"><a class="nav-link active" href="bill.jsp">Bill Management</a></li>
					<li class="nav-item"><a class="nav-link" href="customer.js">Customer</a>
					</li>
					<li class="nav-item"><a class="nav-link" href="manage.js">Customer Management</a></li>
					<li class="nav-item"><a class="nav-link" href="#">Help</a></li>
				</ul>
			</div>
		</div>
		<div class="row">
			<div class="col-lg-4">
				<div class="card bg-light mb-3 border-danger">
					<div class="card-header bg-dark text-light">
						<h4>Bill</h4>
					</div>
					<div class="card-body ">
						
						<form id="formBill" name="formBill" method="post" action="bill.jsp" class="col">
					
					
					Bill Name: 
							<select id="billName" name="billName" class="form-control form-control-sm mb-3">
							<option selected></option>
							<option value="Home">Home</option>
							 <option value="Official">Official</option>
							<option value="Governement">Governement</option>
							<option value="other">Other</option>
							  </select>	
					
					
				    Bill Amount:
					<input id="billAmount" name="billAmount" type="number" class="form-control form-control-sm mt-2" type="number"> <br> 
					Bill Date: 
					<input id="billDate" name="billDate" type="date" class="form-control form-control-sm" type="date"> <br>
					No Of Total Units: 
					<input id="NoOfTunits" name="NoOfTunits" type="number" class="form-control form-control-sm"> <br> 
				    Bill Arrears:
					<input id="BillAr" name="BillAr" type="text" class="form-control form-control-sm"> <br> 
					<input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary"> 
					<input type=" " id="hidBillIDSave" name="hidBillIDSave" value="">
				</form>

						
					</div>
					<div class="card-footer text-light">
						Status:<br/>
						<div id="alertSuccess" class="alert alert-success"></div>
						<div id="alertError" class="alert alert-danger"></div>
					</div>
				</div>
			</div>
			<div class="col-lg-8">
				<div class="text-center">
					<h4>Recent Bill History Status</h4>
					<hr>
					<br>
				</div>
				<div class="card">
					<div class="table-responsive-md border-danger">
					<div id="divBillGrid">

						<%
						Bill billObj = new Bill();
								out.print(billObj.readBill());
					%>
						</div>
					</div>
					<div class="">
						<nav aria-label="...">
							<ul class="pagination justify-content-center">
								<li class="page-item"><a class="page-link" href="#"
									tabindex="-1">Previous</a></li>
								<li class="page-item"><a class="page-link" href="#">3</a></li>
								<li class="page-item"><a class="page-link" href="#">2</a></li>
								<li class="page-item"><a class="page-link" href="#">1</a></li>
								<li class="page-item disabled"><a class="page-link"
									href="#">Next</a></li>
							</ul>
						</nav>
					</div>
				</div>
			</div>
		</div>
		<footer class="bg-dark text-center text-white">
  <!-- Grid container -->
  <div class="container p-4 pb-0">
    <!-- Section: Social media -->
    <section class="mb-4">
      <!-- Facebook -->
      <a class="btn btn-outline-light btn-floating m-1" href="#!" role="button"
        ><i class="fab fa-facebook-f"></i
      ></a>

      <!-- Twitter -->
      <a class="btn btn-outline-light btn-floating m-1" href="#!" role="button"
        ><i class="fab fa-twitter"></i
      ></a>

      <!-- Google -->
      <a class="btn btn-outline-light btn-floating m-1" href="#!" role="button"
        ><i class="fab fa-google"></i
      ></a>

      <!-- Instagram -->
      <a class="btn btn-outline-light btn-floating m-1" href="#!" role="button"
        ><i class="fab fa-instagram"></i
      ></a>

      <!-- Linkedin -->
      <a class="btn btn-outline-light btn-floating m-1" href="#!" role="button"
        ><i class="fab fa-linkedin-in"></i
      ></a>

      <!-- Github -->
      <a class="btn btn-outline-light btn-floating m-1" href="#!" role="button"
        ><i class="fab fa-github"></i
      ></a>
    </section>
    <!-- Section: Social media -->
  </div>
  <!-- Grid container -->

  <!-- Copyright -->
  <div class="text-center p-3" style="background-color: rgba(0, 0, 0, 0.2);">
    Â© 2020 Copyright:
    <a class="text-white" href="https://mdbootstrap.com/">MDBootstrap.com</a>
  </div>
  <!-- Copyright -->
</footer>
	</div>
</body>
</html>
