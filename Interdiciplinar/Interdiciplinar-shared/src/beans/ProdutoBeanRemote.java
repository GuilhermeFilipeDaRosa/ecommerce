/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.util.List;
import javax.ejb.Remote;
import model.Produto;

/**
 *
 * @author User
 */
@Remote
public interface ProdutoBeanRemote {
    public String cadastraProduto(int marca, int categoria, String descricao, double precoUnitario, int qtde, 
            String imagem);
    public List<Produto> getListaProdutos() throws Exception;
    public List<Produto> getListaSearch(String pesquisa) throws Exception;
}
