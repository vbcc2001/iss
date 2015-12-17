$("#btn_enter_doc").click(function(e){
	var doc_id = localStorage.getItem("doc_id");
	if(doc_id==null){
		$("#index").hide();
		$("#reg_doc").show();
	}else{
		$("#index").hide();
		$("#reg_doc_pat").show();
	}
	var content ={"head":{"userID":"","sessionID":"","funcNO":"000004"},"content":{"click_content":"医生使用"}} ;
	$.post("/action/iss/http/action/FunctionAction", { "jsonContent": JSON.stringify(content) },function(result){}, "json");
});
$("#btn_enter_pat").click(function(e){
	var pat_id = localStorage.getItem("pat_id");
	if(pat_id==null){
		$("#index").hide();
		$("#reg_pat").show();
	}else{
		$("#index").hide();
		$("#main").show();
	}
	var content ={"head":{"userID":"","sessionID":"","funcNO":"000004"},"content":{"click_content":"患者使用"}} ;
	$.post("/action/iss/http/action/FunctionAction", { "jsonContent": JSON.stringify(content) },function(result){}, "json");
});
$("#btn_reg_doc").click(function(e){
	if($("#doc_name").val()=="") { 
		$('.modal-body').html("姓名不能为空！");
		$('#myModal').modal('show');
		return;
	}
	var content ={"head":{"userID":"","sessionID":"","funcNO":"000001"},"content":{"name":"880000000"}} ;
	content.content["doc_name"] = $("#doc_name").val();
	content.content["doc_hospital"] = $("#doc_hospital").val();
	content.content["doc_room"] = $("#doc_room").val();
	content.content["doc_job"] =$("#doc_job").val();
	content.content["doc_phone"] = $("#doc_phone").val();
	content.content["doc_email"] = $("#doc_email").val();
	content.content["doc_tel"] = $("#doc_tel").val();	
	content.content["weixin_open_id"] = $("#weixin_open_id").val();
	var $btn = $(this).button('处理中');
	$.post("/action/iss/http/action/FunctionAction", { "jsonContent": JSON.stringify(content) },
	   function(result){
	    	$btn.button('下一步')
			if(result.head.errorNo==""){
				localStorage.setItem("doc_id",result.list[0].id);
				$("#reg_doc").hide();
				$("#reg_doc_pat").show();
			}else{
				$('.modal-body').html(result.head.errorInfo);
				$('#myModal').modal('show');
			}
	 }, "json");
});
$("#btn_reg_doc").click(function(e){
	if($("#doc_name").val()=="") { 
		$('.modal-body').html("姓名不能为空！");
		$('#myModal').modal('show');
		return;
	}
	var content ={"head":{"userID":"","sessionID":"","funcNO":"000001"},"content":{"name":"880000000"}} ;
	content.content["doc_name"] = $("#doc_name").val();
	content.content["doc_hospital"] = $("#doc_hospital").val();
	content.content["doc_room"] = $("#doc_room").val();
	content.content["doc_job"] =$("#doc_job").val();
	content.content["doc_phone"] = $("#doc_phone").val();
	content.content["doc_email"] = $("#doc_email").val();
	content.content["doc_tel"] = $("#doc_tel").val();	
	content.content["weixin_open_id"] = $("#weixin_open_id").val();
	var $btn = $(this).button('处理中');
	$.post("/action/iss/http/action/FunctionAction", { "jsonContent": JSON.stringify(content) },
	   function(result){
	    	$btn.button('下一步')
			if(result.head.errorNo==""){
				localStorage.setItem("doc_id",result.list[0].id);
				$("#reg_doc").hide();
				$("#reg_doc_pat").show();
			}else{
				$('.modal-body').html(result.head.errorInfo);
				$('#myModal').modal('show');
			}
	 }, "json");
});
$("#btn_reg_pat").click(function(e){
	if($("#pat_name").val()=="") { 
		$('.modal-body').html("姓名不能为空！");
		$('#myModal').modal('show');
		return;
	}
	if(isNaN($("#pat_age").val())) { 
		$('.modal-body').html("年龄格式错误");
		$('#myModal').modal('show');
		return;
	}
	var content ={"head":{"userID":"","sessionID":"","funcNO":"000002"},"content":{"name":"880000000"}} ;
	content.content["pat_name"] = $("#pat_name").val();
	content.content["pat_sex"] = $('input[name="pat_sex"]:checked ').val();
	content.content["pat_age"] = $("#pat_age").val();
	content.content["pat_add"] = $("#pat_add").val();
	content.content["pat_phone"] = $("#pat_phone").val();
	content.content["pat_ill_date"] = $("#pat_ill_date").val();
	content.content["pat_ill_type"] = $("#pat_ill_type").val();
	content.content["pat_hospital"] = $("#pat_hospital").val();
	content.content["weixin_open_id"] = $("#weixin_open_id").val();
	var $btn = $(this).button('处理中');
	$.post("/action/iss/http/action/FunctionAction", { "jsonContent": JSON.stringify(content) },
	   function(result){
	    	$btn.button('下一步')
			if(result.head.errorNo==""){
				localStorage.setItem("pat_id",result.list[0].id);
				$("#reg_pat").hide();	
				$("#main").show();
			}else{
				$('.modal-body').html(result.head.errorInfo);
				$('#myModal').modal('show');
			}
	 }, "json");
});