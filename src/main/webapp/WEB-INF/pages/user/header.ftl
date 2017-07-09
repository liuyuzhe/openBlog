<#macro navbar categoryList=[] >
<header class="main-header">
    <nav class="navbar navbar-default">
        <div class="container">
            <div class="navbar-header">
                <a class="navbar-brand nav-logo" href="/">StrongLiu's blog</a>
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#navbar-collapse">
                    <span class="sr-only">切换导航</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
            </div>
            <div class="collapse navbar-collapse" id="navbar-collapse">
                <ul class="nav navbar-nav navbar-right">
                    <li> <a class="write-btn" href="/"> <span class="glyphicon glyphicon-home"> </span> 首页 </a> </li>
                    <#if categoryList?? >
                        <#list categoryList>
                            <li class="dropdown">
                                <a class="dropdown-toggle" data-toggle="dropdown" href="javascript:"> 文章归档
                                    <span class="caret"></span>
                                </a>
                                <ul class="dropdown-menu">
                                    <#items as category>
                                        <li>
                                            <a href="/categories/${category.slug}">${category.name}</a>
                                        </li>
                                    </#items>
                                </ul>
                            </li>
                        </#list>
                    </#if>
                    <li> <a href="javascript:"> <span class="glyphicon glyphicon-user"></span> 关于 </a> </li>
                </ul>
            </div>
        </div>
    </nav>
</header>
</#macro>