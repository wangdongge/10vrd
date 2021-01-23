package cn.tedu.controller;

import cn.tedu.dao.UserDao;
import cn.tedu.entity.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "LoginServlet", urlPatterns = "/loginaction")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置字符集
        request.setCharacterEncoding("utf-8");
        //获取form表单参数
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        String rem = request.getParameter("rem");
        System.out.println("rem"+rem);

        //创建UserDao 并调用login方法
        UserDao dao = new UserDao();
        User user = dao.login(username,password);
        if (user!=null){
            if (rem!=null){//需要记住用户名和密码
                //创建Cookie把用户名和密码保存到cookie中
                Cookie c1 = new Cookie("username",username);
                Cookie c2 = new Cookie("password",password);
                //修改cookie保存时间
                c1.setMaxAge(60*60*24*30);

                //把Cookie下发到浏览器
                response.addCookie(c1);
                response.addCookie(c2);
            }
            //记住登录状态
            HttpSession session = request.getSession();
            //把当前登录的用户对象保存到session里面
            session.setAttribute("user",user);

            System.out.println("登录成功");
            //如果登录成功 重定向到首页列表页面
            response.sendRedirect("/home");
        }else {
            System.out.println("登录失败");
            //如果登陆失败 重定向到登陆的页面
            response.sendRedirect("/showlogin");
        }



    }
}
