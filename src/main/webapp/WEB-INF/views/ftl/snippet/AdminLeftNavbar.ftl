<div class="col-sm-2">
    <div class="alert">
        <ul class="nav nav-pills nav-stacked">
            <li <#if !navbar?exists || navbar == "">class="active"</#if> ><a href="/man/">帐号信息</a></li>
            <li <#if navbar?exists && navbar == "comptPub">class="active"</#if> ><a href="/man/compt/publishGet">赛事发布</a></li>
            <li <#if navbar?exists && navbar == "comptMan">class="active"</#if> ><a href="/man/compt/list">赛事管理</a></li>
            <li <#if navbar?exists && navbar == "news">class="active"</#if> ><a href="/man/news/manList">新闻管理</a></li>
            <li><a href="#">展示管理</a></li>
        </ul>
    </div>
</div>