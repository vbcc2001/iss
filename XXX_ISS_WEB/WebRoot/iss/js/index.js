function skip_doc(){
	$("#reg_doc").hide();
	$("#reg_doc_pat").show();
}
function skip_doc_pat(){
	$("#reg_doc_pat").hide();
	$("#main").show();
}
function skip_pat(){
	$("#reg_pat").hide();	
	$("#main").show();
}
function enter_doc(){
	$("#index").hide();
	$("#reg_doc").show();
}
function enter_pat(){
	$("#index").hide();
	$("#reg_pat").show();
}
function bak_1(){
	$("#main").hide();
	$("#index").show();
}
function bak_2(){
	$("#sub").hide();
	$("#main").show();
}
function update_user(){
	var flag= localStorage.getItem("flag");
	if(flag=="doc"){ 
		var doc_id= localStorage.getItem("doc_id");
		var content ={"head":{"userID":"","sessionID":"","funcNO":"000010"},"content":{"doc_id":doc_id}} ;
		$.post("/action/iss/http/action/FunctionAction", { "jsonContent": JSON.stringify(content) },function(result){
			if(result.head.errorNo==""){
				$("#doc_name_update").val(result.list[0].name);
				$("#doc_hospital_update").val(result.list[0].com);
				$("#doc_room_update").val(result.list[0].dept);
				$("#doc_job_update").val(result.list[0].post);
				$("#doc_phone_update").val(result.list[0].phone);
				$("#doc_email_update").val(result.list[0].email);
				$("#doc_tel_update").val(result.list[0].tel);
				//显示界面
				$("#main").hide();
				$("#update_doc").show();
			}else{
				$('.modal-body').html(result.head.errorInfo);
				$('#myModal').modal('show');
			}
		}, "json");
	}
	if(flag=="pat"){ 
		var pat_id= localStorage.getItem("pat_id");
		var content ={"head":{"userID":"","sessionID":"","funcNO":"000011"},"content":{"pat_id":pat_id}} ;
		$.post("/action/iss/http/action/FunctionAction", { "jsonContent": JSON.stringify(content) },function(result){
			if(result.head.errorNo==""){
				$("#pat_name_update").val(result.list[0].name);
				$('input[name="pat_sex_update"]').each(function(){
					if($(this).val()==result.list[0].sex){ $(this).prop('checked',true); };
				}); 
				$("#pat_age_update").val(result.list[0].age);
				$("#pat_add_update").val(result.list[0].address);
				$("#pat_phone_update").val(result.list[0].phone);
				$("#pat_ill_date_update").val(result.list[0].ill_date);
				$("#pat_ill_type_update").val(result.list[0].ill_type);
				$("#pat_hospital_update").val(result.list[0].hospital);
				//显示界面
				$("#main").hide();
				$("#update_pat").show();
			}else{
				$('.modal-body').html(result.head.errorInfo);
				$('#myModal').modal('show');
			}
		}, "json");
	}
}
function back_doc(){
	$("#update_doc").hide();
	$("#main").show();
}
function back_pat(){
	$("#update_pat").hide();
	$("#main").show();
}
function reset(){
	for(var i=0;i<c.length;i++){
		for(var j=0;j<c[i].c.length;j++){
			c[i].c[j]["check"]=false;
			for(var k=0;k<c[i].c[j].c.length;k++){
				c[i].c[j].c[k]["check"]=false;
			}
		}
	}
	$("#iss").html("ISS评分<br>-");
	$("#ais").html("AIS评分<br>-");	
	$("#rank").html("严重程度<br>-");
	$("#index").hide();	
	$("#sub").hide();
	$("#main").show();
	$(".radmenu").removeClass("selected");
}
function getQueryString(name){
     var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
     var r = window.location.search.substr(1).match(reg);
     if(r!=null)return  unescape(r[2]); return null;
}
$(function(){ 
	//获得医生使用次数
	var content ={"head":{"userID":"","sessionID":"","funcNO":"000005"},"content":{"click_content":"医生使用"}} ;
	$.post("/action/iss/http/action/FunctionAction", { "jsonContent": JSON.stringify(content) },function(result){
		if(result.head.errorNo==""){  $("#click_count_doc").html('<img src="img/glass.png" style="height:25px;width:25px;"/>浏览'+result.list[0].click_count+'次'); }
	}, "json");
	//获得患者使用次数
	content ={"head":{"userID":"","sessionID":"","funcNO":"000005"},"content":{"click_content":"患者使用"}} ;
	$.post("/action/iss/http/action/FunctionAction", { "jsonContent": JSON.stringify(content) },function(result){
		if(result.head.errorNo==""){  $("#click_count_pat").html('<img src="img/glass.png" style="height:25px;width:25px;"/>浏览'+result.list[0].click_count+'次'); }
	}, "json");	
	//初始日期框
	$('#pat_ill_date').datepicker({ language: "zh-CN" });
	$('#pat_ill_date_update').datepicker({ language: "zh-CN" });	
	$('#ill_date').datepicker({ language: "zh-CN" });	
	//点击事件
	$(".radmenu").click(function(e){
		var id = $(this).attr("id");
		$(this).addClass("selected");
		$("#main").hide();
		$("#sub_ul").html("");
		$("#sub").show();
		$("#sub_img").attr("src","img/"+c[id].img);
		$("#sub_title_cn").html(c[id].t);
		$("#sub_title_en").html(c[id].t_en);		
		for(var i=0;i<c[id].c.length;i++){
			var str ="";
			var title=c[id].c[i].t;
			//if(title.length>5){ title="<marquee>"+title+"</marquee>"; }
			if(c[id].c[i].check){		
				str ='<li class="sub_li" id="sub_'+id+'_'+i+'"><img style="width:20px;" src="img/g.png" id="img_'+id+'_'+i+'"/><img src="img/3.png" /><a class="btn">'+title+'</a></li>';
			}else{
				str ='<li class="sub_li" id="sub_'+id+'_'+i+'"><img style="width:20px;" id="img_'+id+'_'+i+'"/><img src="img/3.png" /><a class="btn">'+title+'</a></li>';
			}
			$("#sub_ul").append(str);
		}
		$(".sub_li").click(function(e){
			$(".sub_li").children("a").removeClass("sub_li_selected");
			$(this).children("a").addClass("sub_li_selected");			
			var id = $(this).attr("id");
			var ids = id.split("_") ;
			$(".content").hide();
			$(".score").removeClass("checked");
			if(c[ids[1]].c[ids[2]].c[0].c!=""){ $(".panel-5").parent().show();}
			if(c[ids[1]].c[ids[2]].c[1].c!=""){ $(".panel-4").parent().show();}
			if(c[ids[1]].c[ids[2]].c[2].c!=""){ $(".panel-3").parent().show();}
			if(c[ids[1]].c[ids[2]].c[3].c!=""){ $(".panel-2").parent().show();}
			if(c[ids[1]].c[ids[2]].c[4].c!=""){ $(".panel-1").parent().show();}		
			$(".panel-5").html(c[ids[1]].c[ids[2]].c[0].c);
			$(".panel-4").html(c[ids[1]].c[ids[2]].c[1].c);
			$(".panel-3").html(c[ids[1]].c[ids[2]].c[2].c);
			$(".panel-2").html(c[ids[1]].c[ids[2]].c[3].c);
			$(".panel-1").html(c[ids[1]].c[ids[2]].c[4].c);
			$(".panel-5").attr("id",id+"_0");	
			$(".panel-4").attr("id",id+"_1");	
			$(".panel-3").attr("id",id+"_2");	
			$(".panel-2").attr("id",id+"_3");	
			$(".panel-1").attr("id",id+"_4");	
			if(c[ids[1]].c[ids[2]].c[0].check){ $(".panel-5").addClass("checked"); }	
			if(c[ids[1]].c[ids[2]].c[1].check){ $(".panel-4").addClass("checked"); }
			if(c[ids[1]].c[ids[2]].c[2].check){ $(".panel-3").addClass("checked"); }
			if(c[ids[1]].c[ids[2]].c[3].check){ $(".panel-2").addClass("checked"); }
			if(c[ids[1]].c[ids[2]].c[4].check){ $(".panel-1").addClass("checked"); }			
		});
		$("#sub_"+id+"_0").click();		
	});
	$(".score").click(function(e){ 
		var id = $(this).attr("id");
		var ids = id.split("_") ; 
		if(c[ids[1]].c[ids[2]].c[ids[3]].check){
			c[ids[1]].c[ids[2]].c[ids[3]]["check"]=false;
			$(this).removeClass("checked");
		}else{
			c[ids[1]].c[ids[2]].c[ids[3]]["check"]=true;
			$(this).addClass("checked");
		}
		c[ids[1]].c[ids[2]]["check"]=true;
		$("#img_"+ids[1]+"_"+ids[2]).attr("src","img/g.png");
	});
}) 