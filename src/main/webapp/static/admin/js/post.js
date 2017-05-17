/**
 * Created by liuyuzhe on 2017/5/16.
 */

$("#select-categories").select2({
    placeholder: "选择文章分类",
    allowClear: true,
    tokenSeparators: [',']
});

$("#select-tags").select2({
    tags: true,
    placeholder: "选择或添加文章标签",
    allowClear: true,
    tokenSeparators: [',']
});

(function () {
    var htmlEditor = $("#summer-note").summernote({
        lang: "zh-CN",
        height: 360
    });

    var mdEditor = Mditor.fromTextarea(document.getElementById('md-editor'));
    var switchEditor = $('#switch-editor');

    var mdEditorShow = function() {
        htmlEditor.summernote("code", "");
        $(".md-container").show();
        $(".html-container").hide();
        switchEditor.text("切换为富文本编辑器");
    };
    var htmlEditorShow = function() {
        mdEditor.value = "";
        $(".md-container").hide();
        $(".html-container").show();
        switchEditor.text("切换为Markdown编辑器");
    };

    mdEditorShow();

    switchEditor.click(function() {
        var fmt_type = switchEditor.text();
        if (fmt_type === "切换为富文本编辑器") {
            htmlEditorShow();
        } else {
            mdEditorShow();
        }
    });

})();

$.fn.bootstrapSwitch.defaults.size = 'small';
$.fn.bootstrapSwitch.defaults.handleWidth = '26px';

$("[name='post-status']").bootstrapSwitch({
    onColor : "primary",
    offColor : "warning",
    onText : "公开",
    offText : "私有"
});

$("[name='post_comment_status']").bootstrapSwitch({
    onColor : "primary",
    offColor : "warning",
    onText : "允许",
    offText : "禁止"
});

$("[name='add_thumb_url']").bootstrapSwitch({
    onColor : "primary",
    offColor : "warning",
    onText : "打开",
    offText : "关闭"
});

$("[name='add_thumb_url']").on('switchChange.bootstrapSwitch', function(event, state) {
    $("#thumb-url").toggle(state);
});
