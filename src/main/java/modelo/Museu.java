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
public class Museu extends touristSpot {
    
   
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
    public Museu(int _id,String nome, String descricao, String bairro, String logradouro, double latitude,
                 double longitude, String telefone, String site, String funcionamento, String funcionamentoDomingo,
            String endereco, String especialidade, String email) {
        super(_id, nome, latitude, longitude);
        this.descricao = descricao;
        this.bairro = bairro;
        this.logradouro = logradouro;
        this.telefone = telefone;
        this.site = site;
        this.funcionamento = funcionamento;
        this.funcionamentoDomingo = funcionamentoDomingo;
        this.endereco = endereco;
        this.especialidade = especialidade;
        this.email = email;
    }
    
    public Museu(int _id, String nome, String endereco, String telefone,
            String especialidade, String site, String email, String descricao, String bairro, String logradouro, 
            String funcionamento, String funcionamentoDomingo) {
    super(_id, nome);
    this.endereco = endereco;
    this.telefone = telefone;
    this.especialidade = especialidade;
    this.site = site;
    this.email = email;
    this.descricao = descricao;
    this.bairro = bairro;
    this.logradouro = logradouro;
    this.funcionamento = funcionamento;
    this.funcionamentoDomingo = funcionamentoDomingo;
    }

    public Museu(int _id, String nome, String descricao, String bairro, String logradouro, Double latitude, Double longitude, String telefone, String site) {
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
        this.email = email;    }

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

    // Método toString para imprimir informações do objeto
    @Override
    public String toString() {
        return "Museu{" +
                "nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", bairro='" + bairro + '\'' +
                ", logradouro='" + logradouro + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", telefone='" + telefone + '\'' +
                ", site='" + site + '\'' +
                '}';
    }
}
