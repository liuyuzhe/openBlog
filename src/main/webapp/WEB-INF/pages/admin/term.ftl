<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>StrongLiu's blog - </title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <link rel="stylesheet" href="../../../static/plugin/bootstrap-3.3.7/css/bootstrap.css">
    <link rel="stylesheet" href="http://cdn.bootcss.com/font-awesome/4.3.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="../../../static/plugin/bootstrap-switch/css/bootstrap-switch.css">
    <link rel="stylesheet" href="../../../static/admin/css/style.css">
</head>
<body>

<#import "header.ftl" as mainHeader >
<@mainHeader.navbar active="term" />

<section class="content-page">
    <div class="container">
        <div class="row">
            <div class="col-sm-12">
                <h4 class="page-title">分类/标签管理</h4>
            </div>
            <div class="col-md-6">
                <div class="panel panel-success">
                    <div class="panel-heading">
                        <h5 class="panel-title">分类列表</h5>
                    </div>
                    <div class="panel-body">
                    <#if categoryList?? >
                        <#list categoryList as category >
                            <div class="btn-group">
                                <button type="button" class="btn btn-sm btn-success dropdown-toggle" data-toggle="dropdown">
                                ${category.name} (${category.count})
                                    <span class="caret"></span>
                                </button>
                                <ul class="dropdown-menu">
                                    <li><a class="update-term" href="javascript:void(0)" tid=${category.id} tname=${category.name} tslug=${category.slug} ttype="category">修改</a></li>
                                    <li><a class="delete-term" href="javascript:void(0)" tid=${category.id} ttype="category">删除</a></li>
                                </ul>
                            </div>
                        <#else>
                            <div class="col-md-12 text-center">
                                <div class="col-md-6 alert alert-warning">
                                    还没有一个分类,你可以添加试试!
                                </div>
                            </div>
                        </#list>
                    </#if>
                    </div>
                </div>
            </div>
            <div class="col-md-6">
                <div class="panel panel-info">
                    <div class="panel-heading">
                        <h5 class="panel-title">标签列表</h5>
                    </div>
                    <div class="panel-body">
                    <#if tagList?? >
                        <#list tagList as tag >
                            <div class="btn-group">
                                <button type="button" class="btn btn-sm btn-info dropdown-toggle" data-toggle="dropdown">
                                ${tag.name} (${tag.count})
                                    <span class="caret"></span>
                                </button>
                                <ul class="dropdown-menu">
                                    <li><a class="update-term" href="javascript:void(0)" tid=${tag.id} tname=${tag.name} tslug=${tag.slug} ttype="tag">修改</a></li>
                                    <li><a class="delete-term" href="javascript:void(0)" tid=${tag.id} ttype="tag">删除</a></li>
                                </ul>
                            </div>
                        <#else>
                            <div class="col-md-12 text-center">
                                <div class="col-md-6 alert alert-warning">
                                    还没有一个标签,你可以添加试试!
                                </div>
                            </div>
                        </#list>
                    </#if>
                    </div>
                </div>
            </div>
            <div class="col-md-12">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h5 class="panel-title">添加/修改</h5>
                    </div>
                    <div class="panel-body">
                        <form id="termForm" class="" role="form">
                            <fieldset>
                                <input type="hidden" name="id" />
                                <div class="form-group col-md-4">
                                    <input type="text" name="name" class="form-control" placeholder="请输入名称">
                                </div>
                                <div class="form-group col-md-4">
                                    <input type="text" name="slug" class="form-control" placeholder="请输入缩写">
                                </div>
                                <div class="form-group col-md-4">
                                    <label>所属类型</label>
                                    <input type="checkbox" name="term_type" class="form-control" checked>
                                </div>
                            </fieldset>
                            <div class="text-right col-xs-12">
                                <button type="reset" class="btn btn-sm btn-warning">清除</button>
                                <button id="save-term" type="button" class="btn btn-sm btn-success">保存</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>

<script src="../../../static/plugin/jquery/jquery-3.1.1.min.js"></script>
<script src="../../../static/plugin/bootstrap-3.3.7/js/bootstrap.min.js"></script>
<script src="../../../static/plugin/bootstrap-switch/js/bootstrap-switch.js"></script>
<script src="../../../static/common/js/base.js"></script>
<script src="../../../static/admin/js/term.js"></script>

</body>
</html>