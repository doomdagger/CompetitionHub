<#include "./snippet/header.ftl">

<div class="container-fluid">
    <#include "./snippet/AdminLeftNavbar.ftl">

    <div class="col-sm-10">
        <div class="alert alert-info">
            我的赛事
        </div>
        <!--table th-->
        <div class="alert">
            <div class="alert alert-info">
                <h3>说明</h3>
                <p>报名后要等待赛事发布(如学院)方同意确认
                <p>在"赛事概况"中管理队伍的成员和上传作品
            </div>
            <div>
                <table class="table table-responsive table-bordered numControl"  style="text-align: left;font-size: 13px;">
                    <tr class="bg-primary">
                        <th>序号</th>
                        <th style="width: 180px;">赛事名称</th>
                        <th>报名时间</th>
                        <th>参赛形式</th>
                        <th>参赛身份</th>
                        <th>学分帮助</th>
                        <th style="width: 180px;">管理</th>
                    </tr>
                    <#if signList?exists && (signList?size>0)>
                        <#list signList as item>
                            <tr>
                                <td>${item_index+1}</td>
                                <td><a href="javascript:void(0)" onclick="window.open('/compt/detail?link=${item.skTCompt}')">${item.comptName?if_exists}</a></td>
                                <td>${item.createtime?if_exists}</td>
                                <td>${item.createtime}</td>
                                <td><#if item.isLeader?if_exists>组长<#else>组员</#if></td>
                                <td><#if item.isHelpCredit?if_exists><span style="color: #ff0000">是</span><#else><span>否</span></#if></td>
                                <td>
                                    <a class="btn btn-xs btn-warning" href="/student/showCompetition?link=${item.ID?if_exists}">赛事报名概况</a>
                                    <a class="btn btn-xs btn-danger btnQuitCompt" data-toggle="modal" data-target="#quitComptModal" alt="${item.ID?if_exists}">退出比赛</a>
                                </td>
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
                            <li><a href="/student/myCompetition/${pageable.currentPage-1}">&laquo;</a></li>
                        </#if>

                    <#--中间循环-->
                        <#list 1..pageable.pageCount as p>
                            <#if p == pageable.currentPage>
                                <li class="active"><a>${p}</a></li>
                            <#else>
                                <li><a href="/student/myCompetition/${p}">${p}</a></li>
                            </#if>
                        </#list>

                    <#--here is the >> -->
                        <#if pageable.currentPage == pageable.pageCount>
                            <li><a>&raquo;</a></li>
                        <#else>
                            <li><a href="/student/myCompetition/${pageable.currentPage+1}">&raquo;</a></li>
                        </#if>
                    </#if>
                </#if>
                </ul>
            </div><#--pegeable end-->
        </div>
    </div>
</div>


<!-- Modal for delete a competition-->
<div class="modal fade" id="quitComptModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">退出赛事</h4>
            </div>
            <div class="modal-body">
                <span>确认退出此赛事（此操作不可逆，请谨慎操作）</span>
                <p>注意：如果你为小组赛队长，当你退出比赛后，会解散整个队伍
            </div>
            <div class="modal-footer">
                <input type="hidden" value="" id="QuitComptId">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-danger" data-dismiss="modal" id="confirmQuitCompt">确认退出比赛</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->


<script>
    $(document).ready(function(){
        $('.btnQuitCompt').click(function(){
            $("#QuitComptId").val($(this).attr("alt"));
        });
        $("#confirmQuitCompt").click(function(){
            var link = $("#QuitComptId").val();
            var url = "";
            var data = {};
            $.post(url,data,function(json){
                if(json === "AJAX_SUCCESS"){

                }else{
                    alert("操作失败，请重试");
                }
            });
        });
    });
</script>


<#include "./snippet/footer.ftl">