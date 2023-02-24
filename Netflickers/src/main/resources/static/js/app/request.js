function postRequest(url, data, method, updateId,callback){
	    $.ajax({
        type: method,
        url: url,
        dataType: 'json',
        contentType: 'application/json; charset-utf-8',
        data: JSON.stringify(data),
        statusCode:{
			200:function(result){
				alert(result.message);
				if(updateId){
					loadHtml(updateId,result.url,callback);
				}else{
					window.location.href=result.url;
				}				
			},
			201:function(result){
				alert(result.message);
				if(updateId){
					loadHtml(updateId,result.url,callback);
				}else{
					window.location.href=result.url;
				}
			}
		}
    }); 
}

