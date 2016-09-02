/**
 * TNTConcept Easy Enterprise Management by Autentia Real Bussiness Solution S.L.
 * Copyright (C) 2007 Autentia Real Bussiness Solution S.L.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.autentia.tnt.bean.activity;



import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIData;
import javax.faces.component.UIInput;
import javax.faces.component.html.HtmlInputHidden;
import javax.faces.component.html.HtmlInputText;
import javax.faces.component.html.HtmlSelectBooleanCheckbox;
import javax.faces.component.html.HtmlSelectOneListbox;
import javax.faces.component.html.HtmlSelectOneRadio;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import org.acegisecurity.acls.domain.BasePermission;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.myfaces.custom.fileupload.UploadedFile;
import org.apache.myfaces.custom.schedule.ScheduleMouseEvent;
import org.apache.myfaces.custom.schedule.model.ScheduleEntry;
import org.apache.myfaces.custom.schedule.model.ScheduleModel;
import org.apache.myfaces.custom.schedule.model.SimpleScheduleModel;
import org.apache.myfaces.custom.schedule.renderer.ScheduleEntryRenderer;
import org.hibernate.ObjectNotFoundException;
import org.springframework.util.CollectionUtils;

import com.autentia.tnt.bean.BaseBean;
import com.autentia.tnt.bean.NavigationResults;
import com.autentia.tnt.bean.admin.SettingBean;
import com.autentia.tnt.businessobject.Activity;
import com.autentia.tnt.businessobject.ActivityFile;
import com.autentia.tnt.businessobject.Document;
import com.autentia.tnt.businessobject.DocumentCategory;
import com.autentia.tnt.businessobject.DocumentVersion;
import com.autentia.tnt.businessobject.ExternalActivity;
import com.autentia.tnt.businessobject.Holiday;
import com.autentia.tnt.businessobject.HolidayState;
import com.autentia.tnt.businessobject.Organization;
import com.autentia.tnt.businessobject.Project;
import com.autentia.tnt.businessobject.ProjectRole;
import com.autentia.tnt.businessobject.RequestHoliday;
import com.autentia.tnt.businessobject.Setting;
import com.autentia.tnt.businessobject.User;
import com.autentia.tnt.dao.SortCriteria;
import com.autentia.tnt.dao.hibernate.ProjectRoleDAO;
import com.autentia.tnt.dao.hibernate.UserDAO;
import com.autentia.tnt.dao.search.ActivitySearch;
import com.autentia.tnt.dao.search.DocumentSearch;
import com.autentia.tnt.dao.search.ExternalActivitySearch;
import com.autentia.tnt.dao.search.HolidaySearch;
import com.autentia.tnt.dao.search.OrganizationSearch;
import com.autentia.tnt.dao.search.ProjectRoleSearch;
import com.autentia.tnt.dao.search.RequestHolidaySearch;
import com.autentia.tnt.jsf.schedule.renderer.BitacoreScheduleEntryRenderer;
import com.autentia.tnt.manager.activity.ActivityFileManager;
import com.autentia.tnt.manager.activity.ActivityManager;
import com.autentia.tnt.manager.activity.ExternalActivityManager;
import com.autentia.tnt.manager.admin.ProjectManager;
import com.autentia.tnt.manager.admin.ProjectRoleManager;
import com.autentia.tnt.manager.admin.SettingManager;
import com.autentia.tnt.manager.contacts.OrganizationManager;
import com.autentia.tnt.manager.document.DocumentCategoryManager;
import com.autentia.tnt.manager.document.DocumentManager;
import com.autentia.tnt.manager.holiday.HolidayManager;
import com.autentia.tnt.manager.holiday.RequestHolidayManager;
import com.autentia.tnt.manager.security.AuthenticationManager;
import com.autentia.tnt.manager.security.Permission;
import com.autentia.tnt.upload.Uploader;
import com.autentia.tnt.upload.UploaderFactory;
import com.autentia.tnt.util.DateUtils;
import com.autentia.tnt.util.FacesUtils;
import com.autentia.tnt.util.FileUtil;
import com.autentia.tnt.util.SettingPath;
import com.autentia.tnt.util.SpringUtils;

/**
 * UI bean for Activity objects.
 * 
 * @author stajanov code generator
 */
public class ActivityBean extends BaseBean {
	
	private boolean defaultBillable = false;

	 

	/**
	 * inner comparator class. comparares activities to order a list for start and duration
	 * 
	 * @author german
	 *
	 */
	private class compareActivitiesByStartAndDuration implements Comparator<Activity> {

		Calendar calInst1 = Calendar.getInstance();
		Calendar calInst2 = Calendar.getInstance();
		
		public int compare(Activity a1, Activity a2) {

			calInst1.setTime(a1.getStartDate());
			calInst2.setTime(a2.getStartDate());
			
			calInst1.add(Calendar.MINUTE, a1.getDuration());
			calInst2.add(Calendar.MINUTE, a2.getDuration());
			
			return (int)(calInst2.getTimeInMillis() - calInst1.getTimeInMillis());
		}
		
	}
	
	/**
	 * inner comparator class. comparares externalActivities to order a list for start and duration
	 * 
	 * @author daniel
	 *
	 */
	private class compareExternalActivitiesActivitiesByStartAndDuration implements Comparator<ExternalActivity> {

		Calendar calInst1 = Calendar.getInstance();
		Calendar calInst2 = Calendar.getInstance();
		
		public int compare(ExternalActivity a1, ExternalActivity a2) {

			calInst1.setTime(a1.getEndDate());
			calInst2.setTime(a2.getEndDate());
			
			return (int)(calInst2.getTimeInMillis() - calInst1.getTimeInMillis());
		}
		
	}
	
	/**
	 * inner comparator class. comparares two SelectItem objects  to order a SelectItem list 
	 *   in order to use collections.sort in getOrganizations
	 * 
	 *
	 */
	public class OperacionesComparator implements Comparator{
	
	
		public int compare(Object arg0,Object arg1) {
			try {
				SelectItem p = (SelectItem) arg0;
				SelectItem q = (SelectItem) arg1;
				return p.getLabel().compareTo(q.getLabel());
				
			} catch (Exception ex){
				return -1;
			}
		}
						
		
	}
	
		
	/** Manager */
	private static ActivityManager				manager					= ActivityManager
																				.getDefault();
	private static ExternalActivityManager		externalActivityManager	= ExternalActivityManager
																				.getDefault();
	
	private static ActivityFileManager			activityFileManager		= ActivityFileManager
																			.getDefault();
	
	/** Serial version field */
	private static final long					serialVersionUID		= 1L;

	/** Selected date in bitacore window */
	private Date								selectedDate			= new Date();

	/** Project DAO * */
	private static final ProjectManager			projectManager			= ProjectManager
																				.getDefault();

	/** Organization DAO * */
	private static final OrganizationManager	organizationManager		= OrganizationManager
																				.getDefault();
	
	/** Default DocumentCategory manager */
	private static final DocumentCategoryManager	dcManager			= DocumentCategoryManager
																				.getDefault();

	/** Active search object */
	private OrganizationSearch					organizationSearch		= new OrganizationSearch();

	/** Selected organization * */
	private Organization						selectedOrganization	= null;

	private List<Organization>					organizations			= new ArrayList<Organization>();

	/** List of projects */
	private List<Project>						projects				= new ArrayList<Project>();

	private static AuthenticationManager		authManager				= AuthenticationManager
																				.getDefault();

	private static HolidayManager				holidayManager			= HolidayManager
																				.getDefault();

	/** Settings manager */
	private static final SettingManager			settings				= SettingManager
																				.getDefault();

	/** Role manager */
	private static final ProjectRoleManager		projectRoleMgr			= ProjectRoleManager
																				.getDefault();

	/** Selected project */
	private Project								selectedProject;

	/** data model */
	private SimpleScheduleModel					scheduleModel			= new SimpleScheduleModel();

	/** to reload model */
	private boolean								reloadModel				= true;

