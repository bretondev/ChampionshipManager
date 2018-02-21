<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<t:genericpage>
    <jsp:attribute name="header">
      <h2>Liste des équipes</h2>
    </jsp:attribute>
    <jsp:body>
        <p>
			<a href="${pageContext.request.contextPath}/team/openCreate">Créer une nouvelle équipe</a>
		</p>
		
		<table class="table table-striped table-bordered">
			<c:forEach items="${teams}" var="t">
				<tr>
					<td class="text-center">
						<p>${t.name}</p>
					</td>
					<td class="text-center">
						<form action = "${pageContext.request.contextPath}/team/openUpdate" method = "POST">
					         <input hidden="true" type="text" name="id" value="${t.id}">
					         <input class="btn btn-primary" type = "submit" value = "Modifier" />
						</form>
					</td>
					<td class="text-center">
						<form  action = "${pageContext.request.contextPath}/team/delete" method = "POST" onsubmit="return confirm('Etes-vous sûr(e)?');" >
					         <input hidden="true" type="text" name="id" value="${t.id}">
					         <input class="btn btn-primary" type = "submit" value = "Supprimer" />
						</form>
					</td>

				</tr>
			</c:forEach>
		</table>
    </jsp:body>
</t:genericpage>