$("#btn_enter_doc").click(function(e){
	localStorage.setItem("flag","doc");
	var doc_id = localStorage.getItem("doc_id");
	if(doc_id==null){
		var content ={"head":{"userID":"","sessionID":"","funcNO":"000007"},"content":{"name":"880000000"}} ;
		content.content["weixin_open_id"] = getQueryString("openid");
		content.content["user_type"] = "doctor";
		$.post("/action/iss/http/action/FunctionAction", { "jsonContent": JSON.stringify(content) },
		   function(result){
				if(result.head.errorNo==""){
					localStorage.setItem("doc_id",result.list[0].id);
					$("#index").hide();
					$("#reg_doc_pat").show();
				}else{
					$("#index").hide();
					$("#reg_doc").show();
				}
		 }, "json");
	}else{
		$("#index").hide();
		$("#reg_doc_pat").show();
	}
	var content ={"head":{"userID":"","sessionID":"","funcNO":"000006"},"content":{"click_content":"医生使用"}} ;
	$.post("/action/iss/http/action/FunctionAction", { "jsonContent": JSON.stringify(content) },function(result){}, "json");
});
$("#btn_enter_pat").click(function(e){
	localStorage.setItem("flag","pat");
	var pat_id = localStorage.getItem("pat_id");
	if(pat_id==null){
		var content ={"head":{"userID":"","sessionID":"","funcNO":"000007"},"content":{"name":"880000000"}} ;
		content.content["weixin_open_id"] = getQueryString("openid");
		content.content["user_type"] = "patient";
		$.post("/action/iss/http/action/FunctionAction", { "jsonContent": JSON.stringify(content) },
		   function(result){
				if(result.head.errorNo==""){
					localStorage.setItem("pat_id",result.list[0].id);
					$("#index").hide();
					$("#main").show();
				}else{
					$("#index").hide();
					$("#reg_pat").show();
				}
		 }, "json");		
	}else{
		$("#index").hide();
		$("#main").show();
	}
	var content ={"head":{"userID":"","sessionID":"","funcNO":"000006"},"content":{"click_content":"患者使用"}} ;
	$.post("/action/iss/http/action/FunctionAction", { "jsonContent": JSON.stringify(content) },function(result){}, "json");
});
$("#btn_reg_doc").click(function(e){
	if($("#doc_name").val()=="") { 
		$('.modal-body').html("姓名不能为空！");
		$('#myModal').modal('show');
		return;
	}
	if($("#doc_hospital").val()=="") { 
		$('.modal-body').html("请填写医院！");
		$('#myModal').modal('show');
		return;
	}
	if($("#doc_phone").val()=="") { 
		$('.modal-body').html("电话不能为空！");
		$('#myModal').modal('show');
		return;
	}
	if($("#doc_phone").val()!=""){
		if(isNaN($("#doc_phone").val())) { 
			$('.modal-body').html("手机格式错误");
			$('#myModal').modal('show');
			return;
		}
		if($("#doc_phone").val()<=1) { 
			$('.modal-body').html("手机格式错误!");
			$('#myModal').modal('show');
			return;
		}
		if($("#doc_phone").val().length!=11) { 
			$('.modal-body').html("手机格式错误!");
			$('#myModal').modal('show');
			return;
		}
	}
	if($("#doc_email").val()=="") { 
		$('.modal-body').html("邮箱不能为空！");
		$('#myModal').modal('show');
		return;
	}
	if($("#doc_email").val()!=""){
		var reg = /^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/;
		if(!reg.test($("#doc_email").val())){
			$('.modal-body').html("邮箱格式错误!");
			$('#myModal').modal('show');
			return;
		}
	}
	var content ={"head":{"userID":"","sessionID":"","funcNO":"000001"},"content":{"name":"880000000"}} ;
	content.content["doc_name"] = $("#doc_name").val();
	content.content["doc_hospital"] = $("#doc_hospital").val();
	content.content["doc_room"] = $("#doc_room").val();
	content.content["doc_job"] =$("#doc_job").val();
	content.content["doc_phone"] = $("#doc_phone").val();
	content.content["doc_email"] = $("#doc_email").val();
	content.content["doc_tel"] = $("#doc_tel").val();	
	content.content["weixin_open_id"] = getQueryString("openid");
	var $btn = $(this).button('处理中');
	$.post("/action/iss/http/action/FunctionAction", { "jsonContent": JSON.stringify(content) },
	   function(result){
	    	$btn.button('下一步');
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
	if($('input[name="pat_sex"]:checked').val()==undefined) { 
		$('.modal-body').html("性别不能为空！");
		$('#myModal').modal('show');
		return;
	}
	if($("#pat_age").val()=="") { 
		$('.modal-body').html("年龄不能为空！");
		$('#myModal').modal('show');
		return;
	}	
	if($("#pat_age").val()!=""){	
		if(isNaN($("#pat_age").val())) { 
			$('.modal-body').html("年龄格式错误");
			$('#myModal').modal('show');
			return;
		}
		if($("#pat_age").val()<=0) { 
			$('.modal-body').html("年龄格式错误!");
			$('#myModal').modal('show');
			return;
		}
	}
	if($("#pat_phone").val()!=""){
		if(isNaN($("#pat_phone").val())) { 
			$('.modal-body').html("手机格式错误");
			$('#myModal').modal('show');
			return;
		}
		if($("#pat_phone").val()<=0) { 
			$('.modal-body').html("手机格式错误!");
			$('#myModal').modal('show');
			return;
		}
		if($("#pat_phone").val().length!=11) { 
			$('.modal-body').html("手机格式错误!");
			$('#myModal').modal('show');
			return;
		}
	}
	if($("#pat_ill_date").val()=="") { 
		$('.modal-body').html("受伤时间不能为空！");
		$('#myModal').modal('show');
		return;
	}
	if($("#pat_ill_type").val()=="") { 
		$('.modal-body').html("受伤类型不能为空！");
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
	content.content["weixin_open_id"] = getQueryString("openid");
	var $btn = $(this).button('处理中');
	$.post("/action/iss/http/action/FunctionAction", { "jsonContent": JSON.stringify(content) },
	   function(result){
	    	$btn.button('下一步');
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
$("#btn_reg_doc_pat").click(function(e){
	if($("#name").val()=="") { 
		$('.modal-body').html("姓名不能为空！");
		$('#myModal').modal('show');
		return;
	}
	if($('input[name="sex"]:checked').val()==undefined) { 
		$('.modal-body').html("性别不能为空！");
		$('#myModal').modal('show');
		return;
	}
	if($("#age").val()=="") { 
		$('.modal-body').html("年龄不能为空！");
		$('#myModal').modal('show');
		return;
	}
	if($("#age").val()!=""){	
		if(isNaN($("#age").val())) {
			$('.modal-body').html("年龄格式错误!");
			$('#myModal').modal('show');
			return;
		}
		if($("#age").val()<=0) { 
			$('.modal-body').html("年龄格式错误!");
			$('#myModal').modal('show');
			return;
		}	
	}
	if($("#phone").val()!=""){
		if(isNaN($("#phone").val())) { 
			$('.modal-body').html("手机格式错误");
			$('#myModal').modal('show');
			return;
		}
		if($("#phone").val()<=0) { 
			$('.modal-body').html("手机格式错误!");
			$('#myModal').modal('show');
			return;
		}
		if($("#phone").val().length!=11) { 
			$('.modal-body').html("手机格式错误!");
			$('#myModal').modal('show');
			return;
		}
	}	
	if($("#ill_date").val()=="") { 
		$('.modal-body').html("受伤时间不能为空！");
		$('#myModal').modal('show');
		return;
	}
	if($("#ill_type").val()=="") { 
		$('.modal-body').html("受伤类型不能为空！");
		$('#myModal').modal('show');
		return;
	}
	var content ={"head":{"userID":"","sessionID":"","funcNO":"000003"},"content":{"name":"880000000"}} ;
	content.content["doc_id"] = localStorage.getItem("doc_id");
	content.content["pat_name"] = $("#name").val();
	content.content["pat_sex"] = $('input[name="sex"]:checked ').val();
	content.content["pat_age"] = $("#age").val();
	content.content["pat_add"] = $("#add").val();
	content.content["pat_phone"] = $("#phone").val();
	content.content["pat_ill_date"] = $("#ill_date").val();
	content.content["pat_ill_type"] = $("#ill_type").val();
	var $btn = $(this).button('处理中');
	$.post("/action/iss/http/action/FunctionAction", { "jsonContent": JSON.stringify(content) },
	   function(result){
	    	$btn.button('下一步');
			if(result.head.errorNo==""){
				localStorage.setItem("doc_pat_id",result.list[0].id);
				$("#reg_doc_pat").hide();	
				$("#main").show();
			}else{
				$('.modal-body').html(result.head.errorInfo);
				$('#myModal').modal('show');
			}
	 }, "json");
});
function score(){
	//计算分值
	var arr =[0,0,0,0,0,0];
	var iss_score = 0;
	var ais_score = 0;	
	for(var i=0;i<c.length;i++){
		for(var j=0;j<c[i].c.length;j++){
			for(var k=0;k<c[i].c[j].c.length;k++){
				if(c[i].c[j].c[k].check){
					ais_score+= 1*c[i].c[j].c[k].val;
					if(arr[i]<c[i].c[j].c[k].val){
						arr[i]=c[i].c[j].c[k].val;
					}
				}
			}
		}
	}
	arr.sort(function(a,b){return b-a;});//从大到小排序
	iss_score = arr[0]*arr[0]+arr[1]*arr[1]+arr[2]*arr[2];
	$("#iss").html("ISS评分<br><span style='color:#000000;font-family: simsun;'>"+iss_score+"</span>");
	$("#ais").html("AIS评分<br><span style='color:#000000;font-family: simsun;'>"+ais_score+"</span>");
	if(iss_score<16){
		$("#rank").html("严重程度<br>轻伤");
	}else if(iss_score>=16 && iss_score<25){
		$("#rank").html("严重程度<br>重伤");
	}else{
		$("#rank").html("严重程度<br>严重伤");	
	}
	//记录分值
	var flag= localStorage.getItem("flag");
	var patient_code="";
	if(flag=="doc"){ patient_code = localStorage.getItem("doc_pat_id");}
	if(flag=="pat"){ patient_code = localStorage.getItem("pat_id");}
	var content ={"head":{"userID":"","sessionID":"","funcNO":"000004"},"content":{"patient_code":patient_code,"iss_score":iss_score}} ;
	$.post("/action/iss/http/action/FunctionAction", { "jsonContent": JSON.stringify(content) });
}
$("#btn_update_doc").click(function(e){
	if($("#doc_name_update").val()=="") { 
		$('.modal-body').html("姓名不能为空！");
		$('#myModal').modal('show');
		return;
	}
	if($("#doc_hospital_update").val()=="") { 
		$('.modal-body').html("请填写医院！");
		$('#myModal').modal('show');
		return;
	}
	if($("#doc_phone_update").val()=="") { 
		$('.modal-body').html("电话不能为空！");
		$('#myModal').modal('show');
		return;
	}
	if($("#doc_phone_update").val()!=""){
		if(isNaN($("#doc_phone_update").val())) { 
			$('.modal-body').html("手机格式错误");
			$('#myModal').modal('show');
			return;
		}
		if($("#doc_phone_update").val()<=1) { 
			$('.modal-body').html("手机格式错误!");
			$('#myModal').modal('show');
			return;
		}
		if($("#doc_phone_update").val().length!=11) { 
			$('.modal-body').html("手机格式错误!");
			$('#myModal').modal('show');
			return;
		}
	}
	if($("#doc_email_update").val()=="") { 
		$('.modal-body').html("邮箱不能为空！");
		$('#myModal').modal('show');
		return;
	}
	if($("#doc_email_update").val()!=""){
		var reg = /^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/;
		if(!reg.test($("#doc_email_update").val())){
			$('.modal-body').html("邮箱格式错误!");
			$('#myModal').modal('show');
			return;
		}
	}
	var content ={"head":{"userID":"","sessionID":"","funcNO":"000008"},"content":{}} ;
	content.content["doc_name"] = $("#doc_name_update").val();
	content.content["doc_hospital"] = $("#doc_hospital_update").val();
	content.content["doc_room"] = $("#doc_room_update").val();
	content.content["doc_job"] =$("#doc_job_update").val();
	content.content["doc_phone"] = $("#doc_phone_update").val();
	content.content["doc_email"] = $("#doc_email_update").val();
	content.content["doc_tel"] = $("#doc_tel_update").val();	
	content.content["doc_id"] = localStorage.getItem("doc_id");
	$.post("/action/iss/http/action/FunctionAction", { "jsonContent": JSON.stringify(content) },
	   function(result){
				$('.modal-body').html(result.head.errorInfo);
				$('#myModal').modal('show');
	 }, "json");
});
$("#btn_update_pat").click(function(e){
	if($("#pat_name_update").val()=="") { 
		$('.modal-body').html("姓名不能为空！");
		$('#myModal').modal('show');
		return;
	}
	if($('input[name="pat_sex_update"]:checked').val()==undefined) { 
		$('.modal-body').html("性别不能为空！");
		$('#myModal').modal('show');
		return;
	}
	if($("#pat_age_update").val()=="") { 
		$('.modal-body').html("年龄不能为空！");
		$('#myModal').modal('show');
		return;
	}	
	if($("#pat_age_update").val()!=""){	
		if(isNaN($("#pat_age_update").val())) { 
			$('.modal-body').html("年龄格式错误");
			$('#myModal').modal('show');
			return;
		}
		if($("#pat_age_update").val()<=0) { 
			$('.modal-body').html("年龄格式错误!");
			$('#myModal').modal('show');
			return;
		}
	}
	if($("#pat_phone_update").val()!=""){
		if(isNaN($("#pat_phone_update").val())) { 
			$('.modal-body').html("手机格式错误");
			$('#myModal').modal('show');
			return;
		}
		if($("#pat_phone_update").val()<=0) { 
			$('.modal-body').html("手机格式错误!");
			$('#myModal').modal('show');
			return;
		}
		if($("#pat_phone_update").val().length!=11) { 
			$('.modal-body').html("手机格式错误!");
			$('#myModal').modal('show');
			return;
		}
	}
	if($("#pat_ill_date_update").val()=="") { 
		$('.modal-body').html("受伤时间不能为空！");
		$('#myModal').modal('show');
		return;
	}
	if($("#pat_ill_type_update").val()=="") { 
		$('.modal-body').html("受伤类型不能为空！");
		$('#myModal').modal('show');
		return;
	}
	var content ={"head":{"userID":"","sessionID":"","funcNO":"000009"},"content":{}} ;
	content.content["pat_name"] = $("#pat_name_update").val();
	content.content["pat_sex"] = $('input[name="pat_sex_update"]:checked ').val();
	content.content["pat_age"] = $("#pat_age_update").val();
	content.content["pat_add"] = $("#pat_add_update").val();
	content.content["pat_phone"] = $("#pat_phone_update").val();
	content.content["pat_ill_date"] = $("#pat_ill_date_update").val();
	content.content["pat_ill_type"] = $("#pat_ill_type_update").val();
	content.content["pat_hospital"] = $("#pat_hospital_update").val();
	content.content["pat_id"] = localStorage.getItem("pat_id");
	$.post("/action/iss/http/action/FunctionAction", { "jsonContent": JSON.stringify(content) },
	   function(result){
		$('.modal-body').html(result.head.errorInfo);
		$('#myModal').modal('show');
	 }, "json");
});