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
    <div class="col-sm-offset-2"><h1>大连交通大学竞赛报名官网-<b>注册成功</b></h1></div>
    <div class="jumbotron">
       <#-- <p>验证链接已经发送至您的邮箱，请从邮箱中点击链接以验证完成注册。
        <p><span class="glyphicon glyphicon-send"/>如果你还没有收到邮件，点击按钮以重新发送邮件
        <p><button class="btn btn-default active" id="reSendMail" title="点击按钮重新发送邮件" onclick="location='/user/reSendEmail?email=${email}'"> （等待60秒）重新发送</button>
        -->
        <p>去首页<a href="/index"><b>大连交通大学竞赛报名官网</b></a>&nbsp;
        <p> (<span id="countNum"></span> 秒后为你自动跳转)
    </div>
</div>

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="../resources/dist/js/jquery-1.10.2.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="../resources/dist/js/bootstrap.min.js"></script>
<script>
    var index = 5;
    var timer = setInterval(function(){
        if(index>0){
            $('#countNum').html(index);
        }else{
            clearInterval(timer);
            location.href="/index";
        }
        index--;
    },1000);
</script>
</body>
</html>