package controller;

import dao.NhanVienDao;
import dao.PhongBanDao;
import model.NhanVien;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;

@WebServlet(urlPatterns = "/nhanvien")
public class NhanVienServlet extends HttpServlet {
    NhanVienDao nhanVienDao = new NhanVienDao();
    PhongBanDao phongBanDao = new PhongBanDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        RequestDispatcher dispatcher = null;
        switch (action) {
            case "create":
                req.setAttribute("phongBan", phongBanDao.getAll());
                dispatcher = req.getRequestDispatcher("/create.jsp");
                dispatcher.forward(req, resp);
                break;
            case "search":
                String search = req.getParameter("search");
                req.setAttribute("nhanvien", nhanVienDao.getAllByName(search));
                dispatcher = req.getRequestDispatcher("/home.jsp");
                dispatcher.forward(req, resp);
                break;
            case "edit":
                showEditForm(req,resp);
                break;
            case "delete":
                showDeleteForm(req, resp);
                break;
            default:
                req.setAttribute("nhanvien", nhanVienDao.getAll());
                dispatcher = req.getRequestDispatcher("/home.jsp");
                dispatcher.forward(req, resp);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        RequestDispatcher dispatcher = null;
        switch (action) {
            case "create":
                int id = Integer.parseInt(request.getParameter("id"));
                String name = request.getParameter("name");
                Date birth = Date.valueOf(request.getParameter("birth"));
                String address = request.getParameter("address");
                String phone = request.getParameter("phone");
                String email = request.getParameter("email");
                int idpb = Integer.parseInt(request.getParameter("phongban"));

                NhanVien st = new NhanVien(id, name, birth, address, phone, email, phongBanDao.findById(idpb));
                nhanVienDao.create(st);
                resp.sendRedirect("/student");
                break;
            case "edit":
                updateNhanVien(request, resp);
                break;
            case "delete":
                deleteStaff(request, resp);
                break;


        }
    }
    private void showDeleteForm(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        NhanVien nhanVien = nhanVienDao.findById(id);
        RequestDispatcher dispatcher;
        if (nhanVien == null) {
            dispatcher = request.getRequestDispatcher("error-404.jsp");
        } else {
            request.setAttribute("nhanvien", nhanVien);
            dispatcher = request.getRequestDispatcher("/delete.jsp");
        }
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void showEditForm(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        NhanVien nhanVien =nhanVienDao.findById(id);
        RequestDispatcher dispatcher;
        if (nhanVien == null) {
            dispatcher = request.getRequestDispatcher("error-404.jsp");
        } else {
            request.setAttribute("nhanvien", nhanVien);
            dispatcher = request.getRequestDispatcher("/edit.jsp");
        }
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void updateNhanVien(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        Date birth = Date.valueOf(request.getParameter("birth"));
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        int idpb = Integer.parseInt(request.getParameter("phongban"));
        NhanVien staff = new NhanVien(id, name, birth, address, phone, email, phongBanDao.findById(idpb));
        RequestDispatcher dispatcher;
        if (staff == null) {
            dispatcher = request.getRequestDispatcher("error-404.jsp");
        } else {

            nhanVienDao.edit(id,staff);
            request.setAttribute("staff", staff);
            request.setAttribute("message", "Staff information was updated");
            dispatcher = request.getRequestDispatcher("view/edit.jsp");
        }
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void deleteStaff(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        NhanVien nhanVien= nhanVienDao.findById(id);
        RequestDispatcher dispatcher;
        if (nhanVien == null) {
            dispatcher = request.getRequestDispatcher("error-404.jsp");
        } else {
            nhanVienDao.delete(id);
            try {
                response.sendRedirect("/staff");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
