package com.organizationManagement;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;

import com.organizationManagement.entity.Organization;
import com.organizationManagement.entity.OrganizationKeyClass;
import com.organizationManagement.entity.User;
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
