/**
 * Created by liuyuzhe on 2017/6/8.
 */

(function () {

    $(".delete-post").click(function() {
        var id = $(this).attr('pid');
        var self = this;

        $.post({
            url : "/admin/post/remove",
            dataType : "json",
            data : {postId: id},
            success : function(response) {
                if (response.code === 0) {
                    $(self).closest("tr").remove();
                    console.log(response.message);
                } else {
                    console.log(response.message);
                }
            },
            error : function(response) {
                console.log(response.message);
            }
        })
    });

})();