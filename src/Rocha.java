public class Rocha extends Obstaculo {
    public Rocha(int idX, int idY) {
        super(idX, idY);
    }

    @Override
    public void bater(Robo robo) {
        System.out.println("O robô " + robo.getCor() + " bateu na rocha e voltou para a posição anterior!");
        robo.voltarPosAnterior();
    }
}
