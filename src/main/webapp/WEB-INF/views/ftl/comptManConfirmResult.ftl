<#include "./snippet/header.ftl">

<div class="container-fluid">
    <#include "./snippet/AdminLeftNavbar.ftl">

    <div class="col-sm-10">
        <div class="alert alert-info">
           	 赛事管理 / 赛后成绩录入
        </div>
        <!--table th-->
        <div class="alert">
            <div class="alert alert-info">
                <div class="text-center">
                	<h4><b>赛事：</b>${compt.title?if_exists}</h4>
                	<div id="divForConfirm">
	                	<#if compt.isReward?if_exists>
		                	<span><b>已经确认获奖</b></span> &nbsp; <a class="btn btn-success" href="/man/compt/sign/result/addResultTeam?comptLink=${comptLink?if_exists}">继续录入成绩</a>
	                	<#else>
	                		<span><a class="btn btn-lg btn-success" href="javascript:void(0)" id="btnForYes">确认该赛事参赛者已获奖</a></span>
	                		<span><a class="btn btn-lg btn-warning" href="javascript:void(0)" id="btnForNo">该赛事没有获得任何奖项</a></span>
	                	</#if>
                	</div>
                </div>
            </div>
            
            <!--上传佐证材料-->
            <div class="alert alert-info" style="display:none" id="divForUpFile">
            	<div class="">
	            	<h3><span class="glyphicon glyphicon-eye-open"/>上传获奖证明材料</h3>
	            	<p><b>备注：</b> 为确保赛事获奖的真实性，需要上传说明材料(目前只能上传一次，过后不能更改，慎重上传)</p>
	            	<p><b>格式：</b> 将所有相关文件打包成.rar/.zip格式，大小不能超过10M</p>
	            	<div class="" style="padding:20px;">
	            		<br/>
	            		<form class="form-horizontal" role="form" action="/man/compt/sign/result/confirmResultTeam" method="post" id="formForUpFile" enctype="multipart/form-data">
	            			<div class="form-group">
	            				<button type="button" class="btn btn-success" id="btnForUpFile">选择文件</button>
	            				<input type="file" name="upFile" id="upFile" class="sr-only" accept=".rar,.zip">
	            				<label id="labelForUpFile"><label>
	            			</div>
	            			<div class="form-group">
	            				<label for="remark">备注说明</label>
	            				<textarea class="form-control" placeholder="相关备注说明" name="remark" id="remark"></textarea>
	            			</div>
	            			<div class="form-group">
	            				<input type="hidden" value="reward" name="type">
	            				<input type="hidden" value="${comptLink?if_exists}" name="comptLink" id="comptLink">
	            				<button type="button" class="btn btn-xs btn-info" id="btnForSubmit">确认获奖并提交材料</button>
	            				<button type="button" class="btn btn-xs btn-warning" id="btnForNextTime">下次再说</button>
	            			</div>
	            		</form>
	            	</div>
            	</div><!--alert-->
            </div>
        </div>
    </div>
</div>

<script>
	$("#btnForUpFile").click(function(){
		$("#upFile").click();
	});
	$("#upFile").change(function(){
		var filename = $(this).val();
		$("#labelForUpFile").html("<b>文件名：</b>"+filename);
	});
	$("#btnForNextTime").click(function(){
		$("#divForUpFile").fadeOut();
	});
	$("#btnForYes").click(function(){
		$("#divForUpFile").fadeIn();
	});
	$("#btnForSubmit").click(function(){
		if(confirm("确认该赛事中有参赛者获奖，并在下一步对参赛者进行成绩录入")){
			//location.href="/man/compt/sign/result/confirmResultTeam?type=reward&comptLink="+$("#comptLink").val();
			$("#formForUpFile").submit();
		}
	});
	$("#btnForNo").click(function(){
		if(confirm("确认该赛事中没有有参赛者获奖，且赛事所有流程进行结束")){
			location.href="/man/compt/sign/result/confirmNoReward?comptLink="+$("#comptLink").val();
		}
	});
</script>



<script src="/resources/custom/js/comptManList.js" type="text/javascript"></script>
<#include "./snippet/footer.ftl">