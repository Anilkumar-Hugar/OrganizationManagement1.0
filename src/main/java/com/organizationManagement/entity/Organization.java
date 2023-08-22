package com.organizationManagement.entity;

import org.springframework.lang.NonNull;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "organization")
public class Organization {
	@NonNull
	@EmbeddedId
	private OrganizationKeyClass organizationKeyClass;
	@NonNull
	@NotBlank(message = "organization details should not be null")
	private String OrganizationDetails;
	@Version
	private long version;
}
