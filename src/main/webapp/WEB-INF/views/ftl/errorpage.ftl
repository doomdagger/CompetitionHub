<#include "./snippet/header.ftl">

<p><br/><br/>
<div class="container-fluid">
    <br/><br/>
</div>
<div class="container-fluid">
    <div class="jumbotron" style="text-align: center">
        <h4>Message</h4>
        <p><span style="font-size: 15px;">404</span>
        <h4><#if message?exists>${message?if_exists}</#if></h4>
    </div>
</div>

<#include "./snippet/footer.ftl">