	private String								method					= null;

	private final ScheduleEntryRenderer			entryRenderer			= new BitacoreScheduleEntryRenderer();

	

	private final Calendar						cal						= Calendar.getInstance();
	
	private static final int					NO_EDIT_SELECTED		= -1;
	private static final int					EDIT_ACTIVITY			= 0;
	private static final int					EDIT_EXTERNAL_ACTIVITY	= 1;
	
	private int 								tabsRendered			= NO_EDIT_SELECTED;
	
	/** Uploaded file object */
	private UploadedFile uploadFile;
	
	/** Active search object */
	private DocumentSearch			docSearch				= new DocumentSearch();

	/** Manager */
	private static DocumentManager	docManager				= DocumentManager.getDefault();
	
	
	private List<Document> documents;
	
	private BitacoreTabChangeListener listener = new BitacoreTabChangeListener();

	private int selectedTab;
	/**
	 * Constructor
	 */
	public ActivityBean() {

		// Only show entries for current user
		search.setUser(authManager.getCurrentPrincipal().getUser());

	}

	/**
	 * Get date selected in bitacore page
	 * 
	 * @return date selected in bitacore page
	 */
	public Date getSelectedDate() {
		return selectedDate;
	}

	/**
	 * Set date selected in bitacore page
	 * 
	 * @param date
	 *            date selected in bitacore page
	 */
	public void setSelectedDate(Date date) {
		if (date != null) {

			Calendar newCal = Calendar.getInstance();

			cal.setTime(selectedDate);
			newCal.setTime(date);

			if (cal.get(Calendar.MONTH) != newCal.get(Calendar.MONTH)
					|| cal.get(Calendar.YEAR) != newCal.get(Calendar.YEAR)) {
				reloadModel = true;
			}

			selectedDate = date;
			scheduleModel.setSelectedDate(selectedDate);
			scheduleModel.refresh();
		}
	}

	/**
	 * Get the list of all projects
	 * 
	 * @return the list of all projects
	 */
	public List<SelectItem> getProjects() {
		ArrayList<SelectItem> ret = new ArrayList<SelectItem>();
		List<Project> refs = projectManager.getAllEntities(null, new SortCriteria("name"));
		for (Project ref : refs) {
			ret.add(new SelectItem(ref, ref.getClient().getName() + " - " + ref.getName()));
		}
		return ret;
	}

	public Project getSelectedProject() {
		if (selectedProject == null) {
			if (projects.size() != 0) {
				selectedProject = projects.get(0);
			}
		}
		return selectedProject;
	}

	public List<SelectItem> getRolesBySelectedProject() {
		ArrayList<SelectItem>	ret 		 = new ArrayList<SelectItem>();
		ProjectRoleSearch		prjRolSearch = new ProjectRoleSearch();
		prjRolSearch.setProject(getSelectedProject());
		
		List<ProjectRole> refs = ProjectRoleManager.getDefault().getAllEntities(prjRolSearch, new SortCriteria("name", false));
		if (refs != null){
			for (ProjectRole ref : refs) {
				ret.add(new SelectItem(ref, ref.getName()));
			}

			// Sólo preseleccionamos el primer ProjectRole del proyecto cuando haya cambiado el 
			// proyecto, por que sino borrariamos y creariamos una situación inconsistente
			if (this.getRole() != null && this.getRole().getProject() != null && ! this.getRole().getProject().equals(this.getSelectedProject())){
				HtmlSelectOneRadio optRole = (HtmlSelectOneRadio) FacesUtils.getComponent("activity").findComponent("tabActivity").findComponent("role");
				this.setRole(refs.get(0));
				optRole.setValue(this.getRole());
			}
		}
		
		return ret;
	}

	public void onEditSelected(ActionEvent event) {
		// setSelectedProject( null );
	}

	/**
	 * Set the selectedOrganization value when the combo value changes
	 * 
	 * @param event
	 */
	public void onSelectedOrganizationChanged(ValueChangeEvent event) {
		setSelectedOrganization((Organization) event.getNewValue());

		// Project
		List<Project> projectsByOrganization = ProjectManager.getDefault().getOpenProjectsByOrganization(selectedOrganization);
		if (projectsByOrganization != null && projectsByOrganization.size() != 0) {
			this.selectedProject = (Project) projectsByOrganization.get(0);
			FacesContext.getCurrentInstance().getViewRoot().getAttributes();
		} else {
			this.selectedProject = null;
		}

		HtmlSelectOneListbox projects = (HtmlSelectOneListbox) FacesUtils.getComponent("activity").findComponent("tabActivity")
				.findComponent("projects");
		projects.setValue(selectedProject);
		
		if (selectedProject!=null) {
			HtmlSelectBooleanCheckbox billHtml = (HtmlSelectBooleanCheckbox)FacesUtils.getComponent("activity").findComponent("tabActivity").findComponent("billable");
			billHtml.setValue(selectedProject.getBillable());			
			setBillable(selectedProject.getBillable());
			
			
			HtmlInputHidden hiddenHtml = (HtmlInputHidden)FacesUtils.getComponent("activity").findComponent("tabActivity").findComponent("defaultBillable");
			hiddenHtml.setValue(selectedProject.getBillable());
			
			setDefaultBillable(selectedProject.getBillable());
		}
		
		
		FacesUtils.renderResponse();
	}

	/**
	 * Set the projectOrganization value when the combo value changes
	 * 
	 * @param event
	 */
	public void onSelectedProjectChanged(ValueChangeEvent event) {
		setSelectedProject((Project) event.getNewValue());
		
		if(selectedProject!=null) {	
			HtmlSelectBooleanCheckbox billHtml = (HtmlSelectBooleanCheckbox)FacesUtils.getComponent("activity").findComponent("tabActivity").findComponent("billable");
				billHtml.setValue(selectedProject.getBillable());			
				setBillable(selectedProject.getBillable());			
				
			
			HtmlInputHidden hiddenHtml = (HtmlInputHidden)FacesUtils.getComponent("activity").findComponent("tabActivity").findComponent("defaultBillable");
				hiddenHtml.setValue(selectedProject.getBillable());
				setDefaultBillable(selectedProject.getBillable());
		}
		
		FacesUtils.renderResponse();
	}

	/**
	 * Get the list of all projects
	 * 
	 * @return the list of all projects
	 */
	public List<SelectItem> getProjectsVisiblesBySelectedOrganization() {
		Organization  companySelected = this.getSelectedOrganization();
		List<Project> openProjects 	  = ProjectManager.getDefault().getOpenProjectsByOrganization(companySelected);
		ArrayList<SelectItem> ret  = new ArrayList<SelectItem>();

		if(selectedProject !=null && selectedProject.isFinished()){
			ret.add(new SelectItem(selectedProject, selectedProject.getName() + " (*)"));
		}

		if (openProjects == null || openProjects.size() == 0) {
			projects.clear();
			return ret;
		}

		Project proj = null;
		for (Object ref : openProjects) {
			proj = (Project) ref;
			projects.add(proj);
			if (!proj.isFinished() && !proj.getRoles().isEmpty()) {
				ret.add(new SelectItem(proj, proj.getName()));
			}
		}

		return ret;
	}

	/**
	 * @param selectedOrganization
	 *            the selectedOrganization to set
	 */
	public void setSelectedOrganization(Organization selectedOrganization) {
		// Organization
		this.selectedOrganization = selectedOrganization;

	}

	public void setSelectedProject(Project project) {
		// Project
		this.selectedProject = project;
 	}

	/**
	 * Get the selectedOrganization value
	 * 
	 * @return a Organization selected
	 */
	public Organization getSelectedOrganization() {
		if (selectedOrganization == null) {
			organizations =  organizationManager.getAllEntities(null, new SortCriteria("name"));
			if (! CollectionUtils.isEmpty(organizations)){
				selectedOrganization = organizations.get(0);
			}
		}

		return selectedOrganization;
	}

