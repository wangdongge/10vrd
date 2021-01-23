package cn.tedu.filter;

import cn.tedu.entity.User;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
/*urlPartterns设置处理路径  通过 { } 进行多个访问 */
@WebFilter(filterName = "MyFilter",urlPatterns = {"/showsend","/showbanner"})
public class MyFilter implements Filter {
    /*对象创建时执行的方法*/
    @Override
    public void init(FilterConfig config) throws ServletException {

    }
    /*对象销毁时执行的方法*/
    @Override
    public void destroy() {

    }
    /*开始执行过滤时执行的方法*/
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        //如果执行过滤器后 允许访问后面的资源则调用下面代码 不允许不则调用
        //chain.doFilter(request, response);
        //通过过滤器中的请求和响应对象为Servlet里面请求对象的父类
        //使强转 转回HttpServletXXX
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        //获取session对象
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        //判断是否登录
        if (user==null){//满足条件说明没有登陆
            System.out.println("没登陆，拦截！");
            //重定向到登陆页面
            resp.sendRedirect("/showlogin");
            return;//结束方法
        }else {//登陆过
            System.out.println("登陆过，放行");
            //写到下面这行代码的内容 会在触发Servlet或文件资源之前执行
            chain.doFilter(request,response);//放行
            //写在此处的代码就是执行完Servlet或文件资源之后会触发
        }

    }
}
