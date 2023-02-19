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

function postForm(formId, url, msg, href){
	
	var data = $('#'+formId).serializeObject();
	
	
	postRequest('/post/save', data, msg, href);
}

