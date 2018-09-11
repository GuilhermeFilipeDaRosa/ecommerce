/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import beans.ProdutoBeanRemote;
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

/**
 *
 * @author User
 */
public class ClassificacaoServlet extends HttpServlet { 
    @EJB
    private ProdutoBeanRemote bean;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("application/json");
        PrintWriter saida = response.getWriter();

        String descricao, imagem, content;
        int marca, categoria, qtde;
        double precoUnitario;

        BufferedReader leitor = new BufferedReader(
                new InputStreamReader(request.getInputStream(), "UTF-8"));
        
        content = leitor.lines().collect(Collectors.joining());

        JsonReader reader = Json.createReader(new StringReader(content));
        JsonObject form = reader.readObject();

        marca = form.getJsonNumber("marca").intValue();
        categoria = form.getJsonNumber("categoria").intValue();
        descricao = form.getJsonString("descricao").getString();
        precoUnitario = form.getJsonNumber("precoUnitario").doubleValue();
        qtde = form.getJsonNumber("qtde").intValue();
        imagem = form.getJsonString("imagem").getString();

        String retorno;

        retorno = bean.cadastraProduto(marca, categoria, descricao, precoUnitario, qtde, imagem);

        JsonObject json = Json.createObjectBuilder()
                .add("mensagem", retorno)
                .build();

        saida.write(json.toString());
    }
}
