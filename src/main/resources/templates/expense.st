<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="css/jquery-ui-1.10.1.custom.css" type="text/css">
        <link rel="stylesheet" href="css/jquery.multiselect.css" type="text/css">
        <link rel="stylesheet" href="css/main.css" type="text/css">
        <link rel="stylesheet" href="css/expense.css" type="text/css">
        <script type="text/javascript" src="js/jquery-1.9.1.js"></script>
        <script type="text/javascript" src="js/jquery-ui-1.10.1.custom.js"></script>
        <script type="text/javascript" src="js/jquery.multiselect.js"></script>
        <script type="text/javascript" src="js/tabularForm.js"></script>
        <script type="text/javascript" src="js/expense.js"></script>
        <script type="text/javascript">
            jQuery(function() {
                var expense = new Expense();
                expense.load();
            });
        </script>
    </head>
    <body>
        <h1 class="heading">Expenses</h1>
        <div id="users" style="display: none">
            $users:{user|
                $user.forename$ $user.surname$,
            }$
        </div>
        <ul class='tabular-form'>
            $expenses:{expense|
            <li class="row expense empty">
                <input class="description textbox" value="$expense.description$">
                <input class="amount textbox" value="$expense.amount$">
                <select class="owner">
                    <option>Choose the owner</option>
                </select>
                <select multiple class="participant"></select>
                <div class="action"></div>
            </li>
            }$
        </ul>
        <div class="add-row"><input type="button" value="Add more"></div>
    </body>
</html>