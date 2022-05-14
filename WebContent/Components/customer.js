$(document).ready(function()
{ 
if ($("#alertSuccess").text().trim() == "") 
 { 
 $("#alertSuccess").hide(); 
 } 
 $("#alertError").hide(); 
}); 
$(document).on("click", "#btnSave", function(event)
{ 
// Clear alerts---------------------
 $("#alertSuccess").text(""); 
 $("#alertSuccess").hide(); 
 $("#alertError").text(""); 
 $("#alertError").hide(); 
// Form validation-------------------
var status = validatePaymentForm(); 
if (status != true) 
 { 
 $("#alertError").text(status); 
 $("#alertError").show(); 
 return; 
 } 
// If valid------------------------
var type = ($("#hidPaymentIDSave").val() == "") ? "POST" : "PUT"; 
 $.ajax( 
 { 
 url : "PaymentAPI", 
 type : type, 
 data : $("#formPayment").serialize(), 
 dataType : "text", 
 complete : function(response, status) 
 { 
 onPaymentSaveComplete(response.responseText, status); 
 } 
 }); 
});

function onPaymentSaveComplete(response, status)
{ 
if (status == "success") 
 { 
 var resultSet = JSON.parse(response); 
 if (resultSet.status.trim() == "success") 
 { 
 $("#alertSuccess").text("Successfully saved."); 
 $("#alertSuccess").show(); 
 $("#divPaymentGrid").html(resultSet.data); 
 } else if (resultSet.status.trim() == "error") 
 { 
 $("#alertError").text(resultSet.data); 
 $("#alertError").show(); 
 } 
 } else if (status == "error") 
 { 
 $("#alertError").text("Error while saving."); 
 $("#alertError").show(); 
 } else
 { 
 $("#alertError").text("Unknown error while saving.."); 
 $("#alertError").show(); 
 } 
 $("#hidPaymentIDSave").val(""); 
 $("#formItem")[0].reset(); 
}

$(document).on("click", ".btnUpdate", function(event)
{ 
$("#hidPaymentIDSave").val($(this).data("paymentid")); 
 $("#Name").val($(this).closest("tr").find('td:eq(0)').text()); 
 $("#Email").val($(this).closest("tr").find('td:eq(1)').text()); 
 $("#Address").val($(this).closest("tr").find('td:eq(2)').text()); 
 $("#ContactNumber").val($(this).closest("tr").find('td:eq(3)').text()); 
 $("#CardName").val($(this).closest("tr").find('td:eq(4)').text()); 
 $("#CreditCardNumber").val($(this).closest("tr").find('td:eq(5)').text()); 
 $("#ExpiryDate").val($(this).closest("tr").find('td:eq(6)').text());
 $("#CVV").val($(this).closest("tr").find('td:eq(7)').text());  
});

$(document).on("click", ".btnRemove", function(event)
{ 
 $.ajax( 
 { 
 url : "PaymentAPI", 
 type : "DELETE", 
 data : "PaymentID=" + $(this).data("paymentid"),
 dataType : "text", 
 complete : function(response, status) 
 { 
 onPaymentDeleteComplete(response.responseText, status); 
 } 
 }); 
});

function onPaymentDeleteComplete(response, status)
{ 
if (status == "success") 
 { 
 var resultSet = JSON.parse(response); 
 if (resultSet.status.trim() == "success") 
 { 
 $("#alertSuccess").text("Successfully deleted."); 
 $("#alertSuccess").show(); 
 $("#divPaymentGrid").html(resultSet.data); 
 } else if (resultSet.status.trim() == "error") 
 { 
 $("#alertError").text(resultSet.data); 
 $("#alertError").show(); 
 } 
 } else if (status == "error") 
 { 
 $("#alertError").text("Error while deleting."); 
 $("#alertError").show(); 
 } else
 { 
 $("#alertError").text("Unknown error while deleting.."); 
 $("#alertError").show(); 
 } 
}
function validatePaymentForm() 
{
	//NAME
	if ($("#Name").val().trim() == "")
	{
	return "Insert Name.";
	}
	
	// EMAIL
	if ($("#Email").val().trim() == "")
	{
	return "Insert Email.";
	}
	
	// ADDRESS-------------------------------
	if ($("#Address").val().trim() == "")
	{
	return "Insert Address.";
	}
	
	// CONTACT NUMBER-------------------------------
	if ($("#ContactNumber").val().trim() == "")
	{
	return "Insert Contact Number.";
	}
	
	// is numerical value
	var tmpContactNumber = $("#ContactNumber").val().trim();
	if (!$.isNumeric(tmpContactNumber))
	{
	return "Insert a numerical value for Contact Number.";
	}
	
	// NAME ON CARD-------------------------------
	if ($("#CardName").val().trim() == "")
	{
	return "Insert Name on card.";
	}

	// CREDIT CARD NUMBER-------------------------------
	if ($("#CreditCardNumber").val().trim() == "")
	{
	return "Insert Credit Card Number.";
	}

	// EXPIRY DATE-------------------------------
	if ($("#ExpiryDate").val().trim() == "")
	{
	return "Insert Expiry date.";
	}

	// CVV------------------------
	if ($("#CVV").val().trim() == "")
	{
	return "Insert CVV.";
	}
	
	// is numerical value
	var tmpCVV = $("#CVV").val().trim();
	if (!$.isNumeric(tmpCVV))
	{
	return "Insert a numerical value for CVV.";
	}
return true; 
}
