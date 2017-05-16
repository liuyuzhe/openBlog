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
        height: 360,
        placeholder: "请输入文章内容"
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
