/**
 * Created by root on 14-8-12.
 */

$(document).ready(function(){

    var _AJAX = {
        success:"AJAX_SUCCESS",
        fail:"AJAX_FAIL"
    }
//    Page : newsManPub
    $("#newsPubSubmit").click(function() {
        //get editor data
        editor.sync();
        var htmlContent = $('#editor_id').val(); // jQuery
        //validate
        if(htmlContent.trim().length==0 || $("#title").val().trim().length == 0 ){
            $("#newsPubSubmit").removeClass("btn-success");
            $("#newsPubSubmit").addClass("btn-danger");
            $("#newsPubSubmit").html("内容尚未填写完整");
            return;
        }
        //pass validate
        if($("#newsPubSubmit").hasClass("btn-danger")){
            $("#newsPubSubmit").removeClass("btn-danger");
            $("#newsPubSubmit").addClass("btn-success");
            $("#newsPubSubmit").html("<img src=\"resources/img/loading.gif\" width=\"25px;\"/>"+"正在提交...");
        }
        //ajax post
        $.post(
            '/man/news/manPublishPost',
            {"title":$("#title").val(),"isTop":$("input:checked").get(0).value,"content":htmlContent},
            function(json){
                if(json === _AJAX.success){
                    //发布成功
                    console.log("succ");
                    $("#newsPubSubmit").attr('disabled',true);
                    if($("#newsPubSubmit").hasClass("btn-danger")){
                        $("#newsPubSubmit").removeClass("btn-danger");
                        $("#newsPubSubmit").addClass("btn-warning");
                    }
                    var times = 3;//3秒后跳转
                    $("#newsPubSubmit").html("提交成功( "+times+" 秒后跳转到新闻管理页面)");
                    setInterval(function(){
                        $("#newsPubSubmit").html("提交成功( "+--times+" 秒后跳转到新闻管理页面)");
                        if(times<0){
                            location.href = "/man/news/manList";
                        }
                    },1000);
                }else{
                    //发布失败
                    console.log("fail");
                    alert("提交失败，请刷新页面重试...");
                }
            }
        );
    });


    //Page : newsMan

    //新闻置顶
    $(".newsToTop").click(function(){
        var newsId = $(this).attr("alt");
        var url = "/man/news/manTotop/1";
        var data = {"newsId":newsId};
        var currentBtn = $(this);
        $.post(
            url,
            data,
            function(json){
                if(_AJAX.success == json){
                    currentBtn.parent().find(".newsNoTop").show();
                    currentBtn.hide();
                    currentBtn.parent().find(".isTop").show();
                }else{
                    alert("操作失败");
                }
            }
        );
    });

    //取消置顶
    $(".newsNoTop").click(function(){
        var newsId = $(this).attr("alt");
        var url = "/man/news/manTotop/0";
        var data = {"newsId":newsId};
        var currentBtn = $(this);
        $.post(
            url,
            data,
            function(json){
                if(_AJAX.success === json){
                    currentBtn.parent().find(".newsToTop").show();
                    currentBtn.hide();
                    currentBtn.parent().find(".isTop").hide();
                }else{
                    alert("操作失败");
                }
            }
        );
    });


    //删除询问框
    $("#preDelNews").click(function(){
        $("#preDelNewsId").val($(this).attr("alt"));
    });
    //删除询问框取消按钮
    //$("")
    //确定删除
    $("#doDelNews").click(function(){
        var newsId = $("#preDelNewsId").val();
        var url = "/man/news/manDelete";
        var data = {"newsId":newsId};
        $.post(
            url,
            data,
            function(json){
                if(_AJAX.success == json){
                    $("#newsTips").html("删除成功");
                    $("#newsTips").fadeIn();
                    setTimeout(function(){
                        $("#newsTips").fadeOut();
                    },3000);
                }else{
                    $("#newsTips").html("删除操作失败");
                    $("#newsTips").fadeIn();
                    setTimeout(function(){
                        $("#newsTips").fadeOut();
                    },3000);
                }
            }
        );
    });

});
