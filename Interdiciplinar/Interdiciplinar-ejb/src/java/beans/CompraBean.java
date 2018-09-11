/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dao.CompraDao;
import java.sql.SQLException;
import java.text.ParseException;
import javax.ejb.Stateless;

/**
 *
 * @author User
 */
@Stateless
public class CompraBean implements CompraBeanRemote{
    
    private final CompraDao compraDao;

    public CompraBean() throws Exception {
        this.compraDao = new CompraDao();
    }
    
    @Override
    public boolean efetuaCompra(int ccliente) throws SQLException, ParseException{
        return compraDao.efetuaCompra(ccliente);
    }
    
    @Override
    public int retornaCCompra(int ccliente) throws SQLException{
        return compraDao.retornaCCompra(ccliente);
    }
}
