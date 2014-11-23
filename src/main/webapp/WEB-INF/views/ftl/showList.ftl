<#include "./snippet/header.ftl">

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
<div class="container-fluid">
    <br/>
</div>
<div class="container-fluid">
    
    <div class="container">
	    <h1 style="text-align: center"><label class="label label-info showItem">学院参赛风采展示</label></h1>
	    <br/>
	    <div class="">
		
	        <div class="row text-center">
	        	<#if showList?exists && (showList?size>0) >
	        		<#list showList as item>
			            <!--one item-->
			            <div class="col-sm-6 col-md-3">
			                <div class="thumbnail showItem" style="max-height: 350px;">
			                    <a href="/show/album/${item.ID?if_exists}">
			                        <img src="${item.coverImage?if_exists}" class="img-thumbnail" style="height:190px;" title="${item.title?if_exists}" alt="...">
			                    </a>
			                    <div class="caption">
			                        <h5>${item.title?if_exists}</h5>
			                        <h5><span class="glyphicon glyphicon-flag"> <label class="label label-success">${item.adminer?if_exists}</label></span></h5>
			                        <h5><span class="glyphicon glyphicon-time"> <label class="label label-info">${item.createtime?if_exists}</label></span></h5>
			                    </div>
			                </div>
			            </div><!--one item-->
		            </#list>
	            <#else>
	            	<div class="col-md-12">
	            		<img src="" alt="默认图片"/>
	            	</div>
	            </#if>
	            
	        </div><!--row-->
	
	        <!--pageable-->
            <div style="text-align: right">
                <ul class="pagination">
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
                            <li><a href="/show/list/${pageable.currentPage-1}">&laquo;</a></li>
                        </#if>

                    <#--中间循环-->
                        <#list 1..pageable.pageCount as p>
                            <#if p == pageable.currentPage>
                                <li class="active"><a>${p}</a></li>
                            <#else>
                                <li><a href="/show/list/${p}">${p}</a></li>
                            </#if>
                        </#list>

                    <#--here is the >> -->
                        <#if pageable.currentPage == pageable.pageCount>
                            <li><a>&raquo;</a></li>
                        <#else>
                            <li><a href="/show/list/${pageable.currentPage+1}">&raquo;</a></li>
                        </#if>
                    </#if>
                </#if>
                </ul>
            </div><!--pageable-->
	        
	        
	        
	    </div>
	</div>
    
    
</div>

<#include "./snippet/footer.ftl">