package cn.tedu.controller;

import cn.tedu.dao.BannerDao;
import cn.tedu.entity.Banner;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.File;
import java.io.IOException;

@WebServlet(name = "DeleteBannerServlet", urlPatterns = "/deletebanner")
public class DeleteBannerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        BannerDao dao = new BannerDao();
        //删除图片文件
        Banner b = dao.findAllById(id);
        //得到轮播图的完成路径
        String path = request.getServletContext().getRealPath(b.getUrl());
        new File(path).delete();
        dao.deleteById(id);
        response.sendRedirect("/showbanner");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
