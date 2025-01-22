import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class DoisRobos {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Robo> robos = inicializarRobos(scanner);
        Robo robo1 = robos.get(0);
        Robo robo2 = robos.get(1);
        System.out.println(
                "O robô 1 será " + robo1.getCor() + ", e o robô 2 será " + robo2.getCor() + ".");

        System.out.print("Digite a posição X do alimento: ");
        int alimentoX = scanner.nextInt();
        System.out.print("Digite a posição Y do alimento: ");
        int alimentoY = scanner.nextInt();
        scanner.nextLine();

        int movimentosValidosRobo1 = 0;
        int movimentosInvalidosRobo1 = 0;
        int movimentosValidosRobo2 = 0;
        int movimentosInvalidosRobo2 = 0;

        while (!robo1.encontrouAlimento(alimentoX, alimentoY) && !robo2.encontrouAlimento(alimentoX, alimentoY)) {
            Matriz.printMatriz(robo1, robo2, alimentoX, alimentoY);
            System.out.println(
                    "\n\nPressione Enter para que o robô " + robo1.getCor() + " faça seu próximo movimento.");
            scanner.nextLine();
            int movimentoRobo1 = (int) (Math.random() * 4) + 1;
            try {
                robo1.mover(movimentoRobo1);
                movimentosValidosRobo1++;
            } catch (MovimentoInvalidoException e) {
                movimentosInvalidosRobo1++;
            }

            Matriz.printMatriz(robo1, robo2, alimentoX, alimentoY);
            System.out.println(
                    "\n\nPressione Enter para que o robô " + robo2.getCor() + " faça seu próximo movimento.");
            scanner.nextLine();
            int movimentoRobo2 = (int) (Math.random() * 4) + 1;
            try {
                robo2.mover(movimentoRobo2);
                movimentosValidosRobo2++;
            } catch (MovimentoInvalidoException e) {
                movimentosInvalidosRobo2++;
            }
        }

        if (robo1.encontrouAlimento(alimentoX, alimentoY)) {
            System.out.println("O robô " + robo1.getCor() + " encontrou o alimento!");
        } else {
            System.out.println("O robô " + robo2.getCor() + " encontrou o alimento!");
        }

        System.out.println("Movimentos válidos do robô " + robo1.getCor() + ": " + movimentosValidosRobo1);
        System.out.println("Movimentos inválidos do robô " + robo1.getCor() + ": " + movimentosInvalidosRobo1);
        System.out.println("Movimentos válidos do robô " + robo2.getCor() + ": " + movimentosValidosRobo2);
        System.out.println("Movimentos inválidos do robô " + robo2.getCor() + ": " + movimentosInvalidosRobo2);

        scanner.close();
    }

    public static ArrayList<Robo> inicializarRobos(Scanner scanner) {
        ArrayList<String> cores = new ArrayList<>(
                Arrays.asList("Azul", "Vermelho", "Verde", "Amarelo", "Roxo", "Preto", "Branco"));
        ArrayList<Robo> robos = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            System.out.println("Escolha a cor do Robô" + (i + 1) + ": ");
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
            Robo robo = new Robo(cores.get(escolha));
            robos.add(robo);
            cores.remove(escolha);
        }
        return robos;
    }
}
