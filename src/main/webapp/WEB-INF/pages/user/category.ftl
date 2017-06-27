<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>StrongLiu's blog - </title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <link rel="stylesheet" href="../../../static/plugin/bootstrap-3.3.7/css/bootstrap.css">
    <link rel="stylesheet" href="http://cdn.bootcss.com/font-awesome/4.3.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="../../../static/common/css/common.css">
    <link rel="stylesheet" href="../../../static/user/css/category.css">
    <link rel="stylesheet" href="../../../static/user/css/page.css">
</head>
<body>

<#import "header.ftl" as mainHeader >
<#if categoryList??>
    <@mainHeader.navbar categoryList=categoryList />
<#else>
    <@mainHeader.navbar categoryList=[] />
</#if>

<section>
    <div class="container">
        <div class="row">
            <main class="col-md-8">
            <#if (categoryVo.category)?? >
                <h3 class="title">
                ${categoryVo.category.name} （共${categoryVo.category.count}篇文章）
                </h3>
            </#if>
                <article class="archive">
                <#if (categoryVo.postList)?? >
                    <#list categoryVo.postList as post>
                        <ul>
                            <li>
                                <time> ${(post.create_time)?string("yyyy.MM.dd")} </time>
                                <a href="/posts/${post.slug}">${post.title}</a>
                            </li>
                        </ul>
                    </#list>
                </#if>
                </article>
            <#if categoryVo?? >
                <nav class="pagination post-page">
                    <#if (categoryVo.pageIndex > 1) >
                        <a class="newer-posts" href="/?page=${categoryVo.pageIndex - 1}">
                            <i class="fa fa-angle-left"></i>
                        </a>
                    </#if>
                    <#if (categoryVo.pageIndex > 0) && (categoryVo.pageTotal > 0) >
                        <span class="page-number"> ${categoryVo.pageIndex}  &frasl; 共 ${categoryVo.pageTotal} 页</span>
                    </#if>
                    <#if (categoryVo.pageIndex < categoryVo.pageTotal) >
                        <a class="older-posts" href="/?page=${categoryVo.pageIndex + 1}">
                            <i class="fa fa-angle-right"></i>
                        </a>
                    </#if>
                </nav>
            </#if>
            </main>
            <aside class="col-md-4 hidden-xs hidden-sm sidebar">

            </aside>
        </div>
    </div>
</section>

<script src="../../../static/plugin/jquery/jquery-3.1.1.min.js"></script>
<script src="../../../static/plugin/bootstrap-3.3.7/js/bootstrap.min.js"></script>
<script src="../../../static/user/js/page.js"></script>

</body>
</html>