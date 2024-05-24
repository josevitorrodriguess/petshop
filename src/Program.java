import dao.DaoFactory;
import database.DB;
import entities.Payment;
import funcionalities.CreateFuncionalities;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Locale;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);
        Connection connection = null;

        try {
            connection = DB.getConnection();
            int loop = 1;

            while (loop != 0) {
                System.out.println("Selecione a opção desejada:\n1-Cadastrar dados:\n2-Deletar dados:\n3-Consultar dados:\n4-Atualizar dados:\n5-Realizar um Agendamento ou fazer um pagamento:");
                int resposta = sc.nextInt();

                switch (resposta) {
                    case 1:
                        handleCreate(sc, connection);
                        break;
                    case 2:
                        handleDelete(sc, connection);
                        break;
                    case 3:
                        handleConsult(sc, connection);
                        break;
                    case 4:
                        handleUpdate(sc, connection);
                        break;
                    case 5:
                        handleSchedulingOrPayment(sc, connection);
                        break;
                    default:
                        System.out.println("Opção inválida.");
                }
                System.out.println("Deseja fazer mais alguma operação? (1 para sim / 0 para não)");
                loop = sc.nextInt();
            }
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            sc.close();
        }
    }

    private static void handleCreate(Scanner sc, Connection connection) {
        while (true) {
            System.out.println("O que você irá cadastrar:\n1-Cliente \n2-Pet \n3-Serviço");
            int resp = sc.nextInt();
            if (resp == 1) {
                CreateFuncionalities.createClient(connection);
            } else if (resp == 2) {
                CreateFuncionalities.createPet(connection);
            } else if (resp == 3) {
                CreateFuncionalities.createService(connection);
            }
            System.out.println("Deseja adicionar mais alguma coisa? (1 para sim / 0 para não)");
            int u = sc.nextInt();
            if (u == 0) {
                break;
            }
        }
    }

    private static void handleDelete(Scanner sc, Connection connection) {
        while (true) {
            System.out.println("O que você irá deletar:\n1-Cliente \n2-Pet \n3-Serviço \n4-Cancelar um Agendamento");
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
            System.out.println("Deseja deletar mais alguma coisa? (1 para sim / 0 para não)");
            int o = sc.nextInt();
            if (o == 0) {
                break;
            }
        }
    }

    private static void handleConsult(Scanner sc, Connection connection) {
        while (true) {
            System.out.println("Qual dado você quer consultar:\n1-Cliente\n2-Pet\n3-Serviço\n4-Agendamento\n5-Status do pagamento");
            int resp = sc.nextInt();
            if (resp == 1) {
                CreateFuncionalities.getClient(connection);
            } else if (resp == 2) {
                CreateFuncionalities.getPet(connection);
            } else if (resp == 3) {
                CreateFuncionalities.getService(connection);
            } else if (resp == 4) {
                CreateFuncionalities.getScheduling(connection);
            } else if (resp == 5) {
                CreateFuncionalities.getPayment(connection);
            }
            System.out.println("Deseja consultar mais alguma coisa? (1 para sim / 0 para não)");
            int r = sc.nextInt();
            if (r == 0) {
                break;
            }
        }
    }

    private static void handleUpdate(Scanner sc, Connection connection) {
        while (true) {
            System.out.println("Qual dado você quer atualizar:\n1-Cliente\n2-Pet\n3-Serviço\n4-Reagendar um agendamento");
            int resp = sc.nextInt();
            if (resp == 1) {
                CreateFuncionalities.updateClient(connection);
            } else if (resp == 2) {
                CreateFuncionalities.updatePet(connection);
            } else if (resp == 3) {
                CreateFuncionalities.updateService(connection);
            } else if (resp == 4) {
                CreateFuncionalities.updateScheduling(connection);
            }
            System.out.println("Deseja atualizar mais alguma coisa? (1 para sim / 0 para não)");
            int v = sc.nextInt();
            if (v == 0) {
                break;
            }
        }
    }

    private static void handleSchedulingOrPayment(Scanner sc, Connection connection) {
        while (true) {
            System.out.println("Você irá 1-fazer um agendamento ou 2-efetuar um pagamento:");
            int i = sc.nextInt();
            if (i == 1) {
                CreateFuncionalities.createScheduling(connection);
            } else if (i == 2) {
                handlePayment(sc, connection);
            }
            break;
        }
    }

    private static void handlePayment(Scanner sc, Connection connection) {
        while (true) {
            Payment payment = new Payment();
            System.out.println("Digite o id do pagamento:");
            int id = sc.nextInt();
            payment.setId(id);
            System.out.println("Digite 'sim' para efetuar o pagamento:");
            String p = sc.next();
            if ("sim".equalsIgnoreCase(p)) {
                payment.setPayment(true);
                DaoFactory.createPaymentDAO().update(payment, connection);
                System.out.println("Pagamento efetuado com sucesso");
            } else {
                System.out.println("PAGAMENTO NÃO EFETUADO!");
            }
            System.out.println("Deseja efetuar mais algum pagamento? (1 para sim / 0 para não)");
            int h = sc.nextInt();
            if (h == 0) {
                break;
            }
        }
    }
}
