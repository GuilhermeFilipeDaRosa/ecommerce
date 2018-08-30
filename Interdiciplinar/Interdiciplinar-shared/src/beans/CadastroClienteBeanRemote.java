/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;


import exceptions.AppException;
import javax.ejb.Remote;
/**
 *
 * @author User
 */
@Remote
public interface CadastroClienteBeanRemote {
    public String cadastraCliente(int numero, String nome, String data, String cpf, String cep, String endereco, 
            String complemento, String bairro, String estado, 
                    String cidade, String telefone, String email, String senha)throws AppException;
}
