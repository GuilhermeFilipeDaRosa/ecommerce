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
import model.PedidoItens;
import util.ConnectionUtil;

/**
 *
 * @author User
 */
public class PedidoItensDao {

    Connection connection;

    public PedidoItensDao() throws Exception {
        connection = ConnectionUtil.getConnection();
    }

    public PedidoItens fiendById(int id) throws Exception {
        try {
            PedidoItens pedidoitens = new PedidoItens();
            PreparedStatement p = connection.prepareStatement("SELECT * FROM PEDIDOITENS WHERE CPEDIDOITENS=?");
            p.setInt(1, pedidoitens.getCpedidoitens());
            ResultSet rs = p.executeQuery();
            if (rs.next()) {
                pedidoitens.setCpedidoitens(rs.getInt("CPEDIDOITENS"));
                pedidoitens.setCproduto(rs.getInt("CPRODUTO"));
                pedidoitens.setCpedido(rs.getInt("CPEDIDO"));
                pedidoitens.setQtde(rs.getInt("QTDE"));
            }
            return pedidoitens;
        } catch (SQLException ex) {
            throw new Exception("Erro ao processar consulta! Contatar Suporte.", ex);
        }
    }

    public boolean save(int cpedidoitens, int cproduto, int cpedido, int qtde) throws Exception {

        String SQL = "INSERT INTO PEDIDOITENS(CPEDIDOITENS, CPRODUTO, CPEDIDO, QTDE) "
                + " VALUES(?, ?, ?, ?)";
        try {
            PreparedStatement p = connection.prepareStatement(SQL);
            p.setInt(1, cpedidoitens);
            p.setInt(2, cproduto);
            p.setInt(3, cpedido);
            p.setInt(4, qtde);
            p.execute();
        } catch (SQLException ex) {
            throw new Exception(ex);
        }
        return true;
    }

    public boolean delete(int cpedidoitens) throws SQLException {
        String SQL = "DELETE * FROM PEDIDOITENS WHERE CPEDIDOITENS = ?";
        PreparedStatement p = connection.prepareStatement(SQL);
        p.setInt(1, cpedidoitens);
        p.execute();
        return true;
    }

//    public boolean update(PedidoItens pedidoItens) throws Exception {
//        if (pedidoitens != null && !pedidoitens.equals("")) {
//            String SQL = "UPDATE PEDIDOITENS SET CPRODUTO=?, CPEDIDO=?, QTDE=? WHERE CPEDIDOITENS=?";
//            PreparedStatement p = connection.prepareStatement(SQL);
//            p.setInt(1, pedidoitens.getCpedidoitens());
//            p.setInt(2, pedidoitens.getCproduto());
//            p.setInt(3, pedidoitens.getCpedido());
//            p.setInt(4, pedidoitens.getQtde());
//            p.execute();
//            return true;
//        }
//        return false;
//    }
}

