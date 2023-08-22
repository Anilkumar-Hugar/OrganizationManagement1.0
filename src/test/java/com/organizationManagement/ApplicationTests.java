package com.organizationManagement;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.organizationManagement.entity.Organization;
import com.organizationManagement.entity.OrganizationKeyClass;
import com.organizationManagement.repository.OrganizationRepository;
import com.organizationManagement.service.OrganizationService;

@SpringBootTest
class ApplicationTests {
	@Autowired
	private OrganizationRepository organizationRepository;
	@Autowired
	private OrganizationService organizationService;
	@Test
	public void testCreateOrganization() {
		Organization organization=new Organization();
		OrganizationKeyClass organizationKeyClass=new OrganizationKeyClass(1,"seneca");
		organization.setOrganizationDetails("SGIS");
		organization.setOrganizationKeyClass(organizationKeyClass);
		organization.setVersion(0);
		assertThat(organizationRepository.save(organization)).isNotNull();
	}
	@Test
	public void testGetDetails() {
		assertThat(organizationService.getDetails()).isNotNull();
	}
	@Test
	public void testGetDetailsById() {
		//OrganizationKeyClass organizationKeyClass=new OrganizationKeyClass(1,"seneca");
		Organization organization=new Organization(new OrganizationKeyClass(1, "seneca"),"SGIS",0L);
		//assertThat(organizationService.getDetailsById(1,"seneca")).isNotNull();
		assertEquals(organization, organizationService.getDetailsById(1, "seneca"));
		
	}
	
	

}
