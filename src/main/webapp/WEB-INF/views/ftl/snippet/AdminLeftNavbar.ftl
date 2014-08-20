<div class="col-sm-2">
    <div class="alert">
        <ul class="nav nav-pills nav-stacked">
            <li <#if navbar?if_exists == "" || !navbar?exists>class="active"</#if> ><a href="#">(学院)帐号信息</a></li>
            <li><a href="#">赛事发布</a></li>
            <li><a href="#">赛事管理</a></li>
            <li <#if navbar?if_exists == "news">class="active"</#if> ><a href="/man/news/manList">新闻管理</a></li>
            <li><a href="#">展示管理</a></li>
        </ul>
    </div>
</div>