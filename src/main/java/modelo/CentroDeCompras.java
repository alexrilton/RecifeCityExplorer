/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author euluc
 */

@ManagedBean(name = "centroCompras")
@ViewScoped
public class CentroDeCompras extends touristSpot{

    private String descricao;
    private String bairro;
    private String logradouro;
    private String telefone;
    private String site;
    private String funcionamento;
    private String funcionamentoDomingo;
    private String endereco;
    private String especialidade;
    private String email;



    // Construtor com todos os atributos
    public CentroDeCompras(int _id, String nome, String descricao, String bairro, String logradouro,
            double latitude, double longitude, String telefone, String site, String funcionamento, String funcionamentoDomingo,
            String endereco, String especialidade, String email) {
        super(_id, nome, latitude, longitude);
        this.descricao = descricao;
        this.bairro = bairro;
        this.logradouro = logradouro;
        this.latitude = latitude;
        this.longitude = longitude;
        this.telefone = telefone;
        this.site = site;
        this.funcionamento = funcionamento;
        this.funcionamentoDomingo = funcionamentoDomingo;
        this.endereco = endereco;
        this.especialidade = especialidade;
        this.email = email;
       
    }

    public CentroDeCompras(int _id, String nome, String endereco, String especialidade, String email, String descricao, String bairro, String logradouro, Double latitude, Double longitude, String telefone, String site, String funcionamento, String funcionamentoDomingo) {
        super(_id, nome);
        this.descricao = descricao;
        this.bairro = bairro;
        this.logradouro = logradouro;
        this.latitude = latitude;
        this.longitude = longitude;
        this.telefone = telefone;
        this.site = site;
        this.funcionamento = funcionamento;
        this.funcionamentoDomingo = funcionamentoDomingo;
        this.endereco = endereco;
        this.especialidade = especialidade;
        this.email = email;
    }

    public CentroDeCompras(int _id, String nome, String descricao, String bairro, String logradouro, Double latitude, Double longitude, String telefone, String site, String funcionamento, String funcionamentoDomingo) {
        super(_id, nome);
        this.descricao = descricao;
        this.bairro = bairro;
        this.logradouro = logradouro;
        this.latitude = latitude;
        this.longitude = longitude;
        this.telefone = telefone;
        this.site = site;
        this.funcionamento = funcionamento;
        this.funcionamentoDomingo = funcionamentoDomingo;
    }

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

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getFuncionamento() {
        return funcionamento;
    }

    public void setFuncionamento(String funcionamento) {
        this.funcionamento = funcionamento;
    }

    public String getFuncionamentoDomingo() {
        return funcionamentoDomingo;
    }

    public void setFuncionamentoDomingo(String funcionamentoDomingo) {
        this.funcionamentoDomingo = funcionamentoDomingo;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    

    // Método toString para imprimir informações do objeto
    @Override
    public String toString() {
        return "CentroDeCompras{"
                + "_id=" + _id
                + ", nome='" + nome + '\''
                + ", descricao='" + descricao + '\''
                + ", bairro='" + bairro + '\''
                + ", logradouro='" + logradouro + '\''
                + ", latitude=" + latitude
                + ", longitude=" + longitude
                + ", telefone='" + telefone + '\''
                + ", site='" + site + '\''
                + ", funcionamento='" + funcionamento + '\''
                + ", funcionamentoDomingo='" + funcionamentoDomingo + '\''
                + '}';
    }
}
