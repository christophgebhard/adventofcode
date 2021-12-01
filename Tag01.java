import java.io.*;

public class Tag01{

    private int[] z;
    private String[] s;
    private String dateiname = "tag01.txt";

    private void txtInStringFeld(){
        int zaehler=0;
        int i=0;
        try{
            BufferedReader in = new BufferedReader(new FileReader(dateiname));
            while((in.readLine())!=null){
                zaehler++;
            }
            s = new String[zaehler];
            z = new int[zaehler];
            in = new BufferedReader(new FileReader(dateiname));
            while((s[i]=in.readLine())!=null){                
                i++;
            }
            in.close();
        }catch(Exception e){}
    }

    private void stringFeldInIntFeld(){
        for(int i=0; i<z.length; i++){
            try{
                z[i] = Integer.valueOf(s[i]);
            }catch(NumberFormatException e){}
        }
    }
    
    public int aufgabe1(){
        int ergebnis = 0;
        this.txtInStringFeld();
        this.stringFeldInIntFeld();
        for(int i=1; i<z.length; i++){
            if(z[i]>z[i-1])ergebnis++;
        }
        return ergebnis;
    }

    public int aufgabe2(){
        int ergebnis = 0;
        this.txtInStringFeld();
        this.stringFeldInIntFeld();
        for(int i=3; i<z.length; i++){
            if(z[i]>z[i-3])ergebnis++;
        }
        return ergebnis;
    }

}
