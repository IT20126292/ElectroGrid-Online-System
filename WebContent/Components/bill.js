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
var status = validateBillForm(); 
if (status != true) 
 { 
 $("#alertError").text(status); 
 $("#alertError").show(); 
 return; 
 } 
// If valid------------------------
var type = ($("#hidBillIDSave").val() == "") ? "POST" : "PUT"; 
 $.ajax( 
 { 
 url : "BillAPI", 
 type : type, 
 data : $("#formBill").serialize(), 
 dataType : "text", 
 complete : function(response, status) 
 { 
 onBillSaveComplete(response.responseText, status); 
 } 
 }); 
});

function onBillSaveComplete(response, status)
{ 
if (status == "success") 
 { 
 var resultSet = JSON.parse(response); 
 if (resultSet.status.trim() == "success") 
 { 
 $("#alertSuccess").text("Successfully saved."); 
 $("#alertSuccess").show(); 
 $("#divBillGrid").html(resultSet.data); 
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
 $("#hidBillIDSave").val(""); 
 $("#formItem")[0].reset(); 
}

$(document).on("click", ".btnUpdate", function(event)
{ 
$("#hidBillIDSave").val($(this).data("#billid")); 
 $("#billName").val($(this).closest("tr").find('td:eq(0)').text()); 
 $("#billAmount").val($(this).closest("tr").find('td:eq(1)').text()); 
 $("#billDate").val($(this).closest("tr").find('td:eq(2)').text()); 
 $("#NoOfTunits").val($(this).closest("tr").find('td:eq(3)').text()); 
 $("#BillAr").val($(this).closest("tr").find('td:eq(4)').text()); 
});

$(document).on("click", ".btnRemove", function(event)
{ 
 $.ajax( 
 { 
 url : "BillAPI", 
 type : "DELETE", 
 data : "billID=" + $(this).data("billid"),
 dataType : "text", 
 complete : function(response, status) 
 { 
 onBillDeleteComplete(response.responseText, status); 
 } 
 }); 
});

function onBillDeleteComplete(response, status)
{ 
if (status == "success") 
 { 
 var resultSet = JSON.parse(response); 
 if (resultSet.status.trim() == "success") 
 { 
 $("#alertSuccess").text("Successfully deleted."); 
 $("#alertSuccess").show(); 
 $("#divBillGrid").html(resultSet.data); 
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
function validateBillForm() 
{
	//NAME
	if ($("#billName").val().trim() == "")
	{
	return "Insert Bill Name.";
	}
	
	// EMAIL
	if ($("#billAmount").val().trim() == "")
	{
	return "Insert Bill Amount.";
	}
	
	// ADDRESS-------------------------------
	if ($("#billDate").val().trim() == "")
	{
	return "Insert Bill Date.";
	}
	
	// CONTACT NUMBER-------------------------------
	if ($("#NoOfTunits").val().trim() == "")
	{
	return "Insert Number of units.";
	}
	
	
	// NAME ON CARD-------------------------------
	if ($("#BillAr").val().trim() == "")
	{
	return "Insert Bill AR.";
	}
	
return true; 
}
