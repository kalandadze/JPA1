package com.mziuri.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mziuri.objects.PhoneNumber;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/manageNumber")
public class PhoneBookManager extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String number = req.getParameter("number");
            String name = req.getParameter("name");
            PhoneNumber phoneNumber = new PhoneNumber();
            phoneNumber.setName(name);
            phoneNumber.setNumber(number);
            phoneNumber.save();
            resp.getWriter().write("");
        } catch (IllegalArgumentException e) {
            resp.sendError(402);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("mziuri");
        try (EntityManager manager = factory.createEntityManager()) {
            System.out.println("asdassd");
            Query query = manager.createNamedQuery("find number");
            List numbers = query.getResultList();

            ObjectMapper mapper = new ObjectMapper();
//            String jsonResponse = mapper.writeValueAsString(numbers);
            String response=numbers.toString().substring(1,numbers.toString().length()-1);
            System.out.println(response);
            resp.getWriter().write(response);
        } catch (Exception e) {
            e.printStackTrace();
            resp.sendError(402);
        }
        factory.close();
    }
}
