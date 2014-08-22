<#include "./snippet/header.ftl">

<div class="container-fluid">
    <#include "./snippet/AdminLeftNavbar.ftl">

    <div class="col-sm-10">
        <div class="alert alert-info">
            赛事管理
        </div>
        <!--table th-->
        <div class="alert">
            <div class="alert alert-warning">
                <h3>说明</h3>
                <p>发布赛事：提交一个新的赛事后，将会进入<span style="color: blue">等待审核</span>(由系统管理员进行审核)
                <p>审核通过：管理员审核您提交的赛事后,可以比准发布，批准后才会在首页和赛事列表显示，此时显示<span style="color: green">通过审核</span>
                <p>未通过审核：未能通过管理员审核，显示<span style="color: red">未通过审核</span>，此时可以重新编辑发布赛事
                <p><b>提交(>1) -- 审批(1) -- 拒绝(>2)/通过(>3) -- 报名中(3) -- 结束报名(学院操作)(>4) -- 比赛进行中(4) -- 结束比赛(学院操作)(>5) -- 成绩录入(>6) -- 完成</b>
            </div>
            <div>
                <table class="table table-striped table-bordered table-condensed numControl"  style="text-align: left;font-size: 13px;">
                    <tr>
                        <th>序号</th>
                        <th style="width: 180px;">赛事名称</th>
                        <th>赛事等级</th>
                        <th style="width: 150px;">发布时间</th>
                        <th>状态</th>
                        <th style="width: 150px;">管理</th>
                    </tr>
                    <#if comptList?exists>
                        <#list comptList as compt>
                            <tr id="${compt.ID?if_exists}">
                                <td>${compt_index+1}</td>
                                <td>${compt.title?if_exists}</td>
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
                                <#--审核状态-->
                                <#if compt.status == 1>
                                    <td style="color: blue">等待审核</td>
                                <#elseif compt.status == 2>
                                    <td style="color: red">未通过审核</td>
                                <#elseif compt.status == 3>
                                    <td><span style="color: green">通过审核</span><a href="#" class="btn btn-xs btn-warning">结束报名</a></td>
                                <#elseif compt.status == 4>
                                    <td><span style="color: orange">比赛进行中</span></td>
                                <#elseif compt.status == 5>
                                    <td><span style="color: purple">比赛结束</span></td>
                                <#elseif compt.status == 6>
                                    <td><span style="color: black">完成</span></td>
                                </#if>
                                <#--按钮状态-->
                                <#if compt.status == 1>
                                        <td><a href="/man/compt/updateGet?link=${compt.ID?if_exists}" class="btn btn-xs btn-info">重新编辑</a> <button class="btn btn-xs btn-danger" data-toggle="modal" data-target="#myModal">删除赛事</button></td>
                                    <#elseif compt.status == 2>
                                        <td><a href="#" class="btn btn-xs btn-primary">重新交审</a> <button class="btn btn-xs btn-danger" data-toggle="modal" data-target="#myModal">删除赛事</button></td>
                                    <#elseif compt.status == 3>
                                        <td><a href="#" class="btn btn-xs btn-warning">报名审核</a> <button class="btn btn-xs btn-danger" data-toggle="modal" data-target="#myModal">删除赛事</button></td>
                                    <#elseif compt.status == 4>
                                        <td><a href="#" class="btn btn-xs btn-warning">结束比赛</a> <button class="btn btn-xs btn-danger" data-toggle="modal" data-target="#myModal">删除赛事</button></td>
                                    <#elseif compt.status == 5>
                                        <td><a class="btn btn-xs btn-success">确认结果 & 成绩录入</a></td>
                                    <#elseif compt.status == 6>
                                        <td><span style="color: black">完成</span></td>
                                </#if>
                            </tr>
                        </#list>
                    </#if>
                    <#--<tr>
                        <td>1</td>
                        <td>挑战杯中国赛</td>
                        <td>国家级A</td>
                        <td>2014-05-22 10：00：00</td>
                        <td style="color: blue">等待审核</td>
                        <td><a href="userAc-ComPub.html" class="btn btn-xs btn-info">重新编辑</a> <button class="btn btn-xs btn-danger" data-toggle="modal" data-target="#myModal">删除赛事</button></td>
                    </tr>
                    <tr>
                        <td>2</td>
                        <td>挑战杯中国赛</td>
                        <td>国家级A</td>
                        <td>2014-05-22 10：00：00</td>
                        <td style="color: green">通过审核</td>
                        <td><a href="userAc-ComSignList.html" class="btn btn-xs btn-warning">报名审核</a> <button class="btn btn-xs btn-danger" data-toggle="modal" data-target="#myModal">删除赛事</button></td>
                    </tr>
                    <tr>
                        <td>3</td>
                        <td>挑战杯中国赛</td>
                        <td>国家级A</td>
                        <td>2014-05-22 10：00：00</td>
                        <td style="color: red">未通过审核</td>
                        <td><a href="userAc-ComPub.html" class="btn btn-xs btn-primary">重新发布</a> <button class="btn btn-xs btn-danger" data-toggle="modal" data-target="#myModal">删除赛事</button></td>
                    </tr>
                    <tr>
                        <td>4</td>
                        <td>挑战杯中国赛</td>
                        <td>国家级A</td>
                        <td>2014-05-22 10：00：00</td>
                        <td style="color: purple">比赛结束</td>
                        <td><button class="btn btn-xs btn-success">确认结果 & 成绩录入</button></td>
                    </tr>-->
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
                <h4 class="modal-title" id="myModalLabel">删除赛事</h4>
            </div>
            <div class="modal-body">
                确认删除赛事
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-danger" data-dismiss="modal">删除</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->



<script src="/resources/custom/js/comtManList.js" type="text/javascript"></script>
<#include "./snippet/footer.ftl">