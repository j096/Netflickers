$(document).ready(function(){
	var postId = $('#postId').val();
	loadHtml('comment-list','/comment/list/'+postId+'?page=0',setCommentPaging);
});