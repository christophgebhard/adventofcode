import java.io.*;
import java.util.*;

public class Tag03_2{

    private ArrayList<String> sList;
    private String dateiname = "tag03.txt";
    private int[][] bit;
    private int ox, co, anzahl, breite;
    private boolean[] winnerListe;

    private void txtInStringArrayList(){
        sList = new ArrayList<String>();
        String x;
        try{
            BufferedReader in = new BufferedReader(new FileReader(dateiname));
            while((x=in.readLine())!=null){
                sList.add(x);
            }
            in.close();
        }catch(Exception e){}
    }        

    private void listeInIntFeld(){
        anzahl = sList.size(); System.out.println(anzahl);
        breite = sList.get(0).length();
        bit = new int[anzahl][breite];
        char[] chars;
        for(int i=0; i<anzahl; i++){
            chars = sList.get(i).toCharArray();
            for(int k=0; k<breite; k++){
                try{
                    bit[i][k] = Integer.valueOf(chars[k]+"");
                }catch(NumberFormatException e){}                
            }
        }
    }   

    private void rating(char typ){
        for(int k=0; k<breite; k++){
            winnerListe = new boolean[anzahl];
            this.winnerMarkieren(k,typ);
            this.winnerInListe();
            this.listeInIntFeld();
            if(sList.size()==1)return;
        }
    }

    private void winnerMarkieren(int spalte, char typ){
        int zaehler = 0;
        int winnerWert = 0;
        for(int i=0; i<anzahl; i++){
            zaehler+=bit[i][spalte];
        }
        if(zaehler*2>=anzahl && typ=='o'){
            winnerWert = 1;
        }
        if(zaehler*2<anzahl && typ=='c'){
            winnerWert = 1;
        }
        for(int i=0; i<anzahl; i++){
            if(bit[i][spalte]==winnerWert){
                winnerListe[i]=true;
            }
        }
    }

    private void winnerInListe(){
        sList = new ArrayList<String>();
        for(int i=0; i<anzahl; i++){
            if(winnerListe[i]){
                String tmp = "";
                for(int k=0; k<breite; k++){
                    tmp = tmp + bit[i][k];
                }
                sList.add(tmp);
            }
        }
    }

    private void oxBitsInDezimalUmwandeln(){
        int faktor = 1;
        for(int k=breite-1; k>=0; k--){
            ox+=faktor*bit[0][k];
            faktor*=2;
        }    
    }

    private void coBitsInDezimalUmwandeln(){
        int faktor = 1;
        for(int k=breite-1; k>=0; k--){
            co+=faktor*bit[0][k];
            faktor*=2;
        }    
    }

    public int aufgabe2(){
        ox = 0;
        co = 0;
        
        this.txtInStringArrayList();
        this.listeInIntFeld();
        this.rating('o');
        this.oxBitsInDezimalUmwandeln();

        this.txtInStringArrayList();
        this.listeInIntFeld();
        this.rating('c');
        this.coBitsInDezimalUmwandeln();
        
        return ox*co;
    }

}