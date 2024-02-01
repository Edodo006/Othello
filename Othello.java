import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

public class Othello{
    //VANESSA
    public static final int LATI = 8;
    private Pedine pedine;
    private Othelliera othelliera;

    public Pedine getPedine(){
        return this.pedine;
    }

    //VANESSA
    public Othelliera getOthelliera(){
        return this.othelliera;
    }

    //VANESSA
    public Othello(int LATI){
        this.pedine = new Pedine(true, 2, 2); 
        this.othelliera = new Othelliera(LATI, new int[LATI][LATI]);
        this.othelliera.inizio();
    }

    // EDOARDO
    public static void scritta() {
        int width = 50;
        int height = 30;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();
        g.setFont(new Font("SansSerif", Font.BOLD, 18));
        Graphics2D graphics = (Graphics2D) g;
        graphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        graphics.drawString("OTHELLO", 10, 20);
        for (int y = 0; y < height; y++) {
            StringBuilder sb = new StringBuilder();
            for (int x = 0; x < width; x++) {
                sb.append(image.getRGB(x, y) == -16777216 ? " " : "$");
            }
            if (sb.toString().trim().isEmpty()) {
                continue;
            }
            System.out.println(sb);
        }
        System.out.println();
        System.out.println();
        System.out.println();
    }

