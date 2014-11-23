<#include "./snippet/header.ftl">
<style>
::-webkit-scrollbar-thumb {
    border-radius: 10px;
    background: rgba(0,0,0,0.1);
    -webkit-box-shadow: inset 0 0 6px rgba(0,0,0,0.5);
}
</style>
<div class="container-fluid" style="">
    <!-- line-1 left-1-->
    <div class="col-lg-5">
        <div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
            <!-- Indicators -->
            <ol class="carousel-indicators">
            	<#if adsList?exists && (adsList?size>0)>
            		<#list adsList as item>
            			<li data-target="#carousel-example-generic" data-slide-to="${item_index}" class="<#if item_index == 0>active</#if>"></li>
            		</#list>
            	</#if>
            </ol>
			
            <!-- Wrapper for slides -->
            <div class="carousel-inner">
            	
            	<#if adsList?exists && (adsList?size>0)>
            		<#list adsList as item>
            			<!--one item-->
		                <div class="item <#if item_index == 0>active</#if>" width="100%" style="height:350px;">
		                    <a target="_blank" href="${item.href?if_exists}">
		                    	<img src="${item.image?if_exists}" title="${item.title?if_exists}" width="100%">
		                    </a>
		                    <div class="carousel-caption">
		                        <p>${item.title?if_exists}
		                    </div>
		                </div>
            		</#list>
        		<#else>
        			<div class="item active">
	                    <a href="javscript:void(0)"><img src="/resources/img/img5.jpg" title="大连交通大学软件联盟协会"></a>
	                    <div class="carousel-caption">
	                        <p>大连交通大学软件联盟协会
	                    </div>
	                </div>
            	</#if>
            	
            </div><!--carousel-inner-->

            <!-- Controls -->
            <!--<a class="left carousel-control" href="#carousel-example-generic" data-slide="prev">
                <span class="glyphicon glyphicon-chevron-left"></span>
            </a>
            <a class="right carousel-control" href="#carousel-example-generic" data-slide="next">
                <span class="glyphicon glyphicon-chevron-right"></span>
            </a>-->
        </div>
        <!-- pic top end-->
        <p><br/>
        <div class="panel panel-primary" style="height: 415px">
            <div class="panel-heading">
                <div class="nav">
                    <div class="navbar-left">
                        <label class="label label-danger" style="font-size: 12px;"><b><span class="glyphicon glyphicon-file"></span>新闻中心</b></label>
                    </div>
                    <div class="navbar-right">
                        <a href="/news/" class="btn btn-xs btn-info">更多</a>
                    </div>
                </div>
            </div>

            <div class="list-group" style="font-size: 12px;">
                <#if newsList?? && (newsList?size>0)>
                    <#list newsList as news>
                        <a class="list-group-item" href="/news/detail?link=${news.ID?if_exists}">
                            <#if news.isTop?if_exists>
                                <span class="label label-danger">Top</span>
                            </#if>
                            <label class="label label-warning"><b>${news.adminName?if_exists}</b></label>
                            <span>${news.title?if_exists}</span>
                            <span style="color: #2aabd2">[ 发布时间 ：${news.createtime?if_exists}]</span>
                        </a>
                    </#list>
                <#else>
                    <div class="alert alert-warning">
                        <h4>Sorry!系统暂未发布任何新闻...</h4>
                    </div>
                </#if>
            </div>
        </div>
        <!--line-2 left-3-->
        <div class="panel panel-primary" style="height: 300px">
            <h3><label class="label label-primary center-block">友情链接</label></h3>
            <div class="list-group" style="font-size: 12px;">
            
            	<#if linkList?exists && (adsList?size>0)>
            		<#list linkList as item>
            			<!--one item-->
            			<a target="_blank" href="${item.href?if_exists}" class="list-group-item">${item.title}</a>
            		</#list>
        		<#else>
        			<a href="/index" class="list-group-item">大连交通大学竞赛官网</a>
            	</#if>
            </div>
        </div>
    </div>

    <!--line-1 left-2-->
    <div class="col-sm-7">
        <div class="col-sm-9">
            <div class="panel panel-primary" style="height: 415px">
                <div class="panel-heading">
                    <div class="nav">
                        <div class="navbar-left">
                            <label class="label label-danger" style="font-size: 12px;"><b><span class="glyphicon glyphicon-file"></span>赛事公告</b></label>
                        </div>
                        <div class="navbar-right">
                            <a href="/compt/" class="btn btn-xs btn-info">更多</a>
                        </div>
                    </div>
                </div>


                <#if comptList?? && (comptList?size>0) >
                    <div class="list-group" style="font-size: 12px;">
                        <#list comptList as compt>
                            <a class="list-group-item" href="/compt/detail?link=${compt.ID?if_exists}">
                                <#if compt.isTop?if_exists>
                                    <span class="label label-danger">Top</span>
                                </#if>
                                <label class="label label-warning"><b>${compt.adminName?if_exists}</b></label>
                                <span>${compt.title?if_exists}</span>
                                <span style="color: #2aabd2">[ 发布时间：${compt.createtime?if_exists}]</span>
                            </a>
                        </#list>
                    </div>
                <#else>
                    <div class="alert alert-warning">
                        <h4>Sorry!系统暂未发布任何赛事...</h4>
                    </div>
                </#if>

            </div>
        </div>

        <div class="col-sm-3">
            <div class="panel panel-primary" style="height: 415px;">
                <div class="panel-heading">
                    <div class="nav">
                        <div class="navbar-left">
                            <label class="label label-danger" style="font-size: 12px;"><b><span class="glyphicon glyphicon-user"></span>我的账号</b></label>
                        </div>
                    </div>
                </div>
                <div class="panel-body">
                    <div class="my-index-loginPanel" style="text-align: center">
                        <br/>
                        <#if Session?? && Session["U_LOGIN"]?? && Session["U_LOGIN"]==1>
                            <span class="glyphicon glyphicon-user" id="accountTips">账号信息</span>
                        <#else>
                            <span class="glyphicon glyphicon-user" id="accountTips">账号登录</span>
                        </#if>
                        <p>
                        <div>
                            <br/>

                            <#--login succ-->
                                <div id="indexLoginSucc" <#if Session?? && Session["U_LOGIN"]?? && Session["U_LOGIN"]==1>style="display: block"<#else>style="display: none"</#if> >
                                    <form role="form">
                                        <div class="form-group">
                                            <label class="form-control" id="usernameSucc"><#if Session?? && Session['CUR']?? && Session['CUR']['CUR_NAME']??>${Session['CUR']['CUR_NAME']?if_exists}</#if></label>
                                        </div>
                                        <div class="form-group">
                                            <label class="form-control" id="usertypeSucc">
                                                <#if Session?? && Session['CUR']?? && Session['CUR']['CUR_TYPE']??>
                                                    <#if Session['CUR']['CUR_TYPE'] == 0>学生账号
                                                    <#elseif Session['CUR']['CUR_TYPE'] == 1>管理员账号
                                                    <#elseif Session['CUR']['CUR_TYPE'] == 2>学院账号
                                                    <#elseif Session['CUR']['CUR_TYPE'] == 3>教务账号
                                                    <#else>其他账号
                                                    </#if>
                                                </#if>
                                            </label>
                                        </div>
                                    </form>
                                    <a class="btn btn-success btn-block" id="btnIndexLogOut">注销登录</a>
                                    <br/>
                                    <p><a class="btn btn-primary btn-block" id="btnToAccount"
                                              <#if Session?? && Session['CUR']?? && Session['CUR']['CUR_TYPE']?? && Session['CUR']['CUR_TYPE']==0>
                                              href="/student/"
                                              <#else>
                                              href="/man/"
                                              </#if> >个人中心
                                        </a>
                                    <p><small>仅限大连交通大学在校生注册使用</small>
                                </div>

                            <#--login panel-->
                                <div id="indexLoginPanel" <#if Session?? && Session['U_LOGIN']?? && Session['U_LOGIN']==1>style="display: none"<#else>style="display: block"</#if>>
                                    <form role="form">
                                        <div class="form-group">
                                            <!--<label for="username">用户名</label>-->
                                            <input type="text" class="form-control" id="username" name="username" placeholder="邮箱/学号" value="1209202022@qq.com">
                                        </div>
                                        <div class="form-group">
                                            <!--<label for="pwd">密码</label>-->
                                            <input type="password" class="form-control" id="pwd" name="pwd" placeholder="密码" value="123">
                                        </div>
                                        <div class="form-group">
                                            <label><input type="radio" name="usertype" value="0" id="usertype0" checked="checked">学生</label>&nbsp;&nbsp;
                                            <label><input type="radio" name="usertype" value="1" id="usertype1">教师</label>
                                        </div>
                                        <div class="form-group">
                                            <button type="button" class="btn btn-success btn-block" id="btnIndexLogin">登陆</button>
                                        </div>
                                    </form>
                                    <p><a class="btn btn-primary btn-block"href="/user/regGet">注册帐号</a>
                                    <p><small>仅限大连交通大学在校生注册使用</small>
                                </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
		
        <!--学院风采-->
        <div class="col-sm-12">
            <div class="panel panel-primary scrollBar" style="height: 680px;">
                <h3><label class="label label-primary center-block">学院风采</label></h3>
                <div class="panel-body" style="height: 610px;overflow-y:auto;">
                
                	<#if showList?exists && (showList?size>0)>
                		<#list showList as item>
		               		<!--one item--> 
		                    <div class="media well">
		                    	<a class="pull-left" href="/show/album/${item.ID?if_exists}">
		                    		<img class="media-object img-thumbnail" src="${item.coverImage?if_exists}" style="height:90px;"/> 
		                    	</a>
								<div class="">
									<h4><label calss="label label-info bg-info">${item.title?if_exists}</label></h4>
									<p>${item.description?if_exists}
								</div>
							</div><!--one item-->
						</#list> 
						<div class="text-center">
							<a class="btn btn-default btn-block" href="/show/list">查看更多</a>
						</div>
					<#else>
						<div class="text-center showItem">
			          		<h1><b>内容还是空的哦</b></h1>
			          	</div>
					</#if>
					
					
                </div>
            </div>
        </div>
    </div>
    <!--col-sm-7 end-->

</div>
<!--line-1 end -->


<#--custom-->
<script type="text/javascript" src="/resources/custom/js/common.js"></script>
<#--md5-->
<script src="/resources/dist/js/jquery.md5.js"></script>

<#include "./snippet/comptOnDate.ftl">


<#include "./snippet/footer.ftl">
