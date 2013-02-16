var Expense = function() {
    var self = {};

    self.load = function() {
        $(".owner").multiselect({
            multiple:false,
            header:false,
            selectedList:1
        });
        $(".participant").multiselect({
            noneSelectedText:"Select the participants"
        });
    };

    return self;
}