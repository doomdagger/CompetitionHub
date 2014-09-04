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
        <h2><label class="label label-danger"><span class="glyphicon glyphicon-log-in">报名-添加组员</span></label></h2>
        <div>
            <h4>
            <#if compt?exists && compt.spType>
                <label class="label label-primary">参赛形式：个人赛</label>
            <#else>
                <label class="label label-primary">参赛形式：小组赛 (组员由组长添加)</label>
            </#if>
            </h4>
            <div class="alert alert-info">
            <#if memberList?exists>
                <table class="table table-bordered table-responsive">
                    <tr>
                        <th>序号</th>
                        <th>名字</th>
                        <th>学号</th>
                        <th>邮箱</th>
                        <th>联系电话</th>
                        <th>是否帮助消除处分</th>
                        <th>管理</th>
                    </tr>
                </table>
                <#list memberList as item>
                    <tr>
                        <td>${item_index+1}</td>
                        <td>${item.name?if_exists}</td>
                        <td>${item.number}</td>
                        <td>${item.email}</td>
                        <td>${item.cellphone}</td>
                        <td><#if item.isHelpCredit?if_exists><span style="color: #ff0000">是</span><#else>否</#if></td>
                        <td><a data-toggle="modal" data-target="#btnDelModal" class="btn btn-xs btn-danger btnDelMember">删除</a></td>
                    </tr>
                </#list>
            <#else>
                <h5><b>目前暂未添加组员</b></h5>
            <p><button class="btn btn-xs btn-info" data-toggle="modal" data-target="#btnAddModal">添加组员</button>
            </#if>
            </div>
        </div>
    </div>
</div>


<!--Modal sr-only-->

<!-- Modal -->
<div class="modal fade" id="btnAddModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">添加组员</h4>
            </div>
            <div class="modal-body">
                <form class="form-inline" role="form">
                    <h4><label class="label label-primary">新组员信息</label></h4>
                    <div class="alert alert-info">
                        <div class="form-group"  title="姓名">
                            <label for="memberName">姓名</label>
                            <input type="text" class="form-control" id="memberName" placeholder="姓名">
                        </div>
                        <div class="form-group"  title="学号">
                            <label for="memberNo">学号</label>
                            <input type="text" class="form-control" id="memberNo" placeholder="学号">
                        </div>
                        <div class="form-group"  title="必须提供有效邮箱">
                            <label for="memberEmail">邮箱</label>
                            <input type="text" class="form-control" id="memberEmail" placeholder="邮箱">
                        </div>
                        <div class="form-group" title="必须提供有效联系方式">
                            <label for="memberCellphone">联系电话</label>
                            <input type="text" class="form-control" id="memberCellphone" placeholder="联系电话">
                        </div>
                        <div class="form-group" title="有过教学处分的同学勾选">
                            <label for="cellphone">教学帮助</label>
                            <label class="form-control">
                                <input type="checkbox">是否有消除处分奖励
                            </label>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary">添加</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->


<!-- Modal -->
<div class="modal fade" id="btnAddModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">添加组员</h4>
            </div>
            <div class="modal-body">
                <div class="content">
                    <span id="spnDelTips"><#--确定删除组员？--></span>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary">添加</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->



<#include "./snippet/footer.ftl">