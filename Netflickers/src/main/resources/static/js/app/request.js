function postRequest(url, data, msg, href){
	    $.ajax({
        type: 'POST',
        url: url,
        dataType: 'json',
        contentType: 'application/json; charset-utf-8',
        data: JSON.stringify(data),
        statusCode:{
			201:function(data){
				alert(msg);
				window.location.href=href;
			}
		}
    }); 
}