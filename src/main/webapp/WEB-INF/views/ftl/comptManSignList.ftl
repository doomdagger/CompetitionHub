<#include "./snippet/header.ftl">



<div class="container-fluid">
    <#include "./snippet/AdminLeftNavbar.ftl">

    <div class="col-sm-10">
        <div class="alert alert-info">
            <a href="/man/compt/list">赛事管理</a> / 报名审核
        </div>

        <div class="alert alert-info">

            <div class="panel panel-default">
                <div class="panel-body">
                    <#--compt link-->
                    <input type="hidden" value="${comptLink?if_exists}" id="comptLink">
                    <table class="table table-responsive table-bordered" id="tableSignList">
                        <tr id="thFilterView">
                            <td colspan="5" class="text-center">
                                <div class="">
                                    <label>只看：</label>
                                    <label><input type="radio" name="filter" value="0" checked><span style="color: black">全部</span></label>&nbsp;
                                    <label><input type="radio" name="filter" value="1"><span style="color: green">已通过</span></label>
                                    <small>(点击姓名可查看详细)</small>
                                </div>
                            </td>
                            <td colspan="2" class="text-center">
                                <a class="btn btn-xs btn-success">优先已选中项</a>
                                <a class="btn btn-xs btn-warning">通过已选中项</a>
                            </td>
                        </tr>
                        <tr class="bg-primary" id="thView">
                            <th class="text-center">序号</th>
                            <th class="text-center">
                               <span>(组长)姓名</span>
                            </th>
                            <th class="text-center">成员数</th>
                            <th class="text-center">
                                <span>报名时间</span>
                                <select id="selOrderTime" style="color: #000000">
                                    <option value="0">顺序查看</option>
                                    <option value="1">倒序查看</option>
                                </select>
                            </th>
                            <th class="text-center">排序(数字·从小到大排列)</th>
                            <th class="text-center"><label for="chbForSelAll">全选</label><input type="checkbox" id="chbForSelAll"/></th>
                            <th class="text-center">审核</th>
                        </tr>
                        <#--<#if signList?exists && signList?has_content>
                            <#list signList as item>
                                <tr>
                                    <td class="text-center">${item_index+1}</td>
                                    <td class="text-center"><a target="_blank" href="/man/compt/sign/teamDetail/${item.teamNo?if_exists}">JOECHOW</a></td>
                                    <td class="text-center">${item.memberNum?if_exists}人</td>
                                    <td class="text-center">${item.createtime?if_exists}</td>
                                    <td class="text-center">
                                        <input class="input-sm" type="text" maxlength="4" width="50px" value="${item.orderNum?if_exists}"/>
                                    </td>
                                    <td class="text-center">
                                        <input type="checkbox" class="chbItem" />
                                    </td>
                                    <td class="text-center">
                                        <a class="btn btn-xs btn-success" alt="${item.ID?if_exists}">优先</a>
                                        <button class="btn btn-xs btn-warning" alt="${item.ID?if_exists}">通过</button>
                                    </td>
                                </tr>
                            </#list>
                        <#else>
                            <tr><td colspan="7" class="text-center"><span><b>目前还没有个人/小组报名</b></span></td></tr>
                        </#if>-->
                    </table>
                </div><#--panel-body-->

            </div><#--panel-default-->
        </div>

        <!--pageable-->
        <div style="text-align: right">
            <input type="hidden" value="${currPage?if_exists}" id="currPage">
            <ul class="pagination" id="pageView">
            <#if pageable?exists>
                <#if pageable.pageCount == 1>
                    <li><a>&laquo;</a></li>
                    <li class="active"><a>1</a></li>
                    <li><a>&raquo;</a></li>
                <#else>
                <#--here is the << -->
                    <#if pageable.currentPage == 1>
                        <li><a>&laquo;</a></li>
                    <#else>
                        <li><a href="#/${pageable.currentPage-1}">&laquo;</a></li>
                    </#if>

                <#--中间循环-->
                    <#list 1..pageable.pageCount as p>
                        <#if p == pageable.currentPage>
                            <li class="active"><a>${p}</a></li>
                        <#else>
                            <li><a href="#/${p}">${p}</a></li>
                        </#if>
                    </#list>

                <#--here is the >> -->
                    <#if pageable.currentPage == pageable.pageCount>
                        <li><a>&raquo;</a></li>
                    <#else>
                        <li><a href="#/${pageable.currentPage+1}">&raquo;</a></li>
                    </#if>
                </#if>
            <#else>
                <li><a>&laquo;</a></li>
                <li class="active"><a>1</a></li>
                <li><a>&raquo;</a></li>
            </#if>
            </ul>
        </div><#--pageable-->


    </div>
</div>

<script type="text/javascript" src="/resources/custom/js/comptManSign.js"></script>
<#include "./snippet/footer.ftl">