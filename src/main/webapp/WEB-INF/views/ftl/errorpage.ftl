<#include "./snippet/header.ftl">

<div class="container-fluid" style="margin-top:50px;margin-bottom: 50px;">
    <div class="jumbotron">
        <h4>Message</h4>
        <p><span style="font-size: 15px;">404</span>
        <h4><#if message??>${message?if_exists}</#if></h4>
    </div>
</div>

<#include "./snippet/footer.ftl">