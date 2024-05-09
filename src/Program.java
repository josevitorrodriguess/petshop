import dao.ClientDAO;
import dao.DaoFactory;
import database.DB;
import entities.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Scanner;


public class Program {
    public static void main(String[] args) throws SQLException {
        Connection connection = DB.getConnection();

//        System.out.println("Selecione a opção desejada:\n1-Cadastrar dados:\n2-Deletar dados:\n3-Consultar dados:\n4-Atualizar dados:");

        Payment p = new Payment();
        p.setId(3);

        System.out.println(DaoFactory.createPaymentDAO().get(p,connection));

        connection.close();
    }
}
