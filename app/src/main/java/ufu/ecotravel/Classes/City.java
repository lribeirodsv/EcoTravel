package ufu.ecotravel.Classes;

import java.util.ArrayList;

/**
 * Created by Lucas on 29/10/2017.
 */

public class City {

    private Integer codigo;
    private String nome;
    private String descricao;
    private String estado;
    private String pais;
    private String url;
    private Integer rating;
    private ArrayList<String> places;

    public City(Integer codigo, String nome, String descricao, String estado, String pais, String url, Integer rating){

        this.setCodigo(codigo);
        this.setNome(nome);
        this.setDescricao(descricao);
        this.setEstado(estado);
        this.setPais(pais);
        this.setUrl(url);
        this.setRating(rating);

    }

    public Integer getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getEstado() {
        return estado;
    }

    public String getPais() {
        return pais;
    }

    public String getUrl() {

        return url;
    }

    public Integer getRating() {
        return rating;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }
}
