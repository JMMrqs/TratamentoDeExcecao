public abstract class Obstaculo {
    protected int idX;
    protected int idY;

    public Obstaculo(int idX, int idY) {
        this.idX = idX;
        this.idY = idY;
    }

    public int getIdX() {
        return idX;
    }

    public int getIdY() {
        return idY;
    }

    public abstract void bater(Robo robo);
}
