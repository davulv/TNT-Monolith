<%--

    TNTConcept Easy Enterprise Management by Autentia Real Bussiness Solution S.L.
    Copyright (C) 2007 Autentia Real Bussiness Solution S.L.

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.

--%>

<%-- 
 * TNTConcept Easy Enterprise Management by Autentia Real Bussiness Solution S.L.  
 *	Copyright (C) 2007 Autentia Real Bussiness Solution S.L.					   
 *	
 * 	This program is free software; you can redistribute it and/or
 * 	modify it under the terms of the GNU General Public License
 * 	as published by the Free Software Foundation; either version 2
 * 	of the License, or (at your option) any later version.
 *
 * 	This program is distributed in the hope that it will be useful,
 * 	but WITHOUT ANY WARRANTY; without even the implied warranty of
 * 	MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * 	GNU General Public License for more details.
 *
 * 	You should have received a copy of the GNU General Public License
 * 	along with this program; if not, write to the Free Software
 * 	Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 *
 * 	Autentia Real Bussiness Solution S.L.
 * 	Tlf: +34 91 675 33 06, +34 655 99 11 72
 * 	Fax: +34 91 656 65 04
 * 	info@autentia.com																
 *
 --%>
<%@page language="java" contentType="text/html; charset=UTF-8"%>

<%@include file="/inc/tlibs.jsp"%>

<html>
<head>
<%@include file="/inc/uiCore.jsp"%>
</head>
<body>

<!-- positions.jsp: generated by stajanov code generator -->
<f:loadBundle basename="com.autentia.tnt.resources.messages" var="msg" />
<i:location place="positions" msg="${msg}" />

<f:view>
	<%@include file="/inc/header.jsp"%>
	<h:form id="positions">

		<%-- Header --%>
		<i:titleBar msg="${msg}">
			<h:commandLink action="#{positionBean.search}">
				<h:graphicImage rendered="#{ positionBean.search.searchActive}"
					title="#{msg.entityActions_filtered}"
					value="/img/search_applied.gif" styleClass="titleImg" />
				<h:graphicImage rendered="#{!positionBean.search.searchActive}"
					title="#{msg.entityActions_search}" value="/img/search.gif"
					styleClass="titleImg" />
			</h:commandLink>
			<h:commandLink action="#{positionBean.reset}">
				<h:graphicImage rendered="#{positionBean.search.searchActive}"
					title="#{msg.entityActions_reset}" value="/img/eraser.png"
					styleClass="titleImg" />
			</h:commandLink>
			<h:commandLink action="#{positionBean.create}"
				rendered="#{positionBean.createAvailable}">
				<h:graphicImage title="#{msg.entityActions_create}"
					value="/img/new.gif" styleClass="titleImg" />
			</h:commandLink>
		</i:titleBar>


		<%-- List of positions --%>
		<t:dataTable id="list" var="position" value="#{positionBean.all}"
			preserveDataModel="false" cellpadding="0" cellspacing="0"
			styleClass="listTable" headerClass="listHeaderCell"
			footerClass="listFooter" rows="#{settingBean.mySettings.listSize}"
			rowClasses="listRowO,listRowE"
			columnClasses="listCmdCell,listPositionName,listPositionDescription"
			sortColumn="#{positionBean.sortColumn}"
			sortAscending="#{positionBean.sortAscending}"
			rowOnMouseOver="this.savedClassName=this.className;this.className='listRowSel';"
			rowOnMouseOut="this.className=this.savedClassName;">

			<%-- Commands --%>
			<h:column>
				<f:facet name="header">
					<f:verbatim>-</f:verbatim>
				</f:facet>
				<t:commandLink action="#{positionBean.detail}" immediate="true">
					<f:param name="rowid" value="#{position.id}" />
					<h:graphicImage title="#{msg.entityActions_detail}"
						value="/img/detail.gif" styleClass="cmdImg" />
				</t:commandLink>
			</h:column>

			<h:column>
				<f:facet name="header">
					<f:verbatim>-</f:verbatim>
				</f:facet>
				<t:commandLink action="#{positionBean.showHistory}" immediate="true">
					<f:param name="rowid" value="#{position.id}" />
					<h:graphicImage title="#{msg.entityActions_showHistory}"
						value="/img/history.png" styleClass="cmdImg" />	
				</t:commandLink>
			</h:column>

			<%-- Field: name --%>
			<h:column>
				<f:facet name="header">
					<t:commandSortHeader styleClass="listHeader" columnName="name">
						<f:facet name="ascending">
							<t:graphicImage value="/img/ascending-arrow.gif" border="0" />
						</f:facet>
						<f:facet name="descending">
							<t:graphicImage value="/img/descending-arrow.gif" border="0" />
						</f:facet>
						<f:verbatim>${msg['position.name']}</f:verbatim>
					</t:commandSortHeader>
				</f:facet>

				<t:commandLink action="#{positionBean.detail}" immediate="true">
					<f:param name="rowid" value="#{position.id}" />
					<%-- String field --%>
					<h:outputText value="#{position.name}" />
				</t:commandLink>

			</h:column>


			<%-- Field: description --%>
			<h:column>
				<f:facet name="header">
					<t:commandSortHeader styleClass="listHeader"
						columnName="description">
						<f:facet name="ascending">
							<t:graphicImage value="/img/ascending-arrow.gif" border="0" />
						</f:facet>
						<f:facet name="descending">
							<t:graphicImage value="/img/descending-arrow.gif" border="0" />
						</f:facet>
						<f:verbatim>${msg['position.description']}</f:verbatim>
					</t:commandSortHeader>
				</f:facet>

				<t:commandLink action="#{positionBean.detail}" immediate="true">
					<f:param name="rowid" value="#{position.id}" />
					<%-- String field --%>
					<h:outputText value="#{position.description}" />
				</t:commandLink>

			</h:column>


		</t:dataTable>

		<%-- Paginator control --%>
		<%@include file="/inc/paginator.jsp"%>

	</h:form>
</f:view>

</body>
</html>

