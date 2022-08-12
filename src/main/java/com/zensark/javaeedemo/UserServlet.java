package com.zensark.javaeedemo;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/user")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Map<String, String> queryParams = getQueryParams(request.getQueryString());
		response.setContentType("application/json");
		PrintWriter writer = response.getWriter();
		writer.write(createJSON(queryParams));
	}

	private Map<String, String> getQueryParams(String queryString) {
		Map<String, String> queryParams = new HashMap<>();
		for (String param : queryString.split("&")) {
			String[] qs = param.split("=");
			queryParams.put(qs[0], qs[1]);
		}
		return queryParams;
	}
	
	private String createJSON(Map<String, String> map) {
		StringBuilder jsonBuilder = new StringBuilder();
		jsonBuilder.append("{");
		List<String> keyPairs = new ArrayList<>();
		map.forEach((key,value)->{
			keyPairs.add(String.format("\"%s\":\"%s\"", key, value));
		});
		jsonBuilder.append(String.join(",", keyPairs));
		jsonBuilder.append("}");
		return jsonBuilder.toString();
	}
}
