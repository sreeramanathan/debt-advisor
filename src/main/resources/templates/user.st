<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="css/main.css" type="text/css">
        <script type="text/javascript" src="js/jquery-1.9.1.js"></script>
        <script type="text/javascript" src="js/tabularForm.js"></script>
        <script type="text/javascript" src="js/user.js"></script>
        <script type="text/javascript">
            jQuery(function() {
                var user = new User();
                user.load();
            });
        </script>
    </head>
    <body>
        <h1 class="heading">Users</h1>
        <ul class='tabular-form'>
            $users:{user|
            <li class="row user $if(user.empty)$empty$else$submitted$endif$">
                <input class="forename textbox" value="$user.forename$">
                <input class="surname textbox" value="$user.surname$">
                <div class="action"></div>
            </li>
            }$
        </ul>
        <div class="add-row"><input type="button" value="Add more"></div>
    </body>
</html>