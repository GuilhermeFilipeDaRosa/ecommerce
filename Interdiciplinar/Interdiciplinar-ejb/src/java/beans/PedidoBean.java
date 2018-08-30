/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dao.PedidoDao;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import model.Pedido;

@Stateless
public class PedidoBean {//implements CadastroPedidoBeanRemote, PedidoBeanLocal {

    private final PedidoDao pedidoDao;

    public PedidoBean() throws Exception {
        this.pedidoDao = new PedidoDao();
    }

    //@Override
    public boolean cadastraPedido(int cpedido, Date data, double total, int ccliente){
        try {
            return pedidoDao.save(cpedido, data, total, cpedido);
        } catch (Exception ex) {
            Logger.getLogger(PedidoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    public boolean deletarPedido(int cpedido){
        try{
            return pedidoDao.delete(cpedido);
        }catch(Exception ex){
            Logger.getLogger(PedidoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    public boolean updatePedido(Pedido pedido){
        try{
            return pedidoDao.update(pedido);
        }catch(Exception ex){
            Logger.getLogger(PedidoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    public Pedido fiendById(int cpedido){
        try{
            return pedidoDao.fiendById(cpedido);
        }catch(Exception ex){
            Logger.getLogger(PedidoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
