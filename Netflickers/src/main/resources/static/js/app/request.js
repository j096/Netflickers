function postRequest(url, data){
	    $.ajax({
        type: 'POST',
        url: url,
        dataType: 'json',
        contentType: 'application/json; charset-utf-8',
        data: JSON.stringify(data),
        statusCode:{
			201:function(data){
				alert("글이 등록되었습니다.");
				window.location.href="/";
			}
		}
    }); 
}