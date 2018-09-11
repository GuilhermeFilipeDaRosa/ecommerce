/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dao.CompraItensDao;
import java.sql.SQLException;
import javax.ejb.Stateless;

/**
 *
 * @author User
 */
@Stateless
public class CompraItensBean implements CompraItensBeanRemote{
    private final CompraItensDao compraItensDao;

    public CompraItensBean() throws Exception {
        this.compraItensDao = new CompraItensDao();
    }
    
    @Override
    public String salvaItensCompra(int cproduto, int ccompra, int qtde, double valorUnitario) throws SQLException {
        return compraItensDao.salvaItensCompra(cproduto, ccompra, qtde, valorUnitario);
    }
}
