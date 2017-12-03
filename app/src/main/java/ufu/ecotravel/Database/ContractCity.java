package ufu.ecotravel.Database;

/**
 * Created by Lucas on 22/10/2017.
 */

public class ContractCity {

    public ContractCity() {}

    public static class CityEntry
    {
        public static final String TABLE_NAME = "TABCITIES";
        public static final String CODIGO = "CDCID";
        public static final String NOME = "DSNOME";
        public static final String DESCRICAO = "DSCID";
        public static final String ESTADO = "DSESTD";
        public static final String PAIS = "DSPAIS";
        public static final String URL = "DSURL";
        public static final String RATING = "NURATG";

    }
}

// [{"id":1,"city":"Uberlandia","state":"MG","country":"Brasil","description":"Cidade de uberlandia","image":"image2.jpg","rating":5,"places":[2]}]