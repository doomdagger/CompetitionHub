<#include "./snippet/header.ftl">



<div class="container-fluid">
<#include "./snippet/AdminLeftNavbar.ftl">

    <div class="col-sm-10">
        <div class="alert alert-info">
            作品上传
        </div>
        <div class="alert">
            <div class="alert alert-info">
                <h4><span class="glyphicon glyphicon-upload"><b>上传附件</b></span></h4>
                <form role="form" enctype="multipart/form-data" action="/student/addAttachMentPost" id="attachForm">
                    <input type="file" name="attach" id="attach">
                    <button type="button" class="btn btn-xs btn-success" id="btnSubmit">上传</button>
                </form>
            </div>

            <div class="alert alert-info">
                <div class="panel panel-default">
                    <div class="panel-body">
                        <table class="table table-responsive table-bordered">
                            <tr class="bg-primary">
                                <th>序号</th>
                                <th>名称</th>
                                <th>上传时间</th>
                                <th>管理</th>
                            </tr>
                        <#if attList?exists && (attList?size>0)>
                            <#list attList as item>
                                <tr id="${item.ID?if_exists}">
                                    <td>${item_index+1}</td>
                                    <td>${item.name?if_exists}</td>
                                    <td>${item.createtime?if_exists}</td>
                                    <td>
                                        <button class="btn btn-xs btn-danger btnDelAttach" data-toggle="modal" data-target="#delAttachModal" alt="${item.ID?if_exists}">删除</button>
                                    </td>
                                </tr>
                            </#list>
                        <#else>
                            <tr>
                                <td colspan="4" style="text-align: center"><b>目前还没有上传作品</b></td>
                            </tr>
                        </#if>
                        </table>
                    </div><#--panel body-->
                </div><#--panel-->
            </div><#--alert info-->
        </div><#--alert-->
    </div>
</div>



<!-- Modal for delete a competition-->
<div class="modal fade" id="delAttachModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">删除附件</h4>
            </div>
            <div class="modal-body">
                <span>确认删除该附件</span>
            </div>
            <div class="modal-footer">
                <input type="hidden" value="" id="DelAttachId">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-danger" data-dismiss="modal" id="confirmDelMember">确认删除</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->


<script>
    $(document).ready(function(){
        $(".btnDelAttach").click(function(){
            $("#DelAttachId").val($(this).attr("alt"));
        });
        $("#confirmDelMember").click(function(){
            var link = $("#DelAttachId").val();
            var url = "/student/deleteAttach";
            var data = {"link":link};
            $.post(url,data,function(json){
                if(json === "AJAX_SUCCESS"){
                    //success
                }else{
                    alert("删除失败，请重试");
                }
            });
        });
    });

    //submit file
    $("#btnSubmit").click(function(){
        if($("#attach").val().trim().length == 0){
            alert("请先选择文件");
        }else{
            $("#attachForm").submit();
        }
    });
</script>

<#include "./snippet/footer.ftl">