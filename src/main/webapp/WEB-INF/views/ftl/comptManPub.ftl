<#include "./snippet/header.ftl">

<div class="container-fluid">
    <#include "./snippet/AdminLeftNavbar.ftl">

    <div class="col-sm-10">
        <div class="alert alert-info">
            赛事发布
        </div>
        <!--管理员拒绝发布，重新发布-->
        <div class="alert alert-danger">
            <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
            <h5><b>审核意见</b></h5>
            <p>赛事一般，学校此类赛事过多
        </div>
        <#if isSubmit?? && isSubmit == "success">
            <div class="alert alert-success">
                <h4>表单提交成功</h4>
                <p>可以在下方上传赛事附件
            </div>
        <#else>
            <div class="alert alert-info">
                <form role="form" id="comptForm" method="post" action="/man/compt/publishPost">
                    <div class="form-group">
                        <label for="input1">赛事名称</label>
                        <input type="text" class="form-control" id="input1" name="title" placeholder="赛事名称" required>
                    </div>
                    <div class="form-group">
                        <label for="input2">主办单位</label>
                        <input type="text" class="form-control" id="input2" name="sponsor" placeholder="主办单位" required>
                    </div>
                    <div class="form-group">
                        <label>赛事等级</label>
                        &nbsp;&nbsp;&nbsp;
                        <label><input type="radio" name="level" value="1" checked>国家级A</label>&nbsp;&nbsp;&nbsp;
                        <label><input type="radio" name="level" value="2" >国家级B</label>&nbsp;&nbsp;&nbsp;
                        <label><input type="radio" name="level" value="3" >省级A</label>&nbsp;&nbsp;&nbsp;
                        <label><input type="radio" name="level" value="4" >省级B</label>&nbsp;&nbsp;&nbsp;
                        <label><input type="radio" name="level" value="5" >市级</label>&nbsp;&nbsp;&nbsp;
                        <label><input type="radio" name="level" value="6" >企业级</label>&nbsp;&nbsp;&nbsp;
                        <label><input type="radio" name="level" value="7" >校级</label>&nbsp;&nbsp;&nbsp;
                        <label><input type="radio" name="level" value="8" >院级</label>&nbsp;&nbsp;&nbsp;
                        <label><input type="radio" name="level" value="9" >其他</label>
                    </div>

                    <div class="form-group">
                        <label for="input4">比赛地点</label>
                        <input type="text" class="form-control" id="input4" name="address" placeholder="比赛地点" required>
                    </div>
                    <div class="form-group">
                        <label for="input5">比赛日期</label>
                        <input type="text" class="form-control" id="input5" name="comptDate" placeholder="手动输入具体时间" required>
                    </div>
                    <div class="form-group">
                        <label for="input6">报名开始日期</label>
                        <input type="text" class="form-control datetimepicker" date-link-format="yyyy-mm-dd" readonly id="input6" name="deadline_s" placeholder="点击选择" required>
                    </div>
                    <div class="form-group">
                        <label for="input6">报名截止日期</label>
                        <input type="text" class="form-control datetimepicker" date-link-format="yyyy-mm-dd" readonly id="input6" name="deadline_e" placeholder="点击选择" required>
                    </div>
                    <div class="form-group">
                        <label for="editor1">赛事简介</label>
                        <textarea class="form-control" id="editor1" name="content" placeholder="赛事简介"></textarea>
                        <input type="text" class="sr-only" id="contentProxy" value="" required>
                    </div>
                    <div class="form-group">
                        <label for="editor2">参赛说明</label>
                        <textarea class="form-control" id="editor2" name="comptIntro" placeholder="参赛说明"></textarea>
                        <input type="text" class="sr-only" id="comptIntroProxy" value="" required>
                    </div>
                    <div class="form-group">
                        <label>参赛形式</label>
                        &nbsp;&nbsp;&nbsp;
                        <label><input type="radio" name="sp_type" value="1" id="comType1" checked>个人赛</label>&nbsp;&nbsp;&nbsp;
                        <label><input type="radio" name="sp_type" value="0" id="comType2">小组赛</label>
                        <label style="display: none" id="comType2Text"><input type="text" name="sp_maxMember" value="1" id="maxMember" required>&nbsp;<span class="label label-warning">最大人数(不得少于1人)</span></label>
                    </div>
                    <div class="form-group">
                        <label>是否需要上传作品</label>
                        &nbsp;&nbsp;&nbsp;
                        <label><input type="radio" name="sp_isWroks" value="1" id="comIsNeedWorks1" checked>是</label>&nbsp;&nbsp;&nbsp;
                        <label><input type="radio" name="sp_isWroks" value="0" id="comIsNeedWorks2">否</label>
                    </div>
                    <div class="form-group" id="comIsNeedWorks">
                        <label class="label label-warning" for="input8">作品上传说明</label>
                        <textarea class="form-control" id="input8" name="sp_worksIntro" placeholder="作品上传说明"></textarea>
                    </div>
                    <div class="form-group">
                        <label>是否为资助项目</label>
                        &nbsp;&nbsp;&nbsp;
                        <label><input type="radio" name="isSupport" value="1" id="comIsSupport1" checked>是</label>&nbsp;&nbsp;&nbsp;
                        <label><input type="radio" name="isSupport" value="0" id="comIsSupport2">否</label>
                    </div>
                    <div class="form-group" id="comIsSupport">
                        <label class="label label-warning" for="input9">资助说明</label>
                        <textarea class="form-control" id="input9" name="supportIntro" placeholder="资助说明"></textarea>
                    </div>

                    <div>
                        <#--<input type="hidden" value="" id="appendParam" name="jsonData">-->
                        <button type="submit" id="btnSubmit" class="sr-only"></button>
                        <button type="button" class="btn btn-success btn-block" id="btnSubmitProxy"><#--<img src="/resources/img/loading.gif" width="25px;"/>--> 确认发布</button>
                    </div>
                </form>
            </div>
        </#if>

        <#--上传文件-->
        <div class="alert alert-info">
            <h3><span class="glyphicon glyphicon-upload"></span>附件上传</h3>
            <p>提交表单后才能上传附件，请先提交表单
            <!--upload file-->
            <#if isSubmit?? && isSubmit == "success">
                <div class="form-group>
                    <label for="exampleInputFile">上传附件</label>
                    <input type="file" id="exampleInputFile">
                </div>
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

        $("#btnSubmit").click();
    });
    $("comptForm").validate();
</script>
<#--out link-->
<#include "./snippet/footer.ftl">
