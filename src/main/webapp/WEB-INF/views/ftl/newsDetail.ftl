<#include "./snippet/header.ftl">


<div class="container">
    <div class="jumbotron">
        <h5><span class="glyphicon glyphicon-bookmark">[新闻详情] 发布时间：${news?if_exists.createtime}</span></h5>
        <h3 style="text-align: center"><span>${news?if_exists.title}</span></h3>
        <div class="my-comDetail-content thumbnail">${news?if_exists.content}</div>
    </div>
</div>

<#include "./snippet/footer.ftl">