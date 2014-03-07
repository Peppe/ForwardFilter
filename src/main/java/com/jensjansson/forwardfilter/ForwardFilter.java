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
		final String contextPath = ((HttpServletRequest) request)
				.getContextPath();
		String path = ((HttpServletRequest) request).getRequestURI().substring(
				contextPath.length());
		System.out.println("requestURI: " + ((HttpServletRequest) request).getRequestURI());
		if (path.toLowerCase().contains("page.html")) {
			System.out
					.println("page.html was requested. We should serve that page.");
			chain.doFilter(request, response);
		}
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

	public void destroy() {
	}
}
