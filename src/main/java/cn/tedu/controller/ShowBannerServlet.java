package cn.tedu.controller;

import cn.tedu.dao.BannerDao;
import cn.tedu.entity.Banner;
import cn.tedu.utils.ThUtils;
import org.thymeleaf.context.Context;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ShowBannerServlet", urlPatterns = "/showbanner")
public class ShowBannerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //查询所有的轮播图
        BannerDao dao = new BannerDao();
        List<Banner> list = dao.findAll();
        //把数据和页面整合返回给客户端
        Context context = new Context();
        context.setVariable("list",list);
        ThUtils.print("banner.html",context,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
