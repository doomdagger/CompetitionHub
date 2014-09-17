<#include "./snippet/header.ftl">

<div class="container-fluid">
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
                <div>
                    <label class="label label-danger" id="newsTips"></label>
                </div>
                <table class="table table-responsive table-bordered numControl"  style="text-align: left;font-size: 13px;">
                    <tr class="bg-primary">
                        <th>序号</th>
                        <th style="width: 180px;">新闻标题</th>
                        <th>发布日期</th>
                        <th style="width: 180px;">置顶</th>
                        <th>编辑</th>
                        <th>删除</th>
                    </tr>
                    <#if newsList?exists>
                        <#list newsList as news>

                            <tr id="${news.ID?if_exists}">
                                <td>${news_index+1}</td>
                                <td><a href="javascript:void(0)" onclick="window.open('/news/detail?link=${news?if_exists.ID}')">${news.title?if_exists}</a></td>
                                <td>${news.createtime?if_exists}</td>
                                <#if !news.isTop?if_exists>
                                    <td>
                                        <label class="label label-warning isTop" style="display: none">当前置顶</label>
                                        <a class="btn btn-info btn-xs newsToTop" alt="${news.ID?if_exists}">置顶</a>
                                        <a class="btn btn-primary btn-xs newsNoTop" alt="${news.ID?if_exists}" style="display: none">取消置顶</a>
                                    </td>
                                <#else>
                                    <td>
                                        <label class="label label-warning isTop">当前置顶</label>
                                        <a class="btn btn-primary btn-xs newsNoTop" alt="${news.ID?if_exists}">取消置顶</a>
                                        <a class="btn btn-info btn-xs newsToTop" alt="${news.ID?if_exists}" style="display: none">置顶</a>
                                    </td>
                                </#if>

                                <td><a class="btn btn-xs btn-primary" alt="${news.ID}" href="/man/news/manUpdateGet?link=${news.ID?if_exists}">编辑</a></td>
                                <td><a class="btn btn-xs btn-danger preDelNews" alt="${news.ID?if_exists}" data-toggle="modal" data-target="#myModal">删除赛事</a></td>
                            </tr>

                        </#list>
                    </#if>

                </table>
            </div>
            <!--pageable-->
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
                                <li><a href="/man/news/manList/${pageable.currentPage-1}">&laquo;</a></li>
                            </#if>

                            <#--中间循环-->
                            <#list 1..pageable.pageCount as p>
                                <#if p == pageable.currentPage>
                                    <li class="active"><a>${p}</a></li>
                                <#else>
                                    <li><a href="/man/news/manList/${p}">${p}</a></li>
                                </#if>
                            </#list>

                            <#--here is the >> -->
                            <#if pageable.currentPage == pageable.pageCount>
                                <li><a>&raquo;</a></li>
                            <#else>
                                <li><a href="/man/news/manList/${pageable.currentPage+1}">&raquo;</a></li>
                            </#if>
                        </#if>
                    </#if>
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
                <input type="hidden" value="" id="preDelNewsId">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" id="doDelNews" class="btn btn-danger" data-dismiss="modal">删除</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->



<script src="/resources/custom/js/userAcNews.js" type="text/javascript"></script>
<#include "./snippet/footer.ftl">