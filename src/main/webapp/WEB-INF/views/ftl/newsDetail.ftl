<#include "./snippet/header.ftl">


<div class="container">
    <div class="jumbotron">
        <h5><span class="glyphicon glyphicon-bookmark">[新闻详情] 发布时间：<#if news?exists>${news.createtime?if_exists}</#if></span></h5>
        <h3 style="text-align: center"><span>[标题]：<#if news?exists>${news.title?if_exists}</#if></span></h3>
        <div class="my-comDetail-content thumbnail"><#if news?exists>${news.content?if_exists}</#if></div>
    </div>
</div>

<#include "./snippet/footer.ftl">