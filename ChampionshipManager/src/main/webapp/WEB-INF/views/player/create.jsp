<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<t:genericpage>
    <jsp:attribute name="header">
      <h2>Création d'un joueur</h2>
    </jsp:attribute>
    <jsp:body>
		<form:form method="POST" action="${pageContext.request.contextPath}/player/create"  modelAttribute="player"> 	
			<table>
		        <tr>
		            <td>
		            	<div class="form-group">
			            	<form:label path="firstName">Prénom</form:label>
			            	<form:input class="form-control" path="firstName"/>
			            	<form:errors path="firstName" />
		            	</div>
		            </td>
		        </tr>
		        <tr>
		            <td>
		            	<div class="form-group">
			            	<form:label path="lastName">Nom</form:label>
			            	<form:input class="form-control" path="lastName"/>
			            	<form:errors path="lastName" />
		            	</div>
		            </td>
		        </tr>
		        <tr>
		            <td>
		            	<div class="form-group">
			            	<form:label path="age">Age</form:label>
			            	<form:input class="form-control" path="age"/>
			            	<form:errors path="age" />
		            	</div>
		            </td>
		        </tr>
			</table>
			<input class="btn btn-primary" type="submit" value="Créer" />
		</form:form>
    </jsp:body>
</t:genericpage>
