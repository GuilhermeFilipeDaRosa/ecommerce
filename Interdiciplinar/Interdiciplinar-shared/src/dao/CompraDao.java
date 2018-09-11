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
import java.text.ParseException;
import util.ConnectionUtil;

/**
 *
 * @author User
 */
public class CompraDao {

    Connection connection;

    public CompraDao() throws Exception {
        connection = ConnectionUtil.getConnection();
    }

    public boolean efetuaCompra(int ccliente) throws SQLException, ParseException {
        String SQL = "INSERT INTO COMPRA(CCLIENTE, DATA, STATUS) "
                + " VALUES(?, ?, ?)";
        PreparedStatement p = connection.prepareStatement(SQL);
        p.setInt(1, ccliente);
        java.util.Date d = new java.util.Date();
        java.sql.Date dt = new java.sql.Date(d.getTime());
        p.setDate(2, dt);
        p.setString(3, "N");
        p.execute();
        return true;
    }
    public int retornaCCompra(int ccliente) throws SQLException{
        String SQL = "SELECT FIRST 1 COMPRA.CCOMPRA"
                + "FROM COMPRA"
                + "WHERE COMPRA.CCLIENTE = ?"
                + "ORDER BY COMPRA.CCOMPRA DESC";
        PreparedStatement p = connection.prepareStatement(SQL);
        p.setInt(1, ccliente);
        ResultSet rs = p.executeQuery();
        if (rs.next()) {
            return rs.getInt("CCOMPRA");
        }
        return 0;
    }
}
