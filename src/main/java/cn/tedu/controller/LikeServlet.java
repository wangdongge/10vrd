package cn.tedu.controller;

import cn.tedu.dao.ProductDao;
import cn.tedu.entity.Product;
import org.thymeleaf.context.Context;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "LikeServlet", urlPatterns = "/like")
public class LikeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");

        HttpSession session = request.getSession();
        //通过id获取session里面是否有对应的数据
        String likeId = (String) session.getAttribute("like"+id);
        if (likeId == null){//没有点过赞
            //创建dao
            ProductDao dao = new ProductDao();
            dao.likeById(id);
            //把修改之后的id添加到session对象中
            session.setAttribute("like"+id,id);
        }


        //重定向到详情页面
        response.sendRedirect("/detail?id="+id);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
