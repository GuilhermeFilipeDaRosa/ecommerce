/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.sql.SQLException;
import java.text.ParseException;
import javax.ejb.Remote;

/**
 *
 * @author User
 */
@Remote
public interface CompraBeanRemote{
    public boolean efetuaCompra(int ccliente) throws SQLException, ParseException;
    public int retornaCCompra(int ccliente) throws SQLException;
}
