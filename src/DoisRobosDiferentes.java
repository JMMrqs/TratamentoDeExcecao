import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class DoisRobosDiferentes {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Robo> robos = inicializarRobos(scanner);
        Robo roboNormal = robos.get(0);
        RoboInteligente roboInt = (RoboInteligente) robos.get(1);
        System.out.println(
                "O robô normal será " + roboNormal.getCor() + ", e o inteligente será " + roboInt.getCor() + ".");

        System.out.print("Digite a posição X do alimento: ");
        int alimentoX = scanner.nextInt();
        System.out.print("Digite a posição Y do alimento: ");
        int alimentoY = scanner.nextInt();
        scanner.nextLine();

        int movimentosRoboNormal = 0;
        int movimentosRoboInt = 0;

        while (!roboNormal.encontrouAlimento(alimentoX, alimentoY)
                || !roboInt.encontrouAlimento(alimentoX, alimentoY)) {
            if (!roboNormal.encontrouAlimento(alimentoX, alimentoY)) {
                Matriz.printMatriz(roboNormal, roboInt, alimentoX, alimentoY);
                System.out.println(
                        "\n\nPressione Enter para que o robô " + roboNormal.getCor() + " faça seu próximo movimento.");
                scanner.nextLine();
                int movimento = (int) (Math.random() * 4) + 1;
                try {
                    roboNormal.mover(movimento);
                    movimentosRoboNormal++;
                } catch (MovimentoInvalidoException e) {
                    System.out.println(e.getMessage());
                }
            }

            if (!roboInt.encontrouAlimento(alimentoX, alimentoY)) {
                Matriz.printMatriz(roboNormal, roboInt, alimentoX, alimentoY);
                System.out.println(
                        "\n\nPressione Enter para que o robô " + roboInt.getCor() + " faça seu próximo movimento.");
                scanner.nextLine();
                int movimento = (int) (Math.random() * 4) + 1;
                roboInt.mover(movimento);
                movimentosRoboInt++;
            }
        }

        System.out.println("O robô " + roboNormal.getCor() + "(normal) encontrou o alimento em " + movimentosRoboNormal
                + " movimentos.");
        System.out.println("O robô " + roboInt.getCor() + "(inteligente) encontrou o alimento em " + movimentosRoboInt
                + " movimentos.");

        scanner.close();
    }

    public static ArrayList<Robo> inicializarRobos(Scanner scanner) {
        ArrayList<String> cores = new ArrayList<>(
                Arrays.asList("Azul", "Vermelho", "Verde", "Amarelo", "Roxo", "Preto", "Branco"));
        ArrayList<Robo> robos = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            if (i == 0) {
                System.out.println("Escolha a cor do Robô normal: ");
            } else {
                System.out.println("Escolha a cor do Robô inteligente: ");
            }
            int escolha = -1;
            while (escolha < 0 || escolha >= cores.size()) {
                for (int j = 0; j < cores.size(); j++) {
                    System.out.println(j + " - " + cores.get(j));
                }
                escolha = scanner.nextInt();
                if (escolha < 0 || escolha >= cores.size()) {
                    System.out.println("Opção inválida. Escolha a cor do Robô: ");
                }
            }
            if (i == 0) {
                Robo robo = new Robo(cores.get(escolha));
                robos.add(robo);
            } else {
                RoboInteligente robo = new RoboInteligente(cores.get(escolha));
                robos.add(robo);
            }
            cores.remove(escolha);
        }
        return robos;
    }
}
