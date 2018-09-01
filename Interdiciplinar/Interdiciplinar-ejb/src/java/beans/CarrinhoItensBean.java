/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dao.CarrinhoItensDao;
import java.sql.SQLException;

/**
 *
 * @author User
 */
public class CarrinhoItensBean {
   
    private final CarrinhoItensDao carrinhoItensDao;

    public CarrinhoItensBean() throws Exception {
        this.carrinhoItensDao = new CarrinhoItensDao();
    }
    
    public String salvaProdutoCarrinho(int ccarinho, int cproduto) throws SQLException{
        return carrinhoItensDao.salvaProdutoCarrinho(ccarinho, cproduto);
    }
}
