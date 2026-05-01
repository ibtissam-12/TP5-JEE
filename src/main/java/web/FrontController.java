package web;

import service.*;
import model.Produit;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class FrontController extends HttpServlet {

    private ProduitMetier metier = new ProduitMetierImpl();

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String action = req.getParameter("action");

        if (action == null || action.equals("list")) {
            req.setAttribute("listeProduits", metier.getAllProduits());
            req.getRequestDispatcher("index.jsp").forward(req, resp);
        }

        else if (action.equals("delete")) {
            Long id = Long.parseLong(req.getParameter("id"));
            metier.deleteProduit(id);
            resp.sendRedirect("controller?action=list");
        }

        else if (action.equals("edit")) {
            Long id = Long.parseLong(req.getParameter("id"));
            req.setAttribute("produitEdit", metier.getProduitById(id));
            req.setAttribute("listeProduits", metier.getAllProduits());
            req.getRequestDispatcher("index.jsp").forward(req, resp);
        }
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String action = req.getParameter("action");

        String nom = req.getParameter("nom");
        String desc = req.getParameter("description");
        Double prix = Double.parseDouble(req.getParameter("prix"));

        if (action.equals("add")) {
            metier.addProduit(new Produit(nom, desc, prix));
        }

        else if (action.equals("update")) {
            Long id = Long.parseLong(req.getParameter("idProduit"));
            Produit p = new Produit();
            p.setIdProduit(id);
            p.setNom(nom);
            p.setDescription(desc);
            p.setPrix(prix);
            metier.updateProduit(p);
        }

        resp.sendRedirect("controller?action=list");
    }
}