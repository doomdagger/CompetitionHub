<#include "./snippet/header.ftl">



<div class="container-fluid">
    <#include "./snippet/AdminLeftNavbar.ftl">

    <div class="col-sm-10">
        <div class="alert alert-info">
            帐号信息
        </div>
        <div class="alert alert-info">
            <#if student?exists && student?has_content>
                <ul class="list-group">
                    <li class="list-group-item">类型：<b>学生</b></li>
                    <li class="list-group-item">名称：<b>${student.username?if_exists}</b></li>
                    <li class="list-group-item">邮箱：<b>${student.email?if_exists}</b></li>
                    <li class="list-group-item">学院：<b>${student.academy?if_exists}</b></li>
                    <li class="list-group-item">专业：<b>${student.profession?if_exists}</b></li>
                    <li class="list-group-item">学号：<b>${student.userNo?if_exists}</b></li>
                    <li class="list-group-item">手机：<b>${student.cellphone?if_exists}</b></li>
                </ul>
            <#else>
                <h4>暂无信息</h4>
            </#if>
        </div>
    </div>
</div>


<#include "./snippet/footer.ftl">