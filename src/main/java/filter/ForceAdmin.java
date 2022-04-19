package filter;

import entitys.UsersEntity;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = {"/users/*","/products/*","/provideds/*","/order/*"})
public class ForceAdmin implements HttpFilter{

	@Override
	public void doFilter(HttpServletRequest req, HttpServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		System.out.println("hereFillter");

		UsersEntity u = (UsersEntity) req.getSession().getAttribute("sessionUser");

		if (u == null) {
			resp.sendRedirect("/login");
		} else
			if(u!= null && u.getIsAdmin()==1) {
				chain.doFilter(req, resp);
			}else {
				resp.sendRedirect("/home");
				return;
			}
	}
}
