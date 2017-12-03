package ufu.ecotravel.Classes;

/**
 * Created by Lucas on 29/10/2017.
 */

public class Place {

    private Integer codigo;
    private String nome;
    private String descricao;
    private String cidade;
    private double latitude;
    private double longitude;
    private String data;

    public Place(Integer codigo, String nome, String descricao, String cidade, double latitude, double longitude, String data){

        this.setCodigo(codigo);
        this.setNome(nome);
        this.setDescricao(descricao);
        this.setCidade(cidade);
        this.setLatitude(latitude);
        this.setLongitude(longitude);
        this.setData(data);

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

    public String getCidade() {
        return cidade;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getData() {
        return data;
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

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void setData(String data) {
        this.data = data;
    }
}
