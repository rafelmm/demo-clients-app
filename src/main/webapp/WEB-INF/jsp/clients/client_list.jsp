<%@ include file="/WEB-INF/jsp/partials/header.jsp" %>

<h1><spring:message code="client.list.title"/></h1>
<c:if test="${not empty message}">
	<p>${message}</p>
</c:if>
<table border="1">
	<thead>
		<tr>
			<th><spring:message code="client.id"/></th>
			<th><spring:message code="client.name"/></th>
			<th><spring:message code="client.email"/></th>
			<th></th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="client" items="${clients}">
			<tr>
				<td><a href="${pageContext.request.contextPath}${clientsRoute}${updateRoute}/${client.id}">${client.id}</a></td>
				<td>${client.name}</td>
				<td>${client.email}</td>
				<td><a href="${pageContext.request.contextPath}${clientsRoute}${deleteRoute}/${client.id}" onclick="return confirm('<spring:message code="client.delete.confirm"/>');"><spring:message code="button.delete"/></a></td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<a href="${pageContext.request.contextPath}${clientsRoute}${createRoute}" class="btn btn-primary"><spring:message code="button.new"/></a>

<%@ include file="/WEB-INF/jsp/partials/footer.jsp"%>