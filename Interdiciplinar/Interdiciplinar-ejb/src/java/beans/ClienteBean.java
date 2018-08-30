/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dao.ClienteDao;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import model.Cliente;

@Stateless
public class ClienteBean implements CadastroClienteBeanRemote, ClienteBeanLocal {

    private final ClienteDao clienteDao;

    public ClienteBean() throws Exception {
        this.clienteDao = new ClienteDao();
    }

    @Override
    public String cadastraCliente(int numero, String nome, String data, String cpf, String cep, String endereco, 
            String complemento, String bairro, String estado, 
                    String cidade, String telefone, String email, String senha){
        try {
            return clienteDao.save(numero, nome, data, cpf, cep, endereco, 
             complemento, bairro, estado, cidade, telefone, email, senha);
        } catch (Exception ex) {
            Logger.getLogger(ClienteBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "Não foi possivel efetuar o cadastro, contatar suporte!";
    }
    public boolean deletarCliente(int ccliente){
        try{
            return clienteDao.delete(ccliente);
        }catch(Exception ex){
            Logger.getLogger(ClienteBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    public boolean updateCliente(Cliente cliente){
        try{
            return clienteDao.update(cliente);
        }catch(Exception ex){
            Logger.getLogger(ClienteBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    public Cliente fiendById(int ccliente){
        try{
            return clienteDao.fiendById(ccliente);
        }catch(Exception ex){
            Logger.getLogger(ClienteBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
