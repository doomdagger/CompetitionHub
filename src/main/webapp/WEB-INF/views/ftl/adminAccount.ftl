<#include "./snippet/header.ftl">



<div class="container-fluid">
    <#include "./snippet/AdminLeftNavbar.ftl">

    <div class="col-sm-10">
        <div class="alert alert-info">
            管理员账号管理
        </div>
        <div class="alert">
            <div class="alert alert-info">
                <div class="panel panel-default">
                    <div class="panel-body">
                        <table class="table table-bordered table-responsive">
                            <tr>
                                <th>序号</th>
                                <th>名字</th>
                                <th>登录Email</th>
                                <th>联系电话</th>
                                <th>密码</th>
                                <th>管理</th>
                            </tr>
                            <#if userList?exists && (userList?size>0)>
                                <#list userList as item>
                                    <tr id="${item.ID?if_exists}">
                                        <td>${item_index+1}</td>
                                        <td>${item.uUsername?if_exists}</td>
                                        <td>${item.aEmail?if_exists}</td>
                                        <td>${item.uCellphone?if_exists}</td>
                                        <td>${item.aUserPwd?if_exists}</td>
                                        <td>
                                            <button class="btn btn-xs btn-danger btnDelAdminer" data-toggle="modal" data-target="#delAdminerModal" alt="${item.ID?if_exists}">删除</button>
                                        </td>
                                    </tr>
                                </#list>
                            </#if>
                        </table>
                    </div><#--panel-body-->
                </div><#--panel-defualt-->
            </div><#--alert alert-info-->
        </div>
    </div>
</div>


<!-- Modal for delete a competition-->
<div class="modal fade" id="delAdminerModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
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
                <input type="hidden" value="" id="DelAdminer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-danger" data-dismiss="modal" id="confirmDelAdmin">确认删除</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<script>
    $(document).ready(function(){
        $(".btnDelAdminer").click(function(){
            $("#DelAdminer").val($(this).attr("alt"));
        });
        $("#confirmDelAdmin").click(function(){
            var link = $("#DelAdminer").val();
            var url = "/man/admin/deleteAccount";
            var data = {"link":link};
            $.post(url,data, function (json) {
                if(json === "AJAX_SUCCESS"){
                    $("#"+link).html("<td colspan='6' style='text-align: center'><b>删除成功</b></td>");
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