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


}