    // ESTER
    public static void stampaGrafica(int[][] matrice) {
        System.out.println();
        System.out.print("  ");
        for (int i = 0; i < LATI; i++) {
            System.out.print(i + " ");
        }
        System.out.println();

        for (int i = 0; i < LATI; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < LATI; j++) {
                if (matrice[i][j] == 0) {
                    System.out.print("- ");
                } else if (matrice[i][j] == 1) {
                    System.out.print("B ");
                } else if (matrice[i][j] == 2) {
                    System.out.print("N ");
                }
            }
            System.out.println();
        }
    }

    //VANESSA
    public void eseguiMossa(int x, int y, boolean turno){
        if (othelliera.posizioneValida(x, y)){
            int[][] matrice = othelliera.getMatrice();
            int pedinaDaPosizionare = 0;
            if(turno){
                pedinaDaPosizionare = 1;
            } else if(!turno){
                pedinaDaPosizionare = 2;
            }

            if(verificaMossaValida(x, y, pedinaDaPosizionare, matrice)) {
                matrice[x][y] = pedinaDaPosizionare;
                invertiPedine(x, y, pedinaDaPosizionare, matrice);
                if(turno) {
                    pedine.incrementaPedineBianche();
                } else if(!turno){
                    pedine.incrementaPedineNere();
                }

                pedine.setPedina(!turno);

            } else {
                System.out.println("Coordinate non valide. Riprova.");
            }

            othelliera.setMatrice(matrice);
        } else {
            System.out.println("Coordinate non valide. Riprova.");
        }
    }

    //EDOARDO
    private boolean verificaMossaValida(int x, int y, int pedina, int[][] matrice) {
        if(othelliera.statoCella(matrice, x, y)==false){
            return false;
        }
        for(int dirX = -1; dirX <= 1; dirX++) {
            for (int dirY = -1; dirY <= 1; dirY++) {
                if(dirX != 0 || dirY != 0) {
                    if (verificaDirezione(x, y, pedina, matrice, dirX, dirY)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    //ESTER
    private boolean verificaDirezione(int x, int y, int pedina, int[][] matrice, int dirX, int dirY) {
        int posX = x + dirX;
        int posY = y + dirY;

        if(othelliera.posizioneValida(posX, posY) == false || matrice[posX][posY] != pedina % 2 + 1) {
            return false;
        }
        while (othelliera.posizioneValida(posX, posY) && matrice[posX][posY] == pedina % 2 + 1) {
            posX += dirX;
            posY += dirY;
        }
        return othelliera.posizioneValida(posX, posY) && matrice[posX][posY] == pedina;
    }

    //VANESSA
    private void invertiPedine(int x, int y, int pedina, int[][] matrice){
        for(int dirX = -1; dirX <= 1; dirX++){
            for(int dirY = -1; dirY <= 1; dirY++){
                if(dirX != 0 || dirY != 0){
                    if(verificaDirezione(x, y, pedina, matrice, dirX, dirY)){
                        int posX = x + dirX;
                        int posY = y + dirY;
                        while(matrice[posX][posY] == pedina % 2 + 1){
                            matrice[posX][posY] = pedina;
                            posX += dirX;
                            posY += dirY;
                        }
                    }
                }
            }
        }
    }

    //EDOARDO
    public static void determinaVincitore(Othello gioco) {
        int pedineBianche = gioco.getPedine().getPedinaB();
        int pedineNere = gioco.getPedine().getPedinaN();

        if(pedineBianche > pedineNere){
            System.out.println("Il giocatore bianco ha vinto con " + pedineBianche + " pedine contro " + pedineNere + " pedine nere.");
        }else if(pedineBianche < pedineNere){
            System.out.println("Il giocatore nero ha vinto con " + pedineNere + " pedine contro " + pedineBianche + " pedine bianche.");
        }else{
            System.out.println("La partita è finita in pareggio, entrambi i giocatori che hanno " + pedineBianche + " pedine ciascuno.");
        }
    }

    //EDOARDO
    public int decisioneRiga(){
        Random rand = new Random();
        int riga = rand.nextInt(9);
        return riga;
    }

    //EDOARDO
    public int decisioneCol(){
        Random rand = new Random();
        int colonna = rand.nextInt(9);
        return colonna;
    }

    //VANESSA
    public static void giocaMultiplayer() {
        Othello gioco = new Othello(LATI);
        while(gioco.getOthelliera().fullOthelliera(gioco.getOthelliera().getMatrice()) == false){
            stampaGrafica(gioco.getOthelliera().getMatrice());
            if(gioco.getPedine().getPedina()){
                System.out.println("Turno del giocatore bianco");
            }else{
                System.out.println("Turno del giocatore nero");
            }           
            System.out.println("Inserisci la riga:");
            int riga = Leggi.unInt();
            System.out.println("Inserisci la colonna:");
            int colonna = Leggi.unInt();
            gioco.eseguiMossa(riga, colonna, gioco.getPedine().getPedina());
        }
        gioco.determinaVincitore(gioco);
    }

    //EDOARDO
    public static void giocaControPc(){
        Othello gioco = new Othello(LATI);
        while(gioco.getOthelliera().fullOthelliera(gioco.getOthelliera().getMatrice()) == false){
            stampaGrafica(gioco.othelliera.getMatrice());
            if(gioco.getPedine().getPedina()){
                System.out.println("Inserisci la riga:");
                int riga = Leggi.unInt();
                System.out.println("Inserisci la colonna:");
                int colonna = Leggi.unInt();
                gioco.eseguiMossa(riga, colonna, gioco.getPedine().getPedina());
            }else{
                System.out.println("Turno del giocatore nero");
                int rigaPc = gioco.decisioneRiga();
                int colPc = gioco.decisioneCol();
                gioco.eseguiMossa(rigaPc, colPc, gioco.getPedine().getPedina());
                stampaGrafica(gioco.othelliera.getMatrice());
                System.out.print("Il pc ha mosso la pedina alla riga: " + rigaPc + " e alla colonna: " + colPc);
            }
        }
        gioco.determinaVincitore(gioco);
    }

    //EDOARDO
    public static void menu(Othello gioco) {
        int scelta;
        scritta();

        System.out.println("==MENU DI GIOCO OTHELLO==");
        System.out.println();
        System.out.println("[1] Nuova partita");
        System.out.println("[2] Esci dal gioco");
        System.out.println();
        System.out.println("Inserisci l'opzione da te scelta");
        scelta = Leggi.unInt();

        switch (scelta) {
            case 1: {
                    do {
                        System.out.println("==Scegli la modalità di gioco==");
                        System.out.println("[1] Tu contro il tuo pc, chi vincera' ???");
                        System.out.println("[2] Modalità multiplayer");
                        System.out.println("[3] ESCI DAL GIOCO");
                        System.out.println("Inserisci l'opzione da te scelta");
                        System.out.println();
                        int scelta2 = Leggi.unInt();
                        System.out.println();
                        switch (scelta2) {
                            case 1: {
                                    giocaControPc();
                                    System.out.println("Premi un tasto per tornare al menu principale");
                                    int scelta3 = Leggi.unInt();
                                    break;
                                }
                            case 2: {
                                    giocaMultiplayer();
                                    System.out.println("Premi un tasto per tornare al menu principale");
                                    int scelta3 = Leggi.unInt();
                                    break;
                                }
                            case 3: {
                                    System.out.println("MANDI, MANDIIII");
                                    System.exit(0);
                                    break;
                                }
                            default: {
                                    System.out.println("Hai inserito un opzione non valida");
                                    break;
                                }
                        }                        
                    } while (true);
                }

            case 2: {
                    System.out.print("Perchè hai aperto Othello allora??, ci vediamo alla prossima");
                    System.exit(0);
                    break;
                }

            default: {
                    System.out.println("Hai inserito un opzione non valida");
                    break;
                }
        }
    }

    public static void main(String[] args) {
        Othello gioco = new Othello(LATI);
        menu(gioco);
    }
}
