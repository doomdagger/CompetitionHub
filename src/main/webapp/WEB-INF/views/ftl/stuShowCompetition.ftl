<#include "./snippet/header.ftl">

<div class="container-fluid">
    <#include "./snippet/AdminLeftNavbar.ftl">

    <div class="col-sm-10">

        <div class="alert alert-info">
            赛事报名概况
        </div>
		
            <div class="alert">

                <#--&lt;#&ndash;附件&ndash;&gt;
                <#if compt?exists && compt.isNeedFile?if_exists>
				
                </#if>-->

                <#--comptetition-->
                <#if compt?exists && compt?has_content>
                    <div class="alert alert-info">
                        <p>赛事名称：<a target="_blank" href="/compt/detail?link=${compt.ID?if_exists}"><b>${compt.title?if_exists}</b></a>
                        <p>参赛形式：<#if !compt.spType?if_exists><b>小组赛（最多${compt.spNum}人）</b><#else><b>个人赛</b></#if>
                        <p>作品上传：<#if compt.isNeedFile?if_exists><span style="color: #ff0000"><b>需要</b> </span><#else><b>不需要</b></#if>
                        <p>报名状态：<#if compt.status?exists && compt.status==3><span style="color: green"><b>可报名</b> </span><#else><b>报名已截止</b></#if>
                        <br/>
                        <p>备注：报名截止后将无法继续添加成员
                    </div>
                </#if>
				
                <#--成员列表-->
                <div class="alert alert-info">
                <#-- status大于等于4则已经是结束报名状态 -->
                    <h4>
                        <b>参赛成员</b>
                        <#if compt?exists && compt.status?exists && !compt.spType?if_exists && compt.status==3>
                        	<#if canAdd?if_exists>
                        		<a class="btn btn-xs btn-success" id="btnAddMember">添加成员</a>
                    		<#else>
                    			<a class="btn btn-xs btn-danger" href="javascript:void(0)" disabled>已达到最大人数</a>
                        	</#if>
                    	<#else>
                    		<span>(个人赛不能添加成员/报名已截止)</span>
                        </#if>
                    </h4>
                    <div class="panel panel-default">
                        <div class="panel-body">
                            <table class="table table-bordered table-responsive">
                                <tr class="bg-primary">
                                    <th>序号</th>
                                    <th>姓名</th>
                                    <th>学院</th>
                                    <th>专业</th>
                                    <th>学号</th>
                                    <th>邮箱</th>
                                    <th>联系手机</th>
                                    <th>学分帮助</th>
                                    <th width="150px;">管理</th>
                                </tr>
                                <#if signList?exists && signList?has_content>
                                    <#list signList as item>
                                        <tr id="_${item.ID?if_exists}">
                                            <td>${item_index+1}</td>
                                            <td>${item.name?if_exists}</td>
                                            <td>${item.academy?if_exists}</td>
                                            <td>${item.profession?if_exists}</td>
                                            <td>${item.number?if_exists}</td>
                                            <td>${item.email?if_exists}</td>
                                            <td>${item.cellphone?if_exists}</td>
                                            <td><#if item.isHelpCredit?if_exists><span style="color: #ff0000">是</span><#else><span>否</span></#if></td>
                                            <td>
                                                <button class="btn btn-xs btn-info btnEditMember" alt="${item.ID?if_exists}">编辑信息</button>
                                                <#if !item.isLeader?if_exists>
                                                    <button class="btn btn-xs btn-danger btnDelMember" data-toggle="modal" data-target="#delMemberModal" alt="${item.ID?if_exists}">删除组员</button>
                                                <#else>
                                                    <input type="hidden" value="${item.ID?if_exists}" id="link">
                                                </#if>
                                            </td>
                                        </tr>
                                    </#list>
                                </#if>
                            </table><#--table-->
                        </div><#--panel body-->
                    </div><#--panel-->
                </div>

                <#--添加组员-->
                <#if (compt?exists && compt.status?exists && (compt.status<4)) || (compt?exists && !compt.spType?if_exists)>
                    <div class="alert alert-info" id="divAddTeamMember" style="display: none">
                    <form role="form" id="formAddTeamMember" action="" method="post">
                        <div class="form-group">
                            <label for="name">姓名</label>
                            <input class="form-control" type="text" name="name" id="name" placeholder="姓名">
                        </div>
                        <div class="form-group">
                            <label for="academy">学院</label>
                            <input class="form-control" type="text" name="academy" id="academy" placeholder="学院">
                        </div>
                        <div class="form-group">
                            <label for="profession">专业</label>
                            <input class="form-control" type="text" name="profession" id="profession" placeholder="专业">
                        </div>
                        <div class="form-group">
                            <label for="NO">学号</label>
                            <input class="form-control" type="text" name="NO" id="NO" placeholder="学生卡学号">
                        </div>
                        <div class="form-group">
                            <label for="email">邮箱</label>
                            <input class="form-control" type="text" name="email" id="email" placeholder="组员email不能重复">
                        </div>
                        <div class="form-group">
                            <label for="cellphone">联系手机</label>
                            <input class="form-control" type="text" name="cellphone" id="cellphone" placeholder="有效联系手机">
                        </div>
                        <div class="form-group">
                            <label>是否涉及消除处分</label>
                            <div class="form-control">
                                <input type="radio" name="isHelp" value="1">是
                                <input type="radio" name="isHelp" value="0" checked>否
                            </div>
                        </div>
                        <#--button-->
                        <div>
                            <button class="btn btn-success" type="button" id="btnSubmitForm">确定添加</button>
                            <button class="btn btn-warning" type="button" id="btnCancel">取消添加</button>
                        </div>
                    </form>
                </div>
                </#if>

                <#--编辑组员-->
                <div class="alert alert-info" id="divEditTeamMember" style="display: none">
                    <form role="form" id="formEditTeamMember" method="post">
                        <div class="form-group">
                            <label>姓名</label>
                            <input class="form-control" type="text" name="e_name" id="e_name">
                        </div>
                        <div class="form-group">
                            <label>学院</label>
                            <input class="form-control" type="text" name="e_academy" id="e_academy">
                        </div>
                        <div class="form-group">
                            <label>专业</label>
                            <input class="form-control" type="text" name="e_profession" id="e_profession">
                        </div>
                        <div class="form-group">
                            <label>学号</label>
                            <input class="form-control" type="text" name="e_NO" id="e_NO">
                        </div>
                        <div class="form-group">
                            <label>邮箱</label>
                            <input class="form-control" type="text" name="e_email" id="e_email">
                        </div>
                        <div class="form-group">
                            <label>联系手机</label>
                            <input class="form-control" type="text" name="e_cellphone" id="e_cellphone">
                        </div>
                        <input type="hidden" id="flagForIsLeader" value="0">
                        <input type="hidden" id="originEmail" value="0">
                        <input type="hidden" id="signLink" value="0">
                        <div class="form-group" title="有效卡号，用于获奖后的奖金发放" style="display: none" id="divForCard">
                            <label for="creditCard">银行卡号</label>
                            <input type="text" class="form-control" name="creditCard" id="creditCard" placeholder="银行卡号">
                        </div>
                        <div class="form-group">
                            <label>学分帮助</label>
                            <div class="form-control">
                                <input type="radio" name="e_isHelp" value="1">是
                                <input type="radio" name="e_isHelp" value="0">否
                            </div>
                        </div>
                    <#--button-->
                        <div>
                            <button class="btn btn-success" type="button" id="btnSubmitFormEdit">确定编辑</button>
                            <button class="btn btn-warning" type="button" id="btnCancelEdit">取消编辑</button>
                        </div>
                    </form>
                </div>
				
                <#--附件-->
                <#if compt?exists && compt.isNeedFile?exists && compt.isNeedFile>
                    <div class="alert alert-info">
                        <h4>
                            <b>参赛作品</b>
                            <small>(<span style="color: #ff0000">*需要上传·最大10M*</span> 要求所有作品文件压缩到一个rar/zip压缩包再上传，选择上传后要输入作品名和描述)</small>
                            <label class="btn btn-xs btn-success" for="inputForFile">上传作品</label>
                            <button type="button" class="btn btn-xs btn-warning" id="btnCheckForNeedFileIntro">查看附件上传说明</button>
                        </h4>
                        <#--upload intro-->
                        <div class="panel panel-default" style="display: none" id="divNeedFile">
                            <div class="panel-body">
                                <h5><b>附件上传说明：</b></h5>
                                <p>${compt.needFileIntro?if_exists}</p>
                            </div>
                        </div>
                        <#--upload form-->
                        <div id="divForUploadForm" style="display: none">
                            <form role="form" id="formForNeedFile" method="post" enctype="multipart/form-data" action="/student/addWorks">

                                <div class="form-group" id="divForFileName">
                                    <label>文件名</label>
                                    <label class="form-control"><span class="glyphicon glyphicon-file" id="spanForFileName"></span></label>
                                </div>
                                <div class="form-group">
                                	<label for="workName">作品名</label>
                                	<input id="workName" class="form-control" type="text" name="workName" placeholder="输入团队作品的名字">
                                </div>
                                <div class="form-group">
                                    <label>备注说明</label>
                                    <textarea class="form-control" rows="3" name="needFileIntro" id="needFileIntro"></textarea>
                                </div>
                                <button type="button" class="btn btn-xs btn-primary" id="btnForConfirmUpload">确定上传</button>
                                <button type="button" class="btn btn-xs btn-warning" id="btnForCancelUpload">取消上传</button>
                                <#--hidden input-->
                                <input type="file" accept=".rar,.zip" id="inputForFile" name="upLoadFile" style="display: none">
                                <input type="hidden" value="${link?if_exists}" name="link" id="upLink">
                                <input type="hidden" value="${teamNo?if_exists}" name="teamNo" id="upTeamNo">
                            </form>
                        </div>
                        <br/>
                        <#--current upload works-->
                        <div class="panel panel-default">
                            <div class="panel-body">
                                <table class="table table-responsive table-bordered">
                            <tr class="bg-primary">
                                <th>作品</th>
                                <th>上传时间</th>
                                <th>备注说明</th>
                                <th>管理</th>
                            </tr>
                            <#if attach?exists>
                                <tr id="_${attach.ID?if_exists}">
                                    <td><a target="_blank" href="/download/download?path=${attach.savepath?if_exists}" title="点击可下载">${attach.name?if_exists}</a></td>
                                    <td>${attach.createtime?if_exists}</td>
                                    <td>${attach.introduction?if_exists}</td>
                                    <td>
                                        <button class="btn btn-xs btn-danger" id="btnForDelWorks" alt="${attach.ID?if_exists}">删除</button>
                                        <label class="btn btn-xs btn-warning" id="btnForChangeWorks" for="inputForFile">替换</label>
                                    </td>
                                </tr>
                            <#else>
                                <tr>
                                    <td colspan="4" style="text-align: center"><span><b>当前尚未上传参赛作品（只允许上传一个包含相关作品的压缩包）</b></span></td>
                                </tr>
                            </#if>
                        </table>
                            </div>
                        </div>
                    </div>
                </#if>
        </div><#--alert-->
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
                if(json == "AJAX_SUCCESS"){
                    $("#_"+link).html("<td colspan='7' style='text-align: center;color: #ff0000'>删除成功</td>");
                    setTimeout(function(){
                        $("#_"+link).remove();
                    },3000);
                }else{
                    alert("删除失败，请重试");
                }
            });
        });
        /*显示添加成员*/
        $("#btnAddMember").click(function(){
            $("#divAddTeamMember").hide();
            $("#divEditTeamMember").hide();
            $("#divAddTeamMember").fadeIn();
        });
        /*取消添加成员*/
        $("#btnCancel").click(function(){
            $("#divAddTeamMember").fadeOut();
        });
        /*确定添加成员*/
        $("#btnSubmitForm").click(function(){
            //check
            if(isEmpty($("#name").val())
            		|| isEmpty($("#academy").val())
            		|| isEmpty($("#profession").val())
                    || isEmpty($("#NO").val())
                    || isEmpty($("#email").val())
                    || isEmpty($("#cellphone").val())
                    ){
                alert("请将信息填写完整，否则无法提交！");
                return;
            }
            var isHelp = $("input[name='isHelp']:checked").val();
            var url = "/student/addTeamMember";
            var data = {"name":$("#name").val(),"NO":$("#NO").val(),
            			"academy":$("#academy").val(),"profession":$("#profession").val(),
            			"email":$("#email").val(),"cellphone":$("#cellphone").val(),
            			"isHelp":isHelp,"link":$("#link").val()};
            console.log(data);
            $.ajax({
                url: url,
                type: 'POST',
                data: data,
//                contentType: false, //必须
//                processData: false, //必须
                dataType: 'json',
                success: function(json){
                    if(json === "AJAX_SUCCESS"){
                        alert("组员添加成功");
                        location.reload();
                    }else{
                        alert("添加操作失败，检查添加的Email是否存在重复，请重试")
                    }
                }
            });

        });
        /*显示编辑组员*/
        $(".btnEditMember").click(function(){
            //reset status
            $("#divAddTeamMember").hide();
            $("#divForCard").hide();
            $("#flagForIsLeader").val(0);
            formEditTeamMember.reset();
            //show panel
            $("#divEditTeamMember").hide();
            $("#divEditTeamMember").fadeIn();
            //get data
            var link = $(this).attr("alt");
            var url = "/student/getEditMemberInfo";
            var data = {"link":link}
            $.post(url,data,function(json){
                json = $.parseJSON(json);
                if(json.result == "AJAX_SUCCESS"){
                    if(json.data.isLeader){
                        $("#divForCard").show();
                        $("#flagForIsLeader").val(1);
                        $("#creditCard").val(json.data.creditCard);
                    }
                    if(json.data.isHelpCredit){
                        $("input[name='e_isHelp']:first").attr("checked",true);
                    }else{
                    	$("input[name='e_isHelp']:last").attr("checked",true);
                    }
                    $("#signLink").val(json.data.ID);
                    $("#e_name").val(json.data.name);
                    $("#e_academy").val(json.data.academy);
                    $("#e_profession").val(json.data.academy);
                    $("#e_NO").val(json.data.number);
                    $("#e_email").val(json.data.email);
                    $("#originEmail").val(json.data.email);
                    $("#e_cellphone").val(json.data.cellphone);
                }else{
                    alert("获取信息失败，请重试");
                }
            });
        });
        /*取消编辑组员*/
        $("#btnCancelEdit").click(function(){
            $("#divEditTeamMember").fadeOut();
        });
        /*确定编辑*/
        $("#btnSubmitFormEdit").click(function(){
            //check
            if(isEmpty($("#e_name").val())
            		|| isEmpty($("#e_academy").val())
            		|| isEmpty($("#e_profession").val())
                    || isEmpty($("#e_NO").val())
                    || isEmpty($("#e_email").val())
                    || isEmpty($("#e_cellphone").val())
                    || ($("#flagForIsLeader").val()=="1" && isEmpty($("#creditCard").val()))
                    ){
                alert("请将信息填写完整，否则无法提交！");
                return;
            }
            var isHelp = $("input[name='e_isHelp']:checked").val();
            var url = "/student/confirmEditMember";
            var data = {"name":$("#e_name").val(),"NO":$("#e_NO").val(),"email":$("#e_email").val(),
            	"academy":$("#e_academy").val(),"profession":$("#e_profession").val(),
                "cellphone":$("#e_cellphone").val(),"isHelp":isHelp,
                "creditCard":$("#creditCard").val(),"link":$("#link").val(),
                "originEmail":$("#originEmail").val(),"signLink":$("#signLink").val()
            };
            //console.log(data);
            $.ajax({
                url: url,
                type: 'POST',
                data: data,
//                contentType: false, //必须
//                processData: false, //必须
                dataType: 'json',
                success: function(json){
                    if(json === "AJAX_SUCCESS"){
                        alert("组员编辑成功");
                        location.reload();
                    }else{
                        alert("编辑操作失败，检查添加的Email是否存在重复，请重试")
                    }
                }
            });
        });

        /*查看上传说明*/
        $("#btnCheckForNeedFileIntro").click(function(){
            if($("#divNeedFile").css("display") == "none"){
                $("#divNeedFile").fadeIn();
                return;
            }
            $("#divNeedFile").fadeOut();
        });
        /*文件input change*/
        $("#inputForFile").change(function(){
            var fileName = $(this).val();
            fileName = fileName.split("\\");
            fileName = fileName[fileName.length-1];
            if($("#divForUploadForm").css("display") == "none"){
                $("#divForUploadForm").fadeIn();
                $("#spanForFileName").html(fileName);
                return;
            }
        });
        /*取消上传*/
        $("#btnForCancelUpload").click(function(){
            $("#divForUploadForm").fadeOut();
            $("#inputForFile").val("");
            $("#spanForFileName").html("");
            $("#formForNeedFile")[0].reset();//Reset the form data
        });
        /*确定上传*/
        $("#btnForConfirmUpload").click(function(){
            //var link = $("#upLink").val();
            //var teamNo = $("#upTeamNo").val();
            //var intro = $("#needFileIntro").val();
            //var workName = $("#workName").val();
            //$("#formForNeedFile").attr("action",encodeURI(encodeURI("/student/uploadWorks?link="+link+"&teamNo="+teamNo+"&needFileIntro="+intro+"&workName="+workName)));
            $("#formForNeedFile").submit();
        });
        /*删除作品*/
        $("#btnForDelWorks").click(function(){
            if(!confirm("确定要删除当前作品吗？")){
                return;
            }
            var link = $(this).attr("alt");
            var url = "/student/deleteAttachMent";
            var data = {"link":link};
            var currentTR = $("#_"+link);
            $.post(url,data,function(json){
                if(json == "AJAX_SUCCESS"){
                    currentTR.html("<td colspan='4' style='text-align: center;color: green'><span><b>删除操作成功</b></span></td>");
                    setTimeout(function(){
                        currentTR.fadeOut();
                    },3000);
                }else{
                    alert("删除操作失败，请重试");
                }
            });
        });
        /*判空*/
        function isEmpty(str){
            if($.trim(str).length < 1){
                return true;
            }
            return false;
        }

    });
</script>

<#include "./snippet/footer.ftl">