<#import "common/page.ftlh" as c>
<#import "common/navbar.ftlh" as nav>

<@c.page>

    <@nav.nav>
    </@nav.nav>

    <div style="width: 96%; margin: auto; padding: 10px;">

        <form action="/cart/clear" method="post" class="form-inline my-2 my-lg-0">
            <input type="hidden" name="_csrf" value="${_csrf.token}">
            <button class="btn btn-danger" type="submit" style="width: fit-content; margin: auto">Clear shopping cart</button>
        </form>
        <div class="list" style="display: flex; flex-wrap: wrap; justify-content: center">
            <#list cartItems as item>
                <div class="card" style="width: 16rem;  margin-top: 10px; margin-right: 10px; padding-bottom: 75px;">
                    <img src="/images/${item.product.image.id?c}" class="card-img-top" alt="Card image cap">
                    <div class="card-body">
                        <h5 class="card-title">${item.product.title}</h5>
                        <p class="card-text font-weight-light">${item.product.price}$</p>
                        <div style="position:absolute; bottom:10px;">
                            <form class="d-flex" action="/cart/set-quantity" method="post" style="width: 100%">
                                <input class="form-control text-center me-3" type="number" value="${item.quantity}"
                                       min="1"
                                       required
                                       name="quantity" style="max-width: 3rem; margin-right: 6px;">
                                <input type="hidden" name="_csrf" value="${_csrf.token}">
                                <input type="hidden" name="productId" value="${item.product.id?c}">
                                <button class="btn btn-outline-green flex-shrink-0" type="submit">
                                    <i class="bi-cart-fill me-1"></i>
                                    Save
                                </button>
                            </form>
                            <form action="/cart/delete/${item.product.id?c}" method="post" id="del${item.id}">
                                <input type="hidden" name="_csrf" value="${_csrf.token}">
                            </form>
                            <div style="padding-top: 15px">
                                <a href="/product/${item.product.id?c}" class="btn btn-primary"
                                   style="margin-right: 2px; bottom:20px;">more</a>
                                <button type="submit" class="btn btn-danger" form="del${item.id}">delete</button>
                            </div>
                        </div>
                    </div>
                </div>
            <#else>
                <h3>Products not found :(</h3>
            </#list>
        </div>
    </div>


</@c.page>


