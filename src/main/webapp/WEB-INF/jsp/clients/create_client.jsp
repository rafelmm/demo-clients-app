<%@ include file="/WEB-INF/jsp/partials/header.jsp"%>


<h1><spring:message code="client.form.title"/></h1>
<form action="${pageContext.request.contextPath}${clientsRoute}${createRoute}" method="POST" accept-charset="UTF-8">
	<input type="hidden" name="id" value="${client.id}"> 
	
	<label for="name"><spring:message code="client.name"/>:</label> 
	<input type="text" id="name" name="name" value="${client.name}" required>
	<br> 
	
	<label for="email"><spring:message code="client.email"/>:</label> 
	<input type="email" id="email" name="email" value="${client.email}" required>
	<br>

	<button type="button" onclick="window.history.back();"><spring:message code="button.back"/></button>
	<button type="submit"><spring:message code="button.save"/></button>
</form>

<%@ include file="/WEB-INF/jsp/partials/footer.jsp"%>