	/**
	 * @return Organizaciones con proyectos abiertos que ademas tengan ROLES creados
	 */
	public List<SelectItem> getOrganizations() {
		ArrayList<SelectItem> 		  ret	    = new ArrayList<SelectItem>(32);		
		List<Project>		  		  projects  = projectManager.getDefault().getOpenProjects(new SortCriteria("name"));
		HashMap<Integer, Organization> companies = new HashMap<Integer, Organization>(32);
		
		if (! CollectionUtils.isEmpty(projects)){
			for (Project project: projects){
				if ( (! project.isFinished()) && (project.getOpen())){
					Set <ProjectRole> roles = project.getRoles();
					if (! CollectionUtils.isEmpty(roles)){
						Organization company = project.getClient(); 
						if (! companies.containsKey(company.getId())){
							companies.put(company.getId(), company);
							ret.add(new SelectItem(company, company.getName()));						
						}
					}
				}
			}
		}
		
		Collections.sort(ret, new OperacionesComparator());
		
		return ret;
	}	
	
	
	
	@Override
	public String doAfterSave(String result) {

		/* If user is editing an Activity... */
		if (activity != null) {
			Setting val = settings.get(SettingPath.BITACORE_LAST_BILLABLE, true);
			SettingManager.setValue(val, activity.isBillable());
			settings.save(val);
	
			val = settings.get(SettingPath.BITACORE_LAST_ROLEID, true);
			SettingManager.setValue(val, activity.getRole().getId());
			settings.save(val);
		}

		return super.doAfterSave(result);
	}
	
	
	public String doBeforeSaveExternalActivity() {
		String result = super.doBeforeSave();
		// if the user not has a category
		if (externalActivity.getDocumentCategory() == null || externalActivity.getDocumentCategory().getId() == null) {

			DocumentCategory padre = dcManager.getDocumentCategoryParent();
			// User category
			DocumentCategory extActDocCategory = new DocumentCategory();
			extActDocCategory.setName(externalActivity.getName());
			extActDocCategory.setDescription(externalActivity.getName());
			extActDocCategory.setPadre(padre);
			externalActivity.setDocumentCategory(extActDocCategory);
		} else {
			// Updates the category name
			externalActivity.getDocumentCategory().setName(externalActivity.getName());
			externalActivity.getDocumentCategory().setDescription(externalActivity.getName());
		}
		return result;
	}

	/* activity - generated by stajanov (do not edit/delete) */

	/** Logger */
	private static final Log			log					= LogFactory.getLog(ActivityBean.class);

	// External entities' DAOs

	/** User DAO * */
	private static final UserDAO		userDAO				= new UserDAO();

	/** ProjectRole DAO * */
	private static final ProjectRoleDAO	roleDAO				= new ProjectRoleDAO();

	/** Upload service */
	private static final Uploader		uploader			= UploaderFactory
																	.getInstance("externalActivity");

	/** Active Activity object */
	private Activity					activity;
	
	/** Active Activity object */
	private ExternalActivity			externalActivity;

	/** Active search object */
	private ActivitySearch				search				= new ActivitySearch();

	/** Default sort column */
	private String						sortColumn			= "startDate";

	/** Default sort order */
	private boolean						sortAscending		= false;

	private float						monthPerformedHours	= 0;
	private static final String 		EXT_ACT_DOC_VERSION = "1.0";

	/**
	 * List activitys. Order depends on Faces parameter sort.
	 * 
	 * @return the list of all activitys sorted by requested criterion
	 */
	public List<Activity> getAll() {
		
		return manager.getAllEntities(search, new SortCriteria(sortColumn, sortAscending));
		// return activityDAO.search(search, new SortCriteria(sortColumn,
		// sortAscending));
	}

	// Getters to list possible values of related entities

	/**
	 * Get the list of all users
	 * 
	 * @return the list of all users
	 */
	public List<SelectItem> getUsers() {
		ArrayList<SelectItem> ret = new ArrayList<SelectItem>();
		List<User> refs = userDAO.search(new SortCriteria("name"));
		for (User ref : refs) {
			ret.add(new SelectItem(ref, ref.getName()));
		}
		return ret;
	}

	/**
	 * Get the list of all roles
	 * 
	 * @return the list of all roles
	 */
	public List<SelectItem> getRoles() {
		ArrayList<SelectItem> ret = new ArrayList<SelectItem>();
		List<ProjectRole> refs = roleDAO.search(new SortCriteria("name"));
		for (ProjectRole ref : refs) {
			ret.add(new SelectItem(ref, ref.getName()));
		}
		return ret;
	}

	// Getters to list possible values of enum fields

	// Methods to create/remove instances of one-to-many entities (slave
	// entities)

	/**
	 * Whether or not create button is available for user
	 * 
	 * @return true if user can create objects of type Account
	 */
	public boolean isCreateAvailable() {
		return SpringUtils.isRolePermissionGranted(Permission.Entity_Create(Activity.class));
	}

	/**
	 * Whether or not edit button is available for user
	 * 
	 * @return true if user can edit current object
	 */
	public boolean isEditAvailable() {
		return SpringUtils.isAclPermissionGranted(activity, BasePermission.WRITE);
	}

	/**
	 * Whether or not delete button is available for user
	 * 
	 * @return true if user can delete current object
	 */
	public boolean isDeleteAvailable() {
		return (activity.getId() != null)
				&& SpringUtils.isAclPermissionGranted(activity, BasePermission.DELETE);
	}
	
	/**
	 * Whether or not delete button is available for user
	 * 
	 * @return true if user can delete current object
	 */
	public boolean isDeleteExternalActivityAvailable() {
		return (externalActivity.getId() != null)
				&& SpringUtils.isAclPermissionGranted(externalActivity, BasePermission.DELETE);
	}

