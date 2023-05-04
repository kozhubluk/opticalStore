<#import "common/page.ftlh" as c>
<#import "common/navbar.ftlh" as nav>

<@c.page>

    <@nav.nav>
    </@nav.nav>

    <div style="width: 96%; margin: auto; padding: 10px;">


            <form class="form-inline my-2 my-lg-0" style="width: fit-content; margin: auto">
                <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search">
                <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
            </form>
            <div class="list" style="display: flex; flex-wrap: wrap; justify-content: center; width: fit-content">

                <#list products as product>
                    <div class="card"
                         style="width: 16rem;  margin-top: 10px; margin-right: 10px; padding-bottom: 40px;">
                        <img src="/images/${product.image.id?c}" class="card-img-top" alt="Card image cap">
                        <div class="card-body">
                            <h5 class="card-title">${product.title}</h5>
                            <p class="card-text">${product.description}</p>
                            <p class="card-text font-weight-light">${product.price}$</p>
                            <a href="/product/${product.id?c}" class="btn btn-primary"
                               style="position:absolute; bottom:20px;">more</a>
                        </div>
                    </div>
                <#else>
                    <h3>Products not found :(</h3>
                </#list>
            </div>
        <
    </div>



</@c.page>