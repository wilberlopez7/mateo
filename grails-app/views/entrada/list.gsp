
<%@ page import="inventario.Entrada" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'entrada.label', default: 'Entrada')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-entrada" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-entrada" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="folio" title="${message(code: 'entrada.folio.label', default: 'Folio')}" />
					
						<g:sortableColumn property="factura" title="${message(code: 'entrada.factura.label', default: 'Factura')}" />
					
						<g:sortableColumn property="fechaFactura" title="${message(code: 'entrada.fechaFactura.label', default: 'Fecha Factura')}" />
					
						<g:sortableColumn property="iva" title="${message(code: 'entrada.iva.label', default: 'Iva')}" />
					
						<g:sortableColumn property="total" title="${message(code: 'entrada.total.label', default: 'Total')}" />
					
						<g:sortableColumn property="estatus" title="${message(code: 'entrada.estatus.label', default: 'Estatus')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${entradaInstanceList}" status="i" var="entradaInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${entradaInstance.id}">${fieldValue(bean: entradaInstance, field: "folio")}</g:link></td>
					
						<td>${fieldValue(bean: entradaInstance, field: "factura")}</td>
					
						<td><g:formatDate date="${entradaInstance.fechaFactura}" /></td>
					
						<td>${fieldValue(bean: entradaInstance, field: "iva")}</td>
					
						<td>${fieldValue(bean: entradaInstance, field: "total")}</td>
					
						<td>${fieldValue(bean: entradaInstance, field: "estatus")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${entradaInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
