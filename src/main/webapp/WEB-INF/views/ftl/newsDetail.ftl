<#include "./snippet/header.ftl">


<div class="container">
    <div class="jumbotron">
        <#if news?exists>
            <h5>
                <span class="glyphicon glyphicon-bookmark">[新闻详情] 发布时间：${news.createtime?if_exists}</span>&nbsp;
                <span> 发布者：${news.adminName?if_exists}</span>
            </h5>
            <h3 style="text-align: center"><span>[标题]：${news.title?if_exists}</span></h3>
            <div class="my-comDetail-content">${news.content?if_exists}</div>
        </#if>
    </div>
</div>

<#include "./snippet/footer.ftl">