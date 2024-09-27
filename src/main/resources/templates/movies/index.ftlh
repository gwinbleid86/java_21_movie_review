<#import "../layout.ftlh" as main>
<@main.layout>
    <div class="row d-flex justify-content-between">
        <#list movies.content as movie>
            <div class="card col-6 m-2" style="width: 18rem;">
                <img src="/static/images/No_Image_Available.jpg" class="card-img-top" alt="No image available">
                <div class="card-body">
                    <h5 class="card-title">${movie.name}</h5>
                    <p class="card-text">${movie.year}</p>
                    <a href="/${movie.id}" class="btn btn-primary">More Info</a>
                </div>
            </div>
        </#list>
    </div>
    <nav aria-label="Page navigation example">
        <ul class="pagination">
            <li class="page-item"><a class="page-link" href="#">Previous</a></li>
            <#list 0..movies.totalPages as page>
                <li class="page-item"><a class="page-link" href="/?page=${page}">${page}</a></li>
            </#list>
            <li class="page-item"><a class="page-link" href="#">Next</a></li>
        </ul>
    </nav>
</@main.layout>