<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>"StrongLiu's blog - 附件管理 </title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <link rel="stylesheet" href="../../../static/plugin/bootstrap-3.3.7/css/bootstrap.css">
    <link rel="stylesheet" href="http://cdn.bootcss.com/font-awesome/4.3.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="../../../static/admin/css/style.css">
</head>
<body>

<#import "header.ftl" as mainHeader >
<@mainHeader.navbar active="attach" />

<div class="content-page">
    <div class="container">
        <div class="row">
            <div class="col-sm-12">
                <h4 class="page-title">附件管理</h4>
            </div>
            <div class="col-sm-12">
                <p id="upload-file">将文件拖放到此处或者点击上传</p>
            </div>
        <#if attachPageVo?? >
            <#list (attachPageVo.attachList) as attach >
                <div class="col-md-3 text-center attach">
                    <a class="attach-img" href="javascript:">
                        <img src="/admin/attach/download/${attach.slug}" title="${attach.name}"/>
                    </a>
                    <div class="attach-text">
                        <span>${attach.name}</span>
                    </div>
                    <div>
                        <button type="button" class="btn btn-primary btn-sm copy-attach" aslug="${attach.slug}">
                            <i class="fa fa-copy"></i>
                            <span>复制</span>
                        </button>
                        <button type="button" class="btn btn-danger btn-sm delete-attach" aid="${attach.id}">
                            <i class="fa fa-trash-o"></i>
                            <span>删除</span>
                        </button>
                    </div>
                </div>
            <#else>
                <div class="col-md-12 text-center">
                    <div class="col-md-6 alert alert-warning">
                        还没有一个附件，你可以上传试试!
                    </div>
                </div>
            </#list>
            <#if (attachPageVo.pageIndex > 0) && (attachPageVo.pageTotal > 0) >
                <nav class="col-md-12 text-center">
                    <ul class="pagination">
                        <#list 1..(attachPageVo.pageTotal) as index >
                            <li
                                <#if index == (attachPageVo.pageIndex) >
                                        class="active"
                                </#if> >
                                <a href="/admin/post/?page=${index}">${index}</a>
                            </li>
                        </#list>
                    </ul>
                </nav>
            </#if>
        </#if>
        </div>
    </div>
</div>

<script src="../../../static/plugin/jquery/jquery-3.1.1.min.js"></script>
<script src="../../../static/plugin/bootstrap-3.3.7/js/bootstrap.min.js"></script>
<script src="../../../static/plugin/clipboard.js/clipboard.min.js"></script>
<script src="../../../static/plugin/plupload-3.0-beta1/js/plupload.min.js"></script>
<script src="../../../static/common/js/base.js"></script>
<script src="../../../static/admin/js/attach.js"></script>

</body>
</html>