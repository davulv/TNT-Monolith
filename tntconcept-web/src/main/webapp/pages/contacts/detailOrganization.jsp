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

<%@page language="java" contentType="text/html; charset=UTF-8"%>

<%@include file="/inc/tlibs.jsp" %>

<html>
  <head>
    <%@include file="/inc/uiCore.jsp" %>
  </head>	
  <body>
    
    <!-- editOrganization.jsp: generated by stajanov code generator -->
    <f:loadBundle basename="com.autentia.tnt.resources.messages" var="msg" />
    <i:location place="detailOrganization" msg="${msg}"/> 
    
    <f:view>
      <%@include file="/inc/header.jsp" %>
      <h:form id="organization" enctype="multipart/form-data">
        
        <%-- Header --%>
        <i:titleBar name="${organizationBean.name}" msg="${msg}">
          <t:commandLink action="#{organizationBean.edit}" immediate="true" rendered="#{organizationBean.editAvailable}">
            <f:param name="rowid" value="#{organizationBean.id}" />
            <h:graphicImage title="#{msg.entityActions_edit}"  value="/img/edit.gif" styleClass="cmdImg" />
          </t:commandLink>
          <h:commandLink action="#{organizationBean.delete}" rendered="#{organizationBean.deleteAvailable}" onclick="if( !confirm('#{msg['question.confirmDelete']}') ) return false;">
            <h:graphicImage title="#{msg.entityActions_delete}"  value="/img/delete.gif" styleClass="titleImg" />
          </h:commandLink>
          <h:commandLink action="#{organizationBean.list}" immediate="true">
            <h:graphicImage title="#{msg.entityActions_back}"  value="/img/back.gif" styleClass="titleImg" />
          </h:commandLink>
        </i:titleBar>


        <%-- Detail form --%>
        <table class="detailTable" cellpadding="0" cellspacing="0">

            
    
  
  
      <%-- Ignored field: id --%>
  
                            
    
  
  
      <%-- Field: name --%>
    <tr>
	<td class="detailLabelRW">${msg['organization.name']}:</td>
	<td class="detailFieldCell">
         	       <h:outputText value="#{organizationBean.name}" />
 	      </td>
    </tr>
                            
    
  
  
      <%-- Field: cif --%>
    <tr>
	<td class="detailLabelRW">${msg['organization.cif']}:</td>
	<td class="detailFieldCell">
         	       <h:outputText value="#{organizationBean.cif}" />
 	      </td>
    </tr>
                            
    
  
  
      <%-- Field: street --%>
    <tr>
	<td class="detailLabelRW">${msg['organization.street']}:</td>
	<td class="detailFieldCell">
         	       <h:outputText value="#{organizationBean.street}" />
 	      </td>
    </tr>
                            
    
  
  
      <%-- Field: number --%>
    <tr>
	<td class="detailLabelRW">${msg['organization.number']}:</td>
	<td class="detailFieldCell">
         	       <h:outputText value="#{organizationBean.number}" />
 	      </td>
    </tr>
                            
    
  
  
      <%-- Field: locator --%>
    <tr>
	<td class="detailLabelRW">${msg['organization.locator']}:</td>
	<td class="detailFieldCell">
         	       <h:outputText value="#{organizationBean.locator}" />
 	      </td>
    </tr>
                            
    
  
  
      <%-- Field: postalCode --%>
    <tr>
	<td class="detailLabelRW">${msg['organization.postalCode']}:</td>
	<td class="detailFieldCell">
         	       <h:outputText value="#{organizationBean.postalCode}" />
 	      </td>
    </tr>
                            
    
  
  
      <%-- Field: city --%>
    <tr>
	<td class="detailLabelRW">${msg['organization.city']}:</td>
	<td class="detailFieldCell">
         	       <h:outputText value="#{organizationBean.city}" />
 	      </td>
    </tr>
                            
    
  
  
      <%-- Field: state --%>
    <tr>
	<td class="detailLabelRW">${msg['organization.state']}:</td>
	<td class="detailFieldCell">
         	       <h:outputText value="#{organizationBean.state}" />
 	      </td>
    </tr>
                            
    
  
  
      <%-- Field: country --%>
    <tr>
	<td class="detailLabelRW">${msg['organization.country']}:</td>
	<td class="detailFieldCell">
         	       <h:outputText value="#{organizationBean.country}" />
 	      </td>
    </tr>
                            
    
  
  
      <%-- Field: phone --%>
    <tr>
	<td class="detailLabelRW">${msg['organization.phone']}:</td>
	<td class="detailFieldCell">
         	       <h:outputText value="#{organizationBean.phone}" />
 	      </td>
    </tr>
                            
    
  
  
      <%-- Field: fax --%>
    <tr>
	<td class="detailLabelRW">${msg['organization.fax']}:</td>
	<td class="detailFieldCell">
         	       <h:outputText value="#{organizationBean.fax}" />
 	      </td>
    </tr>
                            
    
  
  
      <%-- Field: email --%>
    <tr>
	<td class="detailLabelRW">${msg['organization.email']}:</td>
	<td class="detailFieldCell">
         	       <h:outputText value="#{organizationBean.email}" />
 	      </td>
    </tr>
                            
    
  
  
      <%-- Field: website --%>
    <tr>
	<td class="detailLabelRW">${msg['organization.website']}:</td>
	<td class="detailFieldCell">
         	       <h:outputText value="#{organizationBean.website}" />
 	      </td>
    </tr>
                            
    
  
  
      <%-- Field: ftpsite --%>
    <tr>
	<td class="detailLabelRW">${msg['organization.ftpsite']}:</td>
	<td class="detailFieldCell">
         	       <h:outputText value="#{organizationBean.ftpsite}" />
 	      </td>
    </tr>
                            
    
  
  
      <%-- Field: notes --%>
    <tr>
	<td class="detailLabelRW">${msg['organization.notes']}:</td>
	<td class="detailFieldCell">
         	       <h:outputText value="#{organizationBean.notes}" />
 	      </td>
    </tr>
                            
    
  
  
      <%-- Ignored field: ownerId --%>
  
                            
    
  
  
      <%-- Ignored field: departmentId --%>
  
                            
    
  
  
      <%-- Ignored field: insertDate --%>
  
                            
    
  
  
      <%-- Ignored field: updateDate --%>
  
                                        
    
  
  
      <%-- Field: type --%>
    <tr>
	<td class="detailLabelRW">${msg['organization.type']}:</td>
	<td class="detailFieldCell">
         	    	<h:outputText value="#{organizationBean.type.name}"/>
 	          </td>
    </tr>
                            
    
  
  
      <%-- Field: category --%>
    <tr>
	<td class="detailLabelRW">${msg['organization.category']}:</td>
	<td class="detailFieldCell">
         	    	<h:outputText value="#{organizationBean.category.name}"/>
 	          </td>
    </tr>
                            
    
  
  
      <%-- Field: province --%>
    <tr>
	<td class="detailLabelRW">${msg['organization.province']}:</td>
	<td class="detailFieldCell">
         	    	<h:outputText value="#{organizationBean.province.name}"/>
 	          </td>
    </tr>
                                      
        </table>

      </h:form>
    </f:view>
    
  </body>
</html>  		

<%-- organization - generated by stajanov (do not edit/delete) --%>
