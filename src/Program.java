import dao.ClientDAO;
import dao.DaoFactory;
import database.DB;
import entities.*;
import funcionalities.CreateFuncionalities;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;


public class Program {
    public static void main(String[] args) throws SQLException {
        Locale.setDefault(Locale.US);
        Connection connection = DB.getConnection();
        Scanner sc = new Scanner(System.in);

            System.out.println("Selecione a opção desejada:\n1-Cadastrar dados:\n2-Deletar dados:\n3-Consultar dados:\n4-Atualizar dados:");
            int resposta = sc.nextInt();

            switch (resposta){
                case 1:
                    System.out.println("O que você irá cadastrar:\n 1-Cliente \n 2-Pet \n 3-Serviço\n");
                    resposta = sc.nextInt();
                    if (resposta==1){
                        CreateFuncionalities.createClient(connection);
                    } else if (resposta==2) {
                        CreateFuncionalities.createPet(connection);
                    } else if (resposta==3) {
                        CreateFuncionalities.createService(connection);
                    }
            }






        connection.close();
    }
}
