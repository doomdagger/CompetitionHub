/**
 * Created by JOECHOW on 2014/8/21.
 */
/**
 * Created by joechow on 14-5-28.
 */

$(document).ready(function(){

    var _AJAX = {
        success:"AJAX_SUCCESS",
        fail:"AJAX_FAIL"
    }

    /*小组赛 和 个人赛的切换*/
    $('#comType1').click(function(){
        $('#comType2Text').fadeOut();
    });
    $('#comType2').click(function(){
        $('#comType2Text').fadeIn();
    });

    /*上传附件说明*/
    $('#comIsNeedWorks1').click(function(){
        $('#comIsNeedWorks').fadeIn();
    });
    $('#comIsNeedWorks2').click(function(){
        $('#comIsNeedWorks').fadeOut();
    });

    /*上传附件说明*/
    $('#comIsSupport1').click(function(){
        $('#comIsSupport').fadeIn();
    });
    $('#comIsSupport2').click(function(){
        $('#comIsSupport').fadeOut();
    });
    //勾选“个人赛”
    $("#comType1").click(function(){
        var check = $(this).attr("checked");
        console.log(check);
        if(check){
            $("#maxMember").val(1);
        }
    });
    //设置最小小组人数
    $("#maxMember").blur(function(){
        var re=  /^[0-9]*[1-9][0-9]*$/;
        if(re.test($(this).val())){
            console.log("right");
        }else{
            console.log("error");
        }
    });

    //多添加一项附件
    $("#addOneMore").click(function(){
        var node = "<div class='form-group'><input type=\"file\" name=\"upFile\" class='col-sm-3'><button type='button' class=\"btn btn-xs btn-danger btnDelDiv col-sm-1\">删除</button></div>";
        $("#uploadFileList").append(node);
        $(".btnDelDiv").on("click",function(){
            $(this).parent().remove();
        });
    });

    $(".upFile").change(function(){
        var filename = $(this).val();
        var ext = filename.substring(filename.indexOf("."));
        //var allowExt = ['.jpg','.png','gif','doc','.xls',''];
        console.log("filename:"+filename);
        console.log("ext:"+ext)

    });
    //上传附件
    $("#btnSubmitFile").click(function(){
        $("#comptUploadFile").submit();
    });

    //删除附件
    $(".btnFileDel").click(function(){
        var isDelete = confirm("确定要删除附件吗?");
        if(isDelete){
            var link = $(this).attr("alt");
            var url = "/man/compt/deleteFile";
            var data = {"link":link};
            $.post(
                url,
                data,
                function(json){
                    if(_AJAX.success === json){
                        alert("删除成功");
                        $("#_"+link).remove();
                    }else{
                        alert("删除失败")
                    }
                }
            );
        }
    });


});