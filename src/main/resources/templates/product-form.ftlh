<#import "common/admin.ftlh" as c>
<#import "common/navbar.ftlh" as nav>
<#assign categories = ['Sunglasses', 'Reading Glasses', 'Computer glasses', 'Lenses', 'Office glasses', 'Accessories']>
<@c.admin>


    <div style="margin: 20px; max-width: 1200px; margin: auto; padding: 20px; margin-top: 30px;
-webkit-box-shadow: 1px 0px 14px -4px rgba(70, 81, 90, 0.11);
-moz-box-shadow: 1px 0px 14px -4px rgba(70, 81, 90, 0.11);
box-shadow: 1px 0px 14px -4px rgba(70, 81, 90, 0.11); border-radius: 15px">
        <form action="/product/create" method="post" id="change" enctype="multipart/form-data">
            <div class="form-group">
                <label for="title">Title</label>
                <input type="text" class="form-control" id="title" name="title" placeholder="title" required>
            </div>
            <div class="form-group">
                <label for="description">Description</label>
                <textarea name="description" class="form-control" id="description" maxlength="340" rows="3"
                          required placeholder="description"></textarea>
            </div>
            <div class="form-group">
                <label for="price">Price</label>
                <input name="price" type="number" min=1 class="form-control" id="price" placeholder="price"
                       required>
            </div>
            <div class="form-group">
                <label for="category">Category</label>
                <select class="form-control" id="category" name="category">
                    <#list categories as category>
                        <option value="${category}">${category}</option>
                    </#list>
                </select>
            </div>
            <div class="form-group">
                <label for="file">Picture</label>
                <input type="file" name="file"/><br><br>
            </div>


            <input type="hidden" name="_csrf" value="${_csrf.token}">
        </form>
        <button type="submit" class="btn btn-primary" form="change">Save</button>
    </div>
</@c.admin>