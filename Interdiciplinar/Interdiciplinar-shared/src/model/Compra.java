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
public class Compra implements Serializable{
    private int ccompra;
    private int ccliente;
    private String data;

    public int getCcompra() {
        return ccompra;
    }

    public void setCcompra(int ccompra) {
        this.ccompra = ccompra;
    }

    public int getCcliente() {
        return ccliente;
    }

    public void setCcliente(int ccliente) {
        this.ccliente = ccliente;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
