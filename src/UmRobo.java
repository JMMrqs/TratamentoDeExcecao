import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class UmRobo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Robo robo = inicializarRobo(scanner);
        System.out.println(
                "A cor do robô será " + robo.getCor() + ".");

        System.out.print("Digite a posição X do alimento: ");
        int alimentoX = scanner.nextInt();
        System.out.print("Digite a posição Y do alimento: ");
        int alimentoY = scanner.nextInt();
        scanner.nextLine();

        while (!robo.encontrouAlimento(alimentoX, alimentoY)) {
            Matriz.printMatriz(robo, alimentoX, alimentoY);
            System.out.print("Digite o movimento (up, down, right, left ou 1, 2, 3, 4): ");
            String movimento = scanner.next();
            try {
                try {
                    robo.mover(Integer.parseInt(movimento));
                } catch (NumberFormatException e) {
                    robo.mover(movimento);
                }
            } catch (MovimentoInvalidoException e) {
                System.out.println(e.getMessage());
            }
        }

        System.out.println(
                "O robô " + robo.getCor() + " encontrou o alimento na posição (" + alimentoX + ", " + alimentoY + ")");
        scanner.close();
    }

    public static Robo inicializarRobo(Scanner scanner) {
        ArrayList<String> cores = new ArrayList<>(
                Arrays.asList("Azul", "Vermelho", "Verde", "Amarelo", "Roxo", "Preto", "Branco"));
        System.out.println("Escolha a cor do Robô: ");
        int escolha = -1;
        while (escolha < 0 || escolha >= cores.size()) {
            for (int i = 0; i < cores.size(); i++) {
                System.out.println(i + " - " + cores.get(i));
            }
            escolha = scanner.nextInt();
            if (escolha < 0 || escolha >= cores.size()) {
                System.out.println("Opção inválida. Escolha a cor do Robô: ");
            }
        }
        Robo robo = new Robo(cores.get(escolha));
        return robo;
    }
}
