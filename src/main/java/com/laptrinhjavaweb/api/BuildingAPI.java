package com.laptrinhjavaweb.api;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.laptrinhjavaweb.api.input.BuildingInput;
import com.laptrinhjavaweb.builder.BuildingSearchBuilder;
import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.service.IBuildingService;
import com.laptrinhjavaweb.service.impl.BuildingService;
import com.laptrinhjavaweb.utils.FormUtil;

/**
 * Servlet implementation class BuildingAPI
 */
@WebServlet("/building")
public class BuildingAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IBuildingService buildingService = new BuildingService();
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		//convert params in request to model input
		BuildingInput buildingInput = FormUtil.toModel(BuildingInput.class, request);
		//convert model input to builder
		BuildingSearchBuilder builder = new BuildingSearchBuilder.Builder()
				.setName(buildingInput.getName())
				.setDistrict(buildingInput.getDistrict())
				.setFloorArea(StringUtils.isNotBlank(buildingInput.getFloorArea()) ? Integer.parseInt(buildingInput.getFloorArea()) : null)
				.setNumberOfBasement(StringUtils.isNotBlank(buildingInput.getNumberOfBasement()) ? Integer.parseInt(buildingInput.getNumberOfBasement()) : null)
				.setRentAreaFrom(buildingInput.getRentAreaFrom())
				.setRentAreaTo(buildingInput.getRentAreaTo())
				.setRentCostFrom(buildingInput.getRentCostFrom())
				.setRentCostTo(buildingInput.getRentCostTo())
				.setStaffId(buildingInput.getStaffId())
				.setTypes(buildingInput.getTypes())
				.build();
		
		//get data from database and save in dto
		List<BuildingDTO> result = buildingService.findAll(builder);
		//convert dto to json to send to client through response
		mapper.writeValue(response.getOutputStream(), result);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}