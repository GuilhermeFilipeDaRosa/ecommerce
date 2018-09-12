/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import exceptions.AppException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringReader;
import java.util.stream.Collectors;
import javax.ejb.EJB;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import beans.ClienteBeanRemote;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Cliente;

/**
 *
 * @author User
 */
public class LoginServlet extends HttpServlet {
    
    @EJB
    private ClienteBeanRemote bean;
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("application/json");
        PrintWriter saida = response.getWriter();
        
        Cliente cliente = new Cliente();
        
        String email, senha, content;
        boolean retorno = false;

        BufferedReader leitor = new BufferedReader(
                new InputStreamReader(request.getInputStream(), "UTF-8"));
        
        content = leitor.lines().collect(Collectors.joining());

        JsonReader reader = Json.createReader(new StringReader(content));
        JsonObject form = reader.readObject();

        email = form.getJsonString("email").getString();
        senha = form.getJsonString("senha").getString();
        
        try {
            cliente = bean.logarCliente(email, senha);
        } catch (Exception ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        JsonObject json = Json.createObjectBuilder()
                .add("logou", cliente.isLogado())
                .add("usuario", cliente.getNome())
                .add("codCliente", cliente.getCcliente())
                .add("session", request.getSession().getId())
                .build();

        saida.write(json.toString());
    }
}
