/**---------------------------合并处理----------------------------------**/
$('#merge').click(function() {  
	
	var select_1=  $("#select_1").val(); 
	var select_2=  $("#select_2").val(); 
	if(select_1=="" || select_2==""){
		alert("先选择要合并的列");
	}else{
		var s = '<div class="row" style="padding-bottom: 6px;"><input  name="merge" type="hidden" value="'+select_1+'@-@-@'+select_2+'">'+
		'<div class="col-xs-1"  style="width:80px;padding-top: 6px;">文件1</div>'+
        '<div class="col-xs-2"><input type="text" class="form-control" value="'+select_1+'"></div>'+
        '<div class="col-xs-2"  style="width:60px;padding-top: 6px;">合并</div>'+
        '<div class="col-xs-1"  style="width:80px;padding-top: 6px;">文件2</div>'+
        '<div class="col-xs-2"><input type="text" class="form-control" value="'+select_2+'"></div>'+
        '<div class="col-xs-2"><span id="delete" class="btn btn-success"onclick="del(this)"><span>-</span></span></div>'+
        '</div>';
		$(s).appendTo('#merge_div');
		$("#select_1").val(""); 
		$("#select_2").val(""); 
	}
}); 
/**---------------------------删除处理----------------------------------**/
function del(obj) {  
		$(obj).parent().parent().remove();
}
/**---------------------------下载处理----------------------------------**/
$('#main').click(function() {  
	 var titles=""
     $("input[name=title]").each(function() { 
            if ($(this).prop('checked')) {  
            	titles +=$(this).val()+",";  
            }  
     });  
	var titles1=""
     $("input[name=title1]").each(function() {  
            if ($(this).prop("checked")) {  
            	titles1 += $(this).val()+",";   
            }  
        });  
	var merge=""
     $("input[name=merge]").each(function() {  
    	 	merge += $(this).val()+",";   
     });  
	var select_1=  $("#select_1").val(); 
	var select_2=  $("#select_2").val(); 
	if(select_1 !=""){
		if( select_2 !=""){
			merge += select_1+'@-@-@'+select_2+","; 
		}else{
			alert("先选择要合并的列");
			return;
		}
	}
	window.location.href="/action/xls/action/XLS?titles="+encodeURI(titles)+"&titles1="+encodeURI(titles1)+"&merge="+encodeURI(merge);
}); 
/**---------------------------上传第一个文件处理----------------------------------**/
$('#fileupload').fileupload({
    url: "/action/xls/action/Upload",
    dataType: 'json',
    done: function (e, data) {
        $('#file_name').text(data.result.name);
        for(var i =0 ; i<data.result.titles.length ; i++){	
    	   var s = '<div class="col-md-2"><label><input  name="title"  id="title_'+i+'" type="checkbox" value="'+data.result.titles[i]+'"><span>'+data.result.titles[i]+'</span></label></div>';
           $(s).appendTo('#titles');
     	   var s1 = '<option value="'+data.result.titles[i]+'">'+data.result.titles[i]+'</option>';
           $(s1).appendTo('#select_1');
        }
    },
    progressall: function (e, data) {
        var progress = parseInt(data.loaded / data.total * 100, 10);
        $('#progress .progress-bar').css(
            'width',
            progress + '%'
        );
    }
}).prop('disabled', !$.support.fileInput).parent().addClass($.support.fileInput ? undefined : 'disabled');
/**---------------------------上传第二个文件处理----------------------------------**/
$('#fileupload1').fileupload({
    url: "/action/xls/action/Upload1",
    dataType: 'json',
    done: function (e, data) {
        $('#file1_name').text(data.result.name);
        for(var i =0 ; i<data.result.titles.length ; i++){  
           var s = '<div class="col-md-2"><label><input  name="title1"  id="title1_'+i+'" type="checkbox" value="'+data.result.titles[i]+'"><span>'+data.result.titles[i]+'</span></label></div>';
           $(s).appendTo('#titles1');
     	   var s1 = '<option value="'+data.result.titles[i]+'">'+data.result.titles[i]+'</option>';
           $(s1).appendTo('#select_2');
        }
    },
    progressall: function (e, data) {
        var progress = parseInt(data.loaded / data.total * 100, 10);
        $('#progress1 .progress-bar').css(
            'width',
            progress + '%'
        );
    }
}).prop('disabled', !$.support.fileInput).parent().addClass($.support.fileInput ? undefined : 'disabled');