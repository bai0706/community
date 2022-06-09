$(function(){
	$(".follow-btn").click(follow);
});

function follow() {
	var btn = this; //获取当前按钮
	if($(btn).hasClass("btn-info")) { // 当前按钮是蓝色样式
		// 关注TA
		$.post(
			CONTEXT_PATH + "/follow",
			{"entityType":3,"entityId":$(btn).prev().val()},   //定义用户实体类型是3；
			//$(btn).prev().val()  指获得当前节点的前一个节点的值
			function(data) {
				data = $.parseJSON(data);  // 转成json对象
				if(data.code == 0) {
					window.location.reload(); //浏览器刷新
				} else {
					alert(data.msg);
				}
			}
		);
		// $(btn).text("已关注").removeClass("btn-info").addClass("btn-secondary");
	} else {
		// 当前按钮不是蓝色样式,取消关注
		$.post(
			CONTEXT_PATH + "/unfollow",
			{"entityType":3,"entityId":$(btn).prev().val()},
			function(data) {
				data = $.parseJSON(data);
				if(data.code == 0) {
					window.location.reload();
				} else {
					alert(data.msg);
				}
			}
		);
		//$(btn).text("关注TA").removeClass("btn-secondary").addClass("btn-info");
	}
}
