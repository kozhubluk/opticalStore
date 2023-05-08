<#import "common/page.ftlh" as c>
<#import "common/navbar.ftlh" as nav>

<@c.page>

    <@nav.nav>
    </@nav.nav>

    <div style="width: 96%; margin: auto; padding: 10px;">
        <#if cartItems?has_content>
            <div style="display: flex; justify-content: center; column-gap: 6px; flex-wrap: wrap;">
                <form action="/cart/clear" method="post" class="form-inline my-2 my-lg-0">
                    <input type="hidden" name="_csrf" value="${_csrf.token}">
                    <button class="btn btn-danger" type="submit" style="width: fit-content; margin: auto">Clear shopping
                        cart
                    </button>
                </form>
                <form class="form-inline my-2 my-lg-0">
                    <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModalCenter" style="width: fit-content; margin: auto">Order</button>
                </form>
            </div>


            <!-- Modal -->
            <div class="modal fade" id="exampleModalCenter" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="exampleModalLabel">Create an order</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">
                            <form method="post" action="cart/order">
                                <div class="mb-3">
                                    <label for="email" class="form-label">Email address</label>
                                    <input  type="email" required class="form-control" id="email" name="email" aria-describedby="emailHelp">
                                </div>
                                <div class="mb-3">
                                    <label for="address" class="form-label">Delivery address</label>
                                    <input type="text" required class="form-control" id="address" name="address">
                                </div>
                                <div class="mb-3">
                                    <select  class="form-select" aria-label="Time selection" name="time">
                                        <option value="09:00-12:00">09:00-12:00</option>
                                        <option value="12:00-14:00">12:00-14:00</option>
                                        <option value="14:00-16:00">14:00-16:00</option>
                                        <option value="16:00-18:00">16:00-18:00</option>
                                    </select>
                                </div>
                                <input type="hidden" name="_csrf" value="${_csrf.token}">
                                <button type="submit" class="btn btn-primary">Order</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
            </div>


        </#if>

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
                <h3>
                    Your shopping cart is empty
                </h3>
            </#list>
        </div>
    </div>


</@c.page>


