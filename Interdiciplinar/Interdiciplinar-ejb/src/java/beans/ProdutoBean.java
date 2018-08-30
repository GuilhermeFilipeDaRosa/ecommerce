/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dao.ProdutoDao;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import model.Produto;

/**
 *
 * @author User
 */
@Stateless
public class ProdutoBean implements CadastraConsultaProdutoBeanRemote, ProdutoBeanLocal {

    private final ProdutoDao produtoDao;

    public ProdutoBean() throws Exception {
        this.produtoDao = new ProdutoDao();
    }

    @Override
    public String cadastraProduto(int marca, int categoria, String descricao, double precoUnitario, int qtde,
            String imagem){
        try {
            return produtoDao.save(marca, categoria, descricao, precoUnitario, qtde, imagem);
        } catch (Exception ex) {
            Logger.getLogger(beans.ProdutoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "Não foi possivel efetuar o cadastro, contatar suporte!";
    }
    
    @Override
    public List<Produto> getListaProdutos() throws Exception {
        return produtoDao.getListaProdutos();
    }
    
    public boolean deletarProduto(int cproduto){
        try{
            return produtoDao.delete(cproduto);
        }catch(Exception ex){
            Logger.getLogger(beans.ProdutoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    public Produto fiendById(int cproduto){
        try{
            return produtoDao.fiendById(cproduto);
        }catch(Exception ex){
            Logger.getLogger(beans.ProdutoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
