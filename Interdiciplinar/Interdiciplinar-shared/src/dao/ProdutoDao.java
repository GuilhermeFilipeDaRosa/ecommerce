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
    
    public List<Produto> getListaProdutos() throws Exception {
        // Lista para manter os valores do ResultSet
        List<Produto> list = new ArrayList<>();
        Produto objeto;
        String SQL = "SELECT FIRST 12 * FROM PRODUTO ORDER BY CPRODUTO DESC";
        try {
            PreparedStatement p = connection.prepareStatement(SQL);
            // 
            ResultSet rs = p.executeQuery();
            // Navega a classe e informa o valor do BD
            while (rs.next()) {
                // Instancia a classe e informa os valores do BD
                objeto = new Produto();
                objeto.setCproduto(rs.getInt("CPRODUTO"));
                objeto.setCcategoria(rs.getInt("CCATEGORIA"));
                objeto.setCmarca(rs.getInt("CMARCA"));
                objeto.setDescricao(rs.getString("DESCRICAO"));
                objeto.setPreco_unitario(rs.getDouble("PRECO_UNITARIO"));
                objeto.setQtde(rs.getInt("QTDE"));
                objeto.setImagem(rs.getString("IMAGEM"));
                objeto.setDataCadastro("DATA_CADASTRO");
                // Inclui na lista
                list.add(objeto);
            }
            rs.close();
            p.close();
        } catch (SQLException ex) {
            throw new Exception(ex);
        }
        // Retorna a lista
        return list;
    }

    public Produto fiendById(int id) throws Exception {
        try {
            Produto produto = new Produto();
            PreparedStatement p = connection.prepareStatement("SELECT * FROM PRODUTO WHERE CPRODUTO=?");
            p.setInt(1, produto.getCproduto());
            ResultSet rs = p.executeQuery();
            if (rs.next()) {
                produto.setCproduto(rs.getInt("CPRODUTO"));
                produto.setCmarca(rs.getInt("CMARCA"));
                produto.setCcategoria(rs.getInt("CCATEGORIA"));
                produto.setDescricao(rs.getString("DESCRICAO"));
                produto.setPreco_unitario(rs.getDouble("PRECO_UNITARIO"));
                produto.setQtde(rs.getInt("QTDE"));
            }
            return produto;
        } catch (SQLException ex) {
            throw new Exception("Erro ao processar consulta! Contatar Suporte.", ex);
        }
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

    public boolean delete(int cproduto) throws SQLException {
        String SQL = "DELETE * FROM PRODUTO WHERE PRODUTO.CPRODUTO = ?";
        PreparedStatement p = connection.prepareStatement(SQL);
        p.setInt(1, cproduto);
        p.execute();
        return true;
    }

    public boolean update(int cproduto) throws Exception {
        Produto produto = new Produto();
        produto = fiendById(cproduto);
        if (produto != null && !produto.equals("")) {
            String SQL = "UPDATE PRODUTO SET CMARCA=?, CCATEGORIA=?, DESCRICAO=?, PRECO_UNITARIO=?, QTDE=? WHERE CPRODUTO=?";
            PreparedStatement p = connection.prepareStatement(SQL);
            p.setInt(1, produto.getCmarca());
            p.setInt(2, produto.getCcategoria());
            p.setString(3, produto.getDescricao());
            p.setDouble(4, produto.getPreco_unitario());
            p.setInt(5, produto.getQtde());
            p.setInt(6, cproduto);
            return true;
        }
        return false;
    }
}
