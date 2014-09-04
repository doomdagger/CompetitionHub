<#include "./snippet/header.ftl">

<div class="container-fluid">
    <#include "./snippet/AdminLeftNavbar.ftl">

    <div class="col-sm-10">
        <div class="alert alert-info">
            我的赛事
        </div>
        <!--table th-->
        <div class="alert">
            <div class="alert alert-warning">
                <h3>说明</h3>
                <p>报名后要等待赛事发布(如学院)方同意确认
            </div>
            <div>
                <table class="table table-striped table-bordered table-condensed numControl"  style="text-align: left;font-size: 13px;">
                    <tr>
                        <th>序号</th>
                        <th style="width: 180px;">赛事名称</th>
                        <th>比赛进行时间</th>
                        <th>参赛形式</th>
                        <th>身份</th>
                        <th>报名状态</th>
                        <th style="width: 200px;">管理</th>
                    </tr>
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
            </div><#--pegeable end-->


        </div>
    </div>
</div>


<!-- Modal for delete a competition-->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">退出赛事</h4>
            </div>
            <div class="modal-body">
                <span>确认退出此赛事（此操作不可逆，请谨慎操作）</span>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-danger" data-dismiss="modal" id="">确认退出</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->



<#include "./snippet/footer.ftl">