<#include "./snippet/header.ftl">

<!--competition introduction-->
<div class="container">
    <h2 style="text-align: center"><label class="label label-info">竞赛报名</label></h2>
</div>

<!--sign up here-->
<div class="container">
    <!--com remark-->
    <div class="alert alert-danger">
        <div>
            <h4><span class="glyphicon glyphicon-registration-mark">说明：</span></h4>
            <div>请认真填写每一项内容,每项内容都将影响到你是否能参加比赛或者赛后获奖</div>
        </div>
    </div>

    <div>
        <h2><label class="label label-danger"><span class="glyphicon glyphicon-log-in">报名-上传作品</span></label></h2>
        <div>
            <h4>
            <#if compt?exists && compt.spType>
                <label class="label label-primary">参赛形式：个人赛</label>
            <#else>
                <label class="label label-primary">参赛形式：小组赛 (组员由组长添加)</label>
            </#if>
            </h4>
            <div class="alert alert-info">
            <#if student?exists>
                <h5><b>组长</b> ： ${student.username?if_exists}</h5>
                <h5><b>学号</b> ： ${student.userNo?if_exists}</h5>
                <h5><b>学院专业</b> ： ${student.academy?if_exists} - ${student.profession?if_exists}</h5>
            <#else>
                <h5>未能读取组长信息</h5>
            </#if>
            </div>
        </div>
    </div>
</div>


<!--competition-->
<div class="container">

</div>


<#include "./snippet/footer.ftl">