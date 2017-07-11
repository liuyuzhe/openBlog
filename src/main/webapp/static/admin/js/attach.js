/**
 * Created by liuyuzhe on 2017/5/18.
 */

var attach = Base.init();

(function () {

    var uploader = new plupload.Uploader({
        browse_button : "upload-file",
        drop_element : "upload-file",
        url : "/admin/attach/upload",
        max_file_size : "50mb",
        chunk_size: 0,
        auto_start : true
    });

    uploader.init();

    uploader.bind("FilesAdded", function(uploader, files) {
        uploader.start();
    });

    uploader.bind("BeforeUpload", function(uploader, file) {
    });

    uploader.bind("UploadProgress", function(uploader, file) {
    });

    uploader.bind("FileUploaded", function(uploader, file, result) {
        var response = JSON.parse(result.response);
        if (response.code === 0) {
            if (!attach.isEmpty(response.data)) {
                var responseData = JSON.parse(response.data);
                var fileSlug = responseData.fileSlug;

                window.location.reload();
                console.log("fileSlug: ", fileSlug);
            }
        }

        console.log("result:", result);
    });

    uploader.bind("Error", function(uploader, error) {
        console.log("error:", error);
    });

    var clipboard = new Clipboard('.copy-attach', {
        text: function (trigger) {
            return $(trigger).attr('aslug');
        }
    });

    clipboard.on('success', function (e) {
        console.info('Action:', e.action);
        console.info('Text:', e.text);
        console.info('Trigger:', e.trigger);
        e.clearSelection();
    });

    clipboard.on('error', function(e) {
        console.error('Action:', e.action);
        console.error('Trigger:', e.trigger);
    });

    $(".delete-attach").click(function() {
        var id = $(this).attr('aid');
        var self = this;

        $.post({
            url : "/admin/attach/remove",
            dataType : "json",
            data : {attachId: id},
            success : function(response) {
                if (response.code === 0) {
                    $(self).closest("div.attach").remove();
                    console.log(response.message);
                } else {
                    console.log(response.message);
                }
            },
            error : function(response) {
                console.log(response.message);
            }
        });
    });

})();
