/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dao.PedidoItensDao;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import model.PedidoItens;

@Stateless
public class PedidoItemBean {//implements CadastroPedidoBeanRemote, PedidoBeanLocal {

    private final PedidoItensDao pedidoItemDao;

    public PedidoItemBean() throws Exception {
        this.pedidoItemDao = new PedidoItensDao();
    }

    //@Override
    public boolean cadastraPedidoItem(int cpedidoItens, int qtde, int cpedido, int cproduto){
        try {
            return pedidoItemDao.save(cpedidoItens, qtde, cpedido, cproduto);
        } catch (Exception ex) {
            Logger.getLogger(PedidoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    public boolean deletarPedidoItem(int cpedidoItem){
        try{
            return pedidoItemDao.delete(cpedidoItem);
        }catch(Exception ex){
            Logger.getLogger(PedidoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    public boolean updatePedido(PedidoItens pedidoItens){
        try{
            //return pedidoItemDao.update(pedidoItens);
        }catch(Exception ex){
            Logger.getLogger(PedidoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    public PedidoItens fiendById(int cpedidoItem){
        try{
            return pedidoItemDao.fiendById(cpedidoItem);
        }catch(Exception ex){
            Logger.getLogger(PedidoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
