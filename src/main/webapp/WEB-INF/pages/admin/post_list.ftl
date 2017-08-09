<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>StrongLiu's blog - </title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <link rel="stylesheet" href="../../../static/plugin/bootstrap-3.3.7/css/bootstrap.css">
    <link rel="stylesheet" href="http://cdn.bootcss.com/font-awesome/4.3.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="../../../static/admin/css/style.css">
</head>
<body>

<#import "header.ftl" as mainHeader >
<@mainHeader.navbar active="post_list" />

<div class="content-page">
    <div class="container">
        <div class="row">
            <div class="col-sm-12">
                <h4 class="page-title">文章管理</h4>
            </div>
            <div class="col-md-12 table-responsive">
                <table class="table table-bordered table-hover">
                    <thead>
                    <tr>
                        <th>文章标题</th>
                        <th>更新时间</th>
                        <th>文章状态</th>
                        <th>评论状态</th>
                        <th>阅读数</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                <#if (postPageVo.postList)?? >
                    <tbody>
                        <#list postPageVo.postList as post>
                        <tr <#if post.status == 'draft'>class="danger"</#if> >
                            <td> <a href="/admin/post/${post.id}">${post.title}</a> </td>
                            <td>${(post.update_time)?string("yyyy-MM-dd")}</td>
                            <td>
                                <#if post.status == 'publish' >
                                    <span class="label label-success">已发布</span>
                                <#elseif post.status == 'private' >
                                    <span class="label label-warning">私有</span>
                                <#else>
                                    <span class="label label-default">草稿</span>
                                </#if>
                            </td>
                            <td>
                                <#if post.comment_status == 'open' >
                                    <span class="label label-success">打开</span>
                                <#else>
                                    <span class="label label-warning">关闭</span>
                                </#if>
                            </td>
                            <td>${post.read_count}</td>
                            <td>
                                <a href="/admin/post/${post.id}" class="btn btn-primary btn-sm">
                                    <i class="fa fa-edit"></i>
                                    <span>编辑</span>
                                </a>
                                <a href="javascript:" class="btn btn-danger btn-sm delete-post" pid=${post.id}>
                                    <i class="fa fa-trash-o"></i>
                                    <span>删除</span>
                                </a>
                                <#if post.status == "publish" >
                                    <a href="/posts/${post.slug}" class="btn btn-success btn-sm" target="_blank">
                                        <i class="fa fa-rocket"></i>
                                        <span>预览</span>
                                    </a>
                                </#if>
                            </td>
                        </tr>
                        </#list>
                    </tbody>
                </#if>
                </table>
            </div>
        <#if (postPageVo.pageIndex > 0) && (postPageVo.pageTotal > 0) >
            <nav class="col-sm-12 text-center">
                <ul class="pagination">
                    <#list 1..(postPageVo.pageTotal) as index >
                        <li
                            <#if index == (postPageVo.pageIndex) >
                                    class="active"
                            </#if> >
                            <a href="/admin/post/?page=${index}">${index}</a>
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
<script src="../../../static/common/js/base.js"></script>
<script src="../../../static/admin/js/menu.js"></script>
<script src="../../../static/admin/js/post_list.js"></script>

</body>
</html>