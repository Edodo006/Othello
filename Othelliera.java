class Othelliera{
    private  int lati;
    private  int[][] matrice;

    public Othelliera(int lati, int matrice[][]) {
        this.lati = lati;
        this.matrice = new int[lati][lati];
        inizio();
    }
    
    public int getLati(){
        return this.lati;
    }

    public int[][] getMatrice(){
        return this.matrice;
    }
    
    public void setMatrice(int[][] matrice){
    this.matrice = matrice;
    }

    public void inizio(){
        matrice[(lati / 2)-1][(lati / 2)-1] = 1;
        matrice[(lati / 2 + 1)-1][(lati / 2)-1] = 2;
        matrice[(lati / 2)-1][(lati / 2 + 1)-1] = 2;
        matrice[(lati / 2 + 1)-1][(lati / 2 + 1)-1] = 1;
    }

    public boolean fullOthelliera(int[][] matrice){
        int count = 0;
        for(int r=0; r<lati; r++) {
            for(int c=0; c<lati; c++) {
                if(matrice[r][c]==0){
                    count += 1;
                }
            }
        }
            return count == 0;    
    }

    public boolean statoCella(int[][] matrice, int x, int y){
        return matrice[x][y] == 0;
    }

    public boolean posizioneValida(int x, int y){
        return x >= 0 && x < lati && y >= 0 && y < lati;
    }
        
   public String toString(){
        String s = "";
        for(int r=0; r < lati; r++){
            for(int c=0; c < lati; c++){
                s += matrice[r][c];
            }
            s += "\n";
        }
        return s;
   }  
    
}
