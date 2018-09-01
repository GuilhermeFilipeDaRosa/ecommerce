/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;

/**
 *
 * @author User
 */
public class CarrinhoItens implements Serializable{
    private int ccarinhoItens;
    private int ccarrinho;
    private int cproduto;

    public int getCcarinhoItens() {
        return ccarinhoItens;
    }

    public void setCcarinhoItens(int ccarinhoItens) {
        this.ccarinhoItens = ccarinhoItens;
    }

    public int getCcarrinho() {
        return ccarrinho;
    }

    public void setCcarrinho(int ccarrinho) {
        this.ccarrinho = ccarrinho;
    }

    public int getCproduto() {
        return cproduto;
    }

    public void setCproduto(int cproduto) {
        this.cproduto = cproduto;
    }
}
