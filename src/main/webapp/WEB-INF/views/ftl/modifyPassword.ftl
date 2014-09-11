<#include "./snippet/header.ftl">



<div class="container-fluid">
    <#include "./snippet/AdminLeftNavbar.ftl">

    <div class="col-sm-10">
        <div class="alert alert-info">
            修改账号密码
        </div>
        <div class="alert alert-info">
            <h4><b>获取邮箱验证码以修改账号密码</b></h4>
            <div class="panel panel-default">
                <div class="panel-body">
                    <form role="form">
                        <div  id="divGetCheckCode"><#--验证模块-->
                            <div class="form-group">
                                <button type="button" class="btn btn-info" id="btnSendEmail">点击发送验证码至你的邮箱<#if user?exists>：${user.email?if_exists}</#if></button>
                                <input class="form-control" name="vcode" id="vcode" placeholder="输入从邮箱中获取的验证码">
                                <input value="<#if user?exists>${user.email?if_exists}</#if>" type="hidden" id="email">
                            </div>
                            <button type="button" class="btn btn-primary" id="btnValid">确定</button>
                        </div>
                        <div id="divSetPwd" style="display: none"><#--修改模块-->
                            <div class="form-group">
                                <label for="newPwd">输入新密码</label>
                                <input type="password" id="newPwd" placeholder="输入要设置的新密码">
                            </div>
                            <div class="form-group">
                                <label for="reNewPwd">重复新密码</label>
                                <input type="password" id="reNewPwd" placeholder="重复输入要设置的新密码，确保两次输入一致">
                            </div>
                            <button type="button" class="btn btn-success" id="btnConfirmModify">确认修改</button>
                            <button type="button" class="btn btn-warning" onclick="location.reload()">取消</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>


<script>
    $(document).ready(function(){

        //发送邮件
        $("#btnSendEmail").click(function(){
            var email = $("#email").val();
            if(email.trim().length < 1){
                alert("操作失败，刷新或重新登录后重试");
            }else{
                var url = "/man/validEmail";
                var data = {"email":email};
                var currBtn = $(this);
                $.post(url,data,function(json){
                    currBtn.attr("disabled",true);
                    if(json === "AJAX_SUCCESS"){
                        $("#vcode").attr("disabled",false);
                        var index = 60;
                        var timer = setInterval(function(){
                            if(index>0){
                                currBtn.html("发送成功|("+index+")秒后重新发送");
                            }else{
                                currBtn.html("重新发送");
                                currBtn.attr("disabled",false);
                                clearInterval(timer);
                            }
                            index--;
                        },1000);
                    }else{
                        var index = 5;
                        var timer = setInterval(function(){
                            if(index>0){
                                currBtn.html("发送失败|("+index+")秒后重新发送");
                            }else{
                                currBtn.html("重新发送");
                                currBtn.attr("disabled",false);
                                clearInterval(timer);
                            }
                            index--;
                        },1000);
                    }
                });
            }/*else end*/
        });

        //验证邮箱
        $("#btnValid").click(function(){
            var code = $(vcode).val().trim();
            if(code.length < 1){
                alert("请输入验证码");
            }else{
                var url = "/man/validEmailCode";
                var data = {"link":code};
                $.post(url,data,function(json){
                    if(json === "AJAX_SUCCESS"){
                        $("#divGetCheckCode").hide();
                        $("#divSetPwd").fadeIn();
                    }else{
                        alert("验证码错误，重新发送或者重新登录尝试");
                    }
                });
            }
        });

        $("#reNewPwd").blur(function(){
            var pwd = $("#newPwd").val().trim();
            if(pwd.length < 1){
                alert("请先输入密码");
            }else if(pwd != $(this).val().trim()){
                alert("两次输入密码不正确");
                $(this).val("");
                $(this).focus();
            }

        });
        //修改密码
        $("#btnConfirmModify").click(function(){
            var pwd = $("#newPwd").val().trim();
            var repwd = $("#reNewPwd").val().trim();
            if(pwd.length > 0 && repwd.length > 0 && pwd == repwd){
                var url = "/man/resetPassword";
                var data = {"link":pwd};
                $.post(url,data,function(json){
                    if(json === "AJAX_SUCCESS"){
                        alert("恭喜！密码修改成功,下次请用新密码登录！");
                        location.href="/man/";
                        location.
                    }else{
                        alert("操作失败，请重试");
                    }
                });
            }
        });
    });
</script>

<#include "./snippet/footer.ftl">