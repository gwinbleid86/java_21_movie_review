<#import "../layout.ftlh" as main>
<@main.layout>

    <div class="card text-center">
        <div class="card-header">
            ${movie.name}
        </div>
        <div class="card-body">
            <h5 class="card-title">${movie.director.fullName}</h5>
            <ul class="list-group list-group-flush">
                <#list movie.cast as item>
                    <li class="list-group-item">${item.fullName}</li>
                </#list>
            </ul>
            <p class="card-text">${movie.description}</p>
        </div>
        <div class="card-footer text-muted">
            ${movie.year}
        </div>
    </div>

</@main.layout>