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
                <a class="navbar-brand" href="../" style="color: #000">project work</a>
            </div>

            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav">

                    <li><a href="../index">index</a></li>
                    <li><a href="../posts">post</a></li>

                    <% if (request.getAttribute("u") == null){ %>

                    <% } else { %>
                        <li><a href="../create">create</a></li>
                        <li><a href="../connected_posts">connects</a></li>
                    <% } %>



                </ul>

                <ul class="nav navbar-nav navbar-right">
                    <% if (request.getAttribute("u") == null){ %>
                        <li><a href="../login">login</a></li>
                        <li><a href="../register">register</a></li>
                    <% } else { %>
                        <li><a href='../user/?name=<%= request.getAttribute("u") %>'><%= request.getAttribute("u") %></a></li>
                        <li><a href="../logout">logout</a></li>
                    <% } %>
                </ul>
            </div><!-- /.navbar-collapse -->
        </div><!-- /.container-fluid -->
    </nav>