	/**
	 * Go to create page
	 * 
	 * @return forward to CREATE page
	 */
	public String create() {
		activity = new Activity();
		externalActivity = new ExternalActivity();
		selectedOrganization = null;
		selectedProject = null;
		organizations.clear();
		projects.clear();
		
		cal.setTime(scheduleModel.getSelectedDate());

		if (scheduleModel.getMode() == ScheduleModel.MONTH || scheduleModel.getMode() == ScheduleModel.WEEK) {			
			cal.set(Calendar.HOUR_OF_DAY, ((SettingBean)FacesUtils.getBean("settingBean")).getMySettings().getWorkingDayHourStarts());
		}
		
		activity.setStartDate(cal.getTime());
		activity.setDescription(FacesUtils.getMessage("activity.description"));
		activity.setUser(authManager.getCurrentPrincipal().getUser());
		
		
		externalActivity.setStartDate(cal.getTime());
		externalActivity.setEndDate(cal.getTime()); //it is going to end in the same day
		externalActivity.setComments(FacesUtils.getMessage("offer.observations"));
		externalActivity.setOwnerId(authManager.getCurrentPrincipal().getUser().getId());
		externalActivity.setDepartmentId(authManager.getCurrentPrincipal().getUser().getDepartmentId());
		

		DocumentCategory padre = dcManager.getDocumentCategoryParent();
		// User category
		DocumentCategory extActDocCategory = new DocumentCategory();
		extActDocCategory.setName(externalActivity.getName());
		extActDocCategory.setDescription(externalActivity.getName());
		extActDocCategory.setPadre(padre);
		externalActivity.setDocumentCategory(extActDocCategory);
		
		// Preselect last selected options
		Setting val = settings.get(SettingPath.BITACORE_LAST_ROLEID, false);
		int projectRoleId = SettingManager.getInt(val, -1);
		
		Project pr = null;
		
		if (projectRoleId != -1) {
			ProjectRole projectRole;
			try{
				projectRole = projectRoleMgr.getEntityById(projectRoleId);
				if(!projectRole.getProject().isFinished()) {
					setRole(projectRole);
					setSelectedProject(projectRole.getProject());
					setSelectedOrganization(getSelectedProject().getClient());
					pr=projectRole.getProject();
				}
			}catch(ObjectNotFoundException onfex){
				// Caso especial. Si no se  localiza el anterior rol, continuamos con la ejecucion.
			}
		}
		
		if(pr!=null) {
			setBillable(pr.getBillable());
			setDefaultBillable(pr.getBillable());
		} else {
			val = settings.get(SettingPath.BITACORE_LAST_BILLABLE, false);
			setBillable(SettingManager.getBoolean(val, false));
		}

		if (scheduleModel.getMode() == ScheduleModel.MONTH || scheduleModel.getMode() == ScheduleModel.WEEK) {

			ActivitySearch searchToday = new ActivitySearch();
			ExternalActivitySearch extActivitySearchToday = new ExternalActivitySearch();

			Calendar initToday = Calendar.getInstance();
			initToday.setTime(scheduleModel.getSelectedDate());
			Calendar lastToday = Calendar.getInstance();
			lastToday.setTime(scheduleModel.getSelectedDate());

			initToday.set(Calendar.HOUR_OF_DAY, cal.getMinimum(Calendar.HOUR_OF_DAY));
			initToday.set(Calendar.MINUTE, cal.getMinimum(Calendar.MINUTE));
			initToday.set(Calendar.SECOND, cal.getMinimum(Calendar.SECOND));
			initToday.set(Calendar.MILLISECOND, cal.getMinimum(Calendar.MILLISECOND));

			lastToday.set(Calendar.HOUR_OF_DAY, cal.getMaximum(Calendar.HOUR_OF_DAY));
			lastToday.set(Calendar.MINUTE, cal.getMaximum(Calendar.MINUTE));
			lastToday.set(Calendar.SECOND, cal.getMaximum(Calendar.SECOND));
			lastToday.set(Calendar.MILLISECOND, cal.getMaximum(Calendar.MILLISECOND));

			searchToday.setStartStartDate(initToday.getTime());
			searchToday.setEndStartDate(lastToday.getTime());			
			
			extActivitySearchToday.setStartStartDate(initToday.getTime());
			extActivitySearchToday.setEndStartDate(lastToday.getTime());			
			
			// Try to obtain the last hour in day.
			
			searchToday.setUser(authManager.getCurrentPrincipal().getUser());
			extActivitySearchToday.setOwner(authManager.getCurrentPrincipal().getUser());

			List<Activity> activities = manager.getAllEntities(searchToday, new SortCriteria(
					sortColumn, sortAscending));
			
			List<ExternalActivity> extActivities = externalActivityManager.getAllEntities(extActivitySearchToday, 
																					  new SortCriteria(sortColumn, sortAscending));
			
			if (activities.size() > 0 && extActivities.size() <= 0) {
				//No externalActivities
				//sort is descendent
				Collections.sort(activities, new compareActivitiesByStartAndDuration());
				activity.setStartDate(activities.get(0).getEndDate());
				externalActivity.setStartDate(activities.get(0).getEndDate());
				externalActivity.setEndDate(activities.get(0).getEndDate());
			} else if (activities.size() <= 0 && extActivities.size() > 0) {
				//No activities
				Collections.sort(extActivities, new compareExternalActivitiesActivitiesByStartAndDuration());
				activity.setStartDate(extActivities.get(0).getEndDate());
				externalActivity.setStartDate(extActivities.get(0).getEndDate());
				externalActivity.setEndDate(extActivities.get(0).getEndDate());
			} else if (activities.size() > 0 && extActivities.size() > 0) {
				
				Collections.sort(activities, new compareActivitiesByStartAndDuration());
				Collections.sort(extActivities, new compareExternalActivitiesActivitiesByStartAndDuration());
				
				Date lastActivityEndDate = activities.get(0).getEndDate();
				Date lastExtActivityEndDate = extActivities.get(0).getEndDate();
				
				Date startDate = (lastActivityEndDate.compareTo(lastExtActivityEndDate) > 0) ? lastActivityEndDate : lastExtActivityEndDate;
				activity.setStartDate(startDate);
				externalActivity.setStartDate(startDate);
				externalActivity.setEndDate(startDate);
			}

		}

		tabsRendered = NO_EDIT_SELECTED;
		
		return NavigationResults.CREATE;
	}
	
	public String createDocument() {
		
		Document doc = new Document();
		
		HashSet<DocumentCategory> categories = new HashSet<DocumentCategory>();
		categories.add(externalActivity.getDocumentCategory());
		doc.setCategories(categories);
		
		doc.setOwnerId(externalActivity.getOwnerId());
		doc.setDepartmentId(externalActivity.getDepartmentId());
		doc.setCreationDate(new Date());
		
//		docVersion.setCreationDate(new Date());
//		docVersion.setOwnerId(externalActivity.getOwnerId());
//		docVersion.setDepartmentId(externalActivity.getDepartmentId());
//		docVersion.setDocument(doc);
//		docVersion.setVersion(EXT_ACT_DOC_VERSION);
//		
//		if (doc.getVersions() == null) {
//			doc.setVersions(new HashSet<DocumentVersion>());
//		}
//		
//		doc.getVersions().add(docVersion);
		documents.add(doc);
		
		
		return null;
	}

	/**
	 * Go to edit page
	 * 
	 * @return forward to EDIT page
	 */
	public String edit() {

		ScheduleEntry entry = scheduleModel.getSelectedEntry();
		
		String tmpId = entry.getId();
		String[] tmpIdParts = tmpId.split("_");
				
		Integer id = Integer.parseInt(tmpIdParts[tmpIdParts.length-1]);
		// activity = activityDAO.getById(id);
		
		if (entry instanceof ActivityScheduleEntry) {
			activity = manager.getEntityById(id);
	
			selectedProject = activity.getRole().getProject();
			selectedOrganization = selectedProject.getClient();
					
			setDefaultBillable(selectedProject.getBillable());
			
			tabsRendered = EDIT_ACTIVITY;
		} else if (entry instanceof ExternalActivityScheduleEntry) {
			externalActivity = externalActivityManager.getEntityById(id);
			ArrayList<DocumentCategory> categories = new ArrayList<DocumentCategory>();
			categories.add(externalActivity.getDocumentCategory());
			
			docSearch = new DocumentSearch();
			docSearch.setCategories(categories);
			documents = docManager.getAllEntities(docSearch, null);
			
			tabsRendered = EDIT_EXTERNAL_ACTIVITY;
		} else {
			tabsRendered = NO_EDIT_SELECTED;
			return NavigationResults.LIST;
		}
		
		return NavigationResults.EDIT;
	}

	
	
	/**
	 * Save bean and stay on it
	 * 
	 * @return forward to list page
	 */
	public String save() {
	
		doBeforeSave();

		if (activity.getId() == null) {
			manager.insertEntity(activity);
		} else {
			manager.updateEntity(activity);
		}

		// Calls an after save action
		String result = doAfterSave(NavigationResults.LIST);

		// Unselect object
		activity = null;
		scheduleModel.setSelectedEntry(null);
		reloadModel = true;
		return result;
	}

	/**
	 * Delete bean and go back to beans list
	 * 
	 * @return forward to LIST page
	 */
	public String delete() {
		// activityDAO.delete(activity);

		Activity act = manager.getEntityById(activity.getId());
		manager.deleteEntity(act);
		// manager.deleteEntity(activity);
		act = null;
		activity = null;
		scheduleModel.setSelectedEntry(null);
		reloadModel = true;
		scheduleModel.refresh();

		return NavigationResults.LIST;
	}

