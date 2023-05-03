<#macro nav>
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <a class="navbar-brand" href="/">Optical store</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">

            <#if user.username??>
                <ul class="navbar-nav mr-auto">
                    <li class="nav-item active" style="font-weight: bold">
                        <a class="nav-link" href="/">${user.username} <span class="sr-only">(current)</span></a>
                    </li>
                    <#if user.isAdmin()>
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                Admin panel
                            </a>
                            <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                                <a class="dropdown-item" href="/product/new">Add item</a>
                                <a class="dropdown-item" href="/product/list">Edit items</a>
                                <div class="dropdown-divider"></div>
                                <a class="dropdown-item" href="/admin/users">
                                    User management</a>
                            </div>
                        </li>
                    </#if>
                    <li class="nav-item active">
                        <a class="nav-link" href="/cart">Shopping Cart</a>
                    </li>
                </ul>
                <form class="form-inline my-2 my-lg-0" action="/logout" method="post">
                    <input type="hidden" name="_csrf" value="${_csrf.token}">
                    <input class="btn btn-outline-danger" type="submit" value="Log out"/>
                </form>
            <#else>
                <div class="my-2 my-lg-0">
                    <a class="btn btn-primary" href="/login" class="link-light">Log in</a>
                </div>
            </#if>
        </div>
    </nav>
</#macro>