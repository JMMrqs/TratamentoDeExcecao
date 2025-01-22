public class RoboInteligente extends Robo {

    public RoboInteligente(String cor) {
        super(cor);
    }

    @Override
    public void mover(int direcao) {
        boolean movimentoValido = false;
        while (!movimentoValido) {
            try {
                super.mover(direcao);
                movimentoValido = true;
            } catch (MovimentoInvalidoException e) {
                direcao = (int) (Math.random() * 4) + 1;
            }
        }
    }

    @Override
    public void mover(String direcao) {
        boolean movimentoValido = false;
        while (!movimentoValido) {
            try {
                super.mover(direcao);
                movimentoValido = true;
            } catch (MovimentoInvalidoException e) {
                int direcaoInt = (int) (Math.random() * 4) + 1;
                switch (direcaoInt) {
                    case 1:
                        direcao = "up";
                        break;
                    case 2:
                        direcao = "down";
                        break;
                    case 3:
                        direcao = "left";
                        break;
                    case 4:
                        direcao = "right";
                        break;
                }
            }
        }
    }
}
