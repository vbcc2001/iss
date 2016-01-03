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
	window.location.href="/action/xls/action/XLS?titles="+encodeURI(titles)+"&titles1="+encodeURI(titles1);
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