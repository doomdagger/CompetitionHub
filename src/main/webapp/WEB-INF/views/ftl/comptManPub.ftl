<#include "./snippet/header.ftl">

<div class="container-fluid">
    <#include "./snippet/AdminLeftNavbar.ftl">

    <div class="col-sm-10">
        <div class="alert alert-info">
            赛事发布
        </div>
        <!--管理员拒绝发布，重新发布-->
        <#if compt?? && compt.isVaild && compt.validResult != "">
            <div class="alert alert-danger">
                <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
                <h5><b>管理员-审核意见:</b></h5>
                <p>${compt.validResult?if_exists}
            </div>
        </#if>

        <#if isSubmit?? && isSubmit == "success">
            <div class="alert alert-success">
                <h4><b>表单提交成功</b></h4>
                <p>可以在下方上传赛事附件
            </div>
        <#else>
            <div class="alert alert-info">
                <form role="form" id="comptForm" method="post" action="/man/compt/publishPost">
                    <div class="form-group">
                        <label for="input1">赛事名称</label>
                        <input type="text" class="form-control" id="input1" name="title" placeholder="赛事名称" value="<#if compt??>${compt.title?if_exists}</#if>" required>
                    </div>
                    <div class="form-group">
                        <label for="input2">主办单位</label>
                        <input type="text" class="form-control" id="input2" name="sponsor" placeholder="主办单位" value="<#if compt??>${compt.sponsor?if_exists}</#if>" required>
                    </div>
                    <div class="form-group">
                        <label>赛事等级</label>
                        &nbsp;&nbsp;&nbsp;
                        <label><input type="radio" name="level" value="1" <#if !compt??>checked<#elseif compt?? && compt.level?? && compt.level == 1>checked</#if> >国家级A</label>&nbsp;&nbsp;&nbsp;
                        <label><input type="radio" name="level" value="2" <#if compt?? && compt.level?? && compt.level == 2>checked</#if>>国家级B</label>&nbsp;&nbsp;&nbsp;
                        <label><input type="radio" name="level" value="3" <#if compt?? && compt.level?? && compt.level == 3>checked</#if>>省级A</label>&nbsp;&nbsp;&nbsp;
                        <label><input type="radio" name="level" value="4" <#if compt?? && compt.level?? && compt.level == 4>checked</#if>>省级B</label>&nbsp;&nbsp;&nbsp;
                        <label><input type="radio" name="level" value="5" <#if compt?? && compt.level?? && compt.level == 5>checked</#if>>市级</label>&nbsp;&nbsp;&nbsp;
                        <label><input type="radio" name="level" value="6" <#if compt?? && compt.level?? && compt.level == 6>checked</#if>>企业级</label>&nbsp;&nbsp;&nbsp;
                        <label><input type="radio" name="level" value="7" <#if compt?? && compt.level?? && compt.level == 7>checked</#if>>校级</label>&nbsp;&nbsp;&nbsp;
                        <label><input type="radio" name="level" value="8" <#if compt?? && compt.level?? && compt.level == 8>checked</#if>>院级</label>&nbsp;&nbsp;&nbsp;
                        <label><input type="radio" name="level" value="9" <#if compt?? && compt.level?? && compt.level == 9>checked</#if>>其他</label>
                    </div>

                    <div class="form-group">
                        <label for="input4">比赛地点</label>
                        <input type="text" class="form-control" id="input4" name="address" placeholder="比赛地点" value="<#if compt??>${compt.address?if_exists}</#if>" required>
                    </div>
                    <div class="form-group">
                        <label for="input5">比赛日期</label>
                        <input type="text" class="form-control" id="input5" name="comptDate" placeholder="手动输入具体时间" value="<#if compt??>${compt.comptDate?if_exists}</#if>" required>
                    </div>
                    <div class="form-group">
                        <label for="input6">报名开始日期</label>
                        <input type="text" class="form-control datetimepicker" date-link-format="yyyy-mm-dd" readonly id="input6" name="deadline_s" placeholder="点击选择" value="<#if compt??>${compt.deadlineS?string("yyyy-MM-dd")}</#if>" required>
                    </div>
                    <div class="form-group">
                        <label for="input6">报名截止日期</label>
                        <input type="text" class="form-control datetimepicker" date-link-format="yyyy-mm-dd" readonly id="input6" name="deadline_e" placeholder="点击选择" value="<#if compt??>${compt.deadlineE?string("yyyy-MM-dd")}</#if>" required>
                    </div>
                    <div class="form-group">
                        <label for="editor1">赛事内容</label>
                        <textarea class="form-control" id="editor1" name="content" placeholder="赛事内容"><#if compt??>${compt.content?if_exists}</#if></textarea>
                        <input type="text" class="sr-only" id="contentProxy" value="" required>
                    </div>
                    <div class="form-group">
                        <label for="editor2">参赛说明</label>
                        <textarea class="form-control" id="editor2" name="comptIntro" placeholder="参赛说明"><#if compt??>${compt.comptIntro?if_exists}</#if></textarea>
                        <input type="text" class="sr-only" id="comptIntroProxy" value="" required>
                    </div>
                    <div class="form-group">
                        <label>参赛形式</label>
                        &nbsp;&nbsp;&nbsp;
                        <label><input type="radio" name="sp_type" value="1" id="comType1" <#if !compt??>checked<#elseif compt?? && compt.spType?if_exists>checked</#if>>个人赛</label>&nbsp;&nbsp;&nbsp;
                        <label><input type="radio" name="sp_type" value="0" id="comType2" <#if compt?? && !compt.spType?if_exists>checked</#if>>小组赛</label>
                        <label <#if !compt??>style="display: none"<#elseif compt?? && compt.spType?if_exists>style="display: none"</#if> id="comType2Text"><input type="text" name="sp_maxMember" value="<#if compt??>${compt.spNum?if_exists}<#else>1</#if>" id="maxMember" required>&nbsp;<span class="label label-warning">最大人数(不得少于1人)</span></label>
                    </div>
                    <div class="form-group">
                        <label>是否需要上传作品</label>
                        &nbsp;&nbsp;&nbsp;
                        <label><input type="radio" name="sp_isWorks" value="1" id="comIsNeedWorks1" <#if !compt??>checked<#elseif compt?? && compt.isNeedFile?if_exists>checked</#if> >是</label>&nbsp;&nbsp;&nbsp;
                        <label><input type="radio" name="sp_isWorks" value="0" id="comIsNeedWorks2" <#if compt?? && !compt.isNeedFile?if_exists>checked</#if>>否</label>
                    </div>
                    <div class="form-group" id="comIsNeedWorks" <#if compt?? && !compt.isNeedFile?if_exists>style="display: none"</#if>>
                        <label class="label label-warning" for="input8">作品上传说明</label>
                        <textarea class="form-control" id="input8" name="sp_worksIntro" rows="3" placeholder="作品上传说明" ><#if compt??>${compt.needFileIntro?if_exists}</#if></textarea>
                    </div>
                    <div class="form-group">
                        <label>是否为资助项目</label>
                        &nbsp;&nbsp;&nbsp;
                        <label><input type="radio" name="isSupport" value="1" id="comIsSupport1" <#if !compt??>checked<#elseif compt?? && compt.isSupport?if_exists>checked</#if>>是</label>&nbsp;&nbsp;&nbsp;
                        <label><input type="radio" name="isSupport" value="0" id="comIsSupport2" <#if compt?? && !compt.isSupport?if_exists>checked</#if>>否</label>
                    </div>
                    <div class="form-group" id="comIsSupport" <#if compt?? && !compt.isSupport?if_exists>style="display: none" </#if>>
                        <label class="label label-warning" for="input9">资助说明</label>
                        <textarea class="form-control" id="input9" name="supportIntro" rows="3" placeholder="资助说明"><#if compt??>${compt.supportIntro?if_exists}</#if></textarea>
                    </div>

                    <div>
                        <input type="hidden" value="<#if !compt??>0<#else>${compt.status?if_exists}</#if>" name="currentStatus">
                        <input type="hidden" value="<#if !compt??>0<#else>${compt.ID?if_exists}</#if>" name="link">
                        <button type="submit" id="btnSubmit" class="sr-only"></button>
                        <button type="button" class="btn btn-success btn-block" id="btnSubmitProxy"><#--<img src="/resources/img/loading.gif" width="25px;"/>--> 确认发布</button>
                    </div>
                </form>
            </div>
        </#if>

        <#--上传文件-->
        <div class="alert alert-info">
            <h4><span class="glyphicon glyphicon-upload"></span>附件上传</h4>
            <!--exist files-->
            <#if attachList??>
                <h5><b>已上传的文件</b></h5>
                <div class="panel">
                    <table class="table table-bordered table-responsive table-hover">
                        <tr>
                            <th>序号</th>
                            <th>文件名(点击下载)</th>
                            <th>上传时间</th>
                            <th>管理</th>
                        </tr>
                        <#if (attachList?size>0)>
                            <#list attachList as item>
                                <tr id="_${item.ID?if_exists}">
                                    <td>${item_index+1}</td>
                                    <td><a href="${item.savepath?if_exists}">${item.name?if_exists}</a></td>
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
            <!--upload file-->
            <#if isSubmit??&& isSubmit == "success">
                <form role="role" class="form-horizontal" method="post" enctype="multipart/form-data" action="/man/compt/uploadFile?link=${link?if_exists}" id="comptUploadFile">
                    <div id="uploadFileList">
                        <div class="form-group">
                            <input type="file" class="col-sm-3 upFile" name="upFile">
                        </div>
                    </div>
                    <input type="hidden" value="${link?if_exists}" name="link" id="tempComptId">
                </form>
                <button type="button" class="btn btn-xs btn-info" id="addOneMore">添加一项</button>
                <button type="button" class="btn btn-xs btn-success" id="btnSubmitFile">上传附件</button>
                <p>备注：
                <p>如果现在不需要上传，您后续也可以到“赛事管理”中继续上传附件
                <p>上传成功后将自动跳转到“赛事管理”页面!
            <#else>
                <p><b>备注：</b>先发布赛事后才能上传附件，请先发布赛事
            </#if>
        </div>
    </div>
