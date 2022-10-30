$('#searching').keyup(function() {
	var searching=$('#searching').val();
	console.log(searching);
	$("#container:contains('"+searching+"')").each(function() {
		var regex = new RegExp(searching,'gi');
		$(this).html( $(this).text().replace(regex, "<span><strong style=\"box-shadow: inset 0 -20px 0 #bfffa1;\">"+searching+"</strong></span>") );
	})
	
})