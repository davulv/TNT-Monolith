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
    
    <!-- editBulletinBoard.jsp: generated by stajanov code generator -->
    <f:loadBundle basename="com.autentia.tnt.resources.messages" var="msg" />
    <i:location place="detailBulletinBoard" msg="${msg}"/> 
    
    <f:view>
        <%@include file="/inc/header.jsp" %>
      <h:form id="bulletinBoard" enctype="multipart/form-data">
        
        <%-- Header --%>
        <i:titleBar name="${bulletinBoardBean.title}" msg="${msg}">
          <t:commandLink action="#{bulletinBoardBean.edit}" immediate="true" rendered="#{bulletinBoardBean.editAvailable}">
            <f:param name="rowid" value="#{bulletinBoardBean.id}" />
            <h:graphicImage title="#{msg.entityActions_edit}"  value="/img/edit.gif" styleClass="cmdImg" />
          </t:commandLink>
          <h:commandLink action="#{bulletinBoardBean.delete}" rendered="#{bulletinBoardBean.deleteAvailable}" onclick="if( !confirm('#{msg['question.confirmDelete']}') ) return false;">
            <h:graphicImage title="#{msg.entityActions_delete}"  value="/img/delete.gif" styleClass="titleImg" />
          </h:commandLink>
          <h:commandLink action="#{bulletinBoardBean.list}" immediate="true">
            <h:graphicImage title="#{msg.entityActions_back}"  value="/img/back.gif" styleClass="titleImg" />
          </h:commandLink>
        </i:titleBar>


        <%-- Detail form --%>
        <table class="detailTable" cellpadding="0" cellspacing="0">

            
  
      <%-- Ignored field: id --%>
  
                            
  
      <%-- Field: creationDate --%>
    <tr>
	<td class="detailLabelRW">${msg['bulletinBoard.creationDate']}:</td>
	<td class="detailFieldCell">
         	    	<h:outputText value="#{bulletinBoardBean.creationDate}" converter="autentia.dateConverter" />
 	          </td>
    </tr>
                            
  
      <%-- Field: title --%>
    <tr>
	<td class="detailLabelRW">${msg['bulletinBoard.title']}:</td>
	<td class="detailFieldCell">
         	       <h:outputText value="#{bulletinBoardBean.title}" />
 	      </td>
    </tr>
                            
  
      <%-- Field: message --%>
    <tr>
	<td class="detailLabelRW">${msg['bulletinBoard.message']}:</td>
	<td class="detailFieldCell">
         	       <h:outputText value="#{bulletinBoardBean.message}" />
 	      </td>
    </tr>
                            
  
      <%-- Field: documentPath --%>
    <tr>
	<td class="detailLabelRW">${msg['bulletinBoard.documentPath']}:</td>
	<td class="detailFieldCell">
                  <i:fileLink type="bulletinBoard" objectId="${bulletinBoardBean.id}" file="${bulletinBoardBean.documentPath}" /> 
     	      </td>
    </tr>
                            
  
      <%-- Ignored field: documentContentType --%>
  
                            
                                         
  
      <%-- Field: category --%>
    <tr>
	<td class="detailLabelRW">${msg['bulletinBoard.category']}:</td>
	<td class="detailFieldCell">
         	    	<h:outputText value="#{bulletinBoardBean.category.name}"/>
 	          </td>
    </tr>
                            
  
      <%-- Field: user --%>
    <tr>
	<td class="detailLabelRW">${msg['bulletinBoard.user']}:</td>
	<td class="detailFieldCell">
         	    	<h:outputText value="#{bulletinBoardBean.user.name}"/>
 	          </td>
    </tr>
                                      
        </table>

      </h:form>
    </f:view>
    
  </body>
</html>  		
<%-- bulletinBoard - generated by stajanov (do not edit/delete) --%>
