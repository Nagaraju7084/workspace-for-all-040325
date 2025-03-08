package com.medi.preclinic.service;

import java.util.List;

import com.medi.preclinic.domain.MediUserType;
import com.medi.preclinic.dto.MediUserTypeDto;

public interface MediUserTypeService {
	
	public MediUserTypeDto createUserType(final MediUserTypeDto userTypeDto);
	public MediUserTypeDto findUserTypeById(final String userTypeId);
	public MediUserTypeDto updateUserType(final MediUserTypeDto userTypeDto);
	public List<MediUserTypeDto> deleteUserType(final MediUserTypeDto userTypeDto);
	public List<MediUserTypeDto> deleteUserTypeById(final String userTypeId);
	
	public List<MediUserTypeDto> findAllUserTypes();
	
	MediUserType dtoToDomain(MediUserTypeDto userTypeDto);
	MediUserTypeDto domainToDto(MediUserType userType); //these conversion methods required for other classes so we keep here as abstract methods and we will provide implementation in the implementation class i.e. MediUserTypeServiceImpl class
}
