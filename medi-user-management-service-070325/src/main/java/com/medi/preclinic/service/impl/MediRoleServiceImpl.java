package com.medi.preclinic.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medi.preclinic.domain.MediRole;
import com.medi.preclinic.dto.MediRoleDto;
import com.medi.preclinic.repository.MediRoleRepository;
import com.medi.preclinic.service.MediRoleService;

@Service
public class MediRoleServiceImpl implements MediRoleService {
	
	@Autowired
	private MediRoleRepository mediRoleRepository;

	@Override
	public MediRoleDto createRole(MediRoleDto mediRoleDto) {
		return domainToDto(mediRoleRepository.save(dtoToDomain(mediRoleDto)));
	}

	@Override
	public MediRoleDto findRoleById(String roleId) {
		return domainToDto(mediRoleRepository.findById(Integer.valueOf(roleId)).get());
	}

	@Override
	public MediRoleDto updateRole(MediRoleDto mediRoleDto) {
		return domainToDto(mediRoleRepository.save(dtoToDomain(mediRoleDto)));
	}

	@Override
	public List<MediRoleDto> deleteRole(MediRoleDto mediRoleDto) {
		mediRoleRepository.delete(dtoToDomain(mediRoleDto));
		return findAllRoles();
	}

	@Override
	public List<MediRoleDto> deleteRoleById(String roleId) {
		mediRoleRepository.deleteById(Integer.valueOf(roleId));
		return findAllRoles();
	}

	@Override
	public MediRoleDto provisioningUser(String userId, String roleId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MediRoleDto deProvisioningUser(String userId, String roleId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MediRoleDto> findAllRoles() {
		return mediRoleRepository.findAll().stream().map(mr -> domainToDto(mr)).collect(Collectors.toList());
	}
	
	@Override
	public MediRole dtoToDomain(MediRoleDto mediRoleDto) {
		MediRole mediRole = new MediRole();
		BeanUtils.copyProperties(mediRoleDto, mediRole);
		return mediRole;
	}
	
	@Override
	public MediRoleDto domainToDto(MediRole mediRole) {
		MediRoleDto mediRoleDto = new MediRoleDto();
		BeanUtils.copyProperties(mediRole, mediRoleDto);
		return mediRoleDto;
	}
}
