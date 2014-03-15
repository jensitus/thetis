    <nav class="navbar navbar-default navbar-fixed-top" role="navigation">
        <div class="container-fluid">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="/thetis-1/connected_posts" style="color: #000">project work</a>
            </div>

            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav">

                    <li><a href="/thetis-1/index">index</a></li>
                    <li><a href="/thetis-1/posts">all posts</a></li>

                    <% if (request.getAttribute("u") == null){ %>

                    <% } else { %>
                        <li><a href="/thetis-1/create">create</a></li>
                        <li><a href="/thetis-1/connected_posts">my connections</a></li>
                    <% } %>



                </ul>

                <ul class="nav navbar-nav navbar-right">
                    <% if (request.getAttribute("u") == null){ %>
                        <li><a href="/thetis-1/login">login</a></li>
                        <li><a href="/thetis-1/register">register</a></li>
                    <% } else { %>
                        <li><a href='/thetis-1/user/?name=<%= request.getAttribute("u") %>'><%= request.getAttribute("u") %></a></li>
                        <li><a href="/thetis-1/logout">logout</a></li>
                    <% } %>
                </ul>
            </div><!-- /.navbar-collapse -->
        </div><!-- /.container-fluid -->
    </nav>