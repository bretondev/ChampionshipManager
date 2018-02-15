<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<t:genericpage>
test
    <jsp:attribute name="header">
      <h2>Liste des compétitions</h2>
    </jsp:attribute>
    <jsp:body>
        <p>
			<a href="${pageContext.request.contextPath}/championship/openCreate">Créer une nouvelle compétition</a>
		</p>
		
		<table class="table table-striped table-bordered">
			<c:forEach items="${championships}" var="c">
				<tr>
					<td class="text-center">
						<p>${c.name}</p>
					</td>
					<td class="text-center">
						<form action = "${pageContext.request.contextPath}/championship/openUpdate" method = "POST">
					         <input hidden="true" type="text" name="id" value="${c.id}">
					         <input class="btn btn-primary" type = "submit" value = "Modifier" />
						</form>
					</td>
					<td class="text-center">
						<form  action = "${pageContext.request.contextPath}/championship/delete" method = "POST" onsubmit="return confirm('Etes-vous sûr(e)?');" >
					         <input hidden="true" type="text" name="id" value="${c.id}">
					         <input class="btn btn-primary" type = "submit" value = "Supprimer" />
						</form>
					</td>

				</tr>
			</c:forEach>
		</table>
    </jsp:body>
</t:genericpage>