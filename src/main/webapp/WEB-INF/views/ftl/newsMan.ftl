<#include "./snippet/header.ftl">

<div class="container"  style="margin-left:50px">
    <#include "./snippet/AdminLeftNavbar.ftl">

    <div class="col-sm-10">
        <div class="alert alert-info">
            新闻管理
        </div>
        <!--table th-->
        <div class="alert">
            <div>
                <a class="btn btn-info btn-block" href="/man/news/manPublishGet">发布新闻</a>
                <p>&nbsp;
            </div>

            <div>
                <table class="table table-striped table-bordered table-condensed numControl"  style="text-align: left;font-size: 13px;">
                    <tr>
                        <th>序号</th>
                        <th style="width: 180px;">新闻标题</th>
                        <th>发布日期</th>
                        <th>置顶</th>
                        <th>删除</th>
                    </tr>
                    <#if newsList?exists>
                        <#list newsList as news>

                            <tr>
                                <td>${news_index+1}</td>
                                <td><a href="/man/news/manDetail/">今日大事件</a></td>
                                <td>${news.createtime?if_exists}</td>
                                <#if !news.isTop?if_exists>
                                    <td><a class="btn btn-info btn-xs newsToTop" id="${news.ID}">置顶</a></td>
                                <#else>
                                    <td><a class="btn btn-primary btn-xs newsNoTop" id="${news.ID}">取消置顶</a></td>
                                </#if>

                                <td><a class="btn btn-xs btn-danger" id="${news.ID}" data-toggle="modal" data-target="#myModal">删除赛事</a></td>
                            </tr>

                        </#list>
                    </#if>

                    <tr>
                        <td>1</td>
                        <td><a href="/man/news/manDetail">今日大事件</a></td>
                        <td>2014-05-22 10：00：00</td>
                        <td><a class="btn btn-info btn-xs">置顶</a></td>
                        <td><a class="btn btn-xs btn-danger" data-toggle="modal" data-target="#myModal">删除赛事</a></td>
                    </tr>
                    <tr>
                        <td>2</td>
                        <td><a href="/man/news/manDetail">今日大事件</a></td>
                        <td>2014-05-22 10：00：00</td>
                        <td><a class="btn btn-primary btn-xs">取消置顶</a></td>
                        <td><a class="btn btn-xs btn-danger" data-toggle="modal" data-target="#myModal">删除赛事</a></td>
                    </tr>

                </table>
            </div>
            <!--pageable-->
            <div style="text-align: right">
                <ul class="pagination">
                    <li><a href="#">&laquo;</a></li>
                    <li class="active"><a href="#">1</a></li>
                    <li><a href="#">2</a></li>
                    <li><a href="#">...</a></li>
                    <li><a href="#">4</a></li>
                    <li><a href="#">5</a></li>
                    <li><a href="#">&raquo;</a></li>
                </ul>
            </div>
        </div>
    </div>
</div>




<!-- Modal for delete a competition-->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">删除该新闻</h4>
            </div>
            <div class="modal-body">
                确认删除该新闻
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-danger" data-dismiss="modal">删除</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->



<script src="/resources/custom/js/userAcNews.js" type="text/javascript"></script>
<#include "./snippet/footer.ftl">