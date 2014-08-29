<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>大连交通大学竞赛系统</title>
    <!-- Bootstrap -->
    <link rel="stylesheet" href="../resources/dist/css/bootstrap.css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="../resources/dist/js/html5shiv.min.js"></script>
    <script src="../resources/dist/js/respond.min.js"></script>
    <![endif]-->
</head>
<body>

<div class="container-fluid">
    <div><img src="/resources/img/top.jpg" height="150px" width="100%"></div>
</div>


<div class="container">
    <div class="col-sm-offset-2"><h1>大连交通大学竞赛官网-(学生账号)注册</h1></div>
    <div class="alert alert-danger col-sm-offset-2" role="alert">
        <p><b>说明：</b>只允许（大连交通大学）学生用户注册，请认真填写正确信息，填写虚假信息无效！
    </div>
    <div class="">
        <form class="form-horizontal" role="form" action="/user/regPost" method="post" id="regForm">
            <div class="form-group">
                <label for="username" class="col-sm-2 control-label"><span style="color: red">*</span>姓名</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" id="username" name="username" placeholder="姓名">
                </div>
            </div>

            <div class="form-group">
                <label for="email" class="col-xs-2 control-label"><span style="color: red">*</span>邮箱</label>
                <div class="col-xs-10">
                    <input type="email" class="form-control" id="email" name="email" placeholder="一个邮箱只能绑定一个学号,可用做登陆账号">
                </div>
            </div>

            <div class="form-group">
                <label class="col-xs-2"></label>
                <div class="col-xs-10">
                    <label>输入邮箱验证码</label>
                    <input type="text" class="form-control" id="validCode" name="code" placeholder="邮箱验证码会发送至您的邮箱" disabled>
                    <button type="button" class="btn btn-xs btn-success" id="sendCode">发送验证码至邮箱</button>
                </div>
            </div>

            <div class="form-group">
                <label for="studentNo" class="col-sm-2 control-label"><span style="color: red">*</span>学号</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" id="studentNo" name="studentNo" placeholder="学生卡学号,可用作登陆账号">
                </div>
            </div>

            <div class="form-group">
                <label for="pwd" class="col-sm-2 control-label"><span style="color: red">*</span>密码</label>
                <div class="col-sm-10">
                    <input type="password" class="form-control" id="pwd" name="pwd" placeholder="密码6-20位，由数字和字母组成">
                </div>
            </div>

            <div class="form-group">
                <label for="rePwd" class="col-sm-2 control-label"><span style="color: red">*</span>重复密码</label>
                <div class="col-sm-10">
                    <input type="password" class="form-control" id="rePwd" placeholder="请重复输入密码">
                </div>
            </div>

            <div class="form-group">
                <label for="academy" class="col-sm-2 control-label"><span style="color: red">*</span>学院</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" id="academy" name="academy" placeholder="所在学院">
                </div>
            </div>
            <div class="form-group">
                <label for="pro" class="col-sm-2 control-label"><span style="color: red">*</span>专业</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" id="pro" name="pro" placeholder="所属专业">
                </div>
            </div>
            <div class="form-group">
                <label for="cellphone" class="col-sm-2 control-label"><span style="color: red">*</span>手机</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" id="cellphone" name="cellphone" placeholder="有效手机">
                </div>
            </div>

            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <button type="button" class="btn btn-primary btn-block" id="regSubmit">注册</button>
                </div>
            </div>
        </form>
    </div>

</div>

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="../resources/dist/js/jquery-1.10.2.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="../resources/dist/js/bootstrap.min.js"></script>

<script src="../resources/custom/js/JSValidate.js"></script>

<script src="../resources/custom/js/register.js"></script>


<#include "./snippet/footer.ftl">