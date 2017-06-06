/**
 * Created by liuyuzhe on 2017/6/5.
 */

(function () {
    var term = Base.init();

    $("#term_type").bootstrapSwitch({
        onColor : "primary",
        offColor : "warning",
        onText : "分类",
        offText : "标签"
    });

    var idElem = $('#termForm input[name=id]');
    var nameElem = $('#termForm input[name=name]');
    var slugElem = $('#termForm input[name=slug]');
    var typeElem = $('#term_type');

    $(".update-term").click(function() {
        var id = $(this).attr('tid');
        var name = $(this).attr('tname');
        var slug = $(this).attr('tslug');
        var state = $(this).attr('ttype') === "category";

        idElem.val(id);
        nameElem.val(name);
        slugElem.val(slug);
        typeElem.bootstrapSwitch("state", state);
    });

    $(".delete-term").click(function() {
        var url = $(this).attr('ttype') === "category" ? "/admin/category/remove" : "/admin/tag/remove";
        var id = $(this).attr('tid');

        $.post({
            url : url,
            dataType : "json",
            data : {id: id},
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
        })
    });

    $("#save-term").click(function() {
        var name = nameElem.val();
        var slug = slugElem.val();
        if (term.isEmpty(name) || term.isEmpty(slug)) {
            return;
        }

        var id = idElem.val();
        var url;
        if (typeElem.bootstrapSwitch('state')) {
            url = term.isEmpty(id) ? "/admin/category/create" : "/admin/category/update";
        } else {
            url = term.isEmpty(id) ? "/admin/tag/create" : "/admin/tag/update";
        }

        $.post({
            url : url,
            dataType : "json",
            data : $("#termForm").serialize(),
            success : function(response) {
                if (response.code === 0) {
                    console.log(response.message);
                } else {
                    console.log(response.message);
                }
            },
            error : function(response) {
                console.log(response.message);
            },
            complete : function() {
                idElem.val("");
            }
        });
    });

})();