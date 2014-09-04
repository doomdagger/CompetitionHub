<#include "./snippet/header.ftl">



<div class="container-fluid" style="">
    <div class="col-sm-3">
        <!--account panel-->
        <div class="panel panel-primary" style="height: 250px;">
            <div class="panel-heading">
                <div class="nav">
                    <div class="navbar-left">
                        <label class="label label-danger" style="font-size: 12px;"><b><span class="glyphicon glyphicon-user"></span>我的账号</b></label>
                    </div>
                </div>
            </div>
            <div class="panel-body">
                <div class="my-index-loginPanel" style="text-align: center">
                    <span class="glyphicon glyphicon-user">账号登陆</span>
                    <p>
                    <div>
                        <#--<img src="/resources/img/img7.jpg" style="width: 100px" alt="..." class="img-thumbnail">-->
                        <br/>
                        <form role="form">
                            <div class="form-group">
                                <!--<label for="username">用户名</label>-->
                                <input type="text" class="form-control" id="username" name="username" placeholder="邮箱/学号">
                            </div>
                            <div class="form-group">
                                <!--<label for="pwd">密码</label>-->
                                <input type="password" class="form-control" id="pwd" name="pwd" placeholder="密码">
                            </div>
                            <div class="form-group">
                                <button type="button" class="btn btn-success btn-block">登陆</button>
                            </div>
                        </form>
                        <#--<p><button class="btn btn-primary btn-block">注册帐号</button>
                        <p><small>仅限大连交通大学在校生注册使用</small>-->
                    </div>
                </div>
            </div>
        </div>

        <!--news panel-->
        <div class="panel panel-primary" style="height: 320px;">
            <div class="panel-heading">
                <div class="nav">
                    <div class="navbar-left">
                        <label class="label label-danger" style="font-size: 12px;"><b><span class="glyphicon glyphicon-file"></span>推荐新闻</b></label>
                    </div>
                    <div class="navbar-right">
                        <a href="/news/" class="btn btn-xs btn-info">更多</a>
                    </div>
                </div>
            </div>
            <div class="list-group" style="font-size: 11px;">
                <#if newsList?? && (newsList?size>0)>
                    <#list newsList as news>
                        <a class="list-group-item" href="/news/detail?link=${news.ID?if_exists}">
                            <#if news.isTop?if_exists>
                                <span class="label label-danger">Top</span>
                            </#if>
                            <span>${news.title?if_exists}</span>
                        </a>
                    </#list>
                <#else>
                    <div class="alert alert-warning">
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
                    <div class="nav">
                        <div class="navbar-left">
                            <label class="label label-danger" style="font-size: 12px;"><b><span class="glyphicon glyphicon-file"></span>赛事公告</b></label>
                        </div>
                        <#--<div class="navbar-right">
                            <a href="/compt/" class="btn btn-xs btn-info">更多</a>
                        </div>-->
                    </div>
                </div>
                <div class="list-group" style="font-size: 13px;">
                    <#if comptList?? && (comptList?size>0)>
                        <#list comptList as compt>
                            <a class="list-group-item" href="/compt/detail?link=${compt.ID?if_exists}">
                                <#if compt.isTop?if_exists>
                                    <span class="label label-danger">Top</span><#--置顶提示-->
                                </#if>
                                <span>${compt.title?if_exists}</span>
                                <span style="color: #46b8da"> [ 发布时间：${compt.createtime?if_exists}]</span>
                            </a>
                        </#list>
                    <#else>
                        <div class="alert alert-warning">
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