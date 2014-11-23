<#include "./snippet/header.ftl">



<div class="container-fluid">
    <#include "./snippet/AdminLeftNavbar.ftl">

    <div class="col-sm-10">
        <div class="alert alert-info">
            <a href="/man/compt/list">赛事管理</a> / <a href="/man/compt/sign/checkSignList/${leader.skTCompt?if_exists}">报名审核</a> / <span>详细信息</span>
        </div>
		
		<div class="alert alert-info">
			<div class="panel panel-default">
				<div class="panel-body">
					<span><b>指导老师：</b> ${leader.teacher?if_exists}</span>
				</div>
			</div>
		</div>
        <#--leader info-->
        <div class="alert alert-info">
            <div class="panel panel-default">
                <div class="panel-body">
                    <table class="table table-responsive table-bordered">
                        <tr class="bg-primary">
                            <th width="20%"><span><b>(组长)个人信息</b></span></th>
                        </tr>
                        <tr>
                            <td><b>姓名</b></td>
                            <td>${leaderInfo.username?if_exists}</td>
                        </tr>
                        <tr>
                            <td><b>学院</b></td>
                            <td>${leaderInfo.academy?if_exists}</td>
                        </tr>
                        <tr>
                            <td><b>专业</b></td>
                            <td>${leaderInfo.profession?if_exists}</td>
                        </tr>
                        <tr>
                            <td><b>学号</b></td>
                            <td>${leader.number?if_exists}</td>
                        </tr>
                        <tr>
                            <td><b>邮箱</b></td>
                            <td>${leader.email?if_exists}</td>
                        </tr>
                        <tr>
                            <td><b>手机</b></td>
                            <td>${leader.cellphone?if_exists}</td>
                        </tr>
                        <tr>
                            <td><b>银行卡号</b></td>
                            <td>${leader.creditCard?if_exists}</td>
                        </tr>
                        <tr>
                            <td><b>作品名称</b></td>
                            <td>
	                            <#if attach?exists>
	                            	<a href="/download/download?path=${attach.savepath?if_exists}"><span class="glyphicon glyphicon-floppy-disk"/>${attach.name?if_exists}</a>
	                            	(点击文件进行下载)
	                            <#else>
	                        		无
	                            </#if>
                            </td>
                        </tr>
                        <tr>
                            <td><b>作品描述</b></td>
                            <td><#if attach?exists>${attach.introduction?if_exists}<#else>无</#if></td>
                        </tr>
                        <!--
                        <tr>
                            <td><b>所属银行</b></td>
                            <td>${leader.creditCardBank}</td>
                        </tr>
                        -->
                    </table>
                </div><#--panel-body-->

            </div><#--panel-default-->
        </div>

        <#--member list-->
        <div class="alert alert-info">
            <div class="panel panel-default">
                <div class="panel-body">
                    <table class="table table-responsive table-bordered">
                        <tr class="bg-primary">
                            <td><span><b>组员列表</b></span></td>
                        </tr>
                        <tr class="bg-primary">
                            <th>序号</th>
                            <th>姓名</th>
                            <th>学院</th>
                            <th>专业</th>
                            <th>学号</th>
                            <th>邮箱</th>
                            <th>联系手机</th>
                            <th>学分帮助</th>
                        </tr>
                        <#if signList?exists && (signList?size>0) >
                        	<#list signList as item>
                        		<tr>
		                            <td>${item_index+1}</td>
		                            <td>${item.name?if_exists}</td>
		                            <td>${item.academy?if_exists}</td>
		                            <td>${item.profession?if_exists}</td>
		                            <td>${item.number?if_exists}</td>
		                            <td>${item.email?if_exists}</td>
		                            <td>${item.cellphone?if_exists}</td>
		                            <td>
		                            	<#if item.isHelpCredit?if_exists>
		                            		<span style="color: #ff0000">是</span>
	                            		<#else>
	                            			<span>否</span>
		                            	</#if>
	                            	</td>
		                        </tr>
                        	</#list>
                    	<#else>
                    		<tr><td><span><b>无组员</b></span></td></tr>
                        </#if>
                    </table>
                </div><#--panel-body-->
            </div><#--panel-default-->
        </div>

    </div>
</div>

<script type="text/javascript" src="/resources/custom/js/comptManSign.js"></script>
<#include "./snippet/footer.ftl">