	public String saveExternalActivity () {
		
		doBeforeSaveExternalActivity();
		
		try {
			
			String fileName=null;
			
			if (uploadFile != null) {
				fileName = FileUtil.getFileName(uploadFile.getName());
				final ActivityFile file = new ActivityFile();
				file.setInsertDate(new Date());
				file.setUser(authManager.getCurrentPrincipal().getUser());
				file.setExternalActivity(externalActivity);
				file.setFile(fileName);
				file.setFileMime(uploadFile.getContentType());
				externalActivity.getFiles().add(file);
			}
			
			if (externalActivity.getId() == null) {
				externalActivityManager.insertEntity(externalActivity);
			} else {
				externalActivityManager.updateEntity(externalActivity);
			}
			
			if (uploadFile != null) {
				
				
				
				if (uploader.exists(Integer.toString(externalActivity.getId()), fileName)) {
					// el fichero ya existe y lo versionamos
					uploader.version(Integer.toString(externalActivity.getId()), fileName, uploadFile);
				} else {
					uploader.store(Integer.toString(externalActivity.getId()), uploadFile);
				}
				
				
				
			}
			
			// Calls an after save action
			String result = super.doAfterSave(NavigationResults.LIST);

			return result;
			
		} catch (IOException e) {
			log.error("save - exception uploading field file", e);
			FacesUtils.addErrorMessage("file", "error.fileTransfer", e
					.getMessage());
			return null;
		}	
		
	}
	
	
	public String deleteExternalActivity() {
		// activityDAO.delete(activity);

		ExternalActivity act = externalActivityManager.getEntityById(externalActivity.getId());
		externalActivityManager.deleteEntity(act);
		// manager.deleteEntity(activity);
		act = null;
		externalActivity = null;
		scheduleModel.setSelectedEntry(null);
		reloadModel = true;
		scheduleModel.refresh();

		return NavigationResults.LIST;
	}
	
	/**
	 * Go back to beans list
	 * 
	 * @return forward to LIST page
	 */
	public String list() {
		activity = null;

		scheduleModel.setSelectedEntry(null);
		reloadModel = true;
		scheduleModel.refresh();

		return NavigationResults.LIST;
	}

	/**
	 * Go to search page
	 * 
	 * @return forward to SEARCH page
	 */
	public String search() {
		return NavigationResults.SEARCH;
	}

	/**
	 * Check if we have an active object.
	 * 
	 * @return true is an object is selected
	 */
	public boolean isActivitySelected() {
		return activity != null;
	}
	
	/**
	 * Check if we have an external activity active object.
	 * 
	 * @return true is an object is selected
	 */
	public boolean isExternalActivitySelected() {
		return externalActivity != null;
	}

	// Getters and setters to manipulate sorting
	public boolean isSortAscending() {
		return sortAscending;
	}

	public void setSortAscending(boolean sortAscending) {
		this.sortAscending = sortAscending;
	}

	public String getSortColumn() {
		return sortColumn;
	}

	public void setSortColumn(String sortColumn) {
		this.sortColumn = sortColumn;
	}

	// Getters and setters to handle search
	public ActivitySearch getSearch() {
		return search;
	}

	public Date getSearchStartStartDate() {
		return search.getStartStartDate();
	}

	public void setSearchStartStartDate(Date val) {
		if (val != null) {
			search.setStartStartDate(val);
		} else {
			search.unsetStartStartDate();
		}
	}

	public boolean isSearchStartStartDateValid() {
		return search.isStartStartDateSet();
	}

	public void setSearchStartStartDateValid(boolean val) {
		if (val) {
			search.setStartStartDate(search.getStartStartDate());
		} else {
			search.unsetStartStartDate();
		}
	}

	public Date getSearchEndStartDate() {
		return search.getEndStartDate();
	}

	public void setSearchEndStartDate(Date val) {
		if (val != null) {
			search.setEndStartDate(val);
		} else {
			search.unsetEndStartDate();
		}
	}

	public boolean isSearchEndStartDateValid() {
		return search.isEndStartDateSet();
	}

	public void setSearchEndStartDateValid(boolean val) {
		if (val) {
			search.setEndStartDate(search.getEndStartDate());
		} else {
			search.unsetEndStartDate();
		}
	}

	public Integer getSearchDuration() {
		return search.getDuration();
	}

	public void setSearchDuration(Integer val) {
		if (search.isDurationSet()) {
			search.setDuration(val);
		}
	}

	public boolean isSearchDurationValid() {
		return search.isDurationSet();
	}

	public void setSearchDurationValid(boolean val) {
		if (val) {
			search.setDuration(search.getDuration());
		} else {
			search.unsetDuration();
		}
	}

	public String getSearchDescription() {
		return search.getDescription();
	}

	public void setSearchDescription(String val) {
		if (search.isDescriptionSet()) {
			search.setDescription(val);
		}
	}

	public boolean isSearchDescriptionValid() {
		return search.isDescriptionSet();
	}

	public void setSearchDescriptionValid(boolean val) {
		if (val) {
			search.setDescription(search.getDescription());
		} else {
			search.unsetDescription();
		}
	}

	public Boolean getSearchBillable() {
		return search.getBillable();
	}

	public void setSearchBillable(Boolean val) {
		if (search.isBillableSet()) {
			search.setBillable(val);
		}
	}

	public boolean isSearchBillableValid() {
		return search.isBillableSet();
	}

	public void setSearchBillableValid(boolean val) {
		if (val) {
			search.setBillable(search.getBillable());
		} else {
			search.unsetBillable();
		}
	}

	public User getSearchUser() {
		return search.getUser();
	}

	public void setSearchUser(User val) {
		if (search.isUserSet()) {
			search.setUser(val);
		}
	}

	public boolean isSearchUserValid() {
		return search.isUserSet();
	}

	public void setSearchUserValid(boolean val) {
		if (val) {
			search.setUser(search.getUser());
		} else {
			search.unsetUser();
		}
	}

	public ProjectRole getSearchRole() {
		return search.getRole();
	}

	public void setSearchRole(ProjectRole val) {
		if (search.isRoleSet()) {
			search.setRole(val);
		}
	}

	public boolean isSearchRoleValid() {
		return search.isRoleSet();
	}

	public void setSearchRoleValid(boolean val) {
		if (val) {
			search.setRole(search.getRole());
		} else {
			search.unsetRole();
		}
	}

	// Getters and setters to handle uploads

	// Getters and setters to manipulate active Activity object
	public java.lang.Integer getId() {
		return activity.getId();
	}

	public int getStartTimeHour() {

		cal.clear();
		cal.setTime(getStartDate());

		return cal.get(Calendar.HOUR_OF_DAY);
	}

	public void setStartTimeHour(int startTime) {

		cal.clear();
		cal.setTime(getStartDate());
		cal.set(Calendar.HOUR_OF_DAY, startTime);

		setStartDate(cal.getTime());
	}

	public int getStartTimeMinute() {

		cal.clear();
		cal.setTime(getStartDate());

		return cal.get(Calendar.MINUTE);
	}

	public void setStartTimeMinute(int startTime) {

		cal.clear();
		cal.setTime(getStartDate());
		cal.set(Calendar.MINUTE, startTime);

		setStartDate(cal.getTime());
	}

	public int getEndTimeHour() {

		cal.clear();
		cal.setTime(getEndDate());

		return cal.get(Calendar.HOUR_OF_DAY);
	}

	public void setEndTimeHour(int endTime) {

		cal.clear();
		cal.setTime(getEndDate());
		cal.set(Calendar.HOUR_OF_DAY, endTime);

		setEndDate(cal.getTime());
	}

	public int getEndTimeMinute() {

		cal.clear();
		cal.setTime(getEndDate());

		return cal.get(Calendar.MINUTE);
	}

	public void setEndTimeMinute(int endTime) {

		cal.clear();
		cal.setTime(getEndDate());
		cal.set(Calendar.MINUTE, endTime);

		setEndDate(cal.getTime());
	}
	
	public int getExternalActivityStartTimeHour() {

		cal.clear();
		cal.setTime(getExternalActivityStartDate());

		return cal.get(Calendar.HOUR_OF_DAY);
	}

	public void setExternalActivityStartTimeHour(int startTime) {

		cal.clear();
		cal.setTime(getExternalActivityStartDate());
		cal.set(Calendar.HOUR_OF_DAY, startTime);

		setExternalActivityStartDate(cal.getTime());
	}

