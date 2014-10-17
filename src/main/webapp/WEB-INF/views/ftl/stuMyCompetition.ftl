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
                <p>关于报名：报名后要等待赛事发布(如学院)方同意确认
                <p>关于组员和作品：<b>组长</b>可以在<b>"赛事报名概况"</b>中管理队伍的成员和上传作品(如需上传作品的赛事)
            </div>
            <div>
                <table class="table table-responsive table-bordered numControl"  style="text-align: left;font-size: 13px;">
                    <tr class="bg-primary">
                        <th>序号</th>
                        <th style="width: 180px;">赛事名称</th>
                        <th>报名时间</th>
                        <th>参赛形式</th>
                        <th>参赛身份</th>
                        <th>学院审核</th>
                        <th>学分帮助</th>
                        <th style="width: 180px;">管理</th>
                    </tr>
                    <#if signList?exists && (signList?size>0)>
                        <#list signList as item>
                            <tr id="_${item.ID?if_exists}">
                                <td>${item_index+1}</td>
                                <td><a href="javascript:void(0)" onclick="window.open('/compt/detail?link=${item.skTCompt}')">${item.comptName?if_exists}</a></td>
                                <td>${item.createtime?if_exists}</td>
                                <td>${item.createtime}</td>
                                <td><#if item.isLeader?if_exists>组长<#else>组员</#if></td>
                                <td><#if item.isValid?if_exists><span style="color: green">已通过</span><#else><span style="color: #ff0000">未通过</span></#if></td>
                                <td><#if item.isHelpCredit?if_exists><span style="color: green">是</span><#else><span>否</span></#if></td>
                                <td>
                                    <#if item.isLeader?if_exists><a class="btn btn-xs btn-warning" href="/student/showCompetition?link=${item.ID?if_exists}">赛事报名概况</a></#if>
                                    <a class="btn btn-xs btn-danger btnQuitCompt" data-toggle="modal" data-target="#quitComptModal" alt="${item.ID?if_exists}">退出比赛</a>
                                </td>
                            </tr>
                        </#list>
                    <#else>
                        <tr>
                           <td colspan="8" style="text-align: center"><span>暂无参与的赛事</span></td>
                        </tr>
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
                <h4 class="modal-title" id="myModalLabel">退出赛事（此操作不可逆，请谨慎操作）</h4>
            </div>
            <div class="modal-body">
                <p><b>注意：</b>
                <p>1.个人赛：退出比赛不会有任何影响
                <p>2.小组赛：如果你为小组赛组长，当你退出比赛后，会解散整个队伍；如果是组员，退出比赛不会对该参赛小组有任何影响
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
            var url = "/student/quitCompetition";
            var data = {"link":link};
            var currentTR = $(this)
            $.post(url,data,function(json){
                if(json === "AJAX_SUCCESS"){
                    var currentTR = $("#_"+link);
                    currentTR.html("<td colspan='8' style='text-align: center'><span style='color: green'>成功退出该比赛</span></td>");
                    setTimeout(function(){
                        currentTR.fadeOut();
                    },3000);
                }else{
                    alert("退出操作失败，请重试");
                }
            });
        });
    });
</script>


<#include "./snippet/footer.ftl">