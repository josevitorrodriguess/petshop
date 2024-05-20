import dao.DaoFactory;
import database.DB;
import entities.Payment;
import funcionalities.CreateFuncionalities;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Locale;
import java.util.Scanner;



public class Program {
    public static void main(String[] args) throws SQLException {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);
        Connection connection = DB.getConnection();
        int loop = 1;

        while (loop != 0) {
            System.out.println("Selecione a opção desejada:\n1-Cadastrar dados:\n2-Deletar dados:\n3-Consultar dados:\n4-Atualizar dados:\n5-Realizar um Agendamento ou fazer um pagamento:");
            int resposta = sc.nextInt();

            switch (resposta) {
                case 1:
                    while (true) {
                        System.out.println("O que você irá cadastrar:\n 1-Cliente \n 2-Pet \n 3-Serviço\n");
                        int resp = sc.nextInt();
                        if (resp == 1) {
                            CreateFuncionalities.createClient(connection);
                        } else if (resp == 2) {
                            CreateFuncionalities.createPet(connection);
                        } else if (resp == 3) {
                            CreateFuncionalities.createService(connection);
                        }
                        System.out.println("Deseja adicionar mais alguma coisa?(1 para sim/0 para nao)");
                        int u = sc.nextInt();
                        if (u == 0) {
                            break;
                        }
                    }
                    break;
                case 2:
                    while (true) {
                        System.out.println("O que você irá deletar:\n 1-Cliente \n 2-Pet \n 3-Serviço \n 4-Cancelar um Agendamento");
                        int resp = sc.nextInt();
                        if (resp == 1) {
                            CreateFuncionalities.deleteClient(connection);
                        } else if (resp == 2) {
                            CreateFuncionalities.deletePet(connection);
                        } else if (resp == 3) {
                            CreateFuncionalities.deleteService(connection);
                        } else if (resp == 4) {
                            CreateFuncionalities.deleteScheduling(connection);
                        }
                        System.out.println("Deseja deletar mais alguma coisa?(1 para sim/0 para nao)");
                        int o = sc.nextInt();
                        if (o == 0) {
                            break;
                        }
                    }
                    break;
                case 3:
                    System.out.println("Qual dado você quer consultar:\n 1-Cliente\n 2-Pet\n 3-Serviço\n 4-Agendamento\n 5-Status do pagamento");
                    int resp3 = sc.nextInt();
                    if (resp3 == 1) {
                        CreateFuncionalities.getClient(connection);
                    } else if (resp3 == 2) {
                        CreateFuncionalities.getPet(connection);
                    } else if (resp3 == 3) {
                        CreateFuncionalities.getService(connection);
                    } else if (resp3 == 4) {
                        CreateFuncionalities.getScheduling(connection);
                    } else if (resp3 == 5) {
                        CreateFuncionalities.getPayment(connection);
                    }
                    System.out.println("Deseja consultar mais alguma coisa?(1 para sim/0 para nao)");
                    int r = sc.nextInt();
                    if (r == 0) {
                        break;
                    }
                    break;
                case 4:
                    System.out.println("Qual dado você quer atualizar:\n 1-Cliente\n 2-Pet\n 3-Serviço\n 4-Reagendar um agendamento");
                    int resp4 = sc.nextInt();
                    if (resp4 == 1) {
                        CreateFuncionalities.updateClient(connection);
                    } else if (resp4 == 2) {
                        CreateFuncionalities.updatePet(connection);
                    } else if (resp4 == 3) {
                        CreateFuncionalities.updateService(connection);
                    } else if (resp4 == 4) {
                        CreateFuncionalities.updateScheduling(connection);
                    }
                    System.out.println("Deseja atualizar mais alguma coisa?(1 para sim/0 para nao)");
                    int v = sc.nextInt();
                    if (v == 0) {
                        break;
                    }
                    break;
                case 5:
                    System.out.println("Você irá 1-fazer um agendamento ou 2-efetuar um pagamento:");
                    int i = sc.nextInt();
                    if (i == 1) {
                        CreateFuncionalities.createScheduling(connection);
                    } else if (i == 2) {
                        Payment payment = new Payment();
                        System.out.println("Digite o id do pagamento:");
                        int id = sc.nextInt();
                        payment.setId(id);
                        System.out.println("Digite sim para efetuar o pagamento:");
                        String p = sc.next();
                        if ("sim".equals(p)) {
                            payment.setPayment(true);
                            DaoFactory.createPaymentDAO().update(payment, connection);
                            System.out.println("Pagamento efetuado com sucesso");
                        } else {
                            System.out.println("PAGAMENTO NÃO EFETUADO!");
                        }
                        System.out.println("Deseja efetuar mais algum pagamento?(1 para sim/0 para nao)");
                        int h= sc.nextInt();
                        if (h == 0) {
                            break;
                        }
                    }
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
            System.out.println("Deseja fazer mais alguma operação?(digite 1 para sim e 0 para não)");
            loop = sc.nextInt();
        }
        connection.close();
    }
}

