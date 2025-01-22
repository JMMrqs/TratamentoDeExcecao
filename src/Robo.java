public class Robo {
    private int x;
    private int y;
    private int prevX;
    private int prevY;
    private String cor;
    private boolean explodido;

    public Robo(String cor) {
        this.cor = cor;
        this.x = 0;
        this.y = 0;
        this.explodido = false;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String getCor() {
        return cor;
    }

    public boolean isExplodido() {
        return explodido;
    }

    public void setExplodido(boolean explodido) {
        this.explodido = explodido;
    }

    public void mover(String direcao) throws MovimentoInvalidoException {
        if (explodido)
            return;

        prevX = x;
        prevY = y;

        switch (direcao.toLowerCase()) {
            case "up":
                if (y + 1 > 3) {
                    System.out.println("Posição atual: (" + x + ", " + y + ")");
                    throw new MovimentoInvalidoException("** Tentativa de movimento inválido: " + direcao);
                }
                y++;
                break;
            case "down":
                if (y - 1 < 0) {
                    System.out.println("Posição atual: (" + x + ", " + y + ")");
                    throw new MovimentoInvalidoException("** Tentativa de movimento inválido: " + direcao);
                }
                y--;
                break;
            case "right":
                if (x + 1 > 3) {
                    System.out.println("Posição atual: (" + x + ", " + y + ")");
                    throw new MovimentoInvalidoException("** Tentativa de movimento inválido: " + direcao);
                }
                x++;
                break;
            case "left":
                if (x - 1 < 0) {
                    System.out.println("Posição atual: (" + x + ", " + y + ")");
                    throw new MovimentoInvalidoException("** Tentativa de movimento inválido: " + direcao);
                }
                x--;
                break;
            default:
                System.out.println("Posição atual: (" + x + ", " + y + ")");
                throw new MovimentoInvalidoException("** Tentativa de movimento inválido: " + direcao);
        }
        System.out.println("Posição atual: (" + x + ", " + y + ")");
    }

    public void mover(int direcao) throws MovimentoInvalidoException {
        switch (direcao) {
            case 1:
                mover("up");
                break;
            case 2:
                mover("down");
                break;
            case 3:
                mover("right");
                break;
            case 4:
                mover("left");
                break;
            default:
                throw new MovimentoInvalidoException("Tentativa de movimento inválido: " + direcao);
        }
    }

    public boolean encontrouAlimento(int alimentoX, int alimentoY) {
        return this.x == alimentoX && this.y == alimentoY;
    }

    public void voltarPosAnterior() {
        this.x = prevX;
        this.y = prevY;
    }
}
