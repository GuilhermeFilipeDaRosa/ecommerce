/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import util.ConnectionUtil;

/**
 *
 * @author User
 */
public class CompraItensDao {
    Connection connection;

    public CompraItensDao() throws Exception {
        connection = ConnectionUtil.getConnection();
    }
        
    public String salvaItensCompra(int cproduto, int ccompra, int qtde, double valorUnitario) throws SQLException {
            String SQL = "INSERT INTO COMPRAITENS(CPRODUTO, CCOMPRA, QTDE, VALORUNITARIO, TOTAL)"
                    + " VALUES(?, ?, ?, ?, ?)";
            PreparedStatement p = connection.prepareStatement(SQL);
            p.setInt(1, cproduto);
            p.setInt(2, ccompra);
            p.setInt(3, qtde);
            p.setDouble(4, valorUnitario);
            p.setDouble(5, (valorUnitario * qtde));
            p.execute();
        return "Compra efetuada com sucesso.";
    }
    
    public void atualizaValorProduto(int qtde, int cproduto) throws SQLException{
        String SQL = "UPDATE PRODUTO SET QTDE=? WHERE CPRODUTO=?";
            PreparedStatement p = connection.prepareStatement(SQL);
            p.setInt(1, qtde);
            p.setInt(2, cproduto);
            p.execute();
    }
}
