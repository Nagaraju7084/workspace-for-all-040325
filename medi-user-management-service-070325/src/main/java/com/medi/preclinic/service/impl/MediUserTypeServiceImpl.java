package com.medi.preclinic.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medi.preclinic.domain.MediUserType;
import com.medi.preclinic.dto.MediUserTypeDto;
import com.medi.preclinic.repository.MediUserTypeRepository;
import com.medi.preclinic.service.MediUserTypeService;

@Service
public class MediUserTypeServiceImpl implements MediUserTypeService {
	
	@Autowired
	private MediUserTypeRepository userTypeRepository;

	@Override
	public MediUserTypeDto createUserType(MediUserTypeDto userTypeDto) {
		return domainToDto(userTypeRepository.save(dtoToDomain(userTypeDto)));
	}

	@Override
	public MediUserTypeDto findUserTypeById(String userTypeId) {
		MediUserType mediUserType = userTypeRepository.findById(Integer.valueOf(userTypeId)).get();
		return domainToDto(mediUserType);
	}

	@Override
	public MediUserTypeDto updateUserType(MediUserTypeDto userTypeDto) {
		return domainToDto(userTypeRepository.save(dtoToDomain(userTypeDto)));
	}

	@Override
	public List<MediUserTypeDto> deleteUserType(MediUserTypeDto userTypeDto) {
		userTypeRepository.delete(dtoToDomain(userTypeDto));
		return findAllUserTypes(); // when we use transaction, what type oftransaction will
		//consider in this method because this method is calling another method i.e.
		//in transaction propogation, this transaction will suspend here and
		//in findallusertypes method will continue and resume this method transaction back
		//or transaction will continue from here to next i.e. propogate to the next
	}

	@Override
	public List<MediUserTypeDto> deleteUserTypeById(String userTypeId) {
		userTypeRepository.deleteById(Integer.valueOf(userTypeId));
		return findAllUserTypes();
	}

	@Override
	public List<MediUserTypeDto> findAllUserTypes() {
		return userTypeRepository.findAll().stream()
				.map(mut -> domainToDto(mut)).collect(Collectors.toList());
	}
	
	/*
	 * converting dto to domain
	 * @param UserTypeDto
	*/
	@Override
	public MediUserType dtoToDomain(MediUserTypeDto userTypeDto) {
		MediUserType userTypeDomain = new MediUserType();
		BeanUtils.copyProperties(userTypeDto, userTypeDomain);
		return userTypeDomain;
	}
	
	/*
	 * converting domain to dto
	 * @param MediUserType
	*/
	@Override
	public MediUserTypeDto domainToDto(MediUserType userType) {
		MediUserTypeDto userTypeDto = new MediUserTypeDto();
		BeanUtils.copyProperties(userType, userTypeDto);
		return userTypeDto;
	}

}
