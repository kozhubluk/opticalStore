<#import "common/page.ftlh" as c>
<#import "common/navbar.ftlh" as nav>

<@c.page>
    <@nav.nav>
    </@nav.nav>
    <section class="py-5">
        <div class="container px-4 px-lg-5 my-5">
            <div class="row gx-4 gx-lg-5 align-items-center">
                <div class="col-md-6"><img class="card-img-top mb-5 mb-md-0" src="/images/${product.image.id}"  alt="..." /></div>
                <div class="col-md-6">
                    <h1 class="display-5 fw-bolder">${product.title}</h1>
                    <div class="fs-5 mb-5">
                        <span>${product.price}$</span>
                    </div>
                    <p class="lead">${product.description}</p>

                        <form class="d-flex" action="/cart/add/" method="post">
                            <input class="form-control text-center me-3" type="number" min="1" required name="quantity" style="max-width: 3rem">
                            <input type="hidden"  value="${product.id?c}" name="productId" >
                            <input type="hidden" name="_csrf" value="${_csrf.token}">
                            <button class="btn btn-outline-green flex-shrink-0" type="submit">
                                <i class="bi-cart-fill me-1"></i>
                                Add to cart
                            </button>
                        </form>
                </div>
            </div>
        </div>
    </section>

</@c.page>