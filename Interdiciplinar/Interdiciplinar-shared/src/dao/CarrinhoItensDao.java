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
import java.util.ArrayList;
import java.util.List;
import model.CarrinhoItens;
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
            p.setInt(2, cproduto);
            p.execute();
            return "Produto Salvo no carrinho com sucesso.";
        }
        return "Produto já foi adicionado ao carrinho.";
    }

    public boolean isProdInCarrinho(int ccarrinho, int cproduto) throws SQLException {
        String SQL = "SELECT * "
                + " FROM CARRINHOITENS"
                + " WHERE CARRINHOITENS.CCARRINHO = ?"
                + " AND CARRINHOITENS.CPRODUTO = ?";
        PreparedStatement p = connection.prepareStatement(SQL);
        p.setInt(1, ccarrinho);
        p.setInt(2, cproduto);
        ResultSet rs = p.executeQuery();
        if (rs.next()) {
            return true;
        }
        return false;
    }
    
    public List<CarrinhoItens> getListaCarrinhoItens() throws Exception {
        List<CarrinhoItens> list = new ArrayList<>();
        CarrinhoItens objeto;
        String SQL = " SELECT * "
                + " FROM CARRINHOITENS "
                + " WHERE CARRINHOITENS.CCARRINO = ?";
        try {
            PreparedStatement p = connection.prepareStatement(SQL);
            
            ResultSet rs = p.executeQuery();
            while (rs.next()) {
                objeto = new CarrinhoItens();
                objeto.setCcarrinho(rs.getInt("CCARRINHO"));
                objeto.setCcarrinhoItens(rs.getInt("CCARRINHOITENS"));
                objeto.setCproduto(rs.getInt("CPRODUTO"));
                
                list.add(objeto);
            }
            rs.close();
            p.close();
        } catch (SQLException ex) {
            throw new Exception(ex);
        }
        
        return list;
    }
}
