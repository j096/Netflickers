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

//버튼 클릭 시 페이지 이동 공통 함수
//request url(이동할 페이지)
function goTo(url){
	window.location.href=url;
}

