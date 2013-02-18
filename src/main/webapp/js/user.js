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

    self.load = function() {
        new TabularForm().wireUpHandlers(ajaxForUser, addUser);
    };

    return self;
}