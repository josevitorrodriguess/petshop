package funcionalities;

import dao.DaoFactory;
import entities.Client;
import entities.Pet;
import entities.Scheduling;
import entities.Service;

import java.sql.Connection;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;


public class CreateFuncionalities {
    static Scanner sc = new Scanner(System.in);


    //create

    public static void createClient(Connection connection){
        Client client = new Client();

        sc.nextLine();
        System.out.println("Digite o nome do cliente:");
        client.setName(sc.nextLine());

        System.out.println("Digite o número do cliente:");
        client.setPhoneNumber(sc.nextLine());
        sc.nextLine();

        System.out.println("Digite o endereço do cliente:");
        client.setAddress(sc.nextLine());

        DaoFactory.createClientDAO().insert(client, connection);
    }

    public static void createPet(Connection connection){
        Pet pet = new Pet();

        sc.nextLine();
        System.out.println("Digite o nome do pet:");
        pet.setName(sc.nextLine());

        System.out.println("Digite a idade do pet:");
        pet.setAge(sc.nextInt());

        sc.nextLine();
        System.out.println("Digite a espécie do pet:");
        pet.setSpecies(sc.nextLine());
        sc.nextLine();

        System.out.println("Digite a raça do pet:");
        pet.setRace(sc.nextLine());

        System.out.println("Digite o id do Dono:");
        pet.setClientId(sc.nextInt());

        DaoFactory.createPetDAO().insert(pet,connection);
    }

    public static void createService(Connection connection){
        Service service = new Service();

        sc.nextLine();
        System.out.println("Digite o nome do servico:");
        service.setName(sc.nextLine());

        System.out.println("Digite a descrição do servico:");
        service.setDescription(sc.nextLine());
        sc.nextLine();

        System.out.println("Digite o preço do servico:");
        service.setPrice(sc.nextDouble());

        DaoFactory.createServiceDAO().insert(service,connection);
    }

    public  static  void createScheduling(Connection connection){
        Scheduling scheduling = new Scheduling();

        System.out.println("Digite o id do dono do pet:");
        scheduling.setClientId(sc.nextInt());

        System.out.println("Digite o id do pet para o agendamento:");
        scheduling.setPetId(sc.nextInt());

        System.out.println("Digite o id do dono do servico:");
        scheduling.setServiceId(sc.nextInt());

        System.out.println("Digite a data e hora no formato (ano mês dia hora minuto):");
        int ano = sc.nextInt();
        int mes = sc.nextInt();
        int dia = sc.nextInt();
        int hora = sc.nextInt();
        int minuto = sc.nextInt();

        String dataHoraString = String.format("%d-%02d-%02dT%02d:%02d", ano, mes, dia, hora, minuto);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd'T'HH:mm");
        LocalDateTime localDateTime = LocalDateTime.parse(dataHoraString, formatter);

        scheduling.setDate(localDateTime);

        DaoFactory.createSchedulingDAO().insert(scheduling,connection);
    }


    //delete
    public static void deleteClient(Connection connection){
        System.out.println("Digite o Id do cliente que deseja deletar:");
            int id = sc.nextInt();
        DaoFactory.createClientDAO().delete(id,connection);
    }

    public static void deletePet(Connection connection){
        System.out.println("Digite o Id do pet que deseja deletar:");
        int id = sc.nextInt();
        DaoFactory.createPetDAO().delete(id,connection);
    }

    public static void deleteService(Connection connection){
        System.out.println("Digite o Id do serviço que deseja deletar:");
        int id = sc.nextInt();
        DaoFactory.createServiceDAO().delete(id,connection);
    }

    public static void deleteScheduling(Connection connection){
        System.out.println("Digite o Id do agendamento que deseja cancelar:");
        int id = sc.nextInt();
        DaoFactory.createSchedulingDAO().delete(id,connection);
    }

   //get

    public  static  void getClient(Connection connection){
        System.out.println("Digite o id do cliente que quer consultar os dados:");
        int id = sc.nextInt();
        System.out.println(DaoFactory.createClientDAO().get(id,connection));
    }

    public static void  getPet(Connection connection){
        System.out.println("Digite o id do Pet que quer consultar os dados:");
        int id = sc.nextInt();
        System.out.println(DaoFactory.createPetDAO().get(id,connection));
    }

    public static void getService(Connection connection){
        System.out.println("Digite o id do serviço que quer saber as informações:");
        int id = sc.nextInt();
        System.out.println(DaoFactory.createServiceDAO().get(id,connection));
    }

    public static void getScheduling(Connection connection){
        System.out.println("Digite o id do agendamento que quer saber as informações:");
        int id = sc.nextInt();
        System.out.println(DaoFactory.createSchedulingDAO().get(id,connection));
    }

    public static void getPayment(Connection connection){
        System.out.println("Digite o id do pagamento para consultar o status:");
        int id = sc.nextInt();
        System.out.println(DaoFactory.createPaymentDAO().get(id,connection));
    }


    //update
    public static void updateClient(Connection connection){
        Client client = new Client();
        System.out.println("Digite o id do cliente que deseja atualizar os dados:");
        int id = sc.nextInt();
        client.setId(id);
        sc.nextLine();

        System.out.println("Digite o novo nome  caso deseje mudar:");
        client.setName(sc.nextLine());

        System.out.println("Digite o novo endereço caso deseje mudar:");
        client.setAddress(sc.nextLine());

        System.out.println("Digite o novo número caso deseje mudar:");
        client.setPhoneNumber(sc.nextLine());

        DaoFactory.createClientDAO().update(client,connection);
    }

    public static void updatePet(Connection connection){
        Pet pet = new Pet();

        System.out.println("Digite o id do pet que deseja atualizar os dados:");
        int id = sc.nextInt();
        pet.setId(id);

        System.out.println("Digite o id do dono do pet que deseja mudar:");
        pet.setClientId(sc.nextInt());

        DaoFactory.createPetDAO().update(pet,connection);
    }


    public static void updateService(Connection connection){
        Service service = new Service();

        System.out.println("Digite o id do serviço que será atualizado:");
        int id = sc.nextInt();
        service.setId(id);

        sc.nextLine();
        System.out.println("Digite caso deseje atualizar o nome do serviço:");
        service.setName(sc.nextLine());


        System.out.println("Digite caso deseje atualizar a descrição do serviço:");
        service.setDescription(sc.nextLine());

        System.out.println("Digite caso deseje atualizar o preço do serviço(caso nao queira digite 0):");
        service.setPrice(sc.nextDouble());

        DaoFactory.createServiceDAO().update(service,connection);
    }

    public static void updateScheduling(Connection connection){
        Scheduling scheduling = new Scheduling();

        System.out.println("Digite o id do agendamento que será alterado:");
        int id = sc.nextInt();

        scheduling.setId(id);

            System.out.println("Digite a nova data e hora no formato(ano mês dia hora minuto)(caso queira mudar):");
            int ano = sc.nextInt();
            int mes = sc.nextInt();
            int dia = sc.nextInt();
            int hora = sc.nextInt();
            int minuto = sc.nextInt();

            String dataHoraString = String.format("%d-%02d-%02dT%02d:%02d", ano, mes, dia, hora, minuto);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd'T'HH:mm");
            LocalDateTime localDateTime = LocalDateTime.parse(dataHoraString, formatter);

            scheduling.setDate(localDateTime);

        DaoFactory.createSchedulingDAO().update(scheduling,connection);
    }


}
