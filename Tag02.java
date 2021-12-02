import java.io.*;

public class Tag02{

    private long horizontal, tiefe, aim;
    private String dateiname = "tag02.txt";

    private void txtDurchgehen(boolean aufgabe2){
        String x;
        int zahl=0;
        try{
            BufferedReader in = new BufferedReader(new FileReader(dateiname));
            while((x=in.readLine())!=null){
                char kategorie = x.charAt(0);
                int laenge = x.length();
                String wert = x.substring(laenge-1,laenge);
                try{
                    zahl = Integer.valueOf(wert);
                }catch(NumberFormatException e){}
                this.wertHinzufuegen(kategorie,zahl,aufgabe2);
            }
            in.close();
        }catch(Exception e){}
    }

    private void wertHinzufuegen(char kategorie, int zahl, boolean aufgabe2){
        if(!aufgabe2){
            if(kategorie=='f')horizontal+=zahl;
            if(kategorie=='d')tiefe+=zahl;
            if(kategorie=='u')tiefe-=zahl;
        }
        if(aufgabe2){
            if(kategorie=='f'){
                horizontal+=zahl;
                tiefe+=aim*zahl;
            }
            if(kategorie=='d')aim+=zahl;
            if(kategorie=='u')aim-=zahl;
        }
    }

    public long aufgabe1(){
        horizontal = 0;
        tiefe = 0;
        this.txtDurchgehen(false);
        return horizontal*tiefe;
    }

    public long aufgabe2(){
        horizontal = 0;
        tiefe = 0;
        aim = 0;
        this.txtDurchgehen(true);
        return horizontal*tiefe;
    }

}
