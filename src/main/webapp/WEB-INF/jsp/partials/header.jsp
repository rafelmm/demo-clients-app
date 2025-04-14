<%@ include file="/WEB-INF/jsp/partials/tags.jspf" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title><spring:message code="app.title"/></title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
</head>
<body>
<%@ include file="/WEB-INF/jsp/partials/routes.jspf" %>
<div style="display: flex; justify-content: space-between; align-items: center; padding: 10px; background-color: #f2f2f2;">
    <div>
        <h2><spring:message code="app.title" /></h2>
        <div>
        	<a href="${pageContext.request.contextPath}${productsRoute}${listRoute}"><spring:message code="menu.products"/></a>
        	<a href="${pageContext.request.contextPath}${clientsRoute}${listRoute}"><spring:message code="menu.clients"/></a>
        </div>
    </div>
    <div>
        <a href="?lang=es">Español</a> | 
        <a href="?lang=en">English</a>
    </div>
    <c:if test="${pageContext.request.userPrincipal != null}">
    <form action="${pageContext.request.contextPath}/logout" method="post" style="display:inline;">
        <sec:csrfInput />
        <input type="submit" value="Cerrar sesión"/>
    </form>
	</c:if>
</div>
<hr>