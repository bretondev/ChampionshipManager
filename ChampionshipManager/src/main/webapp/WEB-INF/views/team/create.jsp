<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<t:genericpage>
    <jsp:attribute name="header">
      <h2>Création d'une équipe</h2>
    </jsp:attribute>
    <jsp:body>
		<form:form method="POST" action="${pageContext.request.contextPath}/team/create"  modelAttribute="team"> 	
			<table>
		        <tr>
		            <td>
		            	<div class="form-group">
			            	<form:label path="name">Nom</form:label>
			            	<form:input class="form-control" path="name"/>
			            	<form:errors path="name" />
		            	</div>
		            </td>
		        </tr>
			</table>
			<input class="btn btn-primary" type="submit" value="Créer" />
		</form:form>
    </jsp:body>
</t:genericpage>
