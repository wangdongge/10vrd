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

@WebServlet(name = "LoadMoreServlet", urlPatterns = "/loadmore")
public class LoadMoreServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String count = request.getParameter("count");
        System.out.println("数量"+count);
        ProductDao dao = new ProductDao();
        List<Product> list = dao.loadMore(count);
        ObjectMapper om = new ObjectMapper();
        String jsonStr = om.writeValueAsString(list);
        response.setContentType("text/html;charset=utf-8");
        PrintWriter pw = response.getWriter() ;
        pw.print(jsonStr);
        pw.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
