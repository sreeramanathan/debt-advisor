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

    var wireUpRowHandlers = function(action) {
        $(".textbox").unbind();
        $(".action").unbind();
        $(".textbox").focus(textboxFocus);
        $(".textbox").blur(textboxBlur);
        $(".action").click(action);
    };

    self.wireUpHandlers = function(action, addRow) {
        wireUpRowHandlers(action);
        $(".add-row").click(addRow);
        $(".add-row").click(function() {
            wireUpRowHandlers(action)
        });
    };

    return self;
}