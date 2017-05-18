/**
 * Created by liuyuzhe on 2017/5/18.
 */

Dropzone.autoDiscover = false;

(function () {
    $("#upload-file").dropzone({
        url : "/admin/attach/upload",
        maxFilesize : 50,
        filesizeBase : 1024,
        uploadMultiple : true,
        maxFiles : 5,
        init: function() {
            this.on('queuecomplete', function (files) {
            });
            this.on('error', function (a, errorMessage, result) {
            });
            this.on('maxfilesreached', function () {
            });
        }
    });
})();
