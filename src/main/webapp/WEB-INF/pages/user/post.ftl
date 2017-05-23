<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>${postVo.post.title!"StrongLiu's blog"}</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <link rel="stylesheet" href="../../../static/plugin/bootstrap-3.3.7/css/bootstrap.css">
    <link rel="stylesheet" href="../../../static/plugin/font-awesome-4.7.0/css/font-awesome.css">
    <link rel="stylesheet" href="../../../static/common/css/common.css">
    <link rel="stylesheet" href="../../../static/user/css/post.css">
    <link rel="stylesheet" href="../../../static/user/css/sidebar.css">
    <link rel="stylesheet" href="../../../static/user/css/page.css">
</head>
<body>

<#include "header.html">

<section>
    <div class="container">
        <div class="row">
            <#if postVo?? >
                <main class="col-md-12 main-content">
                <article class="post">
                    <#if (postVo.post)?? >
                        <header>
                            <h1 class="post-title">${postVo.post.title}</h1>
                            <div class="post-meta">
                                <span class="hidden-xs">
                                    <time class="fa fa-calendar" datetime="${postVo.post.create_time}"> ${postVo.post.create_time} </time>
                                    <i class="fa fa-comment"> ${postVo.post.comment_count!0} </i>
                                </span>
                                <span class="">
                                    <i class="fa fa-eye"> ${(postVo.post.read_count)!0} </i>
                                    <i class="fa fa-heart"> ${(postVo.post.spot_count)!0} </i>
                                </span>
                            </div>
                        </header>
                    </#if>
                    <div class="post-support text-center">
                        <a href="#" class="post-donate"> <i class="fa fa-cny"></i> 打赏 </a>
                        <a href="#" class="post-love"> <i class="fa fa-heart-o"></i> 点赞 </a>
                        <a href="#" class="post-share"> <i class="fa fa-share-alt"></i> 分享 </a>
                    </div>
                    <#if (postVo.tagList)?? >
                        <#list postVo.tagList>
                            <footer>
                                <div class="post-tags clearfix">
                                    <span class="pull-left">
                                        <i class="fa fa-tags"></i>
                                        <#items as tag>
                                            <a href="${tag.slug}">${tag.name}</a>
                                        </#items>
                                    </span>
                                </div>
                            </footer>
                        </#list>
                    </#if>
                </article>
                    <#if (postVo.postPrev)?? >
                        <div class="post-prev">
                            <a href="${postVo.postPrev.slug}" title="${postVo.postPrev.title}"> <i class="fa fa-angle-left"></i> ${postVo.postPrev.title} </a>
                        </div>
                    </#if>
                    <#if (postVo.postNext)?? >
                        <div class="post-next">
                            <a href="${postVo.postNext.slug}" title="${postVo.postNext.title}"> ${postVo.postNext.title} <i class="fa fa-angle-right"></i> </a>
                        </div>
                    </#if>
                </main>
            </#if>
        </div>
    </div>
</section>

<a href="#" id="back-to-top">
    <i class="fa fa-angle-up"></i>
</a>

<script src="../../../static/plugin/jquery/jquery-3.1.1.min.js"></script>
<script src="../../../static/plugin/bootstrap-3.3.7/js/bootstrap.min.js"></script>
<script src="../../../static/user/js/page.js"></script>

</body>
</html>