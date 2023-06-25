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
//TODO - Talvez coloca a lat e long no toString
public class BarERestaurante extends touristSpot {

    //TO DO - Adicionar no construtor ele receber um double latitude um double longitude
    private String endereco;
    private String telefone;
    private String especialidade;
    private String site;
    private String email;
   



    
// Construtor com todos os atributos
    public BarERestaurante(int _id, String nome, String endereco, String telefone, String especialidade, String site, String email, double latitude, double longitude) {
        super(_id, nome, latitude, longitude);
        this.endereco = endereco;
        this.telefone = telefone;
        this.especialidade = especialidade;
        this.site = site;
        this.email = email;
    }

    public BarERestaurante(int _id, String nome, String endereco, String telefone, String especialidade, String site, String email) {
        super(_id, nome);
        this.endereco = endereco;
        this.telefone = telefone;
        this.especialidade = especialidade;
        this.site = site;
        this.email = email;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
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
        return "BaresERestaurantes{"
                + "_id=" + _id
                + ", nome='" + nome + '\''
                + ", endereco='" + endereco + '\''
                + ", telefone='" + telefone + '\''
                + ", especialidade='" + especialidade + '\''
                + ", site='" + site + '\''
                + ", email='" + email + '\''
               
                + '}';
    }
}
