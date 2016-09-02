package com.autentia.tnt.bean.activity;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import javax.faces.model.SelectItem;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;

import com.autentia.tnt.businessobject.Organization;
import com.autentia.tnt.businessobject.Project;
import com.autentia.tnt.businessobject.ProjectRole;
import com.autentia.tnt.businessobject.User;
import com.autentia.tnt.dao.SortCriteria;
import com.autentia.tnt.dao.search.ActivitySearch;
import com.autentia.tnt.dao.search.OrganizationSearch;
import com.autentia.tnt.manager.activity.ActivityManager;
import com.autentia.tnt.manager.admin.ProjectManager;
import com.autentia.tnt.manager.contacts.OrganizationManager;
import com.autentia.tnt.manager.security.AuthenticationManager;
import com.autentia.tnt.manager.security.Principal;
import com.autentia.tnt.util.ConfigurationUtil;
import com.autentia.tnt.util.SpringUtils;

public class ActivityBeanTest {
	
	final static ConfigurationUtil configurationUtil = mock(ConfigurationUtil.class);
	final static ApplicationContext ctx = mock(ApplicationContext.class);
	final static ActivityManager activityManager = mock(ActivityManager.class);
	final static AuthenticationManager authManager = mock(AuthenticationManager.class);
	final static ActivitySearch activitySearch = mock(ActivitySearch.class);
	final static OrganizationManager organizationManager = mock(OrganizationManager.class);
	final static ProjectManager projectManager = mock(ProjectManager.class);
	
	final static User user = mock(User.class);
	final static Principal principal = mock(Principal.class);
	
	final static Organization org = mock(Organization.class);
	
	
	
	private final ActivityBean activityBean = new ActivityBean();

	private static final Project p1 = new Project();
	private static final Project p2 = new Project();

	private static final ProjectRole rol1 = new ProjectRole();
	private static final ProjectRole rol2 = new ProjectRole();
	
	private static List<Project> generateListProjectsForTest(){
		List<Project> list = new ArrayList<Project>();

		rol1.setName("rol1");
		rol1.setProject(p1);

		p1.setId(1);
		p1.setName("Proyecto prueba 1");
		p1.setOpen(true);
		p1.setRoles(new HashSet<ProjectRole>(Arrays.asList(rol1)));
		list.add(p1);

		rol2.setName("rol2");
		rol2.setProject(p2);

		p2.setId(2);
		p2.setName("Proyecto prueba 2");
		p2.setOpen(true);
		p2.setRoles(new HashSet<ProjectRole>(Arrays.asList(rol2)));

		list.add(p2);
		
		return list;
	}
	
	@BeforeClass
	public static void init () {
		when(ctx.getBean("managerActivity")).thenReturn(activityManager);
		when(ctx.getBean("configuration")).thenReturn(configurationUtil);
		when(ctx.getBean("userDetailsService")).thenReturn(authManager);
		when(ctx.getBean("managerOrganization")).thenReturn(organizationManager);
		when(ctx.getBean("managerProject")).thenReturn(projectManager);
		
		when(authManager.getCurrentPrincipal()).thenReturn(principal);
		when(principal.getUser()).thenReturn(user);
		when(projectManager.getOpenProjectsByOrganization(org)).thenReturn(generateListProjectsForTest());
		when(organizationManager.getAllEntities((OrganizationSearch)any(), (SortCriteria) any())).thenReturn(Arrays.asList(org)); 
		SpringUtils.configureTest(ctx);

	}
	
	@Test
	public void shouldShowOnlyOpenProjectsWhenSelectedProjectIsOpen(){
	
		activityBean.setSelectedOrganization(org);
		activityBean.setSelectedProject(p1);
		
		
		List<SelectItem> projects = activityBean.getProjectsVisiblesBySelectedOrganization();
		assertThat(projects.size(), is(greaterThan(0)));
		
		for(SelectItem item:projects) {
			assertThat(((Project)item.getValue()).isFinished(), is(not(true)));
		}
	}

	@Test
	public void shouldShowCurrentProjectWhenSelectedProjectIsClose(){
	
		p1.setOpen(false);
		
		activityBean.setSelectedOrganization(org);
		activityBean.setSelectedProject(p1);
		
		List<SelectItem> projects = activityBean.getProjectsVisiblesBySelectedOrganization();
		assertThat(projects.size(), is(greaterThan(0)));
		
		boolean isOk = false;
		for(SelectItem si:projects) {
			if(((Project)si.getValue()).getId() == p1.getId()){
				isOk = true;
			}
		}
		
		assertThat(isOk, is(true));
		
	}

	@Test
	public void shouldSelectFirstAvailableOrganizationAndShowOnlyOpenProjectsWhenSelectedProjectIsNull(){
	
		activityBean.setSelectedOrganization(null);
		activityBean.setSelectedProject(null);
		
		List<SelectItem> projects = activityBean.getProjectsVisiblesBySelectedOrganization();
		assertThat(projects.size(), is(greaterThan(0)));
		
		for(SelectItem item:projects) {
			assertThat(((Project)item.getValue()).isFinished(), is(not(true)));
		}
	}
}
