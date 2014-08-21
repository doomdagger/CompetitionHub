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

        <div class="alert alert-info">
            <form role="form">
                <div class="form-group">
                    <label for="input1">赛事名称</label>
                    <input type="text" class="form-control" id="input1" name="compt.title" placeholder="赛事名称">
                </div>
                <div class="form-group">
                    <label for="input2">主办单位</label>
                    <input type="text" class="form-control" id="input2" name="compt.sponsor" placeholder="主办单位">
                </div>
                <div class="form-group">
                    <label>赛事等级</label>
                    &nbsp;&nbsp;&nbsp;
                    <label><input type="radio" name="compt.level" checked>国家级A</label>&nbsp;&nbsp;&nbsp;
                    <label><input type="radio" name="compt.level">国家级B</label>&nbsp;&nbsp;&nbsp;
                    <label><input type="radio" name="compt.level">省级A</label>&nbsp;&nbsp;&nbsp;
                    <label><input type="radio" name="compt.level">省级B</label>&nbsp;&nbsp;&nbsp;
                    <label><input type="radio" name="compt.level">企业级</label>&nbsp;&nbsp;&nbsp;
                    <label><input type="radio" name="compt.level">其他</label>
                </div>

                <div class="form-group">
                    <label for="input4">比赛地点</label>
                    <input type="text" class="form-control" id="input4" name="compt.address" placeholder="比赛地点">
                </div>
                <div class="form-group">
                    <label for="input5">比赛日期</label>
                    <input type="text" class="form-control" id="input5" name="compt.comptDate" placeholder="比赛日期">
                </div>
                <div class="form-group">
                    <label for="input6">报名开始日期</label>
                    <input type="text" class="form-control" id="input6" name="compt.deadline_s" placeholder="比赛报名截止日期">
                </div>
                <div class="form-group">
                    <label for="input6">报名截止日期</label>
                    <input type="text" class="form-control" id="input6" name="compt.deadline_e" placeholder="比赛报名截止日期">
                </div>
                <div class="form-group">
                    <label for="input3">赛事简介</label>
                    <textarea class="form-control" id="input3" name="compt.content" placeholder="赛事简介"></textarea>
                </div>
                <div class="form-group">
                    <label for="input7">参赛说明</label>
                    <textarea class="form-control" id="input7" name="compt.comptIntro" placeholder="参赛说明"></textarea>
                </div>
                <div class="form-group">
                    <label>参赛形式</label>
                    &nbsp;&nbsp;&nbsp;
                    <label><input type="radio" name="compt.sp_type" id="comType1" checked>个人赛</label>&nbsp;&nbsp;&nbsp;
                    <label><input type="radio" name="compt.sp_type" id="comType2">小组赛</label>
                    <label style="display: none" id="comType2Text"><input type="text" name="compt.sp_maxMember"><span class="label label-warning">最大人数</span></label>
                </div>
                <div class="form-group">
                    <label>是否需要上传作品</label>
                    &nbsp;&nbsp;&nbsp;
                    <label><input type="radio" name="comIsNeedWorks" id="comIsNeedWorks1" checked>是</label>&nbsp;&nbsp;&nbsp;
                    <label><input type="radio" name="comIsNeedWorks" id="comIsNeedWorks2">否</label>
                </div>
                <div class="form-group" id="comIsNeedWorks">
                    <label class="label label-warning" for="input8">作品上传说明</label>
                    <textarea class="form-control" id="input8" placeholder="作品上传说明"></textarea>
                </div>
                <div class="form-group">
                    <label>是否为资助项目</label>
                    &nbsp;&nbsp;&nbsp;
                    <label><input type="radio" name="comIsSupport" id="comIsSupport1" checked>是</label>&nbsp;&nbsp;&nbsp;
                    <label><input type="radio" name="comIsSupport" id="comIsSupport2">否</label>
                </div>
                <div class="form-group" id="comIsSupport">
                    <label class="label label-warning" for="input9">资助说明</label>
                    <textarea class="form-control" id="input9" placeholder="资助说明"></textarea>
                </div>
                <!--upload file-->
                <div class="form-group">
                    <label for="exampleInputFile">上传附件</label>
                    <input type="file" id="exampleInputFile">
                </div>
                <div>
                    <button type="submit" class="btn btn-success btn-block"><img src="/resources/img/loading.gif" width="25px;"/> 确认发布</button>
                </div>
            </form>
        </div>
    </div>
</div>


<script src="/resources/custom/js/comptManPub.js"></script>
<#include "./snippet/footer.ftl">
