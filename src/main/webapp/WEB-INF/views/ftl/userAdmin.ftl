<#include "./snippet/header.ftl">



<div class="container-fluid">
    <#include "./snippet/AdminLeftNavbar.ftl">

    <div class="col-sm-10">
        <div class="alert alert-info">
            帐号信息
        </div>
        <div class="alert alert-info">
            <ul class="list-group">
                <#if user?exists && user?has_content>
                    <li class="list-group-item">名称：${user.name?if_exists}</li>
                    <li class="list-group-item">类型：<#if user.isSuper?if_exists>最高权限管理员<#else>普通管理员</#if></li>
                    <li class="list-group-item">登陆ID：${user.email?if_exists}</li>
                    <li class="list-group-item">联系手机：${user.cellphone?if_exists}</li>
                <#else>
                    <li class="list-group-item">暂时无法读取账号信息</li>
                </#if>
            </ul>
        </div>
    </div>
</div>


<#include "./snippet/footer.ftl">