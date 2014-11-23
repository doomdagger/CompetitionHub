<div class="col-sm-2">
    <div class="alert">
        <ul class="nav nav-pills nav-stacked">
        <#if Session?exists && Session?has_content && Session['CUR']?exists && Session['CUR']['CUR_TYPE']?exists><#--if session exist-->
            <#if Session['CUR']['CUR_TYPE'] == 0><#--学生-->
                <li <#if !navbar?exists || navbar == "">class="active"</#if> ><a href="/student/">帐号信息(学生)</a></li>
                <li <#if navbar?exists && navbar == "myc">class="active"</#if> ><a href="/student/myCompetition">我的赛事</a></li>
                <li <#if navbar?exists && navbar == "mdpd">class="active"</#if> ><a href="/student/modifyPassword">修改密码</a></li>
            <#elseif Session['CUR']['CUR_TYPE'] == 1><#--管理员-->
                <li <#if !navbar?exists || navbar == "">class="active"</#if> ><a href="/man/">帐号信息(管理员)</a></li>
                <#--<li <#if navbar?exists && navbar == "comptPub">class="active"</#if> ><a href="/man/compt/publishGet">赛事发布</a></li>-->
                <li <#if navbar?exists && navbar == "student">class="active"</#if> ><a href="/man/admin/student/list">学生账号</a></li>
                <li <#if navbar?exists && navbar == "comptMan">class="active"</#if> ><a href="/man/admin/comptList">赛事审核</a></li>
                <li <#if navbar?exists && navbar == "calendar">class="active"</#if> ><a href="/man/admin/calendar/list">赛事日程</a></li>
                <li <#if navbar?exists && navbar == "news">class="active"</#if> ><a href="/man/news/manList">新闻管理</a></li>
                <li <#if navbar?exists && navbar == "ads">class="active"</#if> ><a href="/man/admin/ads/list">广告管理</a></li>
                <li <#if navbar?exists && navbar == "link">class="active"</#if> ><a href="/man/admin/link/list">友情链接</a></li>
                <li <#if navbar?exists && navbar == "notice">class="active"</#if> ><a href="/man/admin/notice">公告管理</a></li>
                <div style="border-bottom: 2px solid #245269;margin-bottom: 15px;width:50%" class="text-center"></div>
                <#if Session['CUR']['CUR_ISTP']?exists && Session['CUR']['CUR_ISTP']?if_exists><#--最高管理员可见-->
                    <li <#if navbar?exists && navbar == "adminAccount">class="active"</#if> ><a href="/man/admin/accountGet">管理员账号</a></li>
                    <li <#if navbar?exists && navbar == "academyAccount">class="active"</#if> ><a href="/man/admin/academyAccount">学院账号</a></li>
                    <li <#if navbar?exists && navbar == "djtuAccount">class="active"</#if> ><a href="/man/admin/djtuAccount">教务账号</a></li>
                    <li <#if navbar?exists && navbar == "mdpd">class="active"</#if> ><a href="/man/modifyPassword">修改密码</a></li>
                </#if>
            <#elseif Session['CUR']['CUR_TYPE'] == 2><#--学院-->
                <li <#if !navbar?exists || navbar == "">class="active"</#if> ><a href="/man/">帐号信息(学院)</a></li>
                <li <#if navbar?exists && navbar == "comptPub">class="active"</#if> ><a href="/man/compt/publishGet">赛事发布</a></li>
                <li <#if navbar?exists && navbar == "comptMan">class="active"</#if> ><a href="/man/compt/list">赛事管理</a></li>
                <li <#if navbar?exists && navbar == "news">class="active"</#if> ><a href="/man/news/manList">新闻管理</a></li>
                <li <#if navbar?exists && navbar == "show">class="active"</#if> ><a href="/man/show/list">展示管理</a></li>
                <li <#if navbar?exists && navbar == "mdpd">class="active"</#if> ><a href="/man/modifyPassword">修改密码</a></li>
            <#elseif Session['CUR']['CUR_TYPE'] == 3><#--教务-->
                <li <#if !navbar?exists || navbar == "">class="active"</#if> ><a href="/man/">帐号信息</a></li>
                <li <#if navbar?exists && navbar == "comptPub">class="active"</#if> ><a href="/man/compt/publishGet">解决处分一览</a></li>
                <li <#if navbar?exists && navbar == "mdpd">class="active"</#if> ><a href="/man/modifyPassword">修改密码</a></li>
            <#else>
                <li>账号信息(暂无)</li>
            </#if>
        </#if><#--if session exist-->
        </ul>
    </div>
</div>