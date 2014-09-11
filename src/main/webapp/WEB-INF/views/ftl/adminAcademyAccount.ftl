<#include "./snippet/header.ftl">



<div class="container-fluid">
<#include "./snippet/AdminLeftNavbar.ftl">

    <div class="col-sm-10">
        <div class="alert alert-info">
            <span>学院账号管理</span>
            <button class="btn btn-xs btn-info" id="btnAddAccount">添加新学院账号</button>
        </div>
        <div class="alert">
        <#--list-->
            <div class="alert alert-info">
                <div class="panel panel-default">
                    <div class="panel-body">
                        <table class="table table-bordered table-responsive">
                            <tr class="bg-primary">
                                <th>序号</th>
                                <th>名字</th>
                                <th>类型</th>
                                <th>登录Email</th>
                                <th>联系电话</th>
                                <th>密码</th>
                                <th width="100px;">管理</th>
                            </tr>
                        <#if userList?exists && (userList?size>0)>
                            <#list userList as item>
                                <tr id="${item.ID?if_exists}">
                                    <td>${item_index+1}</td>
                                    <td>${item.name?if_exists}</td>
                                    <td><#if !item.isSuper?if_exists>普通管理员<#else>最高级管理员</#if></td>
                                    <td>${item.email?if_exists}</td>
                                    <td>${item.cellphone?if_exists}</td>
                                    <td>${item.pwd?if_exists}</td>
                                    <td>
                                        <button class="btn btn-xs btn-info btnEditAccount" alt="${item.ID?if_exists}">编辑</button>
                                        <#if !item.isSuper?if_exists>
                                            <button class="btn btn-xs btn-danger btnDelAdminer" data-toggle="modal" data-target="#delAdminerModal" alt="${item.ID?if_exists}">删除</button>
                                        </#if>
                                    </td>
                                </tr>
                            </#list>
                        <#else>
                            <tr>
                                <td colspan="7" style="text-align: center"><span><b>暂时还没有添加任何学院账号</b></span></td>
                            </tr>
                        </#if>
                        </table>
                    </div><#--panel-body-->
                </div><#--panel-defualt-->
            </div><#--alert alert-info-->

        <#--添加管理员账号-->
            <div class="alert alert-info" style="display: none" id="divAddAccount">
                <h4><b>添加学院账号</b></h4>
                <form role="form" id="addAccountForm" action="/man/admin/addAccount" method="post">
                    <div class="form-group">
                        <label class="" for="name">名字</label>
                        <input class="form-control" type="text" placeholder="该管理员名字" name="name" id="name" required>
                    </div>
                    <div class="form-group">
                        <label class="" for="cellphone">手机</label>
                        <input class="form-control" type="text" placeholder="有效联系手机" name="cellphone" id="cellphone" required>
                    </div>
                    <div class="form-group">
                        <label class="" for="email">Email</label>
                        <input class="form-control" type="text" placeholder="该邮箱为用于登录的唯一标识" name="email" id="email" required>
                    </div>
                    <div class="form-group">
                        <label class="" for="vcode">验证码</label>
                        <input class="form-control" type="text" placeholder="从您填写的邮箱中获取验证码" id="vcode" required disabled>
                        <button type="button" class="btn btn-xs btn-success" id="sendCode">发送验证码至邮箱</button>
                    </div>
                    <div class="form-group">
                        <label class="" for="pwd">密码设定</label>
                        <input class="form-control" type="text" placeholder="为该用户设置初始密码" name="pwd" id="pwd" required>
                    </div>
                    <input type="hidden" value="2" id="type" name="type">
                    <button type="button" class="btn btn-success" id="btnSubmit">确定添加</button>
                    <button type="button" class="btn btn-warning" onclick="$('#divAddAccount').fadeOut();">取消添加</button>
                </form>
            </div><#--add new account div-->

        <#--编辑管理员账号-->
            <div class="alert alert-info" style="display: none" id="divEditAccount">
                <div>
                    <h4><b>编辑学院账号</b></h4>
                    <form role="form" id="editAccountForm" action="/man/admin/updateAccount" method="post">
                        <div class="form-group">
                            <label class="" for="name2">名字</label>
                            <input class="form-control" type="text" placeholder="该管理员名字" name="name" id="name2" required>
                        </div>
                        <div class="form-group">
                            <label class="" for="cellphone2">手机</label>
                            <input class="form-control" type="text" placeholder="有效联系手机" name="cellphone" id="cellphone2" required>
                        </div>
                        <div class="form-group">
                            <label class="" for="email2">Email(已绑定,删除账号才能解除绑定)</label>
                            <input class="form-control" type="text" placeholder="该邮箱为用于登录的唯一标识" name="email" id="email2" readonly>
                        </div>
                    <#--<div class="form-group">
                        <label class="" for="vcode2">验证码</label>
                        <input class="form-control" type="text" placeholder="从您填写的邮箱中获取验证码" id="vcode2" required>
                    </div>-->
                        <div class="form-group">
                            <label class="" for="pwd2">密码设定</label>
                            <input class="form-control" type="text" placeholder="为该用户设置初始密码" name="pwd" id="pwd2" required>
                        </div>
                        <input type="hidden" value="0" id="link" name="link">
                        <input type="hidden" value="2" id="type" name="type">
                        <button type="submit" class="btn btn-success">确定编辑</button>
                        <button type="button" class="btn btn-warning" onclick="$('#divEditAccount').fadeOut();">取消编辑</button>
                    </form>
                </div>
            </div><#--edit account div-->
        </div>



    </div>
