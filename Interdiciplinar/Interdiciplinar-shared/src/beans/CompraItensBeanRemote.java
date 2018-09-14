/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.sql.SQLException;
import javax.ejb.Remote;

/**
 *
 * @author User
 */
@Remote
public interface CompraItensBeanRemote {
    public String salvaItensCompra(int cproduto, int ccompra, int qtde, double valorUnitario) throws SQLException;
    public void atualizaValorProduto(int qtde, int cproduto) throws SQLException;
}
