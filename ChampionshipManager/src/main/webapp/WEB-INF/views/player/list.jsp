<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<t:genericpage>
    <jsp:attribute name="header">
      <h2>Liste des joueurs</h2>
    </jsp:attribute>
    <jsp:body>
        <p>
			<a href="${pageContext.request.contextPath}/player/openCreate">Créer un nouveau joueur</a>
		</p>
		
		<table class="table table-striped table-bordered">
			<c:forEach items="${players}" var="p">
				<tr>
					<td class="text-center">
						<p>${p.firstName}</p>
					</td>
					<td class="text-center">
						<p>${p.lastName}</p>
					</td>
					<td class="text-center">
						<p>${p.age}</p>
					</td>
					<td class="text-center">
						<p>${p.team.name}</p>
					</td>
					<td class="text-center">
						<form action = "${pageContext.request.contextPath}/player/openUpdate" method = "POST">
					         <input hidden="true" type="text" name="id" value="${p.id}">
					         <input class="btn btn-primary" type = "submit" value = "Modifier" />
						</form>
					</td>
					<td class="text-center">
						<form  action = "${pageContext.request.contextPath}/player/delete" method = "POST" onsubmit="return confirm('Etes-vous sûr(e)?');" >
					         <input hidden="true" type="text" name="id" value="${p.id}">
					         <input class="btn btn-primary" type = "submit" value = "Supprimer" />
						</form>
					</td>

				</tr>
			</c:forEach>
		</table>
    </jsp:body>
</t:genericpage>