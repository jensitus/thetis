package course.user.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = {"create"})
public class LoginFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        HttpSession session = httpServletRequest.getSession();
        String sessionID = session.getId();
        String userSessionID = null;

        String user;
        user = (String) session.getAttribute("user");

        Cookie[] cookies = httpServletRequest.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("JSESSIONID")) {
                    sessionID = cookie.getValue();

                }
                if (cookie.getName().equals(user)) {
                    userSessionID = cookie.getValue();

                }
            }
        }

        System.out.println(sessionID);
        System.out.println(userSessionID);

        if (sessionID.equals(userSessionID)){
            System.out.println("true");
        } else {
            ((HttpServletResponse) response).sendRedirect("login");
            return;
        }

        chain.doFilter(request, response);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
