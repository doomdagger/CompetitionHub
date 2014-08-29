/**
 * Created by JOECHOW on 2014/8/21.
 */

$(document).ready(function(){

    var _AJAX = {
        success:"AJAX_SUCCESS",
        fail:"AJAX_FAIL"
    }

    //删除赛事
    $(".btnDelCompt").click(function(){
        $("#linkDelCompt").val($(this).attr("alt"));
    });
    //确定删除赛事
    $("#confirmDelCompt").click(function(){
        var link = $("#linkDelCompt").val();
        var url = "/man/compt/delete";
        var data = {"link":link};
        $.post(url,data,function(json){
            if(json === _AJAX.success){
                alert("删除成功");
                $("#_"+link).remove();//删除元素
            }else{
                alert("删除失败");
            }
        });
    });

    //赛事置顶
    $(".newsToTop").click(function(){
        var link = $(this).attr("alt");
        var url = "/man/compt/manTotop/1";
        var data = {"link":link};
        var currentBtn = $(this);
        $.post(url,data,function(json){
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
        var link = $(this).attr("alt");
        var url = "/man/compt/manTotop/0";
        var data = {"link":link};
        var currentBtn = $(this);
        $.post(url,data,function(json){
                if(_AJAX.success === json){
                    currentBtn.parent().find(".newsToTop").show();
                    currentBtn.hide();
                    currentBtn.parent().find(".isTop").hide();
                }else{
                    alert("操作失败");
                }
            }
        );
    })
});
