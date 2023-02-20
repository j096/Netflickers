function postRequest(url, data, method){
	    $.ajax({
        type: method,
        url: url,
        dataType: 'json',
        contentType: 'application/json; charset-utf-8',
        data: JSON.stringify(data),
        statusCode:{
			201:function(result){
				alert(result.message);
				window.location.href=result.url;
			}
		}
    }); 
}