package ufu.ecotravel.Database;

/**
 * Created by Lucas on 22/10/2017.
 */

public class ContractPlace {

    public ContractPlace () {}

    public static class PlaceEntry
    {
        public static final String TABLE_NAME = "TABPLACES";
        public static final String CODIGO = "CDPLCE";
        public static final String NOME = "DSNOME";
        public static final String DESCRICAO = "DSPLCE";
        public static final String LATITUDE = "DSLATT";
        public static final String LONGITUDE = "DSLONG";
        public static final String DATA = "DSDATE";
    }
}

// JSON EXAMPLE [{"id":2,"title":"Cachoeira 1","description":"Essa é a descrição da cachoeira 1","city":1,"latitude":2.0,"longitude":1.0,"pub_date":"2017-11-15T23:49:40.321166Z","comments":[1]}]