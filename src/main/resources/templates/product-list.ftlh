<#import "common/admin.ftlh" as c>
<#import "common/navbar.ftlh" as nav>

<@c.admin>
    <table class="table" style="width: 100%;   ">
        <thead class="thead-dark" style="border-radius: 12px">
        <tr>
            <th scope="col">Title</th>
            <th scope="col">ID</th>
            <th scope="col">Category</th>
            <th scope="col">Price</th>
            <th scope="col"></th>
        </tr>
        </thead>
        <tbody>
        <#list products as product>
            <tr>
                <td>${product.title}</td>
                <td>${product.id}</td>
                <td>${product.category}</td>
                <td>${product.price}</td>
                <td><a class="btn btn-primary" role="button" href="/product/list/${product.id?c}">edit</a></td>
            </tr>
        </#list>
        </tbody>
    </table>
</@c.admin>