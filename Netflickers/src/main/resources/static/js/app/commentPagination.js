const prev ='<li class="page-item" id="prev">'
      +'<a class="page-link" onClick="setPrevNext(-1)" aria-label="Previous">'
       + '<span aria-hidden="true">&laquo;</span>'
      +'</a>'
    +'</li>';
    
const next = '<li class="page-item" id="next">'
      +'<a class="page-link" onClick="setPrevNext(1)" aria-label="Next">'
       + '<span aria-hidden="true">&raquo;</span>'
      +'</a>'
    +'</li>';

var now,offset,size,totalContent,totalNumber,startNum,endNum,hasNext,hasPrev;
var postId;

function setCommentPaging(){
	offset = parseInt($('#offset').val());
	totalContent = parseInt($('#totalContent').val());
	totalNumber = parseInt($('#totalNumber').val());
	now = parseInt($('#now').val());
	startNum = parseInt($('#startNum').val());
	endNum = parseInt($('#endNum').val());
	hasNext = $('#hasNext').val();
	hasPrev = $('#hasPrev').val();
	
	postId = $('#postId').val();
	
	setPagingNum();
}


function setPrevNext(check){
	
	startNum = startNum+check;
	endNum = endNum+check;
	now = now+check;
	
	var query='?page='+now+'&startNum='+startNum+'&endNum='+endNum+'&now='+now;
	
	
	window.location.href = '/comment/list/'+postId+query;

}

function setPagingNum(){
	
	var totalHtml = prev;	
	
	for(var i=startNum;i<=endNum;i++){
		var num = i+1;
		var query='?page='+i+'&startNum='+startNum+'&endNum='+endNum+'&now='+now;
		totalHtml += '<li class="page-item" id="page'+i+'"><a class="page-link" onClick="loadHtml(\'commnet-list\',\'comment/list/'+postId+query+'\')">'+num+'</a></li>';
	}
	totalHtml += next;
	$('#pagination').html(totalHtml);
	if(hasNext =='false') $('#pagination  #next').attr('class','page-item disabled');
	if(hasPrev == 'false') $('#pagination  #prev').attr('class','page-item disabled');
	$('#pagination  #page'+now+' a').removeAttr('href');
	$('#pagination  #page'+now).attr('class','page-item active');	
}