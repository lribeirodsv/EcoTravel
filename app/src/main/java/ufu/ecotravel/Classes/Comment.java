package ufu.ecotravel.Classes;

/**
 * Created by Lucas on 29/10/2017.
 */

public class Comment {

    private Integer codigo;
    private String nome;
    private String cometario;
    private Integer rating;

    public Comment(Integer codigo, String nome, String cometario, Integer rating){

        this.setCodigo(codigo);
        this.setNome(nome);
        this.setCometario(cometario);
        this.setRating(rating);

    }

    public Integer getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public String getCometario() {
        return cometario;
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

    public void setCometario(String cometario) {
        this.cometario = cometario;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }
}
