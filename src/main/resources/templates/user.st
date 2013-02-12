<html>
    <head>
        <link rel="stylesheet" href="css/user.css" type="text/css">
        <script type="text/javascript" src="js/jquery-1.9.1.js"></script>
        <script type="text/javascript" src="js/user.js"></script>
        <script type="text/javascript">
            jQuery(function() {
                var user = new User();
                user.load();
            });
        </script>
    </head>
    <body>
        <div id="heading">Users</div>
        <ul id='users'>
            $users:{user|
            <li class="user $if(user.empty)$empty$else$submitted$endif$">
                <input name="forename" class="forename" value="$user.forename$">
                <input name="surname" class="surname" value="$user.surname$">

                <div class="action"></div>
            </li>
            }$
        </ul>
        <div class="add-user"><input type="button" value="Add more"></div>
    </body>
</html>