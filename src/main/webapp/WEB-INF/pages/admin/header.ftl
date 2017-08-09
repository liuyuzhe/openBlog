<#macro navbar active="">
<header class="topbar">
    <div class="topbar-left pull-left">
        <div class="text-center">
            <a href="/">StrongLiu</a>
        </div>
    </div>
    <nav class="navbar navbar-default topbar-menu">
        <div class="container">
            <div class="show-more pull-left">
                <button type="button" class="button-menu">
                    <i class="fa fa-bars"></i>
                </button>
            </div>
            <ul class="nav navbar-nav navbar-right pull-right">
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                        <img src="#" alt="user" class="img-circle">
                    </a>
                    <ul class="dropdown-menu">
                        <li> <a href="#"> <i class="fa fa-sun-o"></i> 设置</a> </li>
                        <li> <a href="#"> <i class="fa fa-sign-out"></i> 注销 </a> </li>
                    </ul>
                </li>
            </ul>
        </div>
    </nav>
</header>
<div class="side-menu">
    <div class="sidebar-menu pull-left">
        <ul>
            <li>
                <a href="/admin/" <#if (active == "") >class="active"</#if> >
                    <i class="fa fa-tachometer"></i>
                    <span> 仪表盘 </span>
                </a>
            </li>
            <li>
                <a href="/admin/post/create" <#if (active == "post_edit") >class="active"</#if> >
                    <i class="fa fa-edit"></i>
                    <span> 发布文章 </span>
                </a>
            </li>
            <li>
                <a href="/admin/post/" <#if (active == "post_list") >class="active"</#if> >
                    <i class="fa fa-tasks"></i>
                    <span> 文章管理 </span>
                </a>
            </li>
            <li>
                <a href="/admin/attach/" <#if (active == "attach") >class="active"</#if> >
                    <i class="fa fa-cloud-upload"></i>
                    <span> 附件管理 </span>
                </a>
            </li>
            <li>
                <a href="/admin/term/" <#if (active == "term") >class="active"</#if> >
                    <i class="fa fa-tags"></i>
                    <span> 分类/标签 </span>
                </a>
            </li>
            <li>
                <a href="/admin/" <#if (active == "setting") >class="active"</#if> >
                    <i class="fa fa-gear"></i>
                    <span> 系统设置 </span>
                </a>
            </li>
        </ul>
    </div>
</div>
</#macro>