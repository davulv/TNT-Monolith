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

<%@include file="/inc/tlibs.jsp" %>

<html>
  <head>
    <%@include file="/inc/uiCore.jsp" %>
  </head>	
  <body>
    
    <!-- searchOffer.jsp: generated by stajanov code generator -->
    <f:loadBundle basename="com.autentia.tnt.resources.messages" var="msg" />
    <i:location place="searchOffer" msg="${msg}"/> 
    
    <f:view>
      <%@include file="/inc/header.jsp" %>
      <h:form id="search" enctype="multipart/form-data">
        
        <%-- Header --%>
        <i:titleBar msg="${msg}">
          <h:commandLink action="#{offerBean.list}">
            <h:graphicImage title="#{msg.entityActions_run}"  value="/img/run.png" styleClass="titleImg" />
          </h:commandLink>
        </i:titleBar>
        
        <%-- Search form --%>
        <table class="searchTable" cellpadding="0" cellspacing="0">

          
      <%-- Ignored field: id --%>

                        
      <%-- Field: number --%>
    <tr>
      <td class="searchLabel">${msg['offer.number']}:</td>
      <td class="searchFieldCell">

        
          
          <h:panelGroup>
            <h:message styleClass="error" showSummary="true" showDetail="false" for="number" />
            <h:selectBooleanCheckbox id="numberValid" value="#{offerBean.searchNumberValid}" 
                                      onclick="setEnabled('search:number',this.checked)"/>
            <h:inputText id="number" value="#{offerBean.searchNumber}" size="70"/>
          </h:panelGroup>
          <script>
            setEnabled( 'search:number', ${offerBean.searchNumberValid} )
          </script>

        
      </td>
    </tr>
                        
      <%-- Field: title --%>
    <tr>
      <td class="searchLabel">${msg['offer.title']}:</td>
      <td class="searchFieldCell">

        
          
          <h:panelGroup>
            <h:message styleClass="error" showSummary="true" showDetail="false" for="title" />
            <h:selectBooleanCheckbox id="titleValid" value="#{offerBean.searchTitleValid}" 
                                      onclick="setEnabled('search:title',this.checked)"/>
            <h:inputText id="title" value="#{offerBean.searchTitle}" size="70"/>
          </h:panelGroup>
          <script>
            setEnabled( 'search:title', ${offerBean.searchTitleValid} )
          </script>

        
      </td>
    </tr>
                        
      <%-- Ignored field: description --%>

                        
      <%-- Ignored field: observations --%>

                        
      <%-- Field: creationDate --%>
    <tr>
      <td class="searchLabel">${msg['offer.creationDate']}:</td>
      <td class="searchFieldCell">

        
                    
          <h:panelGroup>
            <h:message styleClass="error" showSummary="true" showDetail="false" for="startCreationDate" />
            <h:selectBooleanCheckbox id="startCreationDateValid" value="#{offerBean.searchStartCreationDateValid}" 
                                      onclick="setEnabled('search:startCreationDate',this.checked)"/>
            <t:inputCalendar id="startCreationDate" value="#{offerBean.searchStartCreationDate}"
                             renderAsPopup="true" popupDateFormat="d/MM/yyyy" renderPopupButtonAsImage="true" 
                             popupTodayString="#{msg['calendar.today']}" popupWeekString="#{msg['calendar.week']}"
                             onchange="setEnabled('search:startCreationDate',true);setChecked('search:startCreationDateValid',true);"
                             >
				<f:validator validatorId="autentia.dateValidator"/>
			</t:inputCalendar>
          </h:panelGroup>
          -
          <h:panelGroup>
            <h:message styleClass="error" showSummary="true" showDetail="false" for="endCreationDate" />
            <h:selectBooleanCheckbox id="endCreationDateValid" value="#{offerBean.searchEndCreationDateValid}" 
                                      onclick="setEnabled('search:endCreationDate',this.checked)"/>
            <t:inputCalendar id="endCreationDate" value="#{offerBean.searchEndCreationDate}"
                             renderAsPopup="true" popupDateFormat="d/MM/yyyy" renderPopupButtonAsImage="true" 
                             popupTodayString="#{msg['calendar.today']}" popupWeekString="#{msg['calendar.week']}"
                             onchange="setEnabled('search:endCreationDate',true);setChecked('search:endCreationDateValid',true);"
                             >
				<f:validator validatorId="autentia.dateValidator"/>
			</t:inputCalendar>
          </h:panelGroup>
          <script>
            setEnabled( 'search:startCreationDate', ${offerBean.searchStartCreationDateValid} )
            setEnabled( 'search:endCreationDate', ${offerBean.searchEndCreationDateValid} )
          </script>

        
      </td>
    </tr>
                        
      <%-- Field: validityDate --%>
    <tr>
      <td class="searchLabel">${msg['offer.validityDate']}:</td>
      <td class="searchFieldCell">

        
                    
          <h:panelGroup>
            <h:message styleClass="error" showSummary="true" showDetail="false" for="startValidityDate" />
            <h:selectBooleanCheckbox id="startValidityDateValid" value="#{offerBean.searchStartValidityDateValid}" 
                                      onclick="setEnabled('search:startValidityDate',this.checked)"/>
            <t:inputCalendar id="startValidityDate" value="#{offerBean.searchStartValidityDate}"
                             renderAsPopup="true" popupDateFormat="d/MM/yyyy" renderPopupButtonAsImage="true" 
                             popupTodayString="#{msg['calendar.today']}" popupWeekString="#{msg['calendar.week']}"
                             onchange="setEnabled('search:startValidityDate',true);setChecked('search:startValidityDateValid',true);"
                             >
				<f:validator validatorId="autentia.dateValidator"/>
			</t:inputCalendar>
          </h:panelGroup>
          -
          <h:panelGroup>
            <h:message styleClass="error" showSummary="true" showDetail="false" for="endValidityDate" />
            <h:selectBooleanCheckbox id="endValidityDateValid" value="#{offerBean.searchEndValidityDateValid}" 
                                      onclick="setEnabled('search:endValidityDate',this.checked)"/>
            <t:inputCalendar id="endValidityDate" value="#{offerBean.searchEndValidityDate}"
                             renderAsPopup="true" popupDateFormat="d/MM/yyyy" renderPopupButtonAsImage="true" 
                             popupTodayString="#{msg['calendar.today']}" popupWeekString="#{msg['calendar.week']}"
                             onchange="setEnabled('search:endValidityDate',true);setChecked('search:endValidityDateValid',true);"
                             >
				<f:validator validatorId="autentia.dateValidator"/>
			</t:inputCalendar>
          </h:panelGroup>
          <script>
            setEnabled( 'search:startValidityDate', ${offerBean.searchStartValidityDateValid} )
            setEnabled( 'search:endValidityDate', ${offerBean.searchEndValidityDateValid} )
          </script>

        
      </td>
    </tr>
                        
      <%-- Field: maturityDate --%>
    <tr>
      <td class="searchLabel">${msg['offer.maturityDate']}:</td>
      <td class="searchFieldCell">

        
                    
          <h:panelGroup>
            <h:message styleClass="error" showSummary="true" showDetail="false" for="startMaturityDate" />
            <h:selectBooleanCheckbox id="startMaturityDateValid" value="#{offerBean.searchStartMaturityDateValid}" 
                                      onclick="setEnabled('search:startMaturityDate',this.checked)"/>
            <t:inputCalendar id="startMaturityDate" value="#{offerBean.searchStartMaturityDate}"
                             renderAsPopup="true" popupDateFormat="d/MM/yyyy" renderPopupButtonAsImage="true" 
                             popupTodayString="#{msg['calendar.today']}" popupWeekString="#{msg['calendar.week']}"
                             onchange="setEnabled('search:startMaturityDate',true);setChecked('search:startMaturityDateValid',true);"
                             >
				<f:validator validatorId="autentia.dateValidator"/>
			</t:inputCalendar>
          </h:panelGroup>
          -
          <h:panelGroup>
            <h:message styleClass="error" showSummary="true" showDetail="false" for="endMaturityDate" />
            <h:selectBooleanCheckbox id="endMaturityDateValid" value="#{offerBean.searchEndMaturityDateValid}" 
                                      onclick="setEnabled('search:endMaturityDate',this.checked)"/>
            <t:inputCalendar id="endMaturityDate" value="#{offerBean.searchEndMaturityDate}"
                             renderAsPopup="true" popupDateFormat="d/MM/yyyy" renderPopupButtonAsImage="true" 
                             popupTodayString="#{msg['calendar.today']}" popupWeekString="#{msg['calendar.week']}"
                             onchange="setEnabled('search:endMaturityDate',true);setChecked('search:endMaturityDateValid',true);"
                             >
				<f:validator validatorId="autentia.dateValidator"/>
			</t:inputCalendar>
          </h:panelGroup>
          <script>
            setEnabled( 'search:startMaturityDate', ${offerBean.searchStartMaturityDateValid} )
            setEnabled( 'search:endMaturityDate', ${offerBean.searchEndMaturityDateValid} )
          </script>

        
      </td>
    </tr>
                        
      <%-- Field: offerState --%>
    <tr>
      <td class="searchLabel">${msg['offer.offerState']}:</td>
      <td class="searchFieldCell">

        
          
          <h:panelGroup>
            <h:message styleClass="error" showSummary="true" showDetail="false" for="offerState" />
            <h:selectBooleanCheckbox id="offerStateValid" value="#{offerBean.searchOfferStateValid}" 
                                      onclick="setEnabled('search:offerState',this.checked)"/>
            <h:selectOneMenu id="offerState" value="#{offerBean.searchOfferState}" onclick="setEnabled('search:offerState',true)">
              <f:converter converterId="autentia.EnumConverter"/>
              <f:selectItems value="#{offerBean.offerStates}" />
            </h:selectOneMenu>
          </h:panelGroup>
          <script>
            setEnabled( 'search:offerState', ${offerBean.searchOfferStateValid} )
          </script>

        
      </td>
    </tr>
                        
      <%-- Field: offerPotential --%>
    <tr>
      <td class="searchLabel">${msg['offer.offerPotential']}:</td>
      <td class="searchFieldCell">

        
          
          <h:panelGroup>
            <h:message styleClass="error" showSummary="true" showDetail="false" for="offerPotential" />
            <h:selectBooleanCheckbox id="offerPotentialValid" value="#{offerBean.searchOfferPotentialValid}" 
                                      onclick="setEnabled('search:offerPotential',this.checked)"/>
            <h:selectOneMenu id="offerPotential" value="#{offerBean.searchOfferPotential}" onclick="setEnabled('search:offerPotential',true)">
              <f:converter converterId="autentia.EnumConverter"/>
              <f:selectItems value="#{offerBean.offerPotentials}" />
            </h:selectOneMenu>
          </h:panelGroup>
          <script>
            setEnabled( 'search:offerPotential', ${offerBean.searchOfferPotentialValid} )
          </script>

        
      </td>
    </tr>
                        
      <%-- Ignored field: totalRoles --%>

                        
      <%-- Ignored field: totalCosts --%>

                        
      <%-- Ignored field: total --%>

                        
      <%-- Ignored field: ownerId --%>

                        
      <%-- Ignored field: departmentId --%>

                        
      <%-- Ignored field: insertDate --%>

                        
      <%-- Ignored field: updateDate --%>

                                  
      <%-- Field: user --%>
    <tr>
      <td class="searchLabel">${msg['offer.user']}:</td>
      <td class="searchFieldCell">

        
          
          <h:panelGroup>
            <h:message styleClass="error" showSummary="true" showDetail="false" for="user" />
            <h:selectBooleanCheckbox id="userValid" value="#{offerBean.searchUserValid}" 
                                      onclick="setEnabled('search:user',this.checked)"/>
            <h:selectOneMenu id="user" value="#{offerBean.searchUser}" onclick="setEnabled('search:user',true)">
              <f:selectItems value="#{offerBean.users}" />
              <f:converter converterId="autentia.EntityConverter"/>
            </h:selectOneMenu>
          </h:panelGroup>
          <script>
            setEnabled( 'search:user', ${offerBean.searchUserValid} )
          </script>

        
      </td>
    </tr>
                        
      <%-- Field: organization --%>
    <tr>
      <td class="searchLabel">${msg['offer.organization']}:</td>
      <td class="searchFieldCell">

        
          
          <h:panelGroup>
            <h:message styleClass="error" showSummary="true" showDetail="false" for="organization" />
            <h:selectBooleanCheckbox id="organizationValid" value="#{offerBean.searchOrganizationValid}" 
                                      onclick="setEnabled('search:organization',this.checked)"/>
            <h:selectOneMenu id="organization" value="#{offerBean.searchOrganization}" onclick="setEnabled('search:organization',true)">
              <f:selectItems value="#{offerBean.organizations}" />
              <f:converter converterId="autentia.EntityConverter"/>
            </h:selectOneMenu>
          </h:panelGroup>
          <script>
            setEnabled( 'search:organization', ${offerBean.searchOrganizationValid} )
          </script>

        
      </td>
    </tr>
                        
      <%-- Ignored field: contact --%>

                        
      <%-- Field: offerRejectReason --%>
    <tr>
      <td class="searchLabel">${msg['offer.offerRejectReason']}:</td>
      <td class="searchFieldCell">

        
          
          <h:panelGroup>
            <h:message styleClass="error" showSummary="true" showDetail="false" for="offerRejectReason" />
            <h:selectBooleanCheckbox id="offerRejectReasonValid" value="#{offerBean.searchOfferRejectReasonValid}" 
                                      onclick="setEnabled('search:offerRejectReason',this.checked)"/>
            <h:selectOneMenu id="offerRejectReason" value="#{offerBean.searchOfferRejectReason}" onclick="setEnabled('search:offerRejectReason',true)">
              <f:selectItems value="#{offerBean.offerRejectReasons}" />
              <f:converter converterId="autentia.EntityConverter"/>
            </h:selectOneMenu>
          </h:panelGroup>
          <script>
            setEnabled( 'search:offerRejectReason', ${offerBean.searchOfferRejectReasonValid} )
          </script>

        
      </td>
    </tr>
                                            
      <%-- Ignored field: interactions --%>

                        
      <%-- Ignored field: roles --%>

                        
      <%-- Ignored field: costs --%>

            
        </table>
        
      </h:form>
    </f:view>
    
  </body>
</html>  		
