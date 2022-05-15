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
var status = validateCustomerForm(); 
if (status != true) 
 { 
 $("#alertError").text(status); 
 $("#alertError").show(); 
 return; 
 } 
// If valid------------------------
var type = ($("#hidCustomerIDSave").val() == "") ? "POST" : "PUT"; 
 $.ajax( 
 { 
 url : "CustomerAPI", 
 type : type, 
 data : $("#formCustomer").serialize(), 
 dataType : "text", 
 complete : function(response, status) 
 { 
 onCustomerSaveComplete(response.responseText, status); 
 } 
 }); 
});

function onCustomerSaveComplete(response, status)
{ 
if (status == "success") 
 { 
 var resultSet = JSON.parse(response); 
 if (resultSet.status.trim() == "success") 
 { 
 $("#alertSuccess").text("Successfully saved."); 
 $("#alertSuccess").show(); 
 $("#divCustomerGrid").html(resultSet.data); 
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
 $("#hidCustomerIDSave").val(""); 
 $("#formItem")[0].reset(); 
}

$(document).on("click", ".btnUpdate", function(event)
{ 
$("#hidCustomerIDSave").val($(this).data("cID")); 
 $("#cus_Name").val($(this).closest("tr").find('td:eq(0)').text()); 
 $("#cus_Nic").val($(this).closest("tr").find('td:eq(1)').text()); 
 $("#cus_addr").val($(this).closest("tr").find('td:eq(2)').text()); 
 $("#cus_pnumber").val($(this).closest("tr").find('td:eq(3)').text()); 
 $("#cus_email").val($(this).closest("tr").find('td:eq(4)').text()); 
 $("#cus_pwd").val($(this).closest("tr").find('td:eq(5)').text());   
});

$(document).on("click", ".btnRemove", function(event)
{ 
 $.ajax( 
 { 
 url : "CustomerAPI", 
 type : "DELETE", 
 data : "cID=" + $(this).data("customerid"),
 dataType : "text", 
 complete : function(response, status) 
 { 
 onCustomerDeleteComplete(response.responseText, status); 
 } 
 }); 
});

function onCustomerDeleteComplete(response, status)
{ 
if (status == "success") 
 { 
 var resultSet = JSON.parse(response); 
 if (resultSet.status.trim() == "success") 
 { 
 $("#alertSuccess").text("Successfully deleted."); 
 $("#alertSuccess").show(); 
 $("#divCustomerGrid").html(resultSet.data); 
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
function validateCustomerForm() 
{
	//CUSTOMER NAME
	if ($("#cus_Name").val().trim() == "")
	{
	return "Insert Name.";
	}
	
	//CUSTOMER NIC
	if ($("#cus_Nic").val().trim() == "")
	{
	return "Insert NIC.";
	}
	
	//CUSTOMER ADDRESS-------------------------------
	if ($("#cus_addr").val().trim() == "")
	{
	return "Insert Address.";
	}
	
	// is numerical value
	var tmpContactNumber = $("#cus_pnumber").val().trim();
	if (!$.isNumeric(tmpContactNumber))
	{
	return "Insert a numerical value for Contact Number.";
	}
	
	//CUSTOMER EMAIL-------------------------------
	if ($("#cus_email").val().trim() == "")
	{
	return "Insert Mail Address.";
	}
	
	//CUSTOMER PWD-------------------------------
	if ($("#cus_pwd").val().trim() == "")
	{
	return "Insert Password.";
	}
return true; 
}
