<#import "spring.ftl" as spring>
<#macro layout>

    <!doctype html>
    <html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport"
              content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
              crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
                crossorigin="anonymous"></script>
        <script src="../static/scripts/main.js"></script>
        <title>Document</title>
    </head>
    <body>

    <style>
        .navbar {
            position: sticky;
            top: 0;
        }
    </style>
    <#assign known = SPRING_SECURITY_CONTEXT?? >
    <#if known>
        <#assign
        user = SPRING_SECURITY_CONTEXT.authentication.principal
        name = user.getUsername()
        >
    </#if>


    <nav class="navbar navbar-expand-lg navbar-dark bg-dark mb-2">
        <div class="container-fluid">
            <a class="navbar-brand" href="/">Movie Review</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                    data-bs-target="#navbarSupportedContent"
                    aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                    <li class="nav-item">
                        <a class="nav-link active" aria-current="page" href="/"><@spring.message "layout.home"/></a>
                    </li>

                    <#if user?? && user.authorities?seq_contains("ADMIN")>
                        <li class="nav-item">
                            <a class="nav-link" href="/create"><@spring.message "layout.create_new_movie"/></a>
                        </li>
                    </#if>

                    <li class="nav-item">
                        <a class="nav-link disabled" href="#" tabindex="-1"
                           aria-disabled="true"><@spring.message "layout.disabled"/></a>
                    </li>
                    <li>
                        <div class="dropdown">
                            <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton1"
                                    data-bs-toggle="dropdown" aria-expanded="false">
                                <@spring.message "layout.lang"/>
                            </button>
                            <ul class="dropdown-menu z-3" aria-labelledby="dropdownMenuButton1">
                                <li><a class="dropdown-item" href="?lang=en"><@spring.message "layout.locale_en"/></a>
                                </li>
                                <li><a class="dropdown-item" href="?lang=ru"><@spring.message "layout.locale_ru"/></a>
                                </li>
                                <li><a class="dropdown-item" href="?lang=ky"><@spring.message "layout.locale_ky"/></a>
                                </li>
                            </ul>
                        </div>
                    </li>
                </ul>
                <#--                <form class="d-flex">-->
                <#--                    <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">-->
                <#--                    <button class="btn btn-outline-success" type="submit">Search</button>-->
                <#--                </form>-->
            </div>

            <#--            <#assign known = SPRING_SECURITY_CONTEXT?? >-->
            <#--            <#if known>-->
            <#--                <#assign-->
            <#--                user = SPRING_SECURITY_CONTEXT.authentication.principal-->
            <#--                name = user.getUsername()-->
            <#--                >-->
            <#if user??>
                <a href="/profile/${name}" class="text-light m-2 m-sm-0">
                    <@spring.message "layout.hello"/>, ${name}:
                    <#list user.authorities as auth>
                        <span class="text-danger">${auth}</span>
                        <#if auth?has_next>
                            /
                        </#if>
                    </#list>
                </a>
                <#if _csrf??>
                    <form action="/logout" method="post">
                        <input type="hidden" name="${(_csrf.parameterName)!'csrf'}"
                               value="${(_csrf.token)!'--no-token--'}"/>
                        <button type="submit" class="btn btn-outline-info m-2"
                                id="logout"><@spring.message "layout.logout"/></button>
                    </form>
                <#else>
                    <a href="/logout" class="btn btn-outline-info m-2"
                       id="logout"><@spring.message "layout.logout"/></a>
                </#if>
            <#else>
                <a href="/auth/login"
                   class="btn btn-outline-info m-2"><@spring.message "layout.login"/></a>
                <a href="/auth/register"
                   class="btn btn-outline-info m-2"><@spring.message "layout.register"/></a>
            </#if>
        </div>
    </nav>

    <div class="container">

        <#nested spring>

    </div>

    </body>
    </html>

</#macro>