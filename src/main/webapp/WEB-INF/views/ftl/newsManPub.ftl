<#include "./snippet/header.ftl">

<div class="container"  style="margin-left:50px">
    <#include "./snippet/AdminLeftNavbar.ftl">

    <div class="col-sm-10">
        <div class="alert alert-info">
            新闻发布
        </div>


        <div class="alert alert-info">
            <form role="form" id="newsPubForm" method="post" enctype="multipart/form-data">
                <div class="form-group">
                    <label for="title">新闻标题</label>
                    <input type="text" class="form-control" name="title" id="title" placeholder="赛事名称">
                </div>

                <div class="form-group">
                    <label>是否置顶</label>
                    &nbsp;&nbsp;&nbsp;
                    <label><input type="radio" name="isTop" value="1" checked>是</label>&nbsp;&nbsp;&nbsp;
                    <label><input type="radio" name="isTop" value="0">否</label>&nbsp;&nbsp;&nbsp;
                </div>

                <div class="form-group">
                    <label for="input4">新闻内容</label>
                    <#--<textarea class="form-control" id="input4" placeholder="新闻内容">Quill编辑器</textarea>-->
                    <!-- 加载编辑器的容器 -->
                    <script id="container" name="content" type="text/plain">
                    </script>
                </div>

                <div>
                    <button id="newsPubSubmit" type="button" class="btn btn-success btn-block">确认发布</button>
                </div>
            </form>
        </div>
    </div>
</div>



<!-- 配置文件 -->
<script type="text/javascript" src="/resources/plugins/ueditor/ueditor.config.js"></script>
<!-- 编辑器源码文件 -->
<script type="text/javascript" src="/resources/plugins/ueditor/ueditor.all.js"></script>
<!-- 实例化编辑器 -->
<script type="text/javascript">
    var ue = UE.getEditor('container');
</script>

<#--顺序在最后-->
<script src="/resources/custom/js/userAcNews.js"></script>

<#include "./snippet/footer.ftl">


