package com.loja.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.loja.dao.LojaDAO;
import com.loja.model.Loja;

@WebServlet("/")
public class LojaServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private LojaDAO lojaDAO;

    public void init() {
        lojaDAO = new LojaDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();

        try {
            switch (action) {
                case "/nova":
                    showNewForm(request, response);
                    break;
                case "/inserir":
                    insertLoja(request, response);
                    break;
                case "/deletar":
                    deleteLoja(request, response);
                    break;
                case "/editar":
                    showEditForm(request, response);
                    break;
                case "/atualizar":
                    updateLoja(request, response);
                    break;
                case "/lucro":
                    calcularLucro(request, response);
                    break;
                default:
                    listLojas(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void listLojas(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        List<Loja> listLojas = lojaDAO.selectAllLojas();
        request.setAttribute("listLojas", listLojas);
        RequestDispatcher dispatcher = request.getRequestDispatcher("listar.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("loja-form.jsp");
        dispatcher.forward(request, response);
    }

    private void insertLoja(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        String nome = request.getParameter("nome");
        int quantidadeItens = Integer.parseInt(request.getParameter("quantidadeItens"));
        double precoMedio = Double.parseDouble(request.getParameter("precoMedio"));
        Loja newLoja = new Loja(0, nome, quantidadeItens, precoMedio);
        lojaDAO.insertLoja(newLoja);
        response.sendRedirect("listar");
    }

    private void deleteLoja(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        lojaDAO.deleteLoja(id);
        response.sendRedirect("listar");
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Loja existingLoja = lojaDAO.selectLoja(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("loja-form.jsp");
        request.setAttribute("loja", existingLoja);
        dispatcher.forward(request, response);
    }

    private void updateLoja(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String nome = request.getParameter("nome");
        int quantidadeItens = Integer.parseInt(request.getParameter("quantidadeItens"));
        double precoMedio = Double.parseDouble(request.getParameter("precoMedio"));

        Loja loja = new Loja(id, nome, quantidadeItens, precoMedio);
        lojaDAO.updateLoja(loja);
        response.sendRedirect("listar");
    }

    private void calcularLucro(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        Loja loja = lojaDAO.selectLoja(id);
        double lucro = loja.calcularLucro();
        request.setAttribute("lucro", lucro);
        request.setAttribute("loja", loja);
        RequestDispatcher dispatcher = request.getRequestDispatcher("mostrar-lucro.jsp");
        dispatcher.forward(request, response);
    }
}
