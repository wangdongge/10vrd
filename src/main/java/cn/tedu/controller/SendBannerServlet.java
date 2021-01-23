package cn.tedu.controller;

import cn.tedu.dao.BannerDao;
import cn.tedu.entity.Banner;
import org.thymeleaf.context.Context;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@MultipartConfig
@WebServlet(name = "SendBannerServlet", urlPatterns = "/sendbanner")
public class SendBannerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置字符集
        request.setCharacterEncoding("utf-8");
        //获取文件上传文件
        Part filePart = request.getPart("file");
        //获取文件上传信息
        String info = filePart.getHeader("content-disposition");
        //获取文件后缀名
        String suffix = info.substring(info.lastIndexOf("."),info.length()-1);
        //获得唯一文件名
        String fileName = UUID.randomUUID()+suffix;
        //得到Tomcat管辖images路径
        String path = request.getServletContext().getRealPath("images/");
        //保存文件
        filePart.write(path+fileName);
        //保存轮播图路径到是数据库中
        Banner banner = new Banner(0,"images/"+fileName);
        BannerDao dao = new BannerDao();
        dao.insert(banner);
        response.sendRedirect("/showbanner");
    }
}