	public int getExternalActivityStartTimeMinute() {

		cal.clear();
		cal.setTime(getExternalActivityStartDate());

		return cal.get(Calendar.MINUTE);
	}

	public void setExternalActivityStartTimeMinute(int startTime) {

		cal.clear();
		cal.setTime(getExternalActivityStartDate());
		cal.set(Calendar.MINUTE, startTime);

		setExternalActivityStartDate(cal.getTime());
	}

	public int getExternalActivityEndTimeHour() {

		cal.clear();
		cal.setTime(getExternalActivityEndDate());

		return cal.get(Calendar.HOUR_OF_DAY);
	}

	public void setExternalActivityEndTimeHour(int endTime) {

		cal.clear();
		cal.setTime(getExternalActivityEndDate());
		cal.set(Calendar.HOUR_OF_DAY, endTime);

		setExternalActivityEndDate(cal.getTime());
	}

	public int getExternalActivityEndTimeMinute() {

		cal.clear();
		cal.setTime(getExternalActivityEndDate());

		return cal.get(Calendar.MINUTE);
	}

	public void setExternalActivityEndTimeMinute(int endTime) {

		cal.clear();
		cal.setTime(getExternalActivityEndDate());
		cal.set(Calendar.MINUTE, endTime);

		setExternalActivityEndDate(cal.getTime());
	}

	public Date getStartDate() {
		return activity.getStartDate();
	}

	public void setStartDate(Date startDate) {
		activity.setStartDate(startDate);
	}

	public Date getEndDate() {
		return activity.getEndDate();
	}

	public void setEndDate(Date endDate) {
		activity.setEndDate(endDate);
	}
	
	public Date getExternalActivityStartDate() {
		return externalActivity.getStartDate();
	}

	public void setExternalActivityStartDate(Date startDate) {
		externalActivity.setStartDate(startDate);
	}

	public Date getExternalActivityEndDate() {
		return externalActivity.getEndDate();
	}

	public void setExternalActivityEndDate(Date endDate) {
		externalActivity.setEndDate(endDate);
	}

	public int getDuration() {
		return activity.getDuration();
	}

	public void setDuration(int duration) {
		activity.setDuration(duration);
	}
	
	

	public String getDescription() {
		return activity.getDescription();
	}

	public void setDescription(String description) {
		activity.setDescription(description);
	}

	public boolean isBillable() {
		return activity.isBillable();
	}

	public void setBillable(boolean billable) {
		activity.setBillable(billable);
	}

	public User getUser() {
		return activity.getUser();
	}

	public void setUser(User user) {
		activity.setUser(user);
	}

	public ProjectRole getRole() {
		return activity.getRole();
	}

	public void setRole(ProjectRole role) {
		activity.setRole(role);
	}

	/* activity - generated by stajanov (do not edit/delete) */

	/**
	 * @return
	 */
	public ScheduleModel getScheduleModel() {

		loadModel();
		return scheduleModel;
	}

	/**
	 * Load data from persistence tier to model for efficiency purposes only
	 * load a month of data
	 */
	/**
	 * 
	 */
	public void loadModel() {

		if (reloadModel) {

			if (log.isTraceEnabled()) {
				log.trace("loading model from persistence tier");
			}

			scheduleModel = new SimpleScheduleModel();

			fillActivities(scheduleModel);
			fillExternalActivities(scheduleModel);
			fillHolidays(scheduleModel);

			scheduleModel.setMode(((SettingBean)FacesUtils.getBean("settingBean")).getMySettings().getMode());
			scheduleModel.setSelectedDate(selectedDate);
			scheduleModel.refresh();

			reloadModel = false;
		}

	}

	/**
	 * @param event
	 */
	public void activityClicked(ScheduleMouseEvent event) {


		setSelectedDate(event.getClickedDate());

		method = null;

		switch (event.getEventType()) {

		case ScheduleMouseEvent.SCHEDULE_BODY_CLICKED:
			method = "create";
			break;
		case ScheduleMouseEvent.SCHEDULE_ENTRY_CLICKED:
			method = "edit";
			break;
		case ScheduleMouseEvent.SCHEDULE_HEADER_CLICKED:
			method = "create";
			break;
		case ScheduleMouseEvent.SCHEDULE_NOTHING_CLICKED:
			break;

		default:
			break;
		}

	}

	/**
	 * @return
	 */
	public String actionPerformed() {

		Class[] types = new Class[0];
		Object[] args = new Object[0];
		String ret = NavigationResults.LIST;

		if (method != null) {
			try {
				ret = (String) this.getClass().getMethod(method, types).invoke(this, args);
			} catch (Exception e) {
				// doesn't ocurrs
			}
		}

		method = null;

		return ret;
	}

	/**
	 * @return
	 */
	public ScheduleEntryRenderer getEntryRenderer() {
		return entryRenderer;
	}

	/**
	 * @param event
	 */
	public void onSchedlueModeChanged(ValueChangeEvent event) {

		scheduleModel.setMode(((Integer) event.getNewValue()).intValue());
		scheduleModel.refresh();
	}

	

	/**
	 * @return
	 */
	public String editSettings() {

		return NavigationResults.SETTINGS;
	}

	

	

	/**
	 * @return
	 */
	public float getMonthPerformedHours() {

		/* we must reload model to obtain data */
		reloadModel = true;
		loadModel();
		return monthPerformedHours;
	}

	/**
	 * @return
	 */
	public float getMonthTotalHours() {
		
		ArrayList<Integer> diasContemplados = new ArrayList<Integer>();
		
		cal.setTime(selectedDate);

		float hoursPerDay = ((SettingBean)FacesUtils.getBean("settingBean")).getMySettings().getWorkingHours();

		int weekendsInMonth = 0;

		int daysInMonth = cal.getActualMaximum(Calendar.DAY_OF_MONTH);

		for (int i = 1; i <= daysInMonth; i++) {
			cal.set(Calendar.DAY_OF_MONTH, i);
			if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY
					|| cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
				weekendsInMonth++;
				diasContemplados.add(i);
			}			
		}
		
		
		// Restamos d�as de vacaciones aceptadas y dias de fiesta
		
		Calendar calMin = Calendar.getInstance();
		Calendar calMax = Calendar.getInstance();
		Calendar calAux = Calendar.getInstance();

		calMin.setTime(selectedDate);
		calMin.set(Calendar.DAY_OF_MONTH, cal.getMinimum(Calendar.DAY_OF_MONTH));
		calMin.set(Calendar.HOUR_OF_DAY, cal.getMinimum(Calendar.HOUR_OF_DAY));
		calMin.set(Calendar.MINUTE, cal.getMinimum(Calendar.MINUTE));
		calMin.set(Calendar.SECOND, cal.getMinimum(Calendar.SECOND));
		calMin.set(Calendar.MILLISECOND, cal.getMinimum(Calendar.MILLISECOND));

		calMax.setTime(selectedDate);
		calMax.set(Calendar.DAY_OF_MONTH, cal.getMaximum(Calendar.DAY_OF_MONTH));
		calMax.set(Calendar.HOUR_OF_DAY, cal.getMaximum(Calendar.HOUR_OF_DAY));
		calMax.set(Calendar.MINUTE, cal.getMaximum(Calendar.MINUTE));
		calMax.set(Calendar.SECOND, cal.getMaximum(Calendar.SECOND));
		calMax.set(Calendar.MILLISECOND, cal.getMaximum(Calendar.MILLISECOND));
		
		
		HolidaySearch monthSearch = new HolidaySearch();

		monthSearch.setStartDate(calMin.getTime());
		monthSearch.setEndDate(calMax.getTime());
		List<Holiday> listaHolidays = holidayManager.getAllEntities(monthSearch, null);
		
		
		int holidays = 0;
		for (Holiday holiday : listaHolidays) {
			calAux.setTime(holiday.getDate());
			int day = calAux.get(Calendar.DAY_OF_MONTH);
			if(!diasContemplados.contains(day)) {
				holidays++;
				diasContemplados.add(day);
			}
		}
		
