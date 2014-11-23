<#include "./snippet/header.ftl">

<#if compt?exists><#--如果有数据-->
<div class="container">
    <div class="jumbotron">
        <h5>
            <span class="glyphicon glyphicon-bookmark">[赛事详情] 发布时间：${compt.createtime?if_exists}</span>&nbsp;&nbsp;&nbsp;&nbsp;
            <span> 发布者：${compt.adminName?if_exists}</span>
        </h5>
        <h3 style="text-align: center"><span>[标题] ${compt.title?if_exists}</span></h3>
        <div class="my-comDetail-content" style="font-size: 15px;">${compt.content?if_exists}</div>
    </div>
    <br/>
</div>

<div class="container">


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
                        <#if compt.level == 1>国家级A
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
            <h4><span class="glyphicon glyphicon-registration-mark"><b>参赛说明</b></span></h4>
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
                            <li><a target="_blank" href="/download/download?path=${item.savepath?if_exists}" title="点击下载-${item.name?if_exists}">${item.name?if_exists}</a>&nbsp;<span> 上传时间：[ ${item.createtime?if_exists} ]</span></li>
                        </#list>
                    </#if>
                </ol>
            </div>
        </div>
    </div>


</div>
<!--exam entrance-->
<div class="container">
    <div class="alert alert-warning">
        <input type="hidden" value="${compt.ID?if_exists}" id="link"/>
            <#if compt.status?exists && compt.status == 3>
                <h4><span class="glyphicon glyphicon-link"><b>报名入口</b>[状态：<label style="color: green">可报名</label>]</span></h4>
                <a class="btn btn-success btn-block" href="javascript:void(0)"alt="${compt.ID?if_exists}" id="btnToSignIn">报名参赛</a>
            <#else>
                <h4><span class="glyphicon glyphicon-link"><b>报名入口</b>[状态：<label style="color: red">不可报名</label>]</span></h4>
                <a class="btn btn-danger btn-block" href="javascript:void(0)" disabled>报名参赛(不可用)</a>
            </#if>
    </div>
</div>

</#if>

<script>
    $("#btnToSignIn").click(function(){
        $(".tempTips").hide();
        var link = $(this).attr("alt");
        var url = "/user/checkLogin";
        var data = {"link":link};
        $.post(url,data,function(json){
            if(json === "AJAX_SUCCESS"){
                //再次验证是否已经参赛
                var curl = "/student/checkSignExist";
                $.post(curl,data,function(sjson){
                    if(sjson == "AJAX_SUCCESS"){
                        var tips = "<div class='tempTips' style='text-align: center'><h5><span style='color: #ff0000'>对不起，你已经报名参赛请勿重复报名</span></h5></div>";
                        $("#btnToSignIn").after(tips);
                        setTimeout(function(){
                            $(".tempTips").fadeOut();
                        },5000);
                    }else{
                        //尚未在此赛事报名，允许报名，正在跳转
                        location.href="/student/signIn?link="+link;
                    }
                })
            }else{
                var tips = "<div class='tempTips' style='text-align: center'><h5><span style='color: #ff0000'>对不起，你尚未登录，请线返回首页登录</span></h5></div>";
                $("#btnToSignIn").after(tips);
                setTimeout(function(){
                    $(".tempTips").fadeOut();
                },5000);
            }
        });
    });
</script>

<#include "./snippet/footer.ftl">