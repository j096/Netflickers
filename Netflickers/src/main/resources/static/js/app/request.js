function postRequest(url, data, method, updateId,callback){
	    $.ajax({
        type: method,
        url: url,
        dataType: 'json',
        contentType: 'application/json; charset-utf-8',
        data: JSON.stringify(data),
        success: function(result){
				alert(result.message);
				if(updateId){
					loadHtml(updateId,result.url,callback);
				}else{
						window.location.href=result.url;
				}

		},
		error: function(jqXHR){
		console.log(jqXHR);
//			if(jqXHR.responseJSON.message)
//			var msg = jqXHR.responseJSON.message;
//			var url = jqXHR.responseJSON.url;
//			alert(msg);
//			if(url)
//				window.location.href=result.url;
				
		}
			
    }); 
}


