/**
 * Created by liuyuzhe on 2017/5/16.
 */

(function () {
    var post = Base.init();

    $("#select-categories").select2({
        placeholder: "选择文章分类",
        allowClear: true,
        tokenSeparators: [',']
    });

    $("#select-tags").select2({
        placeholder: "选择文章标签",
        allowClear: true,
        tokenSeparators: [',']
    });

    var htmlEditor = $("#summer-note").summernote({
        lang: "zh-CN",
        height: 360
    });


    var mdEditor = Mditor.fromTextarea(document.getElementById('md-editor'));
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
        mdEditor.value = "";
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

    $("[name='status']").bootstrapSwitch({
        onColor : "primary",
        offColor : "warning",
        onText : "公开",
        offText : "私有"
    });

    $("[name='comment_status']").bootstrapSwitch({
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

    var thumb = $("#articleForm input[name=add_thumb_url]");
    $("#thumb-url").toggle(thumb.is(':checked'));

    $("[name='add_thumb_url']").on('switchChange.bootstrapSwitch', function(event, state) {
        $("#thumb-url").toggle(state);
    });

    var savePost = function(state) {
        var title = $("#articleForm input[name=title]").val();
        var slug = $("#articleForm input[name=slug]").val();
        var categories = $("#select-categories").val();
        var tags = $("select-tags").val();
        var content = switchEditor.text() === "切换为富文本编辑器" ? mdEditor.value : htmlEditor.summernote('code');
        if (post.isEmpty(title) || post.isEmpty(slug) || post.isEmpty(content)) {
            return;
        }

        var id = $("#articleForm input[name=id]").val();
        var url = post.isEmpty(id) ? "/admin/post/create" : "/admin/post/update" ;
        $("#articleForm input[name=content]").val(content);
        $("#articleForm input[name=categories]").val($("#select-categories").val());
        $("#articleForm input[name=tags]").val($("#select-tags").val());

        var statusElement = $("#articleForm input[name=status]");
        if (!post.isEmpty(state)) {
            statusElement.val(state);
        } else if (statusElement.val() === "on") {
            statusElement.val("publish");
        } else {
            statusElement.val("private");
        }

        var commentStatusElement = $("#articleForm input[name=comment_status]");
        if (commentStatusElement.val() === "on") {
            commentStatusElement.val("open");
        } else {
            commentStatusElement.val("close");
        }

        $.post({
            url : url,
            dataType : "json",
            data : $("#articleForm").serialize(),
            success : function(response) {
                if (response.code == 0) {
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