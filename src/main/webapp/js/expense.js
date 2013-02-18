var Expense = function() {
    var self = {};

    var addExpense = function() {
        var row = $(".tabular-form").append("\
            <li class='row expense empty'>\
                <input class='desciption textbox' value='Description'>\
                <input class='amount textbox' value='Amount'>\
                <select class='owner'>\
                    <option>Choose the owner</option>\
                </select>\
                <select multiple class='participant'></select>\
                <div class='action'></div>\
            </li>\
            ");
        insertOwnerAndParticipantOptions(row);
    };

    var insertOwnerAndParticipantOptions = function(row) {
        var owner = row.find(".owner");
        var participant = row.find(".participant");
        var users = $("#users").text().split(",");
        users.pop();
        $.each(users, function(index, elem) {
            var user = elem.trim();
            owner.append("<option>" + user + "</option>")
            participant.append("<option>" + user + "</option>")
        });
        owner.multiselect({
            multiple:false,
            header:false,
            selectedList:1
        });
        participant.multiselect({
            noneSelectedText:"Select the participants"
        });
    };

    self.load = function() {
        $(".expense").each(function() {
            insertOwnerAndParticipantOptions($(this));
        })
        new TabularForm().wireUpHandlers(function() {
        }, addExpense);
    };

    return self;
}