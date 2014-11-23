/**
 * Created by JOECHOW on 2014/9/1.
 * include the index, compt list,new list
 */

$(document).ready(function(){
    var _AJAX = {
        success:"AJAX_SUCCESS",
        fail:"AJAX_FAIL"
    }

    //主页登录
    $("#btnIndexLogin").click(function(){
        var username = $("#username").val().trim();
        var pwd = $("#pwd").val().trim();
        /*console.log("login");*/
        if(username.length < 1 && pwd.length < 1){
            $("#accountTips").css("color","red");
            $("#accountTips").html("用户名或密码不能为空");
            return;
        }
        //get login
        $("#btnIndexLogin").attr("disabled",true);
        $("#btnIndexLogin").html("<img src='/resources/img/loading.gif'width='16px;'> 正在登录...");
        //ajax post
        var url = document.getElementsByName("usertype")[0].checked ? "/user/indexLoginStudent" : "/user/indexLoginAdmin";
        var data = {"username":username,"pwd": $.md5(pwd)};
        /*console.log("url:"+url+" data:"+data);*/
        $.post(url,data,function(json){
            json = $.parseJSON(json);
            if(json.result === _AJAX.success){
                //登录成功
                //set message
                $("#usernameSucc").html(json.usernameSucc);
                var usertype = json.usertypeSucc==1?"管理员账号":
                    json.usertypeSucc==2?"学院账号":
                        json.usertypeSucc==3?"教务账号":
                            "学生账号";

                $("#usertypeSucc").html(usertype);
                $("#btnToAccount").attr("href",json.usertypeSucc==0 ? "/student/" : "/man/");
                //set tips
                $("#accountTips").css("color","black");
                $("#accountTips").html("账号信息");
                $("#btnIndexLogin").html("<img src='/resources/img/loading.gif'width='15px;'> 登录成功");
                //显示面板
                var timer = setTimeout(function(){
                    //login success
                    $("#indexLoginPanel").hide();
                    $("#indexLoginSucc").fadeIn();
                    $("#btnIndexLogin").attr("disabled",false);
                    $("#afterLoginHref").attr("href",json.usertypeSucc==0 ? "/student/" : "/man/");
                    $("#afterLogin").fadeIn();
                },500);//loading
            }else{
                //登录失败
                $("#accountTips").css("color","red");
                $("#accountTips").html("用户名或密码错误");
                $("#btnIndexLogin").html("<img src='/resources/img/loading.gif'width='15px;'> 登录失败");
                setTimeout(function(){
                    $("#btnIndexLogin").html("登录");
                    $("#btnIndexLogin").attr("disabled",false);
                },1000);
            }
        });
    });


    //主页注销
    $("#btnIndexLogOut").click(function(){
        $(this).attr("disabled",true);
        $(this).html("<img src='/resources/img/loading.gif'width='15px;'> 正在注销...");
        setTimeout(function(){
            location.href="/user/logout";
        },1000);
    });


});
