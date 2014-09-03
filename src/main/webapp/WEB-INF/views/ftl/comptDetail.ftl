<#include "./snippet/header.ftl">

<#if compt??><#--如果有数据-->
<div class="container">
    <div class="jumbotron">
        <h4><span class="glyphicon glyphicon-bookmark">[赛事详情] 发布时间：${compt.createtime?if_exists}</span></h4>
        <h3 style="text-align: center"><span>[标题] ${compt.title?if_exists}</span></h3>
        <div class="my-comDetail-content" style="font-size: 15px;">${compt.content?if_exists}</div>
    </div>
    <br/>
</div>
<div class="alert alert-info">
    <table class="table table-responsive table-condensed table-hover" style="font-size: 13px;">
        <tr><th width="200px;">--详细内容如下：</th><th></th></tr>
        <tr>
            <td>主办单位：</td>
            <td>${compt.sponsor?if_exists}</td>
        </tr>
        <tr>
            <td>赛事等级：</td>
            <td>
                <#if compt.level?exists>
                    <#if compt.level == 1>国际级A
                    <#elseif compt.level == 2>国家级B
                    <#elseif compt.level == 3>省级A
                    <#elseif compt.level == 4>省级B
                    <#elseif compt.level == 5>市级
                    <#elseif compt.level == 6>企业级
                    <#elseif compt.level == 7>校级
                    <#elseif compt.level == 8>院级
                    <#elseif compt.level == 9>其他
                    </#if>
                </#if>
            </td>
        </tr>
        <tr>
            <td>比赛进行地点：</td>
            <td>${compt.address?if_exists}</td>
        </tr>
        <tr>
            <td>比赛进行日期：</td>
            <td>${compt.comptDate?if_exists}</td>
        </tr>
        <tr>
            <td>报名开始时间：</td>
            <td>${compt.deadlineS?string("yyyy-MM-dd")}</td>
        </tr>
        <tr>
            <td>报名截止时间：</td>
            <td>${compt.deadlineE?string("yyyy-MM-dd")}</td>
        </tr>
        <tr>
            <td>参赛形式：</td>
            <td>
                <#if compt.spType?if_exists>个人赛
                <#else>小组赛 最大人数<b>${compt.spNum?if_exists}</b>人
                </#if>
            </td>
        </tr>
        <tr>
            <td>是否需要上传个人作品：</td>
            <td>
                <#if compt.isNeedFile?if_exists>【是】-【说明】：${compt.needFileIntro?if_exists}
                <#else>【否】
                </#if>
            </td>
        </tr>
        <tr>
            <td>是否为资助项目：</td>
            <td>
                <#if compt.isSupport?if_exists>【是】-【说明】：${compt.supportIntro?if_exists}
                <#else>【否】
                </#if>
            </td>
        </tr>
    </table>
</div>

<!--exam appendix-->
<div class="alert alert-info">
    <div class="">
        <h4><span class="glyphicon glyphicon-share"><b>参赛说明</b></span></h4>
        <div class="my-comDetail-content" style="font-size: 15px;">${compt.comptIntro?if_exists}</div>
    </div>
</div>

<!--exam appendix-->
<div class="alert alert-info">
    <div class="">
        <h4><span class="glyphicon glyphicon-download"><b>附件下载</b></span></h4>
        <div style="font-size: 15px">
            <ol>
                <#if attachList?exists>
                    <#list attachList as item>
                        <li><a href="${item.savepath?if_exists}" title="点击下载-${item.name?if_exists}">${item.name?if_exists}</a>&nbsp;<span> 上传时间：[ ${item.createtime?if_exists} ]</span></li>
                    </#list>
                </#if>
            </ol>
        </div>
    </div>
</div>

<!--exam entrance-->
<div class="container">
    <div class="jumbotron">
        <h4><span class="glyphicon glyphicon-link"><b>报名入口</b>
            [状态：
            <#if compt.status?exists && compt.status == 3><label style="color: green">可报名</label>
            <#else><label style="color: red">不可报名</label>
            </#if>
            ]</span></h4>
        <a class="btn btn-danger btn-block" href="/compt/signIn?link=${compt.ID?if_exists}">报名参赛</a>
    </div>
</div>

</#if>

<#include "./snippet/footer.ftl">