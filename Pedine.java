//VANESSA
class Pedine{
    private int nPedineN;
    private int nPedineB;
    private int vMax;
    private boolean pedina;

    public Pedine(boolean pedina, int nPedineB, int nPedineN){
        this.pedina = pedina;
        this.nPedineN = nPedineN;
        this.nPedineB = nPedineN;
        this.vMax = Math.max(nPedineB, nPedineN);
    }

    public boolean getPedina(){
        return this.pedina;
    }

    public void setPedina(boolean pedina){
        this.pedina = pedina;
    }

    public int getPedinaN(){
        return this.nPedineN;
    }

    public int getPedinaB(){
        return this.nPedineB;
    }

    public int getMax(){
        return this.vMax;
    }

    public void incrementaPedineNere(){
        this.nPedineN++;
        aggiornaMax();
    }

    public void incrementaPedineBianche(){
        this.nPedineB++;
        aggiornaMax();
    }

    private void aggiornaMax() {
        this.vMax = Math.min(nPedineB, nPedineN);
    }

}
