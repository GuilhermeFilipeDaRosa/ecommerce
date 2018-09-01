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
import util.ConnectionUtil;

/**
 *
 * @author User
 */
public class CarrinhoItensDao {

    Connection connection;

    public CarrinhoItensDao() throws Exception {
        connection = ConnectionUtil.getConnection();
    }

    public String salvaProdutoCarrinho(int ccarrinho, int cproduto) throws SQLException {
        if (!isProdInCarrinho(ccarrinho, cproduto)) {
            String SQL = "INSERT INTO CARRINHOITENS(CCARRINHO, CPRODUTO) "
                    + " VALUES(?, ?)";
            PreparedStatement p = connection.prepareStatement(SQL);
            p.setInt(1, ccarrinho);
            p.setInt(1, cproduto);
            p.execute();
            return "Produto Salvo no carrinho com sucesso.";
        }
        return "Produto já foi adicionado ao carrinho.";
    }

    public boolean isProdInCarrinho(int ccarrinho, int cproduto) throws SQLException {
        String SQL = "SELECT * "
                + " FROM CARRINHO"
                + " WHERE CARRINHO.CCARINHO = ?"
                + " AND CARRINHO.CPRODUTO = ?";
        PreparedStatement p = connection.prepareStatement(SQL);
        p.setInt(1, ccarrinho);
        p.setInt(1, cproduto);
        ResultSet rs = p.executeQuery();
        if (rs.next()) {
            return true;
        }
        return false;
    }
}
