package com.laptrinhjavaweb.service.impl;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.laptrinhjavaweb.api.output.BuildingTypeOutput;
import com.laptrinhjavaweb.builder.BuildingSearchBuilder;
import com.laptrinhjavaweb.converter.BuildingConverter;
import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.entity.BuildingEntity;
import com.laptrinhjavaweb.enums.BuildingTypeEnum;
import com.laptrinhjavaweb.repository.IBuildingRepository;
import com.laptrinhjavaweb.repository.impl.BuildingRepository;
import com.laptrinhjavaweb.service.IBuildingService;

public class BuildingService implements IBuildingService {
	private IBuildingRepository buildingRepository = new BuildingRepository();
	private BuildingConverter buildingConverter = new BuildingConverter();
	
	@Override
	public List<BuildingDTO> findAll(BuildingSearchBuilder builder) {
		Map<String, Object> params = convertToMapProperties(builder);
		List<BuildingEntity> entities = buildingRepository.findAll(params, builder);
		List<BuildingDTO> result = entities.stream().map(item -> buildingConverter.convertEntityToDTO(item)).collect(Collectors.toList());
		return result;
	}


	private Map<String, Object> convertToMapProperties(BuildingSearchBuilder builder) {
		Map<String, Object> properties = new HashMap<>();
		Field[] fields = BuildingSearchBuilder.class.getDeclaredFields();
		for(Field field : fields) {
			if(!field.getName().startsWith("rentArea") && !field.getName().equals("types") && !field.getName().startsWith("rentCost") && !field.getName().equals("staffId")) {
				field.setAccessible(true);
				try {
					properties.put(field.getName().toLowerCase(), field.get(builder));
				} catch (IllegalArgumentException | IllegalAccessException e) {
					System.out.println(e.getMessage());
				}
			}
		}
		return properties;
	}

	@Override
	public void insert(BuildingDTO dto) {
		BuildingEntity entity = buildingConverter.convertDtoToEntity(dto);
		buildingRepository.insert(entity);
	}


	@Override
	public List<BuildingTypeOutput> getBuildingType() {
		List<BuildingTypeOutput> result = new ArrayList<>();
		for(BuildingTypeEnum item : BuildingTypeEnum.values()){
			BuildingTypeOutput buildingTypeOutput = new BuildingTypeOutput();
			buildingTypeOutput.setCode(item.toString());
			buildingTypeOutput.setName(item.getValue());
			result.add(buildingTypeOutput);
		}
		return result;
	}


	@Override
	public Map<String, String> getMapBuildingType() {
		Map<String, String> result = new HashMap<>();
		for(BuildingTypeEnum item : BuildingTypeEnum.values()){
			result.put(item.toString(), item.getValue());
		}
		return result;
	}
}
