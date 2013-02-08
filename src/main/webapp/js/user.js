var User = function() {
    var self = {};

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
            user.addClass("empty");
            user.find(".forename").val("Forename");
            user.find(".surname").val("Surname");
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
    }

    self.load = function() {
        $(".forename,.surname").focus(textboxFocus);
        $(".forename,.surname").blur(textboxBlur);
        $(".add-user").click(addUser);
    };

    return self;
}