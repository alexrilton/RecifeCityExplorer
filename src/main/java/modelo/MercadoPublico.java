/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author euluc
 */
public class MercadoPublico extends touristSpot{
    
    private String descricao;
    private String bairro;


    // Construtor com todos os atributos
    public MercadoPublico(int _id, String nome, String descricao, String bairro, double latitude, double longitude) {
        super(_id, nome, latitude, longitude);
        this.descricao = descricao;
        this.bairro = bairro;
    }

    // Getters e Setters para todos os atributos
    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    // Método toString para imprimir informações do objeto
    @Override
    public String toString() {
        return "MercadoPublico{" +
                "_id=" + _id +
                ", nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", bairro='" + bairro + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}

