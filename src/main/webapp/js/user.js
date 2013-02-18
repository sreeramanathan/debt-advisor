var User = function() {
    var self = {};

    var addUser = function() {
        $(".tabular-form").append("\
            <li class='row user empty'>\
                <input class='forename textbox' value='Forename'>\
                <input class='surname textbox' value='Surname'>\
                <div class='action'></div>\
            </li>\
            ");
    };

    var createSuccess = function(user) {
        user.addClass("submitted");
    };

    var deleteSuccess = function(user) {
        user.remove();
    };

    var ajaxForUser = function(type, successHandler, user) {
        var forename = user.find('.forename').val();
        var surname = user.find('.surname').val();
        $.ajax({
            url: "user/" + forename + " " + surname,
            type: type,
            success: function() {
                successHandler(user);
            }
        });
    };

    var action = function() {
        var element = $(this);
        var parent = element.parent();
        if (parent.hasClass("submitted")) {
            ajaxForUser("DELETE", deleteSuccess, parent);
        } else if (!parent.hasClass("empty")) {
            ajaxForUser("POST", createSuccess, parent);
        }
    };

    self.load = function() {
        new TabularForm().wireUpHandlers(action, addUser);
    };

    return self;
}