package cn.tedu.controller;

import cn.tedu.dao.CategoryDao;
import cn.tedu.entity.Category;
import cn.tedu.entity.User;
import cn.tedu.utils.ThUtils;
import org.thymeleaf.context.Context;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ShowSendServlet", urlPatterns = "/showsend")
public class ShowSendServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //判断是否登录
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user==null){//满足条件说明没有登陆
            //重定向到登陆页面
            response.sendRedirect("/showlogin");
            return;//结束方法
        }
        //显示发布页面
        Context context = new Context();

        CategoryDao dao = new CategoryDao();
        List<Category> list = dao.findAll();
        context.setVariable("list",list);

        ThUtils.print("send.html", context, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
