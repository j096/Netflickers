const prev ='<li class="page-item">'
      +'<a class="page-link" onClick="setNowPageNum(-1)" aria-label="Previous">'
       + '<span aria-hidden="true">&laquo;</span>'
      +'</a>'
    +'</li>';
    
const next = '<li class="page-item">'
      +'<a class="page-link" onClick="setNowPageNum(1)" aria-label="Next">'
       + '<span aria-hidden="true">&raquo;</span>'
      +'</a>'
    +'</li>';

var now,offset,size,numberCount,totalCount;//서버 관리 변수
var startNum,endNum,url;

$(document).ready(function(){
	set();
});

function set(){
	offset = parseInt($('#offset').val());
	size = parseInt($('#size').val());
	numberCount = parseInt($('#numberCount').val());
	totalCount = parseInt($('#totalCount').val());
	now = parseInt($('#now').val());

	startNum = now - (now%offset);
	var postId = $('#postId').val();
	url = '/comment/list/'+postId+'?page='+now+'&size='+size;
	setPageNumHtml();	
}


function setNowPageNum(check){
	
	startNum = startNum+(check*offset);
	
	if(startNum < 0){
		startNum = 0;
		return;	
	}else if(startNum == numberCount-(numberCount%offset)){
		offset = numberCount%offset;
		
	}else if(startNum > numberCount-(numberCount%offset)){
		
		return;

	}
	
	
	
	
	setPageNumHtml();

}

function setPageNumHtml(){
	endNum = numberCount<offset ? numberCount : startNum + offset;

	var totalHtml = prev;	
	for(var i=startNum;i<endNum;i++){
		var num = i+1;
		totalHtml += '<li class="page-item" id="page'+i+'"><a class="page-link" onClick="loadHtml(\'comment-list\',\''+url+'\')">'+num+'</a></li>';
	}
	totalHtml+= next;
	$('#pagination').html(totalHtml);
	$('#pagination  #page'+now+' a').removeAttr('onClick');
	$('#pagination  #page'+now).attr('class','page-item active');
	
}