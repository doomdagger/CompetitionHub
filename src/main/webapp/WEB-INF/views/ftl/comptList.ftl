<#include "./snippet/header.ftl">



<div class="container-fluid" style="">
    <div class="col-sm-3">
        <!--account panel-->
        <div class="panel panel-primary" style="height: 250px;">
            <div class="panel-heading"><div class="navbar-left">
                <a class="btn btn-default"><b><span class="glyphicon glyphicon-file"></span>我的账号</b></a>
            </div></div>
            <div class="panel-body">
                <div class="my-index-loginPanel" style="text-align: center">
                    <span class="glyphicon glyphicon-user">登陆到我的帐号</span>
                    <p>
                    <div>
                        <img src="/resources/img/img7.jpg" style="width: 100px" alt="..." class="img-thumbnail">
                        <p><h6>姓名：周荣辉</h6>
                        <p><h6>身份：管理员</h6>
                        <p><button class="btn btn-info btn-block">管理中心</button>
                    </div>
                </div>
            </div>
        </div>

        <!--news panel-->
        <div class="panel panel-primary" style="height: 320px;">
            <div class="panel-heading"><span class="glyphicon glyphicon-file sr-only"></span>
                <div class="navbar-left">
                    <a class="btn btn-default"><b><span class="glyphicon glyphicon-file"></span>推荐新闻</b></a>
                </div>
            </div>
            <div class="list-group" style="font-size: 11px;">
                <#if newsList?? && (newsList?size>0)>
                    <#list newsList as news>
                        <a class="list-group-item" href="newsDetail.html">
                            <#if news.isTop?if_exists>
                                <span class="label label-danger">Top</span>
                            </#if>
                            <span>${news.title?if_exists}</span>
                        </a>
                    </#list>
                <#else>
                    <div class="jumbotron">
                        <h3>Sorry,系统暂未发布新闻...</h3>
                    </div>
                </#if>
            </div>
        </div>
    </div>


    <div class="col-sm-9">
        <div>
            <div class="panel panel-primary" style="height: 586px;">
                <div class="panel-heading">
                    <div class="navbar-left">
                        <a class="btn btn-default"><b><span class="glyphicon glyphicon-file"></span>赛事公告</b></a>
                    </div>
                </div>
                <div class="list-group" style="font-size: 13px;">
                    <#if comptList?? && (comptList?size>0)>
                        <#list comptList as compt>
                            <a class="list-group-item" href="comDetail.html">
                                <#if compt.isTop?if_exists>
                                    <span class="label label-danger">Top</span><#--置顶提示-->
                                </#if>
                                <span>${compt.title?if_exists}</span>
                                <span style="color: #46b8da"> [ 发布时间：${compt.createtime?if_exists}]</span>
                            </a>
                        </#list>
                    <#else>
                        <div class="jumbotron">
                            <h3>Sorry,系统暂未发布赛事...</h3>
                        </div>
                    </#if>
                </div>

            </div>

            <#--pageable-->
            <div style="text-align: right">
                <ul class="pagination">
                <#if pageable??>
                    <#if pageable.pageCount == 1>
                        <li><a>&laquo;</a></li>
                        <li class="active"><a>1</a></li>
                        <li><a>&raquo;</a></li>
                    <#else>
                    <#--here is the << -->
                        <#if pageable.currentPage == 1>
                            <li><a>&laquo;</a></li>
                        <#else>
                            <li><a href="/compt/list/${pageable.currentPage-1}">&laquo;</a></li>
                        </#if>

                        <#--中间循环-->
                        <#list 1..pageable.pageCount as p>
                            <#if p == pageable.currentPage>
                                <li class="active"><a>${p}</a></li>
                            <#else>
                                <li><a href="/compt/list/${p}">${p}</a></li>
                            </#if>
                        </#list>

                    <#--here is the >> -->
                        <#if pageable.currentPage == pageable.pageCount>
                            <li><a>&raquo;</a></li>
                        <#else>
                            <li><a href="/compt/list/${pageable.currentPage+1}">&raquo;</a></li>
                        </#if>
                    </#if>
                </#if>
                </ul>
            </div>
        </div>
    </div>
</div>

<div class="container">

</div>




<#include "./snippet/comptOnDate.ftl">


<#include "./snippet/footer.ftl">