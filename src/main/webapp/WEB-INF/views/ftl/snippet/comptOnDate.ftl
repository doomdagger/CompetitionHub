<style>
.showItem{
	-webkit-box-shadow:0 5px 10px #666; 
    -moz-box-shadow:0 5px 10px #666;
    box-shadow:0 5px 10px #666;
    -moz-border-radius:5px;
    -webkit-border-radius:5px;
    -o-border-radius:5px;
    border-radius:5px; 
}
</style>
<!--time line-->
<div class="container-fluid">
    <h2>
        <label class=" label label-primary">
            <span class="glyphicon glyphicon-time"><b>赛事日程</b></span>
        </label>
    </h2>
<#--<div class="progress progress-striped">
    <div class="progress-bar progress-bar-success" role="progressbar" aria-valuenow="100" aria-valuemin="0" aria-valuemax="100" style="width: 100%"></div>
</div>-->
    <div class="" style="width: 100%">
        <img src="/resources/img/timebar.jpg" width="100%">
    </div>
    <div class="row">
    	<#if calList?exists && (calList?size>0)>
    		<#list calList as item>
    			<div class="col-xs-4 col-md-2">
		            <div style="text-align: center"><h6><span>${item.onDate?if_exists}</span></h6></div>
		            <a href="/calendar/detail/${item.ID?if_exists}" class="thumbnail showItem">
		                <img src="${item.cover?if_exists}" alt="查看详情">
		            </a>
		        </div>
    		</#list>
		<#else>
			
    	</#if>
        
    </div>
</div>