package com.laptrinhjavaweb.api;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.laptrinhjavaweb.service.IUserService;
import com.laptrinhjavaweb.service.impl.UserService;


@WebServlet("/user")
public class UserAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private IUserService userService = new UserService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		String action = request.getParameter("action");
		String roleCode = request.getParameter("roleCode");
		if(action != null && action.toLowerCase().equals("load_staff") && roleCode != null) {
			mapper.writeValue(response.getOutputStream(), userService.findStaff(roleCode));
		}
	}
}
