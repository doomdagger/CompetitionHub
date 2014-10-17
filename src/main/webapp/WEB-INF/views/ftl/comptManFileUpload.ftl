<#include "./snippet/header.ftl">

<div class="container-fluid">
    <#include "./snippet/AdminLeftNavbar.ftl">

    <div class="col-sm-10">
        <div class="alert alert-info">
            <a href="/man/compt/list">赛事管理</a> / 附件上传
        </div>
        <div class="alert">
            <div class="alert alert-warning">
                上传赛事相关的附件
            </div>

            <div class="alert alert-info">
                <#if attachList??>
                    <h5><b>已上传的文件</b></h5>
                    <div class="panel">
                        <table class="table table-bordered table-responsive">
                            <tr class="bg-primary">
                                <th>序号</th>
                                <th>文件名(点击下载)</th>
                                <th>上传时间</th>
                                <th>管理</th>
                            </tr>
                            <#if (attachList?size>0)>
                                <#list attachList as item>
                                    <tr id="_${item.ID?if_exists}">
                                        <td>${item_index+1}</td>
                                        <td><a target="_blank" href="/download/download?path=${item.savepath?if_exists}">${item.name?if_exists}</a></td>
                                        <td>${item.createtime?if_exists}</td>
                                        <td><a class="btn btn-xs btn-danger btnFileDel" alt="${item.ID?if_exists}">删除</a></td>
                                    </tr>
                                </#list>
                            <#else>
                                <tr>
                                    <td colspan="4" style="text-align: center"><b>当前没有任何记录</b></td>
                                </tr>
                            </#if>
                        </table>
                    </div>
                </#if>
            </div>

            <#--upload files-->
            <div class="alert alert-info">
                <h4><span class="glyphicon glyphicon-upload"></span><b>附件上传</b></h4>
                <form role="role" class="form-horizontal" method="post" enctype="multipart/form-data" action="/man/compt/uploadFile?link=${link?if_exists}" id="comptUploadFile">
                    <div id="uploadFileList">
                        <div class="form-group">
                            <input type="file" class="col-sm-3 upFile" name="upFile">
                        </div>
                    </div>
                    <input type="hidden" value="${link?if_exists}" name="link" id="link">
                </form>
                <button type="button" class="btn btn-xs btn-info" id="addOneMore">添加一项</button>
                <button type="button" class="btn btn-xs btn-success" id="btnSubmitFile">上传附件</button>
                <p>备注：
                <p>添加完成后，点击<b>"上传附件"</b>按钮开始上传
            </div>

        </div>
    </div>
</div>


<script src="/resources/custom/js/comptManPub.js" type="text/javascript"></script>
<#include "./snippet/footer.ftl">