<%@ page contentType="text/html" language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://alloy.liferay.com/tld/aui" prefix="aui"%>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page import="com.liferay.portal.kernel.portlet.LiferayWindowState"%>

<portlet:defineObjects />
<c:set var="ns">
	<portlet:namespace />
</c:set>

<portlet:actionURL var="sendForm">
</portlet:actionURL>
<portlet:actionURL var="addIspolnitelAction">
	<portlet:param name="action" value="addIspolnitel" />
	<portlet:param name="konkursId" value="${zayavka.konkursId}" />
</portlet:actionURL>

<portlet:renderURL var="popupPageURL" windowState="<%=LiferayWindowState.POP_UP.toString()%>">
	<portlet:param name="action" value="popup" />
	<portlet:param name="input_id" value="temaName" />
</portlet:renderURL>
<c:set var="strUrl"><%=popupPageURL.toString()%></c:set>

<div id="myTab" class="tabs-left">

	<ul class="nav nav-tabs ">
		<li><a href="#tab-1"><spring:message code="zayavka.competitionLegend" /></a></li>
		<li><a href="#tab-2"><spring:message code="zayavka.orgLegend" /></a></li>
		<li><a href="#tab-3"><spring:message code="zayavka.managerLegend" /></a></li>
		<li><a href="#tab-4"><spring:message code="zayavka.ispolniteli" /></a></li>
	</ul>

	<div class="tab-content">

		<div id="tab-1" class="tab-pane"></div>
		<div id="tab-2" class="tab-pane">
			<form:form modelAttribute='${ns}zayavka' method="POST" action="${sendForm}" name="zayavka">
				<form:hidden path="id" />
				<form:hidden path="konkursId" />
				<form:hidden path="userId" />
				<form:label path="konkursNameR">Название конкурса</form:label>
				<form:input path="konkursNameR" disabled="true" />
				<form:label path="temaName">Тема заявки</form:label>
				<form:input path="temaName" id="temaName" onclick="showPopup('Тема заявки','temaName','${strUrl}')" />
				<form:label path="sectionFondId">Секция фонда</form:label>
				<form:select path="sectionFondId" items="${sectionFondList}" itemValue="id" itemLabel="nameR" class="chosen"></form:select>
				<form:label path="otraslNaukaId">Отрасль науки</form:label>
				<form:select path="otraslNaukaId" items="${otraslNaukaList}" itemLabel="name" itemValue="id" class="chosen"></form:select>
				<form:label path="prioritetNaukaId">Приоритет науки</form:label>
				<form:select path="prioritetNaukaId" items="${prioritetNaukaList}" itemLabel="name" itemValue="id" class="chosen"></form:select>
				<form:label path="orgId">Организация заявитель</form:label>
				<form:select path="orgId" items="${listOrg}" itemValue="id" itemLabel="name" class="chosen"></form:select>
				<%-- <form:label path="ispolniteli">Исполнители</form:label>
				<form:select path="ispolniteli" items="${fizInfoList}" itemValue="id" itemLabel="surname" class="chosen" multiple="true"></form:select> --%>
				<aui:button-row>
					<div align="left">
						<aui:button type="submit" />
					</div>
				</aui:button-row>
			</form:form>
		</div>
		<div id="tab-3" class="tab-pane"></div>
		<div id="tab-4" class="tab-pane">

			<c:if test="${not empty ispolniteliList}">
				<table class="table table-condensed table-bordered" style="width: auto">
					<thead>
						<tr>
							<th><spring:message code="zayavka.surname" /></th>
							<th><spring:message code="zayavka.name" /></th>
							<th><spring:message code="zayavka.patronymic" /></th>
							<th><spring:message code="zayavka.birthdayManager" /></th>
							<th><spring:message code="zayavka.degree" /></th>
							<th><spring:message code="zayavka.academicTitle" /></th>
							<th><spring:message code="zayavka.mestoRaboti" /></th>
							<th><spring:message code="zayavka.postManager" /></th>
							<th></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${ispolniteliList}" var="i">
							<tr>
								<td>${i.surname}</td>
								<td>${i.name}</td>
								<td>${i.patronymic}</td>
								<td>${i.birthday}</td>
								<td>${i.uchStepeniName}</td>
								<td>${i.uchZvaniyName}</td>
								<td>${i.orgName}</td>
								<td>${i.post}</td>
								<td><a
									href="<portlet:actionURL><portlet:param name="action" value="deleteIspolnitel"/><portlet:param name="ispolnitelId" value="${i.id}"/><portlet:param name="konkursId" value="${zayavka.konkursId}" /></portlet:actionURL>">Удалить</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</c:if>


			<form:form action="${addIspolnitelAction}" modelAttribute="${ns}ispolnitelModel" method="POST">
				<form:label path="surname">
					<spring:message code="zayavka.surname" />
				</form:label>
				<form:input path="surname" />
				<form:label path="name">
					<spring:message code="zayavka.name" />
				</form:label>
				<form:input path="name" />
				<form:label path="patronymic">
					<spring:message code="zayavka.patronymic" />
				</form:label>
				<form:input path="patronymic" />
				<form:label path="birthday">
					<spring:message code="zayavka.birthdayManager" />
				</form:label>
				<form:input path="birthday" />
				<form:label path="uchStepeniId">
					<spring:message code="zayavka.degree" />
				</form:label>
				<form:select path="uchStepeniId" items="${uchStepeniList}" itemLabel="fullName" itemValue="id" />
				<form:label path="uchZvaniyId">
					<spring:message code="zayavka.academicTitle" />
				</form:label>
				<form:select path="uchZvaniyId" items="${uchZvaniyList}" itemLabel="fullName" itemValue="id" />
				<form:label path="orgId">
					<spring:message code="zayavka.mestoRaboti" />
				</form:label>
				<form:select path="orgId" items="${listOrg}" itemValue="id" itemLabel="name" class="chosen" />
				<form:label path="post">
					<spring:message code="zayavka.postManager" />
				</form:label>
				<form:input path="post" />
				<aui:button-row>
					<div align="left">
						<aui:button type="submit" value="Добавить" />
					</div>
				</aui:button-row>

			</form:form>

		</div>
	</div>
