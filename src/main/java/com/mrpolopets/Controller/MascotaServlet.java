package com.mrpolopets.Controller;

import com.google.gson.Gson; 
import com.mrpolopets.Dao.MascotaDao;
import com.mrpolopets.Implements.MascotaDAOImpl;
import com.mrpolopets.Model.MascotaDTO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/api/mascotas")
public class MascotaServlet extends HttpServlet {

    private MascotaDao mascotaDAO;

    @Override
    public void init() throws ServletException {
        // Inicializamos la implementación una sola vez
        this.mascotaDAO = new MascotaDAOImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. Configurar respuesta como JSON
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        // 2. Obtener datos de la BD
        List<MascotaDTO> listaMascotas = mascotaDAO.listarMascotas();

        // 3. Convertir Java -> JSON usando Gson
        Gson gson = new Gson();
        String json = gson.toJson(listaMascotas);

        // 4. Enviar respuesta
        PrintWriter out = resp.getWriter();
        out.print(json);
        out.flush();
    }
}