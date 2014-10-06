<#include "./snippet/header.ftl">

<div class="container-fluid">
    <#include "./snippet/AdminLeftNavbar.ftl">

    <div class="col-sm-10">
        <div class="alert alert-info">
            赛事审核
        </div>
        <!--table th-->
        <div class="alert">
            <div class="alert alert-info">
                <h3>说明</h3>
                <p>发布赛事：提交一个新的赛事后，将会进入<span style="color: blue">等待审核</span>(由系统管理员进行审核)
                <p>审核通过：管理员审核您提交的赛事后,可以比准发布，批准后才会在首页和赛事列表显示，此时显示<span style="color: green">通过审核</span>
                <p>未通过审核：未能通过管理员审核，显示<span style="color: red">未通过审核</span>，此时可以重新编辑发布赛事
                <p><b>提交 -- 审批 -- 拒绝/通过 -- 报名中 -- 结束报名(学院操作) -- 比赛进行中 -- 结束比赛(学院操作) -- 成绩录入 -- 完成</b>
            </div>
            <div>
                <table class="table table-responsive table-bordered numControl"  style="text-align: left;font-size: 13px;">
                    <tr class="bg-primary">
                        <th>序号</th>
                        <th style="width: 180px;">赛事名称</th>
                        <th>赛事等级</th>
                        <th style="width: 150px;">发布时间</th>
                        <th>发布者</th>
                        <#--<th>状态</th>-->
                        <th>置顶状态</th>
                        <th style="width: 200px;">管理</th>
                    </tr>
                    <#if comptList?exists && (comptList?size>0)>
                        <#list comptList as compt>
                            <tr id="_${compt.ID?if_exists}">
                                <td>${compt_index+1}</td>
                                <td><a href="javascript:void(0)" onclick="window.open('/compt/detail?link=${compt.ID?if_exists}')">${compt.title?if_exists}</a></td>
                                <#--赛事等级-->
                                <td>
                                    <#if compt.level == 1>国家级A
                                        <#elseif compt.level == 2>国家级B
                                        <#elseif compt.level == 3>省级A
                                        <#elseif compt.level == 4>省级B
                                        <#elseif compt.level == 5>企业级
                                    <#else>其他
                                    </#if>
                                </td>
                                <#--发布时间-->
                                <td>${compt.createtime?if_exists}</td>
                                <#--发布者-->
                                <td>${compt.adminName?if_exists}</td>
                                <#--置顶状态-->
                                <#--<#if !compt.isTop?if_exists>
                                    <td>
                                        <label class="label label-warning isTop" style="display: none">当前置顶</label>
                                        <a class="btn btn-info btn-xs newsToTop" alt="${compt.ID?if_exists}">置顶</a>
                                        <a class="btn btn-primary btn-xs newsNoTop" alt="${compt.ID?if_exists}" style="display: none">取消置顶</a>
                                    </td>
                                <#else>
                                    <td>
                                        <label class="label label-warning isTop">当前置顶</label>
                                        <a class="btn btn-primary btn-xs newsNoTop" alt="${compt.ID?if_exists}">取消置顶</a>
                                        <a class="btn btn-info btn-xs newsToTop" alt="${compt.ID?if_exists}" style="display: none">置顶</a>
                                    </td>
                                </#if>-->
                                <#--审核状态-->
                                <#if compt.status == 1>
                                    <td style="color: blue">等待审核</td>
                                <#elseif compt.status == 2>
                                    <td style="color: red">未通过审核</td>
                                <#elseif compt.status == 3>
                                    <td><span style="color: green">通过审核</span></td>
                                <#elseif compt.status == 4>
                                    <td><span style="color: orange">比赛进行中</span></td>
                                <#elseif compt.status == 5>
                                    <td><span style="color: purple">比赛结束</span></td>
                                <#elseif compt.status == 6>
                                    <td><span style="color: black">完成</span></td>
                                <#else>
                                    <td>零状态</td>
                                </#if>

                                <#--按钮状态-->
                                <#if compt.status == 1>
                                        <td>
                                            <button class="btn btn-xs btn-info btnAccess" alt="${compt.ID}">批准通过</button>
                                            <button class="btn btn-xs btn-danger btnDeny" alt="${compt.ID}">拒绝通过</button>
                                        </td>
                                    <#elseif compt.status == 2>
                                        <td><button class="btn btn-xs btn-primary btnAccess" alt="${compt.ID}">批准通过</button>
                                        </td>
                                    <#elseif (compt.status>2) || (compt.status<7)>
                                        <td><span style="color: green">通过审核</span></td>
                                    <#--<#elseif compt.status == 4>
                                        <td>
                                            <a href="#" class="btn btn-xs btn-warning" alt="${compt.ID}">结束比赛</a>
                                            <button class="btn btn-xs btn-danger btnDelCompt" data-toggle="modal" data-target="#myModal" alt="${compt.ID}">删除赛事</button>
                                            <a class="btn btn-xs" alt="${compt.ID}" href="/man/compt/uploadGet?link=${compt.ID?if_exists}">附件上传</a>
                                        </td>
                                    <#elseif compt.status == 5>
                                        <td><a class="btn btn-xs btn-success" alt="${compt.ID}">确认结果 & 成绩录入</a></td>
                                    <#elseif compt.status == 6>
                                        <td><span style="color: black">完成</span></td>-->
                                    <#else>
                                        <td>零状态</td>
                                </#if>
                            </tr>
                        </#list>
                    <#else>
                        <tr>
                            <td colspan="7" style="text-align: center"><span><b>目前系统没有发布过任何赛事</b></span></td>
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
                            <li><a href="/man/admin/comptList/${pageable.currentPage-1}">&laquo;</a></li>
                        </#if>

                    <#--中间循环-->
                        <#list 1..pageable.pageCount as p>
                            <#if p == pageable.currentPage>
                                <li class="active"><a>${p}</a></li>
                            <#else>
                                <li><a href="/man/admin/comptList/${p}">${p}</a></li>
                            </#if>
                        </#list>

                    <#--here is the >> -->
                        <#if pageable.currentPage == pageable.pageCount>
                            <li><a>&raquo;</a></li>
                        <#else>
                            <li><a href="/man/admin/comptList/${pageable.currentPage+1}">&raquo;</a></li>
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
                <h4 class="modal-title" id="myModalLabel">删除赛事</h4>
            </div>
            <div class="modal-body">
                确认删除赛事
            </div>
            <div class="modal-footer">
                <input type="hidden" id="linkDelCompt" value="">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-danger" data-dismiss="modal" id="confirmDelCompt">删除</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->


<script>
    $(document).ready(function(){
        //access
        $(".btnAccess").click(function(){
            var link = $(this).attr("alt");
            var url = "/man/admin/changeStatus";
            var data = {"link":link,"status":3};
            $.post(url,data,function(json){
                if(json === "AJAX_SUCCESS"){
                    location.reload();
                }else{
                    alert("操作失败，请重试");
                }
            });
        });
        //deny
        $(".btnDeny").click(function(){
            var link = $(this).attr("alt");
            var url = "/man/admin/changeStatus";
            var data = {"link":link,"status":2};
            $.post(url,data,function(json){
                if(json === "AJAX_SUCCESS"){
                    location.reload();
                }else{
                    alert("操作失败，请重试");
                }
            });
        });
    });
</script>
<#include "./snippet/footer.ftl">