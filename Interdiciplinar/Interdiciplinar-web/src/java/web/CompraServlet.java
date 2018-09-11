/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import beans.CompraBeanRemote;
import beans.CompraItensBeanRemote;
import beans.ProdutoBeanRemote;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringReader;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class CompraServlet extends HttpServlet {
    
    @EJB
    private CompraBeanRemote beanCompra;
    @EJB
    private CompraItensBeanRemote beanCompraItens;
    @EJB
    private ProdutoBeanRemote beanProduto;
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        PrintWriter saida = response.getWriter();
        JsonObject json;
        String retorno = "Não foi possivel efetuar a compra";
        String content;
        int ccliente, cproduto, qtde;
        
        BufferedReader leitor = new BufferedReader(
                new InputStreamReader(request.getInputStream(), "UTF-8"));

        content = leitor.lines().collect(Collectors.joining());

        JsonReader reader = Json.createReader(new StringReader(content));
        JsonObject dados = reader.readObject();
        
        ccliente = dados.getJsonNumber("ccliente").intValue();
        cproduto = dados.getJsonNumber("cproduto").intValue();
        qtde = dados.getJsonNumber("qtde").intValue();

        try {
            if(beanCompra.efetuaCompra(ccliente)){
                retorno = beanCompraItens.salvaItensCompra(cproduto, beanCompra.retornaCCompra(ccliente), qtde, beanProduto.retornaValorUnitario(cproduto));
            }
        } catch (Exception ex) {
            Logger.getLogger(ProdutoServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            json = Json.createObjectBuilder()
                    .add("mensagem",retorno).build();
        } catch (Exception ex) {
            Logger.getLogger(ProdutoServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        saida.write(retorno.toString());
    }
}