</div>




<aui:script>
AUI().use(
  'aui-tabview',
  'history',
  function(Y) {
    var tabview = new Y.TabView(
      {
        srcNode: '#myTab'
      }
    ).render();
    
    tabview.selectChild(sessionStorage.getItem('tab') || 0);
    tabview.after('selectionChange', function (e) {
   		sessionStorage.setItem('tab', e.newVal.get('index') || null);
    });
   
  }
);
function showPopup(title,input_id, popup_page_url) {
	AUI().use(
		    'aui-base',
		    'liferay-util-window',
		    'aui-io-plugin-deprecated',
		     function(A) {
		            var saveButton = [
					{
						label: 'Save',
						on: {
							click: function() {
								getDataFromPopup(input_id);
							}
						}
					}
				];
				var closeButton = [
					{
						cssClass: 'close',
						label: '\u00D7',
						render: true,
						on: {
							click: function() {
								<portlet:namespace />textDialog.hide();
							}
						}
					}
				];

		            <portlet:namespace />textDialog = Liferay.Util.Window.getWindow(
		                {
		                	height: '50%',
		                    dialog: {
		                    	align: Liferay.Util.Window.ALIGN_CENTER,
		                        centered: true,
		                        constrain2view: false,
		                        modal: true,
		                        resizable: false,
		                        width:'70%',
		                        
		                        toolbars: {
											footer: saveButton,
											header: closeButton
								}, 
		                    },
		                    title: title
		                }
		                ).plug(A.Plugin.IO, {uri:popup_page_url}).render();
		                
		                
		        }
		    
		); 
}
</aui:script>