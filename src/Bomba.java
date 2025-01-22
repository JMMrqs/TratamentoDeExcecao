public class Bomba extends Obstaculo {
    public Bomba(int idX, int idY) {
        super(idX, idY);
    }

    @Override
    public void bater(Robo robo) {
        System.out.println("O robô " + robo.getCor() + " explodiu ao bater na bomba!");
        robo.setExplodido(true);
    }
}
