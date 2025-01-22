public class RoboInteligente extends Robo {

    public RoboInteligente(String cor) {
        super(cor);
    }

    @Override
    public void mover(int direcao) {
        boolean movimentoValido = false;
        boolean messageSent = false;
        while (!movimentoValido) {
            try {
                super.mover(direcao);
                movimentoValido = true;
            } catch (MovimentoInvalidoException e) {
                direcao = (int) (Math.random() * 4) + 1;
                if (!messageSent)
                System.out.println("Robô inteligente reajustou seu movimento.");
                messageSent = true;
            }
        }
    }

    @Override
    public void mover(String direcao) {
        boolean movimentoValido = false;
        boolean messageSent = false;
        while (!movimentoValido) {
            try {
                super.mover(direcao);
                movimentoValido = true;
            } catch (MovimentoInvalidoException e) {
                if (!messageSent)
                System.out.println("Tentativa falha: " + direcao + ". Robô inteligente reajustou seu movimento.");
                messageSent = true;
                
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
