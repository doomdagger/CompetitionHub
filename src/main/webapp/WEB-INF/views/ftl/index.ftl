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
                    <a href="#"><img src="resources/img/img3.jpg" alt="..."></a>
                    <div class="carousel-caption">
                        <p>这里可以添加标题
                    </div>
                </div>
                <!--one item-->
                <div class="item">
                    <img src="resources/img/img4.jpg" alt="...">
                    <div class="carousel-caption">
                        <p>这里可以添加标题
                    </div>
                </div>
                <!--one item-->
                <div class="item">
                    <img src="resources/img/img5.jpg" alt="...">
                    <div class="carousel-caption">
                        <p>这里可以添加标题
                    </div>
                </div>
                <!--one item-->
                <div class="item">
                    <img src="resources/img/img6.jpg" alt="...">
                    <div class="carousel-caption">
                        <p>这里可以添加标题
                    </div>
                </div>
                <!--one item-->
                <div class="item">
                    <img src="resources/img/img7.jpg" alt="...">
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
                <span class="glyphicon glyphicon-file sr-only"></span>
                <div class="navbar-left">
                    <a class="btn btn-default"><b><span class="glyphicon glyphicon-file"></span>新闻中心</b></a>
                    <a href="/news/list" class="btn btn-default btn-xs btn-info">查看更多</a>
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
                    <div class="jumbotron">
                        <h4>Sorry!系统暂未发布任何新闻...</h4>
                    </div>
                </#if>
            </div>
        </div>
        <!--line-2 left-3-->
        <div class="panel panel-default" style="height: 300px">
            <h3><label class="label label-primary center-block">友情链接</label></h3>
            <div class="list-group" style="font-size: 12px;">
                <a href="#" class="list-group-item">大连交通大学竞赛官网</a>
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
                    <span class="glyphicon glyphicon-file sr-only"></span>
                    <div class="navbar-left">
                        <a class="btn btn-default"><b><span class="glyphicon glyphicon-file"></span>新闻中心</b></a>
                        <a href="/compt/list" class="btn btn-default btn-xs btn-info">查看更多</a>
                    </div>
                </div>

                <div class="list-group" style="font-size: 12px;">
                    <#if comptList?? && (comptList?size>0) >
                        <#list comptList as compt>
                            <a class="list-group-item" href="/compt/detail?link=${compt.ID?if_exists}">
                                <#if compt.isTop?if_exists>
                                    <span class="label label-danger">Top</span>
                                </#if>
                                <span>${compt.title?if_exists}</span>
                                <span style="color: #2aabd2">[ 发布时间：${compt.createtime?if_exists}]</span></a>
                        </#list>
                    <#else>
                        <div class="jumbotron">
                            <h4>Sorry!系统暂未发布任何赛事...</h4>
                        </div>
                    </#if>
                </div>
            </div>
        </div>

        <div class="col-sm-3">
            <div class="panel panel-primary" style="height: 415px;">
                <div class="panel-heading">
                    <div class="navbar-left">
                        <a class="btn btn-default"><b><span class="glyphicon glyphicon-file"></span>我的账号</b></a>
                    </div>
                </div>
                <div class="panel-body">
                    <div class="my-index-loginPanel" style="text-align: center">
                        <span class="glyphicon glyphicon-user">个人信息</span>
                        <p>
                        <div>
                            <img src="resources/img/img7.jpg" style="width: 100px" alt="..." class="img-thumbnail">
                            <p><h6>姓名：周荣辉</h6>
                            <p><h6>身份：学生</h6>
                            <p><h6>学院：机械学院</h6>
                            <p><h6>专业：软件工程</h6>
                            <p><h6>学号：1018160222</h6>
                            <p><button class="btn btn-info btn-block">管理中心</button>
                            <p><button class="btn btn-warning btn-block">注销帐号</button>
                            <p><button class="btn btn-primary btn-block">注册帐号</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!--学院风采-->
        <div class="col-sm-12">
            <div class="panel panel-default" style="height: 647px">
                <h3><label class="label label-primary center-block">学院风采</label></h3>
                <div class="panel-body">
                    <div class="list-group">
                        <div class="list-group-item">
                            <div>
                                <h4 class="list-group-item-heading">List group item heading</h4>
                                <img class="list-group-item-text" src="resources/img/img7.jpg" height="50px">
                                <p class="list-group-item-text">hello world</p>
                            </div>
                        </div>
                        <div class="list-group-item">
                            <div>
                                <h4 class="list-group-item-heading">List group item heading</h4>
                                <img class="list-group-item-text" src="resources/img/img7.jpg" height="50px">
                                <p class="list-group-item-text">hello world</p>
                            </div>
                        </div>
                        <div class="list-group-item">
                            <div>
                                <h4 class="list-group-item-heading">List group item heading</h4>
                                <img class="list-group-item-text" src="resources/img/img7.jpg" height="50px">
                                <p class="list-group-item-text">hello world</p>
                            </div>
                        </div>
                        <div class="list-group-item">
                            <div>
                                <h4 class="list-group-item-heading">List group item heading</h4>
                                <img class="list-group-item-text" src="resources/img/img7.jpg" height="50px">
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

<#include "./snippet/comptOnDate.ftl">


<#include "./snippet/footer.ftl">
