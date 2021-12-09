import java.io.*;

public class Tag08{

    private int anzahl, ergebnis;
    private int[] laenge, outLaenge;
    private String[] sTeile;
    private String[] buchstabe = {"a","b","c","d","e","f","g"};
    private boolean[][] zahl, chiffre, output, fuenfer, sechser;
    private String dateiname = "tag08.txt";

    public Tag08(){
        chiffre = new boolean[10][7];
        output = new boolean[4][7];
        zahl = new boolean[10][7];
        fuenfer = new boolean[3][7];
        sechser = new boolean[3][7];
        laenge = new int[10];
        outLaenge = new int[4];
        anzahl = 0;
        ergebnis = 0;
    }
    
    private void txtDurchgehen1(){
        String x;
        try{
            BufferedReader in = new BufferedReader(new FileReader(dateiname));
            while((x=in.readLine())!=null){
                this.zeileInChiffres(x);
                this.outputLaengen();
            }
            in.close();
        }catch(Exception e){}
    }

    private void txtDurchgehen2(){
        String x;
        try{
            BufferedReader in = new BufferedReader(new FileReader(dateiname));
            while((x=in.readLine())!=null){
                this.zeileInChiffres(x);
                this.chiffreLaengen();
                this.zuordnen();
                this.outputBerechnen();
            }
            in.close();
        }catch(Exception e){}
    }

    private void zeileInChiffres(String s){
        sTeile = s.split(" ");
        for(int i=0; i<10; i++){        
            for(int k=0; k<7; k++){
                chiffre[i][k]=sTeile[i].contains(buchstabe[k]);
            }       
        }
        for(int i=0; i<4; i++){        
            for(int k=0; k<7; k++){
                output[i][k]=sTeile[i+11].contains(buchstabe[k]);
            }       
        }
    }

    private void chiffreLaengen(){
        int nrFuenfer = 0;
        int nrSechser = 0;
        for(int i=0; i<10; i++){
            laenge[i]=0;
            for(int k=0; k<7; k++){
                if(chiffre[i][k])laenge[i]++;
            }
            if(laenge[i]==5){
                fuenfer[nrFuenfer]=chiffre[i];
                nrFuenfer++;
            }
            if(laenge[i]==6){
                sechser[nrSechser]=chiffre[i];
                nrSechser++;
            }
        }
    }
    
    private void outputLaengen(){
        for(int i=0; i<4; i++){
            outLaenge[i]=0;
            for(int k=0; k<7; k++){
                if(output[i][k])outLaenge[i]++;
            }
            if(outLaenge[i]==2 || outLaenge[i]==3 || outLaenge[i]==4 || outLaenge[i]==7)anzahl++;
        }
    }
    
    private void zuordnen(){
        boolean[] fuenferErledigt = new boolean[3];
        boolean[] sechserErledigt = new boolean[3];
        for(int i=0; i<10; i++){
            switch(laenge[i]){
                case 2: zahl[1]=chiffre[i]; break;
                case 3: zahl[7]=chiffre[i]; break;
                case 4: zahl[4]=chiffre[i]; break;
                case 7: zahl[8]=chiffre[i]; break;
            }
        }
        for(int i=0; i<3; i++){
            if(!chiffreAEnthaeltB(sechser[i],zahl[1])){
                zahl[6]=sechser[i];
                sechserErledigt[i]=true;
            }
        }
        for(int i=0; i<3; i++){
            if(chiffreAEnthaeltB(sechser[i],zahl[4])){
                zahl[9]=sechser[i];
                sechserErledigt[i]=true;
            }
        }
        for(int i=0; i<3; i++){
            if(!sechserErledigt[i]){
                zahl[0]=sechser[i];
                sechserErledigt[i]=true;
            }
        }
        for(int i=0; i<3; i++){
            if(chiffreAEnthaeltB(fuenfer[i],zahl[1])){
                zahl[3]=fuenfer[i];
                fuenferErledigt[i]=true;
            }
        }
        for(int i=0; i<3; i++){
            if(chiffreAEnthaeltB(zahl[6],fuenfer[i])){
                zahl[5]=fuenfer[i];
                fuenferErledigt[i]=true;
            }
        }
        for(int i=0; i<3; i++){
            if(!fuenferErledigt[i]){
                zahl[2]=fuenfer[i];
                fuenferErledigt[i]=true;
            }
        }

    }
    
    private boolean chiffreAEnthaeltB(boolean[] A, boolean[] B){
        for(int i=0; i<7; i++){
            if(!A[i]&&B[i])return false;
        }
        return true;
    }

    private boolean chiffreAIstGleichB(boolean[] A, boolean[] B){
        for(int i=0; i<7; i++){
            if(!A[i]&&B[i] || A[i]&&!B[i])return false;
        }
        return true;
    }

    private void outputBerechnen(){
        int wert = 0;
        int faktor = 1;
        for(int i=3; i>=0; i--){
            for(int k=0; k<10; k++){
                if(this.chiffreAIstGleichB(output[i],zahl[k])){
                    wert += k*faktor;
                }
            }
            faktor *= 10;
        }
        ergebnis += wert;
    }

    public int aufgabe1(){
        this.txtDurchgehen1();
        return anzahl;
    }
    
    public int aufgabe2(){
        this.txtDurchgehen2();
        return ergebnis;
    }
}