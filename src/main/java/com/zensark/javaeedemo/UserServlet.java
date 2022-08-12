package com.zensark.javaeedemo;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet("/user")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private final ObjectMapper mapper = new ObjectMapper();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Map<String, String> queryParams = getQueryParams(request.getQueryString());
		response.setContentType("application/json");
		PrintWriter writer = response.getWriter();
		writer.write(mapper.writeValueAsString(queryParams));
	}

	private Map<String, String> getQueryParams(String queryString) {
		Map<String, String> queryParams = new HashMap<>();
		for (String param : queryString.split("&")) {
			String[] qs = param.split("=");
			queryParams.put(qs[0], qs[1]);
		}
		return queryParams;
	}
}
