import dao.DaoFactory;
import database.DB;
import entities.*;




public class Program {
    public static void main(String[] args) {


        Client ze = new Client("ze","rua da mata","83988822933");
        Pet toto = new Pet("paçoca",3,"dog","pinsher",10);


        DaoFactory.createClientDAO().insert(ze);
        DaoFactory.createPetDAO().insert(toto);

    }
}
