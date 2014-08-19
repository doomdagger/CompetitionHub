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

<div class="container">
    <div class="col-sm-offset-2"><h1>大连交通大学竞赛官网-(学生账号)注册-邮箱验证</h1></div>
    <div class="alert alert-danger col-sm-offset-2" role="alert">
        <p>验证链接已经发送至您的邮箱，请从邮箱中点击链接以验证完成注册。
        <p><span class="glyphicon glyphicon-send"/>如果你还没有收到邮件，点击按钮以重新发送邮件
        <p><button class="btn btn-default active" id="reSendMail" title="点击按钮重新发送邮件" onclick="location='/user/reSendEmail?email=${email}'"> （等待60秒）重新发送</button>
    </div>
</div>

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="../resources/dist/js/jquery-1.10.2.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="../resources/dist/js/bootstrap.min.js"></script>
<script>
    var index = 60;
    var interval = setInterval(function(){
        if(index-->0){
            $('#reSendMail').html('（等待'+index+'秒）重新发送');
        }else{
            $('#reSendMail').html('重新发送邮件');
            $('#reSendMail').removeClass('active');
            //interval.stop;
        }
    },1000);

    $('#reSendEmail').click(function(){
        window.location="/user/reSendEmail";
    });
</script>
</body>
</html>