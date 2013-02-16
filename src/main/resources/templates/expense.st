<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="css/jquery-ui-1.10.1.custom.css" type="text/css">
        <link rel="stylesheet" href="css/jquery.multiselect.css" type="text/css">
        <link rel="stylesheet" href="css/expense.css" type="text/css">
        <script type="text/javascript" src="js/jquery-1.9.1.js"></script>
        <script type="text/javascript" src="js/jquery-ui-1.10.1.custom.js"></script>
        <script type="text/javascript" src="js/jquery.multiselect.js"></script>
        <script type="text/javascript" src="js/expense.js"></script>
        <script type="text/javascript">
            jQuery(function() {
                var expense = new Expense();
                expense.load();
            });
        </script>
    </head>
    <body>
        <div id="heading">Expenses</div>
        <ul id='expenses'>
            $expenses:{expense|
            <li class="expense empty">
                <input class="description" value="$expense.description$">
                <input class="amount" value="$expense.amount$">
                <select class="owner">
                    <option>Choose the owner</option>
                    $users:{user|
                    <option>$user.forename$ $user.surname$</option>
                    }$
                </select>
                <select multiple class="participant">
                    $users:{user|
                    <option>$user.forename$ $user.surname$</option>
                    }$
                </select>

                <div class="action"></div>
            </li>
            }$
        </ul>
        <div class="add-expense"><input type="button" value="Add more"></div>
    </body>
</html>