/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dao.CarrinhoDao;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author User
 */
public class CarrinhoBean implements CarrinhoBeanRemote{
    private final CarrinhoDao carrinhoDao;

    public CarrinhoBean() throws Exception {
        this.carrinhoDao = new CarrinhoDao();
    }

    @Override
    public String cadastraCarrino(int ccliente){
        try {
            return carrinhoDao.save(ccliente);
        } catch (Exception ex) {
            Logger.getLogger(CarrinhoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "Não foi possivel efetuar o cadastro, contatar suporte!";
    }
    
    @Override
    public boolean possuiCarrinho(int ccliente) throws SQLException{
        return carrinhoDao.possuiCarrinho(ccliente);
    }
    
    @Override
    public int retornaCodCarrinho(int ccliente) throws SQLException{
        return carrinhoDao.retornaCodCarrinho(ccliente);
    }
}
