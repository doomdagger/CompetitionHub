<#include "./snippet/header.ftl">

<p><br/><br/>
<div class="container-fluid">
    <br/><br/>
</div>
<div class="container-fluid">
    <div class="jumbotron" style="text-align: center">
        <h4><b>提示信息：</b></h4>
        <h5>您的登陆可能已失效，请重新登陆尝试</h5>
        <h5>错误信息：<small><b><#if message?exists>${message?if_exists}</#if></b></small></h5>
    </div>
</div>

<#include "./snippet/footer.ftl">