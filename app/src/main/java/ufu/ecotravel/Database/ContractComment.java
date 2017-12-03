package ufu.ecotravel.Database;

/**
 * Created by Lucas on 22/10/2017.
 */

public class ContractComment {

    public ContractComment() {}

    public static class CommentEntry
    {
        public static final String TABLE_NAME = "TABCOMMENTS";
        public static final String CODIGO = "CDCOMM";
        public static final String NOME = "DSNOME";
        public static final String COMENTARIO = "DSCOMM";
        public static final String RATING = "NURATG";
    }
}

// [{"id":1,"place":2,"name":"Guilherme","comment":"Comentário 1","rating":5}]