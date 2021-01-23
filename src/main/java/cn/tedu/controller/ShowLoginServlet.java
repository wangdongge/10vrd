package cn.tedu.controller;

import cn.tedu.utils.ThUtils;
import org.thymeleaf.context.Context;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@MultipartConfig
@WebServlet(name = "ShowLoginServlet", urlPatterns = "/showlogin")
public class ShowLoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //显示登陆页面
        Context context = new Context();
        //取出Cookie数据内容
        Cookie[] cookies = request.getCookies();
        //非空判断
        if (cookies!=null){
            //遍历所有Cookie
            for (Cookie cookie : cookies) {
                //判断Cookie中保存的是否为用户名
                if (cookie.getName().equals("username")){
                    String username = cookie.getValue();//取出cookie中的用户名
                    //把用户名保存到Context容器中
                    context.setVariable("username",username);
                }
                //判断cookie中是否是密码
                if (cookie.getName().equals("password")){
                    String password = cookie.getValue();
                    context.setVariable("password",password);
                }

            }
        }
        ThUtils.print("login.html",context,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
