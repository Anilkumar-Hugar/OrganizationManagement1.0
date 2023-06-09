package com.organizationManagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.organizationManagement.entity.Branch;
import com.organizationManagement.service.BranchService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/branch")
@SecurityRequirement(name = "basicAuth")
public class BranchController {
	@Autowired
	private BranchService branchService;

	@PostMapping
	@Operation(summary = "Create organization and save it to the database")
	@PreAuthorize("hasAnyAuthority('ADMIN','USER')")
	public ResponseEntity<Branch> createBranch(@Valid @RequestBody Branch branch) {
		Branch branches = branchService.createBranch(branch);
		return ResponseEntity.ok(branches);
	}

	@GetMapping
	@Operation(summary = "Display all details")
	@PreAuthorize("hasAnyAuthority('ADMIN','USER')")
	public ResponseEntity<List<Branch>> getAllDetails(int pageNo,int pageSize) {
		Pageable pageable=PageRequest.of(pageNo, pageSize);
		return ResponseEntity.ok(branchService.getAllDetails(pageable));
	}

	@GetMapping("/getById")
	@Operation(summary = "Display details based on Id")
	@PreAuthorize("hasAnyAuthority('ADMIN','USER')")
	public ResponseEntity<Branch> getDetailsById(@Valid @RequestParam(name = "id") int id) {
		return ResponseEntity.ok(branchService.getDetailsById(id));
	}

	@DeleteMapping
	@Operation(summary = "Delete details based on Id")
	@PreAuthorize("hasAnyAuthority('ADMIN','USER')")
	public ResponseEntity<String> deleteById(@Valid @RequestParam(name = "id") int id) {
		return ResponseEntity.ok(branchService.deleteById(id));
	}

	@PutMapping("/{id}")
	@Operation(summary = "Update details based on Id")
	@PreAuthorize("hasAnyAuthority('ADMIN','USER')")
	public ResponseEntity<Branch> updateDetails(@Valid @RequestParam(name = "id") int id,@Valid @RequestBody Branch branch) {
		return ResponseEntity.ok(branchService.updateDetails(id, branch));
	}

	@PatchMapping(consumes ="application/json-patch+json")
	@Operation(summary = "update partial details")
	@PreAuthorize("hasAnyAuthority('ADMIN','USER')")
	public ResponseEntity<Branch> update(@Valid @RequestParam(name = "id") int id,@Valid  @RequestBody JsonPatch jsonPatch) throws JsonProcessingException, IllegalArgumentException, JsonPatchException{
		Branch patchedBranch=branchService.patch(id, jsonPatch);
		return ResponseEntity.ok(patchedBranch);
	}
}
