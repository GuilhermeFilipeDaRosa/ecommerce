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
import model.Compra;
import model.Produto;

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
        String retorno = "Não foi possivel efetuar a compra", content;
        Produto produto = new Produto();
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
            produto = beanProduto.retornaDadosProduto(cproduto);
            if (produto.getQtde() < qtde) {
                if (beanCompra.efetuaCompra(ccliente)) {
                    retorno = beanCompraItens.salvaItensCompra(cproduto, beanCompra.retornaCCompra(ccliente), qtde, beanProduto.retornaValorUnitario(cproduto));
                }
            }else{
               retorno = "O produto não possui quantidade suficiente.";
            }
        } catch (Exception ex) {
            Logger.getLogger(ProdutoServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            json = Json.createObjectBuilder()
                    .add("mensagem", retorno).build();
        } catch (Exception ex) {
            Logger.getLogger(ProdutoServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        saida.write(retorno.toString());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        PrintWriter saida = response.getWriter();
        JsonObject retorno = null, json;
        String dados = null;

        try {
            for (Compra compra : beanCompra.retornaComprasPendentes()) {
                if (dados != null) {
                    dados += ",";
                }
                json = Json.createObjectBuilder()
                        .add("ccompra", compra.getCcompra())
                        .add("ccliente", compra.getCcliente())
                        .add("data", compra.getData())
                        .add("status", compra.getStatus()).build();
                if (dados != null) {
                    dados += json.toString();
                } else {
                    dados = json.toString();
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(ProdutoServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            retorno = Json.createObjectBuilder()
                    .add("produtos", "[" + dados + "]").build();
        } catch (Exception ex) {
            Logger.getLogger(ProdutoServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        saida.write(retorno.toString());
    }
}