		int requestedHolidays = 0;
		RequestHolidayManager rhManager = RequestHolidayManager.getDefault();
		RequestHolidaySearch rhSearch = new RequestHolidaySearch();
		rhSearch.setUserRequest(authManager.getCurrentPrincipal().getUser());
		rhSearch.setState(HolidayState.ACCEPT);
		rhSearch.setStartBeginDate(calMin.getTime());
		rhSearch.setEndBeginDate(calMax.getTime());
		rhSearch.setStartFinalDate(calMin.getTime());
		rhSearch.setEndFinalDate(calMax.getTime());

		List<RequestHoliday> listH = rhManager.getAllEntities(rhSearch, null);

		for (RequestHoliday rH : listH) {
			Calendar cActual = Calendar.getInstance();
			cActual.setTime(rH.getBeginDate());
			while (!cActual.getTime().after(rH.getFinalDate())) {
				int day = cActual.get(Calendar.DAY_OF_MONTH);
				if(!diasContemplados.contains(day)) {
					requestedHolidays++;
					diasContemplados.add(day);
				}
				cActual.add(Calendar.DAY_OF_MONTH, 1);
			}

		}
		

		return (daysInMonth - weekendsInMonth - holidays - requestedHolidays) * hoursPerDay;
	}

	public void validateHoursExternalActivity(FacesContext context, UIComponent toValidate, Object value) {
		validateHoursGeneric(context, toValidate, value, "tabExternalActivity");
	}
	
	public void validateHours(FacesContext context, UIComponent toValidate, Object value) {
		validateHoursGeneric(context, toValidate, value, "tabActivity");
	}
	
	public void validateHoursGeneric(FacesContext context, UIComponent toValidate, Object value, String tabName) {

		HtmlInputText startTimeHour = (HtmlInputText) FacesUtils.getComponent("activity").findComponent(tabName)
				.findComponent("startTimeHour");
		HtmlInputText startTimeMinute = (HtmlInputText) FacesUtils.getComponent("activity").findComponent(tabName)
				.findComponent("startTimeMinute");

		HtmlInputText endTimeHour = (HtmlInputText) FacesUtils.getComponent("activity").findComponent(tabName)
				.findComponent("endTimeHour");
		HtmlInputText endTimeMinute = (HtmlInputText) FacesUtils.getComponent("activity").findComponent(tabName)
				.findComponent("endTimeMinute");

		HtmlInputText duration = (HtmlInputText) FacesUtils.getComponent("activity").findComponent(tabName)
				.findComponent("duration");

		if (startTimeHour.getSubmittedValue() != null
				&& startTimeMinute.getSubmittedValue() != null
				&& endTimeHour.getSubmittedValue() != null
				&& endTimeMinute.getSubmittedValue() != null) {

			Date startTime = DateUtils.timeToDate(Integer.valueOf(
					startTimeHour.getSubmittedValue().toString()).intValue(), Integer.valueOf(
					startTimeMinute.getSubmittedValue().toString()).intValue());
			Date endTime = DateUtils.timeToDate(Integer.valueOf(
					endTimeHour.getSubmittedValue().toString()).intValue(), Integer.valueOf(
					endTimeMinute.getSubmittedValue().toString()).intValue());

			if (toValidate.equals(startTimeHour) || toValidate.equals(startTimeMinute)) {

				if (startTime.after(endTime)) {

					((UIInput) toValidate).setValid(false);
					FacesMessage message = new FacesMessage(
							"La hora de inicio no puede ser posterior a la hora de fin");
					context.addMessage(toValidate.getClientId(context), message);
				}
			}

			if (toValidate.equals(endTimeHour) || toValidate.equals(endTimeMinute)) {

				if (startTime.before(endTime)) {

					((UIInput) toValidate).setValid(false);
					FacesMessage message = new FacesMessage(
							"La hora de fin no puede ser anterior a la hora inicial");
					context.addMessage(toValidate.getClientId(context), message);
				}
			}

			if (toValidate.equals(duration)) {

				if (Integer.valueOf(duration.getSubmittedValue().toString()).intValue() < 0) {

					((UIInput) toValidate).setValid(false);
					FacesMessage message = new FacesMessage("La duracion no puede ser negativa");
					context.addMessage(toValidate.getClientId(context), message);
				}
			}
		}
	}

	private void fillHolidays(SimpleScheduleModel model) {

		Calendar calMin = Calendar.getInstance();
		Calendar calMax = Calendar.getInstance();

		calMin = getMinimumSearchTime(selectedDate);
		calMax = getMaximumSearchTime(selectedDate);
		
		RequestHolidayManager rhManager = RequestHolidayManager.getDefault();
		RequestHolidaySearch rhSearch = new RequestHolidaySearch();
		rhSearch.setUserRequest(authManager.getCurrentPrincipal().getUser());
		rhSearch.setState(HolidayState.ACCEPT);
		rhSearch.setStartBeginDate(calMin.getTime());
		rhSearch.setEndBeginDate(calMax.getTime());
		rhSearch.setStartFinalDate(calMin.getTime());
		rhSearch.setEndFinalDate(calMax.getTime());

		List<RequestHoliday> listH = rhManager.getAllEntities(rhSearch, null);

		for (RequestHoliday rH : listH) {
			Calendar cActual = Calendar.getInstance();
			cActual.setTime(rH.getBeginDate());
			while (!cActual.getTime().after(rH.getFinalDate())) {
				if (cActual.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY
						&& cActual.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
					model.setHoliday(cActual.getTime(), FacesUtils
							.getMessage("activity.acceptedHolidays"));
				}
				cActual.add(Calendar.DAY_OF_MONTH, 1);
			}

		}

		HolidaySearch monthSearch = new HolidaySearch();

		monthSearch.setStartDate(calMin.getTime());
		monthSearch.setEndDate(calMax.getTime());
		List<Holiday> listaHolidays = holidayManager.getAllEntities(monthSearch, null);

		for (Holiday holiday : listaHolidays) {
			model.setHoliday(holiday.getDate(), holiday.getDescription());
		}

	}
	
	private void fillExternalActivities(SimpleScheduleModel model) {
	
		Calendar calMin = Calendar.getInstance();
		Calendar calMax = Calendar.getInstance();

		calMin = getMinimumSearchTime(selectedDate);
		calMax = getMaximumSearchTime(selectedDate);
		
		ExternalActivitySearch search = new ExternalActivitySearch();
		
		search.setStartStartDate(calMin.getTime());
		search.setEndStartDate(calMax.getTime());
		
		search.setStartEndDate(calMin.getTime());
		search.setEndEndDate(calMax.getTime());
		
		search.setOwner(authManager.getCurrentPrincipal().getUser());
		
		List<ExternalActivity> extActivities = externalActivityManager.getAllEntities(search,new SortCriteria(
				sortColumn, sortAscending));
		
		for (ExternalActivity extActivity : extActivities) {
			
			ExternalActivityScheduleEntry entry = new ExternalActivityScheduleEntry(extActivity);
			
			model.addEntry(entry);
		}
		
	}
	
	private void fillActivities(SimpleScheduleModel model) {
		
		ActivitySearch searchMonth = new ActivitySearch();
		
		Calendar calMin = Calendar.getInstance();
		Calendar calMax = Calendar.getInstance();

		calMin = getMinimumSearchTime(selectedDate);
		calMax = getMaximumSearchTime(selectedDate);
		
		
		searchMonth.setStartStartDate(calMin.getTime());
		searchMonth.setEndStartDate(calMax.getTime());
		searchMonth.setUser(authManager.getCurrentPrincipal().getUser());
		/* search data in persistence layer */
		List<Activity> activities = manager.getAllEntities(searchMonth, new SortCriteria(
				sortColumn, sortAscending));

		/*
		 * activityDAO.search(searchMonth, new SortCriteria(sortColumn,
		 * sortAscending));
		 */

		monthPerformedHours = 0;

		/* load data in component */
		for (Activity actualActivity : activities) {

			calMin.setTime(actualActivity.getStartDate());
			calMax.setTime(selectedDate);

			if (calMin.get(Calendar.MONTH) == calMax.get(Calendar.MONTH)) {
				monthPerformedHours += actualActivity.getDuration();
			}

			ActivityScheduleEntry entry = new ActivityScheduleEntry(actualActivity);

			scheduleModel.addEntry(entry);
		}

		monthPerformedHours /= 60;

		
	}
	
	private Calendar getMaximumSearchTime(Date date) {
		
		Calendar calMax = Calendar.getInstance(); 
		
		calMax.setTime(date);
		calMax.set(Calendar.DAY_OF_MONTH, cal.getMaximum(Calendar.DAY_OF_MONTH));
		calMax.set(Calendar.HOUR_OF_DAY, cal.getMaximum(Calendar.HOUR_OF_DAY));
		calMax.set(Calendar.MINUTE, cal.getMaximum(Calendar.MINUTE));
		calMax.set(Calendar.SECOND, cal.getMaximum(Calendar.SECOND));
		calMax.set(Calendar.MILLISECOND, cal.getMaximum(Calendar.MILLISECOND));
		
		if (((SettingBean)FacesUtils.getBean("settingBean")).getMySettings().getLoadExtraDays()) {
			calMax.add(Calendar.MONTH, 1);
		}
		
		return calMax;
		
	}
	
	private Calendar getMinimumSearchTime(Date date) {
		
		Calendar calMin = Calendar.getInstance();
		
		calMin.setTime(date);
		calMin.set(Calendar.DAY_OF_MONTH, cal.getMinimum(Calendar.DAY_OF_MONTH));
		calMin.set(Calendar.HOUR_OF_DAY, cal.getMinimum(Calendar.HOUR_OF_DAY));
		calMin.set(Calendar.MINUTE, cal.getMinimum(Calendar.MINUTE));
		calMin.set(Calendar.SECOND, cal.getMinimum(Calendar.SECOND));
		calMin.set(Calendar.MILLISECOND, cal.getMinimum(Calendar.MILLISECOND));	
		
		if (((SettingBean)FacesUtils.getBean("settingBean")).getMySettings().getLoadExtraDays()) {
			calMin.add(Calendar.MONTH, -1);
		}
		
		return calMin;
	}

	public boolean isDefaultBillable() {
		return defaultBillable;
	}

	public void setDefaultBillable(boolean defaultBillable) {
		this.defaultBillable = defaultBillable;
	}

	/**
	 * @return
	 * @see com.autentia.tnt.businessobject.ExternalActivity#getCategory()
	 */
	public String getCategory() {
		return externalActivity.getCategory();
	}

	/**
	 * @return
	 * @see com.autentia.tnt.businessobject.ExternalActivity#getComments()
	 */
	public String getComments() {
		return externalActivity.getComments();
	}

	/**
	 * @return
	 * @see com.autentia.tnt.businessobject.ExternalActivity#getLocation()
	 */
	public String getLocation() {
		return externalActivity.getLocation();
	}

	/**
	 * @return
	 * @see com.autentia.tnt.businessobject.ExternalActivity#getName()
	 */
	public String getName() {
		return externalActivity.getName();
	}

	/**
	 * @return
	 * @see com.autentia.tnt.businessobject.ExternalActivity#getOrganizer()
	 */
	public String getOrganizer() {
		return externalActivity.getOrganizer();
	}

	/**
	 * @param category
	 * @see com.autentia.tnt.businessobject.ExternalActivity#setCategory(java.lang.String)
	 */
	public void setCategory(String category) {
		externalActivity.setCategory(category);
	}

	/**
	 * @param comments
	 * @see com.autentia.tnt.businessobject.ExternalActivity#setComments(java.lang.String)
	 */
	public void setComments(String comments) {
		externalActivity.setComments(comments);
	}

	/**
	 * @param location
	 * @see com.autentia.tnt.businessobject.ExternalActivity#setLocation(java.lang.String)
	 */
	public void setLocation(String location) {
		externalActivity.setLocation(location);
	}

	/**
	 * @param name
	 * @see com.autentia.tnt.businessobject.ExternalActivity#setName(java.lang.String)
	 */
	public void setName(String name) {
		externalActivity.setName(name);
	}

	/**
	 * @param organizer
	 * @see com.autentia.tnt.businessobject.ExternalActivity#setOrganizer(java.lang.String)
	 */
	public void setOrganizer(String organizer) {
		externalActivity.setOrganizer(organizer);
	}

	/**
	 * retrieves the duration of the external activity, calculated as the difference between start time and end time  
	 * @return
	 */
	public int getExternalActivityDuration() {
		GregorianCalendar startCal = new GregorianCalendar();
		GregorianCalendar endCal = new GregorianCalendar();
		
		startCal.setTime(getExternalActivityStartDate());
		endCal.setTime(getExternalActivityEndDate());
		
		int duration = (int) (endCal.getTimeInMillis() - startCal.getTimeInMillis())/(60*1000);
			
		return duration;
	}
	
	/**
	 * sets the end time using the duration (in minutes) of the external activity
	 * @param minutes
	 */
	public void setExternalActivityDuration(int minutes) {
				
	}
	
	public boolean isActivityTabRendered() {
		return (tabsRendered == NO_EDIT_SELECTED || tabsRendered == EDIT_ACTIVITY);
	}
	
	public boolean isExternalActivityTabRendered() {
		return (tabsRendered == NO_EDIT_SELECTED || tabsRendered == EDIT_EXTERNAL_ACTIVITY);
	}
	
	public int getSelectedTabIndex() {
		return tabsRendered == EDIT_EXTERNAL_ACTIVITY ? EDIT_EXTERNAL_ACTIVITY : EDIT_ACTIVITY;
	}
	
	public List<Document> getDocuments() {
		
		return documents;
		
	}
	
	/**
	 * @return the uploadFile
	 */
	public UploadedFile getUploadFile() {
		return uploadFile;
	}

	/**
	 * @param uploadFile the uploadFile to set
	 */
	public void setUploadFile(UploadedFile uploadFile) {
			this.uploadFile = uploadFile;
	}

	/**
	 * @return
	 * @see com.autentia.tnt.businessobject.ExternalActivity#getFiles()
	 */
	public Set<ActivityFile> getFiles() {
		return externalActivity.getFiles();
	}

	/**
	 * @return the listener
	 */
	public BitacoreTabChangeListener getListener() {
		return listener;
	}

	/**
	 * @return
	 * @see com.autentia.tnt.bean.activity.BitacoreTabChangeListener#getSelectedTab()
	 */
	public int getSelectedTab() {
		return selectedTab;
	}
	
	public void setSelectedTab(int selectedTab) {
		this.selectedTab = selectedTab;
	}
	
	public Integer getExternalActivityId() {
		return externalActivity.getId();
	}
	
	public String deleteFile() {
		
		UIData table = (UIData) FacesUtils.getComponent("activity:tabExternalActivity:list");
		ActivityFile toDelete = (ActivityFile) table.getRowData(); 
		externalActivity.getFiles().remove(toDelete);
		
		return null;
	}
	
	public boolean isProjectsRendered(){
		boolean has = true;
		
		if (this.selectedOrganization == null){
			has = false;
		} else {
			List<Project> projects = ProjectManager.getDefault().getProjectsByOrganization(selectedOrganization);
			has = ! ((projects == null) || projects.isEmpty());
		}
		
		return  has;		
	}
	
	public boolean isRolesRendered(){
		boolean has = true;
		
		if (this.selectedOrganization == null){
			has = false;
		} else if (this.selectedProject == null) {
			has = false;
		} else {
			Set<ProjectRole> roles = this.selectedProject.getRoles();
			has = ! ((roles == null) || roles.isEmpty());
		}
		
		return  has;
	}
}
