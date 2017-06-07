/**
 * Created by liuyuzhe on 2017/5/16.
 */

(function () {
    var post = Base.init();

    $("#select-categories").select2({
        placeholder : "选择文章分类",
        allowClear : true,
        tokenSeparators : [','],
        language : {
            noResults: function() {
                return "未找到可用项";
            }
        },
        escapeMarkup : function (markup) {
            return markup;
        }
    });

    $("#select-tags").select2({
        //tags: true,
        placeholder : "选择文章标签",
        allowClear : true,
        tokenSeparators : [','],
        language : {
            noResults: function() {
                return "未找到可用项";
            }
        },
        escapeMarkup : function (markup) {
            return markup;
        }
    });

    var htmlEditor = $("#summer-note").summernote({
        lang : "zh-CN",
        height : 360,
        focus : true
    });

    editormd.emoji     = {
        path  : "http://www.emoji-cheat-sheet.com/graphics/emojis/",
        ext   : ".png"
    };

    editormd.twemoji = {
        path : "http://twemoji.maxcdn.com/72x72/",
        ext  : ".png"
    };

    var mdEditor = editormd({
        id : "md-editor",
        height : 400,
        syncScrolling : "single",
        path : "../../static/plugin/editor.md/lib/",
        placeholder : "",
        toolbarIcons : function() {
            return ["undo", "redo", "|",
                "bold", "del", "italic", "quote", "uppercase", "lowercase", "|",
                "h1", "h2", "h3", "h4", "h5", "h6", "|",
                "list-ul", "list-ol", "hr", "|",
                "goto-line", "watch", "preview", "fullscreen", "clear", "search", "|",
                "help"
            ];
        }
    });

    var switchEditor = $('#switch-editor');
    var fmt_type = $("#articleForm input[name=fmt_type]");

    var mdEditorShow = function() {
        htmlEditor.summernote("code", "");
        $(".md-container").show();
        $(".html-container").hide();
        switchEditor.text("切换为富文本编辑器");
        fmt_type.val("markdown");
    };
    var htmlEditorShow = function() {
        mdEditor.setMarkdown("");
        $(".md-container").hide();
        $(".html-container").show();
        switchEditor.text("切换为Markdown编辑器");
        fmt_type.val("html");
    };

    if (fmt_type.val() !== "html") {
        mdEditorShow();
    } else {
        htmlEditorShow();
    }
    switchEditor.click(function() {
        if (fmt_type.val() !== "html") {
            htmlEditorShow();
        } else {
            mdEditorShow();
        }
    });

    $.fn.bootstrapSwitch.defaults.size = 'small';
    $.fn.bootstrapSwitch.defaults.handleWidth = '26px';

    var statusElement = $("#articleForm input[name=status]");
    var commentStatusElement = $("#articleForm input[name=comment_status]");

    statusElement.bootstrapSwitch({
        onColor : "primary",
        offColor : "warning",
        onText : "公开",
        offText : "私有"
    });

    commentStatusElement.bootstrapSwitch({
        onColor : "primary",
        offColor : "warning",
        onText : "允许",
        offText : "禁止"
    });

    $("#add_thumb_url").bootstrapSwitch({
        onColor : "primary",
        offColor : "warning",
        onText : "打开",
        offText : "关闭"
    });

    $("#thumb-url").toggle($("#add_thumb_url").is(':checked'));
    $("#add_thumb_url").on('switchChange.bootstrapSwitch', function(event, state) {
        $("#thumb-url").toggle(state);
    });

    var savePost = function(state) {
        var title = $("#articleForm input[name=title]").val();
        var slug = $("#articleForm input[name=slug]").val();
        var categories = $("#select-categories").val();
        var tags = $("select-tags").val();
        var content = switchEditor.text() === "切换为富文本编辑器" ? mdEditor.getMarkdown() : htmlEditor.summernote('code');
        if (post.isEmpty(title) || post.isEmpty(slug) || post.isEmpty(content)) {
            return;
        }

        var id = $("#articleForm input[name=id]").val();
        var url = post.isEmpty(id) ? "/admin/post/create" : "/admin/post/update" ;
        $("#articleForm input[name=content]").val(content);
        $("#articleForm input[name=categories]").val($("#select-categories").val());
        $("#articleForm input[name=tags]").val($("#select-tags").val());

        if (!post.isEmpty(state)) {
            statusElement.val(state);
        } else if (statusElement.bootstrapSwitch('state')) {
            statusElement.val("publish");
        } else {
            statusElement.val("private");
        }

        if (commentStatusElement.bootstrapSwitch('state')) {
            commentStatusElement.val("open");
        } else {
            commentStatusElement.val("close");
        }

        var articleFormArray = $("#articleForm :input")
            .filter(function() {
                return !post.isEmpty($(this).val()) && ($(this).attr('name') != "md-editor-markdown-doc");
            })
            .serializeArray()
            .concat($("#articleForm input[type=checkbox]:not(:checked)")
                .map(function() {
                    if (!post.isEmpty($(this).attr("name")) && !post.isEmpty($(this).val())) {
                        return {"name": $(this).attr("name"), "value": $(this).val()};
                    }
                })
                .get()
            );

        var articleFormData = $.param(articleFormArray);

        $.post({
            url : url,
            dataType : "json",
            data : articleFormData,
            success : function(response) {
                if (response.code === 0) {
                    console.log(response.message);
                } else {
                    console.log(response.message);
                }
            },
            error : function(response) {
                console.log(response.message);
            }
        });
    };

    $("#save_draft").click(function() {
        savePost("draft");
    });

    $("#save_post").click(function() {
        savePost();
    });

})();