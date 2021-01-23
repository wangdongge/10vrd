package cn.tedu.controller;

import cn.tedu.dao.ProductDao;
import cn.tedu.entity.Product;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "Json1Servlet", urlPatterns = "/json1")
public class Json1Servlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //服务器端只能给客户端返回一个json字符串
        ProductDao dao = new ProductDao();
        List<Product> list = dao.findall();
        //把集合转成json字符串
        ObjectMapper om = new ObjectMapper();
        //这个方法可以将任意字符串转换为json字符串
        String jsonStr = om.writeValueAsString(list);
        System.out.println(jsonStr);
        response.setContentType("text/html;charset=utf-8");
        PrintWriter pw = response.getWriter();
        pw.print(jsonStr);
        pw.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
