import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class DoisRobosEObstaculos {
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

        List<Obstaculo> obstaculos1 = new ArrayList<>();
        System.out.print("Digite o número de bombas: ");
        int numBombas = scanner.nextInt();
        for (int i = 0; i < numBombas; i++) {
            System.out.print("Digite a posição X da bomba " + (i + 1) + ": ");
            int bombaX = scanner.nextInt();
            System.out.print("Digite a posição Y da bomba " + (i + 1) + ": ");
            int bombaY = scanner.nextInt();
            obstaculos1.add(new Bomba(bombaX, bombaY));
        }

        System.out.print("Digite o número de rochas: ");
        int numRochas = scanner.nextInt();
        for (int i = 0; i < numRochas; i++) {
            System.out.print("Digite a posição X da rocha " + (i + 1) + ": ");
            int rochaX = scanner.nextInt();
            System.out.print("Digite a posição Y da rocha " + (i + 1) + ": ");
            int rochaY = scanner.nextInt();
            obstaculos1.add(new Rocha(rochaX, rochaY));
        }
        scanner.nextLine();
        List<Obstaculo> obstaculos2 = new ArrayList<>(obstaculos1);

        int movimentosRoboNormal = 0;
        int movimentosRoboInt = 0;

        while ((!roboNormal.isExplodido() || !roboInt.isExplodido())
                && (!roboNormal.encontrouAlimento(alimentoX, alimentoY)
                        && !roboInt.encontrouAlimento(alimentoX, alimentoY))) {

            if (!roboNormal.encontrouAlimento(alimentoX, alimentoY) && !roboNormal.isExplodido()) {
                Matriz.printMatriz(roboNormal, roboInt, alimentoX, alimentoY, obstaculos1, obstaculos2);
                System.out.println(
                        "\n\nPressione Enter para que o robô " + roboNormal.getCor() + " faça seu próximo movimento.");
                scanner.nextLine();

                int movimento = (int) (Math.random() * 4) + 1;
                try {
                    roboNormal.mover(movimento);
                    movimentosRoboNormal++;
                    verificarObstaculos(roboNormal, obstaculos1);
                } catch (MovimentoInvalidoException e) {
                    System.out.println(e.getMessage());
                }
            }

            if (!roboInt.encontrouAlimento(alimentoX, alimentoY) && !roboInt.isExplodido()) {
                Matriz.printMatriz(roboNormal, roboInt, alimentoX, alimentoY, obstaculos1, obstaculos2);
                System.out.println(
                        "\n\nPressione Enter para que o robô " + roboInt.getCor() + " faça seu próximo movimento.");
                scanner.nextLine();

                int movimento = (int) (Math.random() * 4) + 1;
                roboInt.mover(movimento);
                movimentosRoboInt++;
                verificarObstaculos(roboInt, obstaculos2);
            }
        }

        Matriz.printMatriz(roboNormal, roboInt, alimentoX, alimentoY, obstaculos1, obstaculos2);
        if (roboNormal.encontrouAlimento(alimentoX, alimentoY)) {
            System.out.println(
                    "O robô " + roboNormal.getCor() + "(normal) encontrou o alimento em " + movimentosRoboNormal
                            + " movimentos.");
        } else if (roboNormal.isExplodido()) {
            System.out.println("O robô " + roboNormal.getCor() + "(normal) explodiu após " + movimentosRoboNormal
                    + " movimentos.");
        } else {
            System.out.println("O robô " + roboNormal.getCor() + "(normal) fez " + movimentosRoboNormal
                    + " movimentos, mas não encontrou o alimento.");
        }

        if (roboInt.encontrouAlimento(alimentoX, alimentoY)) {
            System.out
                    .println("O robô " + roboInt.getCor() + "(inteligente) encontrou o alimento em " + movimentosRoboInt
                            + " movimentos.");
        } else if (roboInt.isExplodido()) {
            System.out.println(
                    "O robô " + roboInt.getCor() + "(inteligente) explodiu após " + movimentosRoboInt + " movimentos.");
        } else {
            System.out.println("O robô " + roboInt.getCor() + "(normal) fez " + movimentosRoboNormal
                    + " movimentos, mas não encontrou o alimento.");
        }

        scanner.close();
    }

    private static void verificarObstaculos(Robo robo, List<Obstaculo> obstaculos) {
        for (Obstaculo obstaculo : obstaculos) {
            if (robo.getX() == obstaculo.getIdX() && robo.getY() == obstaculo.getIdY()) {
                obstaculo.bater(robo);
                if (obstaculo instanceof Bomba) {
                    obstaculos.remove(obstaculo);
                    break;
                }
            }
        }
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
