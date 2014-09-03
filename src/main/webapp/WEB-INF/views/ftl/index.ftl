<#include "./snippet/header.ftl">



<div class="container-fluid" style="">
    <!-- line-1 left-1-->
    <div class="col-lg-5">
        <div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
            <!-- Indicators -->
            <ol class="carousel-indicators">
                <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
                <li data-target="#carousel-example-generic" data-slide-to="1"></li>
                <li data-target="#carousel-example-generic" data-slide-to="2"></li>
                <li data-target="#carousel-example-generic" data-slide-to="3"></li>
                <li data-target="#carousel-example-generic" data-slide-to="4"></li>
            </ol>

            <!-- Wrapper for slides -->
            <div class="carousel-inner">
                <!--one item-->
                <div class="item active">
                    <a href="#"><img src="/resources/img/img5.jpg" alt="..."></a>
                    <div class="carousel-caption">
                        <p>这里可以添加标题
                    </div>
                </div>
                <!--one item-->
                <div class="item">
                    <img src="/resources/img/img5.jpg" alt="...">
                    <div class="carousel-caption">
                        <p>这里可以添加标题
                    </div>
                </div>
                <!--one item-->
                <div class="item">
                    <img src="/resources/img/img5.jpg" alt="...">
                    <div class="carousel-caption">
                        <p>这里可以添加标题
                    </div>
                </div>
                <!--one item-->
                <div class="item">
                    <img src="/resources/img/img5.jpg" alt="...">
                    <div class="carousel-caption">
                        <p>这里可以添加标题
                    </div>
                </div>
                <!--one item-->
                <div class="item">
                    <img src="/resources/img/img5.jpg" alt="...">
                    <div class="carousel-caption">
                        <p>这里可以添加标题
                    </div>
                </div>

            </div>

            <!-- Controls -->
            <a class="left carousel-control" href="#carousel-example-generic" data-slide="prev">
                <span class="glyphicon glyphicon-chevron-left"></span>
            </a>
            <a class="right carousel-control" href="#carousel-example-generic" data-slide="next">
                <span class="glyphicon glyphicon-chevron-right"></span>
            </a>
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
                            <span>${news.title?if_exists}</span>
                            <span style="color: #2aabd2">[ 发布时间 ：${news.createtime?if_exists}]</span></a>
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
                <a href="/index" class="list-group-item">大连交通大学竞赛官网</a>
                <a href="http://www.djtu.edu.cn" class="list-group-item">大连交通大学官网</a>
                <a href="http://jw.djtu.edu.cn" class="list-group-item">大连交通大学教务在线</a>
                <a href="#" class="list-group-item">大连理工大学创新可以官网</a>
                <a href="#" class="list-group-item">中国挑战杯官网</a>
                <a href="#" class="list-group-item">中国挑战杯官网</a>
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
                                <span>${compt.title?if_exists}</span>
                                <span style="color: #2aabd2">[ 发布时间：${compt.createtime?if_exists}]</span></a>
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
                                            <input type="text" class="form-control" id="username" name="username" placeholder="邮箱/学号" style="font-size: 6px;">
                                        </div>
                                        <div class="form-group">
                                            <!--<label for="pwd">密码</label>-->
                                            <input type="password" class="form-control" id="pwd" name="pwd" placeholder="密码" style="font-size: 6px;">
                                        </div>
                                        <div class="form-group">
                                            <label><input type="radio" name="usertype" value="0" id="usertype0" checked="checked">学生</label>&nbsp;&nbsp;
                                            <label><input type="radio" name="usertype" value="1" id="usertype1">老师</label>
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
            <div class="panel panel-primary" style="height: 647px">
                <h3><label class="label label-primary center-block">学院风采</label></h3>
                <div class="panel-body">
                    <div class="list-group">
                        <div class="list-group-item">
                            <div>
                                <h4 class="list-group-item-heading">List group item heading</h4>
                                <img class="list-group-item-text" src="/resources/img/img7.jpg" height="50px">
                                <p class="list-group-item-text">hello world</p>
                            </div>
                        </div>
                        <div class="list-group-item">
                            <div>
                                <h4 class="list-group-item-heading">List group item heading</h4>
                                <img class="list-group-item-text" src="/resources/img/img7.jpg" height="50px">
                                <p class="list-group-item-text">hello world</p>
                            </div>
                        </div>
                        <div class="list-group-item">
                            <div>
                                <h4 class="list-group-item-heading">List group item heading</h4>
                                <img class="list-group-item-text" src="/resources/img/img7.jpg" height="50px">
                                <p class="list-group-item-text">hello world</p>
                            </div>
                        </div>
                        <div class="list-group-item">
                            <div>
                                <h4 class="list-group-item-heading">List group item heading</h4>
                                <img class="list-group-item-text" src="/resources/img/img7.jpg" height="50px">
                                <p class="list-group-item-text">hello world</p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!--col-sm-7 end-->

</div>
<!--line-1 end -->


<#--custom-->
<script type="text/javascript" src="/resources/custom/js/common.js"></script>

<#include "./snippet/comptOnDate.ftl">


<#include "./snippet/footer.ftl">
