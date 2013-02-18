var TabularForm = function() {
    var self = {};

    var emptyTheUser = function(row) {
        row.addClass("empty");
        row.find(".textbox").each(function() {
            var textbox = $(this);
            textbox.val(this.defaultValue);
        });
    };

    var textboxFocus = function() {
        var row = $(this).parent(".row");
        if (row.hasClass("empty")) {
            row.find(".textbox").val("");
            row.removeClass("empty");
        }
    };

    var textboxBlur = function() {
        var row = $(this).parent(".row");
        var rowIsEmpty =
            $.inArray(true,
                $.map(row.find(".textbox"),
                    function(elem) {
                        return $(elem).val() !== "";
                    }
                )
            ) === -1;

        if (rowIsEmpty) {
            emptyTheUser(row);
        }
    };

    var createSuccess = function(row) {
        row.addClass("submitted");
    };

    var deleteSuccess = function(row) {
        row.remove();
    };

    var action = function(elem, ajax) {
        var parent = elem.parent();
        if (parent.hasClass("submitted")) {
            ajax("DELETE", deleteSuccess, parent);
        } else if (!parent.hasClass("empty")) {
            ajax("POST", createSuccess, parent);
        }
    };

    var wireUpRowHandlers = function(ajax) {
        $(".textbox").unbind();
        $(".action").unbind();
        $(".textbox").focus(textboxFocus);
        $(".textbox").blur(textboxBlur);
        $(".action").click(function() {
            action($(this), ajax);
        });
    };

    self.wireUpHandlers = function(ajax, addRow) {
        wireUpRowHandlers(ajax);
        $(".add-row").click(addRow);
        $(".add-row").click(function() {
            wireUpRowHandlers(ajax)
        });
    };

    return self;
}