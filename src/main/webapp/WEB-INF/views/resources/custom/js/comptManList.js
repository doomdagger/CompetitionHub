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
    	console.log($("#linkDelCompt").val());
    	console.log($(this).attr());
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

    //结束报名
    $(document).delegate("a.btnCloseSignCompt", "click",function(){
        if(confirm("确定要结束该赛事报名吗？这将会禁止学生继续报名")){
            //close the signup
            var link = $(this).attr("alt");
            var status = 4;
            var url = "/man/compt/updateStatusByAjax";
            var data = {"link":link,"status":status};
            var currBtn = $(this);
            $.post(url,data,function(response){
                var currTd = currBtn.parent();
                var nextTd = currTd.next();
                if(response === "AJAX_SUCCESS"){
                    currTd.children().remove();
                    currTd.html("<span style=\"color: green\">操作成功</span>");
                    //对下个TD操作
                    nextTd.html("<span style=\"color: green\"><img src='/resources/img/loading3.gif'> 正在更新状态...</span>");
                    setTimeout(function(){
                        currTd.html("<span style=\"color: orange\">比赛进行中</span>");
                        nextTd.html("<a href=\"javascript:void(0)\" class=\"btn btn-xs btn-warning btnCloseCompt\" alt="+link+">结束比赛</a>&nbsp;"
                        +"<button class=\"btn btn-xs btn-danger btnDelCompt\" data-toggle=\"modal\" data-target=\"#myModal\" alt="+link+">删除赛事</button>&nbsp;"
                        +"<a class=\"btn btn-xs btn-default\" href=\"/man/compt/uploadGet?link="+link+"\" alt="+link+">附件上传</a>");
                    },1000);
                }else{
                    var originContent = currTd.html();
                    currTd.html("<span style=\"color: #ff0000\">操作失败</span>");
                    setTimeout(function(){
                        currTd.html(originContent);
                    },1000);
                }
            });
        }
    });

    //结束比赛
    $(document).delegate("a.btnCloseCompt", "click",function(){
        if(confirm("确定结束该比赛了吗？这将意味着赛事已经进行完，正在等待赛事结果")){
            var link = $(this).attr("alt");
            var status = 5;
            var url = "/man/compt/updateStatusByAjax";
            var data = {"link":link,"status":status};
            var currBtn = $(this);
            $.post(url,data,function(response){
                var currTd = currBtn.parent();
                var prevTd = currTd.prev();
                if(response === "AJAX_SUCCESS"){
                    //对当前td操作
                    currTd.children().remove();
                    prevTd.html("<span style=\"color: green\">操作成功</span>");
                    currTd.html("<span style=\"color: green\"><img src='/resources/img/loading3.gif'> 正在更新状态...</span>");
                    currTd.html();
                    setTimeout(function(){
                        currTd.html("<a class=\"btn btn-xs btn-success btnConfirmResult\" href=\"/man/compt/sign/result/confirmResult?comptLink="+link+"\" alt="+link+">确认结果 & 成绩录入</a>");
                        prevTd.html("<span style=\"color: purple\">比赛结束</span>");
                    },1000);
                }else{
                    var originContent = currTd.html();
                    currTd.html("<span style=\"color: #ff0000\">操作失败</span>");
                    setTimeout(function(){
                        currTd.html(originContent);
                    },1000);
                }
            });

        }
    });

    /*$(document).delegate("a.btnConfirmResult","click",function(){
        var link = $(this).attr("alt");
        location.href = "/man/compt/confirmResult?link="+link;
    });*/
});
