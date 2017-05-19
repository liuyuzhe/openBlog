<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>"StrongLiu's blog - 附件管理 </title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <link rel="stylesheet" href="../../../static/plugin/bootstrap-3.3.7/css/bootstrap.css">
    <link rel="stylesheet" href="http://cdn.bootcss.com/font-awesome/4.3.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="../../../static/plugin/dropzone-4.3.0/dropzone.css">
    <link rel="stylesheet" href="../../../static/admin/css/style.css">
</head>
<body>

<#include "header.html">

<div class="content-page">
    <div class="container">
        <div class="row">
            <div class="col-sm-12">
                <h4 class="page-title">附件管理</h4>
            </div>
            <div class="col-md-12">
                <form action="#" class="dropzone" id="upload-file">
                    <div class="fallback">
                        <input name="file" type="file" multiple />
                    </div>
                </form>
            </div>
            <#if attachPageVo?? >
                <#list attachPageVo.attachList as attach>
                    <div class="col-md-2 text-center attach">
                        <a class="" href="${attach.slug}">
                            <img class="attach-img" src="${attach.slug}" title="${attach.name}"/>
                        </a>
                        <div class="">
                            <span class="attach-text">${attach.name}</span>
                        </div>
                        <div class="">
                            <button type="button" class="btn btn-primary btn-sm">
                                <i class="fa fa-copy"></i> <span>复制</span>
                            </button>
                            <button type="button" class="btn btn-danger btn-sm">
                                <i class="fa fa-trash-o"></i> <span>删除</span>
                            </button>
                        </div>
                    </div>
                <#else>
                    <div class="col-md-12 text-center">
                        <div class="col-md-6 alert alert-warning">
                            目前还没有一个附件，你可以上传试试!
                        </div>
                    </div>
                </#list>
            </#if>
            <#if (attachPageVo.pageIndex > 0) && (postPageVo.pageTotal > 0) >
                <nav class="col-md-12 text-center">
                    <ul class="pagination">
                        <#list 1..(attachPageVo.pageTotal) as index>
                            <li
                                <#if index == (attachPageVo.pageIndex) >
                                    class="active"
                                </#if> >
                                <a href="#">${index}</a>
                            </li>
                        </#list>
                    </ul>
                </nav>
            </#if>
        </div>
    </div>
</div>

<script src="../../../static/plugin/jquery/jquery-3.1.1.min.js"></script>
<script src="../../../static/plugin/bootstrap-3.3.7/js/bootstrap.min.js"></script>
<script src="../../../static/plugin/dropzone-4.3.0/dropzone.js"></script>
<script src="../../../static/admin/js/attach.js"></script>

</body>
</html>