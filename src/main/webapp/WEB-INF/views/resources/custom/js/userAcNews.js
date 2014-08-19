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
        console.log("submit btn in");
        //var ue = UE.getContent();
        var htmlContent;
        ue.ready(function(){
            //获取html格式文本
            htmlContent = ue.getContent();
        });
        //ajax post
        console.log("pass");
        $.post(
            '/man/news/manPublishPost',
            {"title":$("#title").val(),"isTop":$("input:checked").get(0).value,"content":htmlContent},
            function(json){
                if(json === _AJAX.success){
                    //发布成功
                    console.log("succ");
                }else{
                    //发布失败
                    console.log("fail");
                }
            }
        );
    });


    //Page : newsMan

    //新闻置顶
    $(".newsToTop").click(function(){
        var newsId = $(this).attr("id");
        var url = "/man/news/manTotop/1";
        var data = {"newsId":newsId};
        $.post(
            url,
            data,
            function(json){
                if(_AJAX.success === json){
                    $(this).html("取消置顶");
                    $(this).removeClass("btn-info");
                    $(this).addClass("btn-primary");

                    alert("succ");
                }else{
                    alert("操作失败");
                }
            }
        );
    });

    //取消置顶
    $(".newsNoTop").click(function(){
        var newsId = $(this).attr("id");
        var url = "/man/news/manTotop/0";
        var data = {"newsId":newsId};
        $.post(
            url,
            data,
            function(json){
                if(_AJAX.success === json){
                    //$(this).html("置顶");
                    $(this).removeClass("btn-primary");
                    $(this).addClass("btn-info");

                    alert("succ");
                }else{
                    alert("操作失败");
                }
            }
        );
    });
});
