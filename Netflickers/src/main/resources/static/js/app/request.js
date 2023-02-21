function postRequest(url, data, method){
	    $.ajax({
        type: method,
        url: url,
        dataType: 'json',
        contentType: 'application/json; charset-utf-8',
        data: JSON.stringify(data),
        statusCode:{
			200:function(result){
				alert(result.message);
				window.location.href=result.url;				
			},
			201:function(result){
				alert(result.message);
				window.location.href=result.url;
			}
		}
    }); 
}


function dynamicUpdateRequest(url, data, method, updateId){
	
	$.ajax({
        type: method,
        url: url,
        dataType: 'json',
        contentType: 'application/json; charset-utf-8',
        data: JSON.stringify(data),
        statusCode:{
			200:function(result){
				return result;			
			},
			201:function(result){
				alert(result.message);
				$("#"+updateId).load(result.url);
			}
		}
    }); 
	
}