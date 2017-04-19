<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>strongliu's blog</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <link rel="stylesheet" href="../../../static/plugin/bootstrap-3.3.7/css/bootstrap.css">
    <link rel="stylesheet" href="../../../static/plugin/font-awesome-4.7.0/css/font-awesome.css">
    <link rel="stylesheet" href="../../../static/css/common.css">
    <link rel="stylesheet" href="../../../static/css/post.css">
    <link rel="stylesheet" href="../../../static/css/sidebar.css">
</head>
<body>

<#include "../common/header.html">

<section>
    <div class="container">
        <div class="row">
            <#if categorylist??>
                <#list categorylist>
                    <div class="col-md-8">
                        <ul class="nav nav-tabs nav-justified post-category">
                            <li class="active">
                                <a href="#">首页</a>
                            </li>
                            <#items as category>
                                <li>
                                    <a href="#">#{category}</a>
                                </li>
                            </#items>
                        </ul>
                    </div>
                </#list>
            </#if>
            <main class="col-md-8 main-content">
                <#if (postPageVo.postList)??>
                    <#list postPageVo.postList as post>
                        <article class="post">
                            <div class="post-summary clearfix">
                                <div class="post-image hidden-xs">
                                    <a href="#">
                                        <img src="https://www.vtrois.com/wp-content/uploads/2017/02/kratos-update.png" />
                                    </a>
                                </div>
                                <div class="post-inner">
                                    <header class="post-title">
                                        <a href="#">${post.title}</a>
                                    </header>
                                    <div>
                                        <p>${post.excerpt}</p>
                                    </div>
                                </div>
                            </div>
                            <footer class="post-footer">
                                <div class="post-meta">
                            <span class="hidden-xs pull-left">
                                <time class="fa fa-calendar"> ${post.create_time} </time>
                                <a href="#"> <i class="fa fa-comment-o"> ${(post.comment_count)!0} </i> </a>
                            </span>
                            <span class="pull-left">
                                <a href="#"> <i class="fa fa-eye"> ${(post.read_count)!0} </i> </a>
                                <i class="fa fa-heart-o"> ${(post.spot_count)!0} </i>
                            </span>
                                </div>
                        <span class="post-link pull-right">
                            <a href="#" class="btn btn-custom">阅读全文</a>
                        </span>
                            </footer>
                        </article>
                    </#list>
                </#if>
                <#if postPageVo??>
                    <nav class="pagination">
                        <a href="#">
                            <i class="fa fa-angle-left"></i>
                        </a>
                        <span>第 ${postPageVo.pageIndex} 页 &frasl; 共 ${postPageVo.pageTotal} 页</span>
                        <a href="#">
                            <i class="fa fa-angle-right"></i>
                        </a>
                    </nav>
                </#if>
            </main>
            <aside class="col-md-4 hidden-xs hidden-sm sidebar">
                <div class="widget-about">
                    <div class="about-background">
                    </div>
                    <div class="user-avatar">
                        <a href="#">
                            <img src="https://www.vtrois.com/wp-content/uploads/2017/02/avatar.jpg">
                        </a>
                    </div>
                    <div class="user-motto">
                        <p>博学 慎思 明辨 笃行</p>
                    </div>
                </div>
                <#if taglist??>
                    <#list taglist>
                        <div class="widget-tags">
                            <h4 class="widget-title">标签云</h4>
                            <div class="tag-cloud">
                                <#items as tag>
                                    <a href="#">${tag}</a>
                                </#items>
                            </div>
                        </div>
                    </#list>
                </#if>
            </aside>
        </div>
    </div>
</section>

<script src="../../../static/plugin/jquery/jquery-3.1.1.min.js"></script>
<script src="../../../static/plugin/bootstrap-3.3.7/js/bootstrap.min.js"></script>

</body>
</html>