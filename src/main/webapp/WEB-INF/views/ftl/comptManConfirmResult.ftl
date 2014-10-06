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
                <p>成绩录入</p>
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



<script src="/resources/custom/js/comptManList.js" type="text/javascript"></script>
<#include "./snippet/footer.ftl">