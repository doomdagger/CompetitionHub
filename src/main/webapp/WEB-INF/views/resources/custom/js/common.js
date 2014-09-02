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
        $("#btnIndexLogin").html("<img src='/resources/img/loading.gif'width='16px;'> 正在登录...");
        //ajax post
        var url = $("#type0").attr("checked")?"/user/indexLoginStudent":"/user/indexLoginAdmin";
        var data = {"username":username,"pwd":pwd};
        /*console.log("url:"+url+" data:"+data);*/
        $.post(url,data,function(json){
            json = $.parseJSON(json);
            console.log(json);
            console.log(json['result']);
            if(json.result === _AJAX.success){
                //set message
                $("#usernameSucc").html(json.usernameSucc);
                json.usertypeSucc = json.usertypeSucc==1?"管理员账号":
                    json.usertypeSucc==2?"学院账号":
                        json.usertypeSucc==3?"教务账号":
                            "学生账号";
                $("#usertypeSucc").html(json.usertypeSucc);
                //set tips
                $("#accountTips").css("color","black");
                $("#accountTips").html("账号信息");
                $("#btnIndexLogin").html("<img src='/resources/img/loading.gif'width='15px;'> 登录成功");
                var timer = setTimeout(function(){
                    //login success
                    $("#indexLoginPanel").hide();
                    $("#indexLoginSucc").fadeIn();
                },2000);//loading
            }else{
                $("#accountTips").css("color","red");
                $("#accountTips").html("用户名或密码错误");
                $("#btnIndexLogin").attr("disabled",true);
                $("#btnIndexLogin").html("<img src='/resources/img/loading.gif'width='15px;'> 登录失败");
                setTimeout(function(){
                    $("#btnIndexLogin").html("登录");
                    $("#btnIndexLogin").attr("disabled",false);
                },2000);
            }
        });
    });


    //主页注销
    $("#btnIndexLogOut").click(function(){

    });
});
