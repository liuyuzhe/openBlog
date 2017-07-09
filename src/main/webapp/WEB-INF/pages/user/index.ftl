<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
<#if (postPageVo.pageIndex)?? && (postPageVo.pageIndex > 1) >
    <title>StrongLiu's blog - 第 ${postPageVo.pageIndex} 页</title>
<#else>
    <title>StrongLiu's blog - 首页 </title>
</#if>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <link rel="stylesheet" href="../../../static/plugin/bootstrap-3.3.7/css/bootstrap.css">
    <link rel="stylesheet" href="../../../static/plugin/font-awesome-4.7.0/css/font-awesome.css">
    <link rel="stylesheet" href="../../../static/common/css/common.css">
    <link rel="stylesheet" href="../../../static/user/css/post.css">
    <link rel="stylesheet" href="../../../static/user/css/sidebar.css">
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
        <#if categoryList?? >
            <#list categoryList>
                <div class="col-md-8">
                    <ul class="nav nav-tabs nav-justified post-category">
                        <li class="active">
                            <a href="/">首页</a>
                        </li>
                        <#items as category>
                            <li>
                                <a href="/categories/${category.slug}">${category.name}</a>
                            </li>
                        </#items>
                    </ul>
                </div>
            </#list>
        </#if>
            <main class="col-md-8 main-content">
            <#if (postPageVo.postList)?? >
                <#list postPageVo.postList as post>
                    <article class="post">
                        <div class="post-summary clearfix">
                            <#if (post.thumb_url)?? && (post.thumb_url) != "" >
                                <div class="post-image hidden-xs">
                                    <a href="/posts/${post.slug}">
                                        <img src=${post.thumb_url}/>
                                    </a>
                                </div>
                            </#if>
                            <div class="post-inner">
                                <header class="post-title">
                                    <a href="/posts/${post.slug}">${post.title}</a>
                                </header>
                                <div>
                                    <p>${post.excerpt}</p>
                                </div>
                            </div>
                        </div>
                        <footer class="post-footer">
                            <div class="post-meta">
                            <span class="hidden-xs pull-left">
                                <time class="fa fa-calendar"> ${(post.create_time)?string("yyyy-MM-dd")}</time>
                                <a href="/posts/${post.slug}">
                                    <i class="fa fa-comment-o"> ${(post.comment_count)!0} </i>
                                </a>
                            </span>
                            <span class="pull-left">
                                <a href="/posts/${post.slug}">
                                    <i class="fa fa-eye"> ${(post.read_count)!0} </i>
                                </a>
                                <i class="fa fa-heart-o"> ${(post.spot_count)!0} </i>
                            </span>
                            </div>
                        <span class="post-link pull-right">
                            <a href="/posts/${post.slug}" class="btn btn-custom">阅读全文</a>
                        </span>
                        </footer>
                    </article>
                </#list>
            </#if>
            <#if postPageVo?? >
                <nav class="pagination post-page">
                    <#if (postPageVo.pageIndex > 1) >
                        <a class="newer-posts" href="/?page=${postPageVo.pageIndex - 1}">
                            <i class="fa fa-angle-left"></i>
                        </a>
                    </#if>
                    <#if (postPageVo.pageIndex > 0) && (postPageVo.pageTotal > 0) >
                        <span class="page-number"> ${postPageVo.pageIndex}  &frasl; 共 ${postPageVo.pageTotal} 页</span>
                    </#if>
                    <#if (postPageVo.pageIndex < postPageVo.pageTotal) >
                        <a class="older-posts" href="/?page=${postPageVo.pageIndex + 1}">
                            <i class="fa fa-angle-right"></i>
                        </a>
                    </#if>
                </nav>
            </#if>
            </main>
            <aside class="col-md-4 hidden-xs hidden-sm sidebar">
                <div class="widget-about">
                    <div class="about-background">
                    </div>
                    <div class="user-avatar">
                        <a href="javascript:">
                            <img src="https://www.vtrois.com/wp-content/uploads/2017/02/avatar.jpg">
                        </a>
                    </div>
                    <div class="user-motto">
                        <p>博学 慎思 明辨 笃行</p>
                    </div>
                </div>
            <#if tagList??>
                <#list tagList>
                    <div class="widget-tags">
                        <h4 class="widget-title">标签云</h4>
                        <div class="tag-cloud">
                            <#items as tag>
                                <a href="/tags/${tag.slug}">${tag.name}</a>
                            </#items>
                        </div>
                    </div>
                </#list>
            </#if>
            </aside>
        </div>
    </div>
</section>

<a href="javascript:" id="back-to-top">
    <i class="fa fa-angle-up"></i>
</a>

<script src="../../../static/plugin/jquery/jquery-3.1.1.min.js"></script>
<script src="../../../static/plugin/bootstrap-3.3.7/js/bootstrap.min.js"></script>
<script src="../../../static/user/js/page.js"></script>

</body>
</html>