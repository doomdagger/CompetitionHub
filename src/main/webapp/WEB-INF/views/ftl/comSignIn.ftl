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
        <h2><label class="label label-danger"><span class="glyphicon glyphicon-log-in">报名入口</span></label></h2>
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



            <form id="signInForm" class="form-inline" role="form" method="post" action="/student/confirmSignin">
                <input type="hidden" value="${link?if_exists}" id="link" name="link">
                <input type="hidden" value="${slink?if_exists}" id="slink" name="slink">
                <#if signInInfo?exists && signInInfo?has_content>
                    <!--组长资料-->
                    <h4><label class="label label-primary">（组长）个人信息</label></h4>
                    <div class="alert alert-info">
                        <div class="form-group"  title="必须提供有效邮箱">
                            <label for="email">邮箱</label>
                            <input type="text" class="form-control" id="email" name="email" placeholder="邮箱" value="${signInInfo.email?if_exists}">
                        </div>
                        <div class="form-group" title="必须提供有效联系方式">
                            <label for="cellphone">联系电话</label>
                            <input type="text" class="form-control" id="cellphone" anme="cellphone" placeholder="联系电话" value="${signInInfo.cellphone?if_exists}">
                        </div>
                        <div class="form-group" title="有效卡号，用于获奖后的奖金发放">
                            <label for="cellphone">银行卡号</label>
                            <input type="text" class="form-control" id="creditCard" name="creditCard" placeholder="银行卡号" value="${signInInfo.creditCard?if_exists}">
                        </div>
                        <div class="form-group" title="有效卡号，用于获奖后的奖金发放">
                            <label for="cellphone">所属银行</label>
                            <input type="text" class="form-control" id="creditCardBank" name="creditCardBank" placeholder="所属银行" value="${signInInfo.creditCardBank?if_exists}">
                        </div>
                        <div class="form-group" title="赛事指导老师">
                        	<label for="teacher">指导老师</label>
                        	<input type="text" id="teacher" name="teacher" placeholder="指导老师,确定后无法修改" value="${signInInfo.teacher?if_exists}">
                        </div>
                        <div class="form-group" title="有过教学处分的同学勾选">
                            <label for="cellphone">教学帮助</label>
                            <label class="form-control">
                                <input type="checkbox" name="isHelpCredit" <#if signInInfo.isHelpCredit?if_exists>checked="true"</#if> >是否涉及消除处分
                            </label>
                        </div>
                    </div>
                <#elseif student?exists && student?has_content>
                    <!--组长资料-->
                    <h4><label class="label label-primary">（组长）个人信息</label></h4>
                    <div class="alert alert-info">
                        <div class="form-group"  title="必须提供有效邮箱">
                            <label for="email">邮箱</label>
                            <input type="text" class="form-control" name="email" id="email" placeholder="邮箱" value="${student.email?if_exists}">
                        </div>
                        <div class="form-group" title="必须提供有效联系方式">
                            <label for="cellphone">联系电话</label>
                            <input type="text" class="form-control" name="cellphone" id="cellphone" placeholder="联系电话" value="${student.cellphone?if_exists}">
                        </div>
                        <div class="form-group" title="有效卡号，用于获奖后的奖金发放">
                            <label for="cellphone">银行卡号</label>
                            <input type="text" class="form-control" name="creditCard" id="creditCard" placeholder="银行卡号" value="${student.creditCard?if_exists}">
                        </div>
                        <div class="form-group" title="赛事指导老师">
                        	<label for="teacher">指导老师</label>
                        	<input type="text" class="form-control" id="teacher" name="teacher" placeholder="指导老师" value="${student.teacher?if_exists}">
                        </div>
                        <div class="form-group" title="有过教学处分的同学勾选">
                            <label for="cellphone">教学帮助</label>
                            <label class="form-control">
                                <input name="isHelpCredit" type="checkbox">是否有消除处分奖励
                            </label>
                        </div>
                    </div>
                <#else>
                    <!--组长资料-->
                    <h4><label class="label label-primary">（组长）个人信息</label></h4>
                    <div class="alert alert-info">
                        <div class="form-group"  title="必须提供有效邮箱">
                            <label for="email">邮箱</label>
                            <input type="text" class="form-control" name="email" id="email" placeholder="邮箱">
                        </div>
                        <div class="form-group" title="必须提供有效联系方式">
                            <label for="cellphone">联系电话</label>
                            <input type="text" class="form-control" name="cellphone" id="cellphone" placeholder="联系电话">
                        </div>
                        <div class="form-group" title="有效卡号，用于获奖后的奖金发放">
                            <label for="cellphone">银行卡号</label>
                            <input type="text" class="form-control" name="creditCard" id="creditCard" placeholder="银行卡号">
                        </div>
                        <div class="form-group" title="有过教学处分的同学勾选">
                            <label for="cellphone">教学帮助</label>
                            <label class="form-control">
                                <input name="isHelpCredit" type="checkbox">是否有消除处分奖励
                            </label>
                        </div>
                    </div>
                </#if>

                <!--submit-->
                <div>
                    <#if next?exists && next?has_content>
                        <button type="submit" class="btn btn-success btn-block">确认报名</button>
                    <#else>
                        <button type="submit" class="btn btn-success btn-block">确定编辑</button>
                    </#if>
                </div>

            </form>
        </div>
    </div>
</div>



<#--validator-->
<script src="/resources/plugins/validator/jquery.validate.min.js"></script>
<script>
    $("#signInForm").validate();
</script>

<#include "./snippet/footer.ftl">