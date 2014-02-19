<div class="navigation">
    <a href="index">index</a>


    <% if (request.getAttribute("u") == null){ %>
        <a href="login">login</a>
        <a href="register">register</a>
    <% } else { %>
        Hi, <%= request.getAttribute("u") %>
        <a href="logout">logout</a>
    <% } %>


</div>