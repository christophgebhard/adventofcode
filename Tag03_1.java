import java.io.*;
import java.util.*;

public class Tag03_1{

    private ArrayList<String> sList;
    private String dateiname = "tag03.txt";
    private int[][] bit;
    private int[] zaehler, gBin, eBin;
    private int gamma, epsilon, anzahl, breite;

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
        anzahl = sList.size();
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
    
    private void haeufigsteZaehlen(){
        for(int k=0; k<breite; k++){
            for(int i= 0; i<anzahl; i++){
                zaehler[k]+=bit[i][k];
            }
            if(zaehler[k]*2>=anzahl){
                gBin[k]=1;
            }
            eBin[k]=1-gBin[k];
        }    
    }
    
    private void bitsInDezimalUmwandeln(){
        int faktor = 1;
        for(int k=breite-1; k>=0; k--){
            gamma+=faktor*gBin[k];
            epsilon+=faktor*eBin[k];
            faktor*=2;
        }    
    }

    public int aufgabe1(){
        this.txtInStringArrayList();
        this.listeInIntFeld();
        gamma = 0;
        epsilon = 0;
        zaehler = new int[breite];
        gBin = new int[breite];
        eBin = new int[breite];
        this.haeufigsteZaehlen();
        this.bitsInDezimalUmwandeln();
        return gamma*epsilon;
    }

}