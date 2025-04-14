<%@ include file="/WEB-INF/jsp/partials/header.jsp" %>

<h1><spring:message code="product.list.title"/></h1>
<ul>
	<c:forEach var="product" items="${products}">
		<h4>${product.name}</h4>
		<p>${product.description}</p>
		<p><spring:message code="product.price"/>:${product.price}</p>
	</c:forEach>
</ul>
<%@ include file="/WEB-INF/jsp/partials/footer.jsp"%>