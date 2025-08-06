package com.example.calculatorandroidapplication;
// Iveta Teivena mājas darbs U3
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView darbibu_logs, vestures_logs;
    private Float skaitlis_nr1;
    private Float skaitlis_nr2;
    private String temp_skaitlis="";
    private String darbiba="";
    private String viss_uzdevums_text="";
    Float iznakums;
    String iznakums_str;


    Button poga_0, poga_1,poga_2,poga_3,poga_4,poga_5,poga_6,poga_7,poga_8,poga_9, poga_komats;
    Button poga_saskaitit, poga_atnemt,poga_dalit,poga_reizinat, poga_rezultats, poga_dzest;


    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_main);
        darbibu_logs = findViewById(R.id.darbibu_logs);
        vestures_logs =findViewById(R.id.vestures_logs);

        taisam_pogas(poga_0,R.id.poga_0);
        taisam_pogas(poga_1,R.id.poga_1);
        taisam_pogas(poga_2,R.id.poga_2);
        taisam_pogas(poga_3,R.id.poga_3);
        taisam_pogas(poga_4,R.id.poga_4);
        taisam_pogas(poga_5,R.id.poga_5);
        taisam_pogas(poga_6,R.id.poga_6);
        taisam_pogas(poga_7,R.id.poga_7);
        taisam_pogas(poga_8,R.id.poga_8);
        taisam_pogas(poga_9,R.id.poga_9);
        taisam_pogas(poga_dalit,R.id.poga_dalit);
        taisam_pogas(poga_reizinat,R.id.poga_reizinat);
        taisam_pogas(poga_atnemt,R.id.poga_atnemt);
        taisam_pogas(poga_saskaitit,R.id.poga_saskaitit);
        taisam_pogas(poga_rezultats,R.id.poga_rezultats);
        taisam_pogas(poga_dzest,R.id.poga_dzest);
        taisam_pogas(poga_komats,R.id.poga_komats);
    }
    public static String parbaudit_komatu(Float iznakums) {
        if (iznakums == null) return "null";

        if (iznakums == Math.floor(iznakums)) {
            // Whole number — return as integer string
            return Integer.toString(iznakums.intValue());
        } else {
            // Decimal part exists — return full float string
            return iznakums.toString();
        }
    }
    void taisam_pogas(Button poga,int pogas_id ){
        poga=findViewById(pogas_id);
        poga.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Button poga =(Button) v;
        String input = poga.getText().toString();
        //viss_uzdevums_text = darbibu_logs.getText().toString();

        if (input.equals("C")) {
            vestures_logs.setText("");
            darbibu_logs.setText("0");
            skaitlis_nr1=null;
            skaitlis_nr2=null;
            temp_skaitlis="";
            darbiba="";
            viss_uzdevums_text="";
        }
        else {
            //ja tempskeitlis sakas ar 0 un uzreiz neseko . tad nodzesam visu
            if (temp_skaitlis.length() == 2 && (temp_skaitlis.charAt(0) == '0' && temp_skaitlis.charAt(1) != '.')){
                viss_uzdevums_text="";
                temp_skaitlis="";
                input="";

            }

            //ja darbibas
            else if (input.equals("/") || input.equals("*") || input.equals("+") || input.equals("-")) {
                System.out.println("viss uzdevums: "+viss_uzdevums_text);

                // ja temp skaitlis nav tuks un darbiba jau nav noradita
                if (!temp_skaitlis.isEmpty() && darbiba.isEmpty()) {
                    darbiba = input;
                    skaitlis_nr1=Float.parseFloat(temp_skaitlis);
                    viss_uzdevums_text=viss_uzdevums_text+input;
                    System.out.println("tempskaitlis nav tuks: "+temp_skaitlis+" viss uzdevums: "+viss_uzdevums_text);
                    temp_skaitlis="";

                }
                // else
                else{
                    input="";
                    //viss_uzdevums_text="";
                    System.out.println("ievadita darbiba: "+temp_skaitlis.isEmpty()+" viss uzdevums: "+viss_uzdevums_text);

                }
            }
            //ja gribam rezultatu
            else if (input.equals("=")){
                //ja jau satur =
                System.out.println("viss uzdevuma text: "+viss_uzdevums_text);
                System.out.println("darbiba: "+darbiba+" tempskaitlis: "+temp_skaitlis);
                if (viss_uzdevums_text.contains("=")) {

                    return;
                }
                //ja nav ievadita darbiba
                else if(darbiba.equals("")){
                    input="";
                    return;
                }
                //vai ir abi skaitli, skaitlis 1 un tempskaitlis kas tiks setots uz skaitli 2 zemaak
                else if (skaitlis_nr1 ==null || temp_skaitlis.isBlank()){
                    input="";
                    System.out.println("sk1: "+skaitlis_nr1+" sk2: "+skaitlis_nr2);
                    return;
                }
                else {
                    //handlojam erroru
                    try {
                        skaitlis_nr2=Float.parseFloat(temp_skaitlis);
                        temp_skaitlis="";
                        // Successfully parsed, you can now use skaitlis_nr2
                    } catch (NumberFormatException e) {
                        // Handle the case where temp_skaitlis is not a valid float
                        System.out.println("Invalid float: " + temp_skaitlis);
                        // Optionally set a default value
                        temp_skaitlis="0";
                    }


                    System.out.println("sk11: "+skaitlis_nr1+" sk22: "+skaitlis_nr2);
                    // kalkulatora logika
                    switch (darbiba) {
                        case "+":
                            iznakums = skaitlis_nr1 + skaitlis_nr2;
                            iznakums_str = parbaudit_komatu(iznakums);
                            vestures_logs.setText(viss_uzdevums_text);
                            viss_uzdevums_text=iznakums_str;
                            skaitlis_nr1=iznakums;
                            skaitlis_nr2=null;
                            temp_skaitlis=iznakums_str;
                            darbiba="";
                            input="";
                            break;
                        case "-":
                            iznakums = skaitlis_nr1 - skaitlis_nr2;
                            iznakums_str = parbaudit_komatu(iznakums);
                            vestures_logs.setText(viss_uzdevums_text);
                            viss_uzdevums_text=iznakums_str;
                            skaitlis_nr1=iznakums;
                            skaitlis_nr2=null;
                            temp_skaitlis=iznakums_str;
                            darbiba="";
                            input="";
                            break;
                        case "*":
                            iznakums = skaitlis_nr1 * skaitlis_nr2;
                            iznakums_str = parbaudit_komatu(iznakums);
                            vestures_logs.setText(viss_uzdevums_text);
                            viss_uzdevums_text=iznakums_str;
                            skaitlis_nr1=iznakums;
                            skaitlis_nr2=null;
                            temp_skaitlis=iznakums_str;
                            darbiba="";
                            input="";
                            break;
                        case "/":
                            iznakums = (skaitlis_nr1 / skaitlis_nr2);
                            iznakums_str = parbaudit_komatu(iznakums);
                            vestures_logs.setText(viss_uzdevums_text);
                            viss_uzdevums_text=iznakums_str;
                            skaitlis_nr1=iznakums;
                            skaitlis_nr2=null;
                            temp_skaitlis=iznakums_str;
                            darbiba="";
                            input="";
                            break;
                    }
                }

            }

            if (
                    !input.equals("/") &&
                            !input.equals("*") &&
                            !input.equals("+") &&
                            !input.equals("-") &&
                            !input.equals("=")
            ){

                //ja tempskaitlis ir 0 un ievadam input velvienu 0
                if (temp_skaitlis.equals("0") && input.trim().equals("0")) {
                    System.out.println("nepievienoju tempskaitlim: '"+input+"'");
                    temp_skaitlis=input;
                    viss_uzdevums_text=input;
                }
                // ja tempskaitli ir tuks un ievada komatu, vai tempskaitlis satur komatu un ievada velvienu  komatu, tad skip
                else if (temp_skaitlis.isBlank() && input.equals(".") || temp_skaitlis.contains(".") && input.equals(".")){
                    System.out.println("nepievienoju tempskaitlim jo jau ir viens komats: '"+input+"'");
                }
                else {
                    System.out.println("Pievienoju tempskaitlim: "+temp_skaitlis+" vertibu: "+input+" viss uzdevums pirms pievienosanas: "+viss_uzdevums_text);
                    temp_skaitlis = temp_skaitlis+input;
                    viss_uzdevums_text=viss_uzdevums_text+input;
                    System.out.println("tempskaitlis: "+temp_skaitlis+" uzdevums pec peivienosanas: "+viss_uzdevums_text);
                }

            }

            //paradam visu uzdevumu uzdevumu logaa
            System.out.println("pasas beigas: "+"viss uzdevums: "+viss_uzdevums_text+" tempskaitlis: "+temp_skaitlis+" input: "+input);
            darbibu_logs.setText(viss_uzdevums_text);
        }



    }
}