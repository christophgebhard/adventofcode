import java.io.*;
import java.util.*;

public class Tag01{

    private int[] z;
    private ArrayList<String> sList;
    private String dateiname = "tag01.txt";

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

    private void stringArrayListInIntFeld(){
        z = new int[sList.size()];
        for(int i=0; i<z.length; i++){
            try{
                z[i] = Integer.valueOf(sList.get(i));
            }catch(NumberFormatException e){}
        }
    }

    public int aufgabe1(){
        int ergebnis = 0;
        this.txtInStringArrayList();
        this.stringArrayListInIntFeld();
        for(int i=1; i<z.length; i++){
            if(z[i]>z[i-1])ergebnis++;
        }
        return ergebnis;
    }

    public int aufgabe2(){
        int ergebnis = 0;
        this.txtInStringArrayList();
        this.stringArrayListInIntFeld();
        for(int i=3; i<z.length; i++){
            if(z[i]>z[i-3])ergebnis++;
        }
        return ergebnis;
    }

}