</div>

<!-- Modal for delete a competition-->
<div class="modal fade" id="delAdminerModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">删除账号</h4>
            </div>
            <div class="modal-body">
                <span>确认删除该账号</span>
            </div>
            <div class="modal-footer">
                <input type="hidden" value="" id="DelAdminer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-danger" data-dismiss="modal" id="confirmDelAdmin">确认删除</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<#--validator-->
<script src="/resources/plugins/validator/jquery.validate.js"></script>

<script>
    $(document).ready(function(){
        $(".btnDelAdminer").click(function(){
            $("#DelAdminer").val($(this).attr("alt"));
        });
        $("#confirmDelAdmin").click(function(){
            var link = $("#DelAdminer").val();
            var url = "/man/admin/deleteAccount";
            var data = {"link":link};
            $.post(url,data, function (json) {
                if(json === "AJAX_SUCCESS"){
                    $("#"+link).html("<td colspan='7' style='text-align: center'><b>删除成功</b></td>");
                    setTimeout(function(){
                        $("#"+link).remove();
                    },3000);
                }else{
                    alert("删除失败，请重试");
                }
            });
        });
    });
    //add new Account
    $("#btnAddAccount").click(function(){
        $("#divAddAccount").fadeIn();
    });
    $("#addAccountForm").validate();//add
    $("#editAccountForm").validate();//edit
    //edit
    $(".btnEditAccount").click(function(){
        //request for the account info
        $("#divEditAccount").fadeIn();
        var link = $(this).attr("alt");
        $("#link").val(link);
        var url = "/man/admin/getAccountInfo";
        var data = {"link":link};
        $.post(url,data,function(json){
            var jsonData = $.parseJSON(json);
            if(jsonData.result === "AJAX_SUCCESS"){
                $("#name2").val(jsonData.name);
                $("#cellphone2").val(jsonData.cellphone);
                $("#email2").val(jsonData.email);
                $("#pwd2").val(jsonData.pwd);
            }else{
                alert("操作失败请重试");
            }
        });
    });
    //checkExist
    $("#email").blur(function(){
        var email = $(this).val();
        var url = "/man/admin/checkEmailExist";
        var data = {"email":email};
        $.post(url,data,function(json){
            if(json === "AJAX_SUCCESS"){
                alert("该邮箱已经被使用，请使用其他邮箱");
                $("#email").val("");
                $("#email").attr("placeholder",email+"已经被使用，请尝试使用其他邮箱");
            }
        });
    });
    //send code
    $("#sendCode").click(function(){
        if($("#email").val().trim().length < 1){
            alert("请先输入邮箱");
            $("#email").focus();
            return;
        }else{
            var url = "/man/admin/sendCode";
            var email = $("#email").val();
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
        }
    });

    //确定添加
    $("#btnSubmit").click(function(){
        var link = $("#vcode").val().trim();
        var url = "/man/admin/checkCode";
        var data = {"link":link};
        $.post(url,data,function(json){
            if(json === "AJAX_SUCCESS"){
                $("#addAccountForm").submit();
            }else{
                alert("验证码不正确，请重新输入");
            }
        });
    });
</script>
<#include "./snippet/footer.ftl">