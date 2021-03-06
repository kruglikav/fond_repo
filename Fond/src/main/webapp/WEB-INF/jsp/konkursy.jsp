<%@ page contentType="text/html" language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://alloy.liferay.com/tld/aui" prefix="aui"%>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<c:set var="ns">
	<portlet:namespace />
</c:set>

<portlet:defineObjects />


<c:if test="${!empty errorMsg}">
        <div class="alert alert-error">
            <button type="button" class="close" data-dismiss="alert">&times;</button>
            <p>${errorMsg}</p>
        </div>
</c:if>
 <table class="table table-bordered table-striped table-hover">
 	<thead>
 		<tr><th>Наименование</th><th>Описание</th><th>Завершение конкурса</th><th>Условия</th><th>Подать заявку</th></tr>
 	</thead>
 	<tbody>
 		<c:forEach var="i" items="${konkursyList}">
 			<tr>
 				<td>${i.nameR}</td>
 				<td>${i.descriptionR}</td>
 				<td>${i.stopKonkurs}</td>
 				<td><a href="<portlet:resourceURL><portlet:param name="konkursId" value="${i.id}" /></portlet:resourceURL>" ><spring:message code="konkursy.downloadUsl"/></a></td>
 				<td><a href="<portlet:renderURL><portlet:param name="view" value="zayavka"/><portlet:param name="konkursId" value="${i.id}"/></portlet:renderURL>">К заявке</a></td>
 			</tr>
 		</c:forEach>
 	</tbody>
 </table>