</div>

<#--datePicker-->
<link rel="stylesheet" href="/resources/plugins/datePicker/css/bootstrap-datetimepicker.min.css">
<script src="/resources/plugins/datePicker/js/bootstrap-datetimepicker.min.js"></script>
<script src="/resources/plugins/datePicker/js/locales/bootstrap-datetimepicker.zh-CN.js"></script><#--中文包-->
<#--validator-->
<script src="/resources/plugins/validator/jquery.validate.min.js"></script>
<#--kindedtior-->
<script charset="utf-8" src="/resources/plugins/kindeditor/kindeditor.js"></script>
<script charset="utf-8" src="/resources/plugins/kindeditor/lang/zh_CN.js"></script>
<#--custom-->
<script src="/resources/custom/js/comptManPub.js"></script>
<#--inner js-->
<script>
    KindEditor.ready(function(K) {
        window.editor1 = K.create('#editor1',{height:'250px;'});
        window.editor2 = K.create('#editor2',{height:'250px;'});
    });
    $('.datetimepicker').datetimepicker({
        language:'zh-CN',
        format: 'yyyy-mm-dd',
        autoclose:true,
        todayHighlight:true,
        startDate:new Date(),
        startView:2,
        minView:2,
        todayBtn:1
    });
    $("#btnSubmitProxy").click(function(){
        editor1.sync();
        $("#contentProxy").val($("#editor1").val());
        editor2.sync();
        $("#comptIntroProxy").val($("#editor2").val());
        //上面为了验证是否content是否有值
        $("#btnSubmit").click();
    });
    $("comptForm").validate();
</script>
<#--out link-->
<#include "./snippet/footer.ftl">
