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
        Scanner sc = new Scanner(System.in);
        Connection connection = DB.getConnection();



            System.out.println("Selecione a opção desejada:\n1-Cadastrar dados:\n2-Deletar dados:\n3-Consultar dados:\n4-Atualizar dados:\n5-Realizar um Agendamento:");
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
                case 2:
                    System.out.println("O que você irá deletar:\n 1-Cliente \n 2-Pet \n 3-Serviço \n 4-Cancelar um Agendamento");
                    resposta = sc.nextInt();
                    if (resposta==1){
                        CreateFuncionalities.deleteClient(connection);
                    } else if (resposta==2) {
                       CreateFuncionalities.deletePet(connection);
                    } else if (resposta==3) {
                       CreateFuncionalities.deleteService(connection);
                    } else if (resposta==4) {
                      CreateFuncionalities.deleteScheduling(connection);
                    }
                case 3:
                    System.out.println("Qual dado você quer consultar:\n 1-Cliente\n 2-Pet\n 3-Serviço\n 4-Agendamento\n 5-Status do pagamento");
                    resposta = sc.nextInt();

                    if (resposta==1){
                      CreateFuncionalities.getClient(connection);
                    } else if (resposta==2) {
                        CreateFuncionalities.getPet(connection);
                    } else if (resposta==3) {
                      CreateFuncionalities.getService(connection);
                    } else if (resposta==4) {
                       CreateFuncionalities.getScheduling(connection);
                    }else if(resposta==5){
                        CreateFuncionalities.getPayment(connection);
                    }
            }






        connection.close();
    }
}
