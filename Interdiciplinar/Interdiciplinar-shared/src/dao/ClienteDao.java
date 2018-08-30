/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import model.Cliente;
import util.ConnectionUtil;

/**
 *
 * @author User
 */
public class ClienteDao {

    Connection connection;

    public ClienteDao() throws Exception {
        connection = ConnectionUtil.getConnection();
    }

    public Cliente fiendById(int ccliente) throws Exception {
        try {
            Cliente cliente = new Cliente();
            PreparedStatement p = connection.prepareStatement("SELECT * FROM CLIENTE WHERE CCLIENTE=?");
            p.setInt(1, cliente.getCcliente());
            ResultSet rs = p.executeQuery();
            if (rs.next()) {
                cliente.setCcliente(rs.getInt("CCLIENTE"));
                cliente.setNome(rs.getString("NOME"));
                cliente.setData_nascimento(rs.getString("DATA_NASCIMENTO"));
                cliente.setCpf(rs.getString("CPF"));
                cliente.setCep(rs.getString("CEP"));
                cliente.setEndereco(rs.getString("ENDERECO"));
                cliente.setNumero(rs.getInt("NUMERO"));
                cliente.setComplemento(rs.getString("COMPLEMENTO"));
                cliente.setBairro(rs.getString("BAIRRO"));
                cliente.setEstado(rs.getString("ESTADO"));
                cliente.setCidade(rs.getString("CIDADE"));
                cliente.setTelefone(rs.getString("TELEFONE"));
                cliente.setEmail(rs.getString("EMAIL"));
                cliente.setSenha(rs.getString("SENHA"));
            }
            return cliente;
        } catch (SQLException ex) {
            throw new Exception("Erro ao processar consulta! Contatar Suporte.", ex);
        }
    }

    public boolean verificaUsuario(String cpf) throws Exception {
        String SQL = "SELECT CLIENTE.NOME FROM CLIENTE WHERE CLIENTE.CPF = ?";
        try {
            Cliente cliente = new Cliente();
            PreparedStatement p = connection.prepareStatement(SQL);
            p.setString(1, cpf);
            ResultSet rs = p.executeQuery();
            if (rs.next()) {
                cliente.setNome(rs.getString("NOME"));
                return false;
            }
        } catch (SQLException ex) {
            throw new Exception(ex);
        }
        return true;
    }

    public String save(int numero, String nome, String data, String cpf, String cep, String endereco, 
            String complemento, String bairro, String estado, 
                    String cidade, String telefone, String email, String senha) throws Exception {

        String SQL = "INSERT INTO CLIENTE(NOME, DATA_NASCIMENTO, CPF, CEP, ENDERECO, NUMERO, COMPLEMENTO,"
                + " BAIRRO, ESTADO, CIDADE, TELEFONE, EMAIL, SENHA) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        if (verificaUsuario(cpf)) {
            try {
                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                Date date = format.parse(data);
                PreparedStatement p = connection.prepareStatement(SQL);
                p.setString(1, nome);
                p.setDate(2, new java.sql.Date (date.getTime()));
                p.setString(3, cpf);
                p.setString(4, cep);
                p.setString(5, endereco);
                p.setInt(6, numero);
                p.setString(7, complemento);
                p.setString(8, bairro);
                p.setString(9, estado);
                p.setString(10, cidade);
                p.setString(11, telefone);
                p.setString(12, email);
                p.setString(13, senha);
                p.execute();
            } catch (SQLException ex) {
                throw new Exception(ex);
            }
            return "Cadastrado com sucesso!";
        }
        return "Já possui cliente cadastrado com esse cpf!";
    }

    public boolean delete(int ccliente) throws SQLException {
        String SQL = "DELETE * FROM CLIENTE WHERE CLIENTE.CCLIENTE = ?";
        PreparedStatement p = connection.prepareStatement(SQL);
        p.setInt(1, ccliente);
        p.execute();
        return true;
    }

    public boolean update(Cliente cliente) throws Exception {
        if (cliente != null && !cliente.equals("")) {
            String SQL = "UPDATE CLIENTE SET NOME=?, DATA_NASCIMENT0=?, CPF=?, CEP=?, ENDERECO=?, NUMERO=?, "
                    + " COMPLEMENTO=?, BAIRRO=?, ESTAD0=?, CIDADE=?, TELEFONE=?, EMAIL=?, SENHA=? WHERE CCLIENTE=?";
            PreparedStatement p = connection.prepareStatement(SQL);
            p.setString(1,cliente.getNome());
            //p.setDate(2, new java.sql.Date(cliente.getData_nascimento().getTime()));
            p.setString(3,cliente.getCpf());
            p.setString(4,cliente.getCep());
            p.setString(5,cliente.getEndereco());
            p.setInt(6,cliente.getNumero());
            p.setString(7,cliente.getComplemento());
            p.setString(8,cliente.getBairro());
            p.setString(9,cliente.getEstado());
            p.setString(10,cliente.getCidade());
            p.setString(11,cliente.getTelefone());
            p.setString(12,cliente.getEmail());
            p.setString(13,cliente.getSenha());
            p.setInt(14, cliente.getCcliente());
            p.setString(15,cliente.getNome());
            p.execute();
            return true;
        }
        return false;
    }

    public boolean login(Cliente cliente) throws SQLException, Exception {
        try {
            String SQL = "SELECT CLIENTE.EMAIL, CLIENTE.SENHA WHERE CLIENTE.CCLIENTE = ?";
            PreparedStatement p = connection.prepareStatement(SQL);
            p.setInt(1, cliente.getCcliente());
            ResultSet rs = p.executeQuery();
            if (rs.next()) {
                cliente.setEmail(rs.getString("EMAIL"));
                cliente.setSenha(rs.getString("SENHA"));
                return true;
            }
        } catch (SQLException ex) {
            throw new Exception(ex);
        }
        return false;
    }
}
