package filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface HttpFilter extends Filter {

	@Override
	default void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest request = (HttpServletRequest) arg0;
		HttpServletResponse respone = (HttpServletResponse) arg1;
		this.doFilter(request, respone, arg2);
	}

	void doFilter(HttpServletRequest req, HttpServletResponse resp, FilterChain chain)
			throws IOException, ServletException;

	@Override
	default void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	default void destroy() {
	}
}
