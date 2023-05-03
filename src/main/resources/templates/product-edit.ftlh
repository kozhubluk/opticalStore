<#import "common/admin.ftlh" as c>
<#import "common/navbar.ftlh" as nav>

<@c.admin>
    <#assign categories = ['Sunglasses', 'Reading Glasses', 'Computer glasses', 'Lenses', 'Office glasses', 'Accessories']>
    <h1 style="text-align: center; margin-top: 15px">Item ID:${product.id?c}</h1>
    <div style="margin: 20px; max-width: 1200px; margin: auto; padding: 20px; margin-top: 30px;
-webkit-box-shadow: 1px 0px 14px -4px rgba(70, 81, 90, 0.11);
-moz-box-shadow: 1px 0px 14px -4px rgba(70, 81, 90, 0.11);
box-shadow: 1px 0px 14px -4px rgba(70, 81, 90, 0.11); border-radius: 15px">
        <form action="/product/edit" method="post" id="change">
            <div class="form-group">
                <label for="title">Title</label>
                <input type="text" class="form-control" id="title" name="title" value="${product.title}" required>
            </div>
            <div class="form-group">
                <label for="description">Description</label>
                <textarea name="description" class="form-control" id="description" maxlength="340" rows="3"
                          required>${product.description}</textarea>
            </div>
            <div class="form-group">
                <label for="price">Price</label>
                <input name="price" type="number" min=1 class="form-control" id="price" value="${product.price?c}"
                       required>
            </div>
            <div class="form-group">
                <label for="category">Category</label>
                <select class="form-control" id="category" name="category">
                    <#list categories as category>
                        <option value="${category}" <#if (category == product.category)> selected="selected"</#if>>${category}</option>
                    </#list>
                </select>
            </div>
            <input type="hidden" name="_csrf" value="${_csrf.token}">
            <input type="hidden" name="productId" value="${product.id?c}">


        </form>
        <button type="submit" class="btn btn-primary" form="change">Save</button>
        <input class="btn btn-danger" type="submit" value="delete" form="del">
        <form action="/product/delete/${product.id?c}" id="del" method="post">
            <input type="hidden" name="_csrf" value="${_csrf.token}">

        </form>
    </div>
</@c.admin>