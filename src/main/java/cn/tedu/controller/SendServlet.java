package cn.tedu.controller;

import cn.tedu.dao.ProductDao;
import cn.tedu.entity.Product;
import org.thymeleaf.context.Context;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@MultipartConfig
@WebServlet(name = "SendServlet", urlPatterns = "/send")
public class SendServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String title = request.getParameter("title");
        String author = request.getParameter("author");
        String intro = request.getParameter("intro");
        String categoryId = request.getParameter("categoryId");
        System.out.println(title);
        System.out.println(author);
        System.out.println(intro);
        System.out.println(categoryId);
        //获取文件上传信息
        Part filePart = request.getPart("file");
        //获取文件上传信息
        String info = filePart.getHeader("content-disposition");
        //获取文件后缀名
        String suffix = info.substring(info.lastIndexOf("."),info.length()-1);
        System.out.println(suffix);
        //唯一的文件名
        String fileName = UUID.randomUUID()+suffix;
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd/");
        Date date = new Date();//得到当前的日期时间对象
        String datePath = format.format(date);
        System.out.println(datePath);
        //根据日期路径创建文件夹
        String path = request.getServletContext().getRealPath("images/"+datePath);
        new File(path).mkdirs();//一定要带s的方法
        //检查target目录下images里面是否出现2021文件夹
        //如果没有出现target上右键reload from disk 从磁盘重新加载
        filePart.write(path+fileName);
        //把参数封装到Product实体类中
        Product p = new Product(0,title,author,intro,"images/"+datePath+fileName,
                0,0,System.currentTimeMillis(),Integer.parseInt(categoryId));
        System.out.println(p);
        //创建Dao 并调用insert方法
        ProductDao dao = new ProductDao();
        dao.insert(p);
        //重定向到首页
        response.sendRedirect("/home");
    }
}
