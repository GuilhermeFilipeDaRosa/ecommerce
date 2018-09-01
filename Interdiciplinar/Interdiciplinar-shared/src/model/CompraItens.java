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
public class CompraItens implements Serializable{
    private int ccompraItens;
    private int qtde;
    private int cproduto;
    private int ccompra;
    private double valorUnitario;

    public int getCcompraItens() {
        return ccompraItens;
    }

    public void setCcompraItens(int ccompraItens) {
        this.ccompraItens = ccompraItens;
    }

    public int getQtde() {
        return qtde;
    }

    public void setQtde(int qtde) {
        this.qtde = qtde;
    }

    public int getCproduto() {
        return cproduto;
    }

    public void setCproduto(int cproduto) {
        this.cproduto = cproduto;
    }

    public int getCcompra() {
        return ccompra;
    }

    public void setCcompra(int ccompra) {
        this.ccompra = ccompra;
    }

    public double getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(double valorUnitario) {
        this.valorUnitario = valorUnitario;
    }
}
