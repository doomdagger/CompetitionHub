<#include "./snippet/header.ftl">

<p><br/><br/>
<div class="container-fluid">
    <br/><br/>
</div>
<!--common bottom part start-->
<div class="container">
    <p>
    <h1>Attention:</h1>
    <div class="jumbotron">
        <div class="center-block">
            <h2><b>Sorry</b></h2>
            <h3>${message?if_exists}</h3>
        </div>
        <div class="center-block">
            <button class="btn btn-warning btn-lg" onclick="javascript:history.go(-1);">返回上一页/Go Back</button>
            <a class="btn btn-info btn-lg" href="/index">首页/MainPage</a>
        </div>
    </div>
</div>

<#include "./snippet/footer.ftl">