<html>
    <head>
        <link rel="stylesheet" href="css/user.css" type="text/css">
    </head>
    <body>
        <div id="heading">Users</div>
        <ul id='users'>
            $users:{user|
            <li class="user $if(user.empty)$empty$endif$">
                <input class="forename" value="$user.forename$">
                <input class="surname" value="$user.surname$">

                <div class="action"></div>
            </li>
            }$
        </ul>
        <div class="add-user"><input type="button" value="Add more"></div>
    </body>
</html>