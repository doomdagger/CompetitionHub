<#include "./snippet/header.ftl">

<#if compt??><#--如果有数据-->
<div class="container">
    <div class="jumbotron">
        <h4><span class="glyphicon glyphicon-bookmark">[赛事详情] 发布时间：${compt.createtime?if_exists}</span></h4>
        <h3 style="text-align: center"><span>[标题] ${compt.title?if_exists}</span></h3>
        <div class="my-comDetail-content" style="font-size: 15px;">${compt.content?if_exists}</div>
    </div>
    <br/>
    <div>
        <table class="table table-responsive table-condensed table-hover" style="font-size: 13px;">
            <tr><th width="200px;">--详细内容如下：</th><th></th></tr>
            <tr>
                <td>主办单位：</td>
                <td>大连交通大学</td>
            </tr>
            <tr>
                <td>赛事等级：</td>
                <td>大连交通大学</td>
            </tr>
            <tr>
                <td>比赛进行地点：</td>
                <td>大连交通大学</td>
            </tr>
            <tr>
                <td>比赛进行日期：</td>
                <td>大连交通大学</td>
            </tr>
            <tr>
                <td>报名开始时间：</td>
                <td>大连交通大学</td>
            </tr>
            <tr>
                <td>报名截止时间：</td>
                <td>大连交通大学</td>
            </tr>
            <tr>
                <td>参赛形式：</td>
                <td>小组赛（最多 5人）</td>
            </tr>
            <tr>
                <td>是否需要上传个人作品：</td>
                <td>【是】【说明如下】：需要上传个人作品</td>
            </tr>
            <tr>
                <td>是否为资助项目：</td>
                <td>【是】【说明如下】：学校资助每个小组1000元</td>
            </tr>
        </table>
    </div>
</div>

<!--exam appendix-->
<div class="container">
    <div class="jumbotron">
        <h4><span class="glyphicon glyphicon-share"><b>赛事说明</b></span></h4>
        <div class="my-comDetail-content" style="font-size: 15px;">${compt.comptIntro?if_exists}</div>
    </div>
</div>

<!--exam appendix-->
<div class="container">
    <div class="jumbotron">
        <h4><span class="glyphicon glyphicon-download"><b>附件下载</b></span></h4>
        <div style="font-size: 15px">
            <ol>
                <li><a href="#">附件1.rar</a></li>
                <li><a href="#">附件1.rar</a></li>
                <li><a href="#">附件1.rar</a></li>
            </ol>
        </div>
    </div>
</div>

<!--exam entrance-->
<div class="container">
    <div class="jumbotron">
        <h4><span class="glyphicon glyphicon-link"><b>报名入口</b> [状态：<label style="color: green">可报名</label> / <label style="color: red">不可报名</label>]</span></h4>
        <button class="btn btn-danger btn-block" onclick="location='signUpCom.html'">报名参赛</button>
    </div>
</div>

</#if>

<#include "./snippet/footer.ftl">