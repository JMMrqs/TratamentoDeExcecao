import java.util.List;

public class Matriz {
    static final int lado = 4;

    public static void printMatriz(Robo robo, int alimentoX, int alimentoY) {
        int roboX = robo.getX();
        int roboY = robo.getY();
        for (int y = lado - 1; y >= 0; y--) {
            for (int x = 0; x < lado; x++) {
                if (x == roboX && y == roboY) {
                    System.out.print("🤖");
                } else if (x == alimentoX && y == alimentoY) {
                    System.out.print("🥕");
                } else {
                    System.out.print(". ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void printMatriz(Robo robo1, Robo robo2, int alimentoX, int alimentoY) {
        int robo1X = robo1.getX();
        int robo1Y = robo1.getY();
        int robo2X = robo2.getX();
        int robo2Y = robo2.getY();
        String cor1 = robo1.getCor();
        String cor2 = robo2.getCor();

        System.out.println("Robô " + cor1 + ":");
        for (int y = lado - 1; y >= 0; y--) {
            for (int x = 0; x < lado; x++) {
                if (x == robo1X && y == robo1Y) {
                    System.out.print("🤖");
                } else if (x == alimentoX && y == alimentoY) {
                    System.out.print("🥕");
                } else {
                    System.out.print(". ");
                }
            }
            System.out.println();
        }
        System.out.println("Robô " + cor2 + ":");
        for (int y = lado - 1; y >= 0; y--) {
            for (int x = 0; x < lado; x++) {
                if (x == robo2X && y == robo2Y) {
                    System.out.print("🤖");
                } else if (x == alimentoX && y == alimentoY) {
                    System.out.print("🍎");
                } else {
                    System.out.print(". ");
                }
            }
            System.out.println();
        }
    }

    public static void printMatriz(Robo robo1, Robo robo2, int alimentoX, int alimentoY, List<Obstaculo> obstaculos1,
            List<Obstaculo> obstaculos2) {
        int robo1X = robo1.getX();
        int robo1Y = robo1.getY();
        int robo2X = robo2.getX();
        int robo2Y = robo2.getY();
        String cor1 = robo1.getCor();
        String cor2 = robo2.getCor();
        boolean explodido1 = robo1.isExplodido() ? true : false;
        boolean explodido2 = robo2.isExplodido() ? true : false;

        System.out.println("Robô " + cor1 + ":");
        for (int y = lado - 1; y >= 0; y--) {
            for (int x = 0; x < lado; x++) {
                boolean roboAlimento = false;
                boolean isObstaculo = false;
                if (x == robo1X && y == robo1Y && !explodido1) {
                    System.out.print("🤖");
                    roboAlimento = true;

                } else if (x == alimentoX && y == alimentoY) {
                    System.out.print("🥕");
                    roboAlimento = true;
                } else {
                    for (Obstaculo obstaculo : obstaculos1) {
                        if (obstaculo.getIdX() == x && obstaculo.getIdY() == y && obstaculo instanceof Bomba) {
                            System.out.print("💣");
                            isObstaculo = true;
                            break;
                        }
                        if (obstaculo.getIdX() == x && obstaculo.getIdY() == y) {
                            System.out.print("🪨");
                            isObstaculo = true;
                            break;
                        }
                    }
                    if (!roboAlimento && !isObstaculo) {
                        System.out.print(". ");
                    }
                }
            }
            System.out.println();
        }
        System.out.println("Robô " + cor2 + ":");
        for (int y = lado - 1; y >= 0; y--) {
            for (int x = 0; x < lado; x++) {
                boolean roboAlimento = false;
                boolean isObstaculo = false;
                if (x == robo2X && y == robo2Y && !explodido2) {
                    System.out.print("🤖");
                    roboAlimento = true;

                } else if (x == alimentoX && y == alimentoY) {
                    System.out.print("🍎");
                    roboAlimento = true;
                } else {
                    for (Obstaculo obstaculo : obstaculos2) {
                        if (obstaculo.getIdX() == x && obstaculo.getIdY() == y && obstaculo instanceof Bomba) {
                            System.out.print("💣");
                            isObstaculo = true;
                            break;
                        }
                        if (obstaculo.getIdX() == x && obstaculo.getIdY() == y) {
                            System.out.print("🪨");
                            isObstaculo = true;
                            break;
                        }
                    }
                    if (!roboAlimento && !isObstaculo) {
                        System.out.print(". ");
                    }
                }
            }
            System.out.println();
        }
    }
}