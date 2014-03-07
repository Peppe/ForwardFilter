package com.jensjansson.forwardfilter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class ForwardFilter implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		String path = req.getRequestURI().substring(
				req.getContextPath().length());

		if (path.startsWith("/page.html")) {
			chain.doFilter(request, response); // Goes to default servlet.
		} else if (path.startsWith("/VAADIN/")) {
			chain.doFilter(request, response); // Goes to default servlet.
		} else if (path.startsWith("/UIDL/")) {
			request.getRequestDispatcher("/app/UIDL/").forward(request,
					response); // UIDL calls within Vaadin app
		} else {
			// Vaadin app calls
			request.getRequestDispatcher("/app").forward(request, response);
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

	public void destroy() {
	}
}
