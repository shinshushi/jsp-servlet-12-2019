package com.laptrinhjavaweb.service;

import java.util.List;
import java.util.Map;

import com.laptrinhjavaweb.api.output.BuildingTypeOutput;
import com.laptrinhjavaweb.builder.BuildingSearchBuilder;
import com.laptrinhjavaweb.dto.BuildingDTO;

public interface IBuildingService {
	List<BuildingDTO> findAll(BuildingSearchBuilder builder);
	void insert(BuildingDTO dto);
	List<BuildingTypeOutput> getBuildingType();
	Map<String,String> getMapBuildingType();
}
