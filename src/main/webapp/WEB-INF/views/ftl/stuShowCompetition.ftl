<#include "./snippet/header.ftl">

<p><br/><br/>
<div class="container-fluid">
    <#include "./snippet/AdminLeftNavbar.ftl">

    <div class="col-sm-10">

        <div class="alert alert-info">
            赛事报名概况
        </div>

        <div class="alert">

            <#--附件-->
            <#if compt?exists && compt.isNeedFile?if_exists>

            </#if>

            <#--comptetition-->
            <#if compt?exists && compt?has_content>
                <div class="alert alert-info">
                    <p>赛事名称：<a href="/compt/detail?link=${compt.ID?if_exists}"><b>${compt.title?if_exists}</b></a>
                    <p>参赛形式：<#if compt.spType?if_exists><b>小组赛</b><#else><b>个人赛</b></#if>
                    <p>作品上传：<#if compt.isNeedFile?if_exists><span style="color: #ff0000"><b>需要</b></span>&nbsp;<a class="btn btn-success" href="/student/addAttachMentGet?clink=${clink?if_exists}&slink=${slink?if_exists}">附件管理</a><#else><b>不需要</b></#if>
                </div>
            </#if>

            <#--成员列表-->
            <#if signList?exists && signList?has_content>
                <div class="alert alert-info">
                    <h4><b>参赛成员</b><a class="btn btn-xs btn-success">添加成员</a></h4>
                    <div class="panel panel-default">
                        <div class="panel-body">
                            <table class="table table-bordered table-responsive">
                                <#if isLeader?if_exists>
                                    <tr class="bg-primary">
                                        <th>序号</th>
                                        <th>名字</th>
                                        <th>学号</th>
                                        <th>邮箱</th>
                                        <th>联系手机</th>
                                        <th>学分帮助</th>
                                        <th width="150px;">管理</th>
                                    </tr>
                                    <#list signList as item>
                                        <tr id="${item.ID?if_exists}">
                                            <td>${item_index+1}</td>
                                            <td>${item.name?if_exists}</td>
                                            <td>${item.number?if_exists}</td>
                                            <td>${item.email?if_exists}</td>
                                            <td>${item.cellphone?if_exists}</td>
                                            <td><#if item.isHelpCredit?if_exists><span style="color: #ff0000">是</span><#else><span>否</span></#if></td>
                                            <td>
                                                <button class="btn btn-xs btn-info" alt="${item.ID?if_exists}">编辑信息</button>
                                                <#if Session['CUR']['CUR_LINK']?exists && Session['CUR']['CUR_LINK'] != item.skTStudent?if_exists>
                                                    <button class="btn btn-xs btn-danger btnDelMember" data-toggle="modal" data-target="#delMemberModal" alt="${item.ID?if_exists}">删除组员</button>
                                                </#if>
                                            </td>
                                        </tr>
                                    </#list>
                                <#else>
                                <#--非队长-->
                                    <tr class="bg-primary">
                                        <th>序号</th>
                                        <th>名字</th>
                                        <th>学号</th>
                                        <th>邮箱</th>
                                        <th>联系手机</th>
                                        <th>学分帮助</th>
                                    </tr>
                                    <#list signList as item>
                                        <tr id="${item.ID?if_exists}">
                                            <td>${item_index+1}</td>
                                            <td>${item.name?if_exists}</td>
                                            <td>${item.number?if_exists}</td>
                                            <td>${item.email?if_exists}</td>
                                            <td>${item.cellphone?if_exists}</td>
                                            <td><#if item.isHelpCredit?if_exists><span style="color: #ff0000">是</span><#else><span>否</span></#if></td>
                                        </tr>
                                    </#list>
                                </#if>
                            </table>
                        </div><#--panel body-->
                    </div><#--panel-->
                </div>
            </#if>
        </div>

    </div><#--col-sm-10-->


</div>



<!-- Modal for delete a competition-->
<div class="modal fade" id="delMemberModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">删除组员</h4>
            </div>
            <div class="modal-body">
                <span>确认删除该组员</span>
            </div>
            <div class="modal-footer">
                <input type="hidden" value="" id="DelMemberId">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-danger" data-dismiss="modal" id="confirmDelMember">确认删除</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->


<script>
    $(document).ready(function(){
        $(".btnDelMember").click(function(){
            $("#DelMemberId").val($(this).attr("alt"));
        });
        $("#confirmDelMember").click(function(){
            var link = $("#DelMemberId").val();
            var url = "/student/deleteTeamMember";
            var data = {"link":link};
            $.post(url,data,function(json){
                if(json === "AJAX_SUCCESS"){
                    $("#"+link).html("<td colspan='7' style='text-align: center;color: #ff0000'>删除成功</td>");
                    setTimeout(function(){
                        $("#"+link).remove();
                    },3000);
                }else{
                    alert("删除失败，请重试");
                }
            });
        });
    });
</script>

<#include "./snippet/footer.ftl">