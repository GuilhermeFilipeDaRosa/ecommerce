/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.Produto;
import util.ConnectionUtil;

/**
 *
 * @author User
 */
public class ProdutoDao {

    Connection connection;

    public ProdutoDao() throws Exception {
        connection = ConnectionUtil.getConnection();
    }
    
    public List<Produto>getListaSearch(String pesquisa) throws Exception{
        List<Produto> list = new ArrayList<>();
        Produto objeto;
        String SQL = " SELECT FIRST 12 * "
                + " FROM PRODUTO "
                + " WHERE PRODUTO.QTDE > 0"
                + " AND LOWER(PRODUTO.DESCRICAO) LIKE LOWER('%?%')";
        try {
            PreparedStatement p = connection.prepareStatement(SQL);
            p.setString(1, pesquisa);
            ResultSet rs = p.executeQuery();
            while (rs.next()) {
                objeto = new Produto();
                objeto.setCproduto(rs.getInt("CPRODUTO"));
                objeto.setCcategoria(rs.getInt("CCATEGORIA"));
                objeto.setCmarca(rs.getInt("CMARCA"));
                objeto.setDescricao(rs.getString("DESCRICAO"));
                objeto.setPreco_unitario(rs.getDouble("PRECO_UNITARIO"));
                objeto.setQtde(rs.getInt("QTDE"));
                objeto.setImagem(rs.getString("IMAGEM"));
                objeto.setDataCadastro("DATA_CADASTRO");
                
                list.add(objeto);
            }
            rs.close();
            p.close();
        } catch (SQLException ex) {
            throw new Exception(ex);
        }
        return list;
    }
    
    public List<Produto> getListaProdutos() throws Exception {
        List<Produto> list = new ArrayList<>();
        Produto objeto;
        String SQL = " SELECT FIRST 12 * "
                + " FROM PRODUTO "
                + " WHERE PRODUTO.QTDE > 0"
                + " ORDER BY CPRODUTO DESC";
        try {
            PreparedStatement p = connection.prepareStatement(SQL);
            ResultSet rs = p.executeQuery();
            while (rs.next()) {
                objeto = new Produto();
                objeto.setCproduto(rs.getInt("CPRODUTO"));
                objeto.setCcategoria(rs.getInt("CCATEGORIA"));
                objeto.setCmarca(rs.getInt("CMARCA"));
                objeto.setDescricao(rs.getString("DESCRICAO"));
                objeto.setPreco_unitario(rs.getDouble("PRECO_UNITARIO"));
                objeto.setQtde(rs.getInt("QTDE"));
                objeto.setImagem(rs.getString("IMAGEM"));
                objeto.setDataCadastro("DATA_CADASTRO");
                
                list.add(objeto);
            }
            rs.close();
            p.close();
        } catch (SQLException ex) {
            throw new Exception(ex);
        }
        return list;
    }

    public String save(int marca, int categoria, String descricao, double precoUnitario, int qtde, String imagem) throws Exception {

        String SQL = "INSERT INTO PRODUTO(CMARCA, CCATEGORIA, DESCRICAO, PRECO_UNITARIO, QTDE, IMAGEM, DATA_CADASTRO) "
                + " VALUES(?, ?, ?, ?,?,?,?)";
        try {
            PreparedStatement p = connection.prepareStatement(SQL);
            p.setInt(1, marca);
            p.setInt(2, categoria);
            p.setString(3, descricao);
            p.setDouble(4, precoUnitario);
            p.setInt(5, qtde);
            p.setString(6, imagem);
            java.util.Date d = new java.util.Date();
            java.sql.Date dt = new java.sql.Date (d.getTime());
            p.setDate(7, dt);
            p.execute();
        } catch (SQLException ex) {
            throw new Exception(ex);
        }
        return "Produto cadastrado com sucesso.";
    }
}
