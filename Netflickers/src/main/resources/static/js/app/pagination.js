const prev ='<li id="prev" class="page-item">'
      +'<a class="page-link" onClick="setPrevNext(-1)" aria-label="Previous">'
       + '<span aria-hidden="true">&laquo;</span>'
      +'</a>'
    +'</li>';
    
const next = '<li id="next" class="page-item">'
      +'<a class="page-link" onClick="setPrevNext(1)" aria-label="Next">'
       + '<span aria-hidden="true">&raquo;</span>'
      +'</a>'
    +'</li>';

var now,offset,size,totalContent,totalNumber,startNum,endNum,hasNext,hasPrev;//서버 관리 변수

$(document).ready(function(){
	offset = parseInt($('#offset').val());
	totalContent = parseInt($('#totalContent').val());
	totalNumber = parseInt($('#totalNumber').val());
	now = parseInt($('#now').val());
	startNum = parseInt($('#startNum').val());
	endNum = parseInt($('#endNum').val());
	hasNext = $('#hasNext').val();
	hasPrev = $('#hasPrev').val();

	setPagingNum();
});


function setPrevNext(check){
	
	startNum = startNum+check;
	endNum = endNum+check;
	now = now+check;
	
	var query='?page='+now+'&startNum='+startNum+'&endNum='+endNum+'&now='+now;
	
	
	window.location.href = '/post/list'+query;
	
	


}

function setPagingNum(){
	
	var totalHtml = prev;	

	for(var i=startNum;i<=endNum;i++){
		var num = i+1;
		var query='?page='+i+'&startNum='+startNum+'&endNum='+endNum+'&now='+now;
		totalHtml += '<li class="page-item" id="page'+i+'"><a class="page-link" href="/post/list'+query+'">'+num+'</a></li>';
	}
	totalHtml += next;
	$('#pagination').html(totalHtml);
	if(hasNext =='false') $('#pagination  #next').attr('class','page-item disabled');
	if(hasPrev == 'false') $('#pagination  #prev').attr('class','page-item disabled');
	$('#pagination  #page'+now+' a').removeAttr('href');
	$('#pagination  #page'+now).attr('class','page-item active');	
}
