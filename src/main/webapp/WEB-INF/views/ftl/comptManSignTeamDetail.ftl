<#include "./snippet/header.ftl">



<div class="container-fluid">
    <#include "./snippet/AdminLeftNavbar.ftl">

    <div class="col-sm-10">
        <div class="alert alert-info">
            <a href="/man/compt/list">赛事管理</a> / <a href="/man/compt/sign/checkSignList/2">报名审核</a> / <span>详细信息</span>
        </div>

        <#--leader info-->
        <div class="alert alert-info">
            <div class="panel panel-default">
                <div class="panel-body">
                    <table class="table table-responsive table-bordered">
                        <tr class="bg-primary">
                            <th width="20%"><span><b>(组长)个人信息</b></span></th>
                        </tr>
                        <tr>
                            <td>姓名</td>
                            <td>JOECHOW</td>
                        </tr>
                        <tr>
                            <td>学院</td>
                            <td>软件学院</td>
                        </tr>
                        <tr>
                            <td>专业</td>
                            <td>软件工程</td>
                        </tr>
                        <tr>
                            <td>学号</td>
                            <td>联系手机</td>
                        </tr>
                        <tr>
                            <td>邮箱</td>
                            <td>1209202022@qq.com</td>
                        </tr>
                        <tr>
                            <td>手机</td>
                            <td>18640886602</td>
                        </tr>
                        <tr>
                            <td>银行卡号</td>
                            <td>6222 8888 8888 8888</td>
                        </tr>
                    </table>
                </div><#--panel-body-->

            </div><#--panel-default-->
        </div>

        <#--member list-->
        <div class="alert alert-info">
            <div class="panel panel-default">
                <div class="panel-body">
                    <table class="table table-responsive table-bordered">
                        <tr class="bg-primary">
                            <td><span><b>组员列表</b></span></td>
                        </tr>
                        <tr class="bg-primary">
                            <th>序号</th>
                            <th>姓名</th>
                            <th>学号</th>
                            <th>邮箱</th>
                            <th>联系手机</th>
                            <th>学分帮助</th>
                        </tr>
                        <tr>
                            <td>1</td>
                            <td>组员1</td>
                            <td>101810000</td>
                            <td>8888@qq.com</td>
                            <td>13800138000</td>
                            <td><span style="color: #ff0000">是</span></td>
                        </tr>
                    </table>
                </div><#--panel-body-->
            </div><#--panel-default-->
        </div>

    </div>
</div>

<script type="text/javascript" src="/resources/custom/js/comptManSign.js"></script>
<#include "./snippet/footer.ftl">