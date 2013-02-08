var User = function() {
    var self = {};

    var emptyTheUser = function(user) {
        user.addClass("empty");
        user.find(".forename").val("Forename");
        user.find(".surname").val("Surname");
    }

    var textboxFocus = function() {
        var user = $(this).parent(".user");
        if (user.hasClass("empty")) {
            user.find(".forename,.surname").val("");
            user.removeClass("empty");
        }
    };

    var textboxBlur = function() {
        var user = $(this).parent(".user");
        if (user.find(".forename").val() === "" && user.find(".surname").val() === "") {
            emptyTheUser(user);
        }
    };

    var addUser = function() {
        $("#users").append("\
            <li class='user empty'>\
                <input class='forename' value='Forename'>\
                <input class='surname' value='Surname'>\
                <div class='action'></div>\
            </li>\
            ");
        $(".forename,.surname").unbind();
        $(".forename,.surname").focus(textboxFocus);
        $(".forename,.surname").blur(textboxBlur);
    };

    var createSuccess = function(user) {
        user.addClass("submitted");
    };

    var deleteSuccess = function(user) {
        user.removeClass("submitted");
        emptyTheUser(user);
    };

    var ajaxForUser = function(type, successHandler, user) {
        $.ajax({
            url: "user",
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
        } else if (!element.hasClass("empty")) {
            ajaxForUser("POST", createSuccess, parent);
        }
    };

    self.load = function() {
        $(".forename,.surname").focus(textboxFocus);
        $(".forename,.surname").blur(textboxBlur);
        $(".add-user").click(addUser);
        $(".action").click(action);
    };

    return self;
}