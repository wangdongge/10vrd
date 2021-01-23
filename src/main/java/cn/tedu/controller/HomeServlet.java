package cn.tedu.controller;

import cn.tedu.dao.BannerDao;
import cn.tedu.dao.CategoryDao;
import cn.tedu.dao.ProductDao;
import cn.tedu.entity.Banner;
import cn.tedu.entity.Category;
import cn.tedu.entity.Product;
import cn.tedu.entity.User;
import cn.tedu.utils.ThUtils;
import org.thymeleaf.context.Context;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "HomeServlet", urlPatterns = "/home")
public class HomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取传递过来的分类id
        String cid = request.getParameter("cid");

        //获取传递过来的搜索keyword
        String keyword = request.getParameter("keyword");

        //显示首页页面
        Context context = new Context();

        CategoryDao dao = new CategoryDao();
        List<Category> list = dao.findAll();
        context.setVariable("list",list);

        BannerDao dao1 = new BannerDao();
        List<Banner> bList = dao1.findAll();
        context.setVariable("bList",bList);

        //获取session对象
        HttpSession session = request.getSession();
        //取出保存的用户对象
        User user = (User) session.getAttribute("user");
//        if (user==null){
//            System.out.println("从来没登录过");
//        }else {
//            System.out.println("已经登录过");
//        }
        //把用户对象装进容器
        context.setVariable("user",user);

        //查询出所有作品
        ProductDao pDao = new ProductDao();
        if (cid!=null) {//说明查询的是某个分类的作品
            //cid有值说明需要查询某个分类
            List<Product> pList = pDao.findByCID(cid);
            context.setVariable("pList", pList);
        }else if (keyword!=null){//
            //判断
            List<Product> pList = pDao.findByKeyword(keyword);
            context.setVariable("pList",pList);
        }else {//查询所有作品
            List<Product> pList = pDao.findall();
            context.setVariable("pList",pList);
        }





        //查询浏览最多
        List<Product> vList = pDao.findViewList();
        context.setVariable("vList",vList);
        //查询最受欢迎
        List<Product> lList = pDao.findLikeList();
        context.setVariable("lList",lList);


        ThUtils.print("home.html",context,response);


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
