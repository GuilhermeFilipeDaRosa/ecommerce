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
import java.util.Date;
import model.Pedido;
import util.ConnectionUtil;

/**
 *
 * @author User
 */
public class PedidoDao {

    Connection connection;

    public PedidoDao() throws Exception {
        connection = ConnectionUtil.getConnection();
    }

    public Pedido fiendById(int id) throws Exception {
        try {
            Pedido pedido = new Pedido();
            PreparedStatement p = connection.prepareStatement("SELECT * FROM PEDIDO WHERE CPEDIDO=?");
            p.setInt(1, pedido.getCpedido());
            ResultSet rs = p.executeQuery();
            if (rs.next()) {
                pedido.setCpedido(rs.getInt("CPEDIDO"));
                pedido.setData(rs.getString("DATA"));
                pedido.setTotal(rs.getDouble("TOTAL"));
                pedido.setCcliente(rs.getInt("CCLIENTE"));
            }
            return pedido;
        } catch (SQLException ex) {
            throw new Exception("Erro ao processar consulta! Contatar Suporte.", ex);
        }
    }

    public boolean save(int cpedido, Date data, double total, int ccliente) throws Exception {

        String SQL = "INSERT INTO PEDIDO(CPEDIDO, DATA, TOTAL, CCLIENTE) "
                + " VALUES(?, ?, ?, ?)";
        try {
            PreparedStatement p = connection.prepareStatement(SQL);
            p.setInt(1, cpedido);
            //p.setString(2, new java.util.Date(data.getTime()));
            p.setDouble(3, total);
            p.setInt(4, ccliente);
            p.execute();
        } catch (SQLException ex) {
            throw new Exception(ex);
        }
        return true;
    }

    public boolean delete(int cpedido) throws SQLException {
        String SQL = "DELETE * FROM PEDIDO WHERE CPEDIDO = ?";
        PreparedStatement p = connection.prepareStatement(SQL);
        p.setInt(1, cpedido);
        p.execute();
        return true;
    }

    public boolean update(Pedido pedido) throws Exception {
        if (pedido != null && !pedido.equals("")) {
            String SQL = "UPDATE PEDIDO SET CPEDIDO=?, DATA=?, TOTAL=? WHERE CCLIENTE=?";
            PreparedStatement p = connection.prepareStatement(SQL);
            p.setInt(1, pedido.getCpedido());
            p.setString(2, pedido.getData());
            p.setDouble(3, pedido.getTotal());
            p.setInt(4, pedido.getCcliente());
            p.execute();
            return true;
        }
        return false;
    }
}
