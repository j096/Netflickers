document.write('<script type="text/javascript" src="/js/app/request.js"></script>');


$.fn.serializeObject = function()

{

   var o = {};

   var a = this.serializeArray();

   $.each(a, function() {

       if (o[this.name]) {

           if (!o[this.name].push) {

               o[this.name] = [o[this.name]];

           }

           o[this.name].push(this.value || '');

       } else {

           o[this.name] = this.value || '';

       }

   });

   return o;

};

//form 관련 저장/수정/삭제 리퀘스트 공통 함수
//form id, request url, response msg, response href
function postForm(formId, url, method){
	
	var data = $('#'+formId).serializeObject();
	
	postRequest(url, data, method);
}

function postUpdateForm(formId, updateId, url, method){
	
	var data = $('#'+formId).serializeObject();
	
	$('#'+formId)[0].reset();
	
	dynamicUpdateRequest(url,data,method, updateId);
	
	
}

//버튼 클릭 시 페이지 이동 공통 함수
//request url(이동할 페이지)
function goTo(url){
	window.location.href=url;
}


function updateToModifyForm(id){
	var commentId = $("#"+id+" #id").val();
	var writer = $("#"+id+" #writer").val();
	var content = $("#"+id+" #content").text();
	
	var origin = $("#"+id).html();
	
	var commentForm = '<div>'
    	+'<form id="form-comment'+commentId+'">'
			+'<div class="form-group">'
              +'<label for="writer">작성자</label>'
              +'<input type="text" class="form-control" id="writer" name="writer" value="'+writer+'" readonly>'
       		+'</div>'    	
	        +'<div class="form-group">'
	          +'<textarea class="form-control form-control-lg" id="content" name="content" placeholder="댓글을 입력하세요">'+content+'</textarea>'
	        +'</div>'    	
    	+'</form>'
		+'<div align="right">'
	    	+'<button type="button" class="btn btn-primary" id="btn-comment-save" onClick="postForm(\'form-comment'+commentId+'\',\'/comment/update/'+commentId+'\',\'PUT\')">등록</button>'
	    +'</div>'
    +'</div>';

	$("#"+id).html(commentForm);
}

function resetHtml(id){
	
}

