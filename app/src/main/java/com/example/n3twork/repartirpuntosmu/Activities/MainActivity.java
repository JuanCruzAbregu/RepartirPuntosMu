package com.example.n3twork.repartirpuntosmu.Activities;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethod;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.n3twork.repartirpuntosmu.R;

import java.lang.reflect.Array;

public class MainActivity extends ActionBarActivity {

    private EditText editTextCapturar;
    private Button btnOk;
    private ListView listViewPuntos;
    private TextView textViewAgilidad, textViewVitalidad, textViewEnergia, textViewComando, textViewFuerza;
    private String title, fza, agi, vit, ene, cmd;
    private double fzaPorcentaje, agiPorcentaje, vitPorcentaje, enePorcentaje,  cmdPorcentaje;

    private String[] guerreros = new String[]{
            "DK, BK, BM",
            "ELF, ME, HE",
            "DW, SM, GM",
            "SUM, BS, DM",
            "RF, FM",
            "MG, DM",
            "DL, LE"
    };

    private int fuerza = 0;
    private int agilidad = 0;
    private int vitalidad = 0;
    private int energia = 0;
    private int comando = 0;
    private int calculador = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher_round);

        setContentView(R.layout.activity_main);

        btnOk = (Button)findViewById(R.id.buttonOk);
        editTextCapturar = (EditText)findViewById(R.id.editTextCaputrar);
        listViewPuntos = (ListView)findViewById(R.id.listViewPuntos);
        textViewFuerza = (TextView)findViewById(R.id.textViewPuntoFuerza);
        textViewAgilidad = (TextView)findViewById(R.id.textViewPuntoAgilidad);
        textViewVitalidad = (TextView)findViewById(R.id.textViewPuntoVitalidad);
        textViewEnergia = (TextView)findViewById(R.id.textViewPuntoEnergia);
        textViewComando = (TextView)findViewById(R.id.textViewPuntoComando);


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, guerreros);
        listViewPuntos.setAdapter(adapter);

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);

                String puntosTotales = editTextCapturar.getText().toString();

                if(!puntosTotales.isEmpty()){

                    calculador = Integer.parseInt(puntosTotales);
                    listViewPuntos.setVisibility(View.VISIBLE);
                    inputMethodManager.hideSoftInputFromWindow(editTextCapturar.getWindowToken(), 0);

                }else{

                    Toast.makeText(getApplicationContext(), "Ingrese el total de puntos", Toast.LENGTH_SHORT).show();

                }

            }
        });

        listViewPuntos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                    if(position == 0){

                        fzaPorcentaje = 0.4;
                        agiPorcentaje = 0.3;
                        vitPorcentaje = 0.1;
                        enePorcentaje = 0.2;
                        cmdPorcentaje = 0.0;

                        calcularPuntos(position, fzaPorcentaje, agiPorcentaje, vitPorcentaje, enePorcentaje, cmdPorcentaje);
                        createDialog(title, fza, agi, vit, ene, cmd);

                    }

                    if(position == 1){

                        final String[] elfa = {"Agilidad","Energía"};

                        elfaDialog(position, elfa);

                    }

                    if(position == 2){

                        fzaPorcentaje = 0.02;
                        agiPorcentaje = 0.4;
                        vitPorcentaje = 0.08;
                        enePorcentaje = 0.5;
                        cmdPorcentaje = 0.0;

                        calcularPuntos(position, fzaPorcentaje, agiPorcentaje, vitPorcentaje, enePorcentaje, cmdPorcentaje);
                        createDialog(title, fza, agi, vit, ene, cmd);

                    }

                    if(position == 3){

                        fzaPorcentaje = 0.1;
                        agiPorcentaje = 0.3;
                        vitPorcentaje = 0.1;
                        enePorcentaje = 0.5;
                        cmdPorcentaje = 0.0;

                        calcularPuntos(position, fzaPorcentaje, agiPorcentaje, vitPorcentaje, enePorcentaje, cmdPorcentaje);
                        createDialog(title, fza, agi, vit, ene, cmd);

                    }

                    if(position == 4){

                        fzaPorcentaje = 0.4;
                        agiPorcentaje = 0.13;
                        vitPorcentaje = 0.07;
                        enePorcentaje = 0.4;
                        cmdPorcentaje = 0.0;

                        calcularPuntos(position, fzaPorcentaje, agiPorcentaje, vitPorcentaje, enePorcentaje, cmdPorcentaje);
                        createDialog(title, fza, agi, vit, ene, cmd);

                    }

                    if(position == 5){

                        final String[] mg = {"Caballero","Mago","Híbrido"};
                        magicGladiator(position, mg);

                    }

                    if(position == 6){

                        fzaPorcentaje = 0.25;
                        agiPorcentaje = 0.2;
                        vitPorcentaje = 0.1;
                        enePorcentaje = 0.25;
                        cmdPorcentaje = 0.2;

                        calcularPuntos(position, fzaPorcentaje, agiPorcentaje, vitPorcentaje, enePorcentaje, cmdPorcentaje);
                        createDialog(title, fza, agi, vit, ene, cmd);

                    }


            }
        });
    }

    private void createDialog(String title, String force, String agility, String vitality, String energy, String command) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        //Obligamos a tocar boton para cancelar
        builder.setCancelable(false);

        View v = LayoutInflater.from(this).inflate(R.layout.dialog_layout, null);
        builder.setView(v);

        TextView textViewTitulo = (TextView)v.findViewById(R.id.textViewTitulo);
        TextView textoFuerza    = (TextView)v.findViewById(R.id.textViewPuntoFuerza);
        TextView textoAgilidad  = (TextView)v.findViewById(R.id.textViewPuntoAgilidad);
        TextView textoVitalidad = (TextView)v.findViewById(R.id.textViewPuntoVitalidad);
        TextView textoEnergia   = (TextView)v.findViewById(R.id.textViewPuntoEnergia);
        TextView textoComando   = (TextView)v.findViewById(R.id.textViewPuntoComando);

        textViewTitulo.setText(title);
        textoFuerza.setText(force);
        textoAgilidad.setText(agility);
        textoVitalidad.setText(vitality);
        textoEnergia.setText(energy);
        textoComando.setText(command);

        builder.setPositiveButton("Nuevo", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                editTextCapturar.setText("");
                listViewPuntos.setVisibility(View.INVISIBLE);

            }
        });

        builder.setNegativeButton("Volver", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {


            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void calcularPuntos(int numTitulo, double fzaPorcentaje, double agiPorcentaje, double vitPorcentaje,
                                double enePorcentaje, double cmdPorcentaje){

        title     = guerreros[numTitulo];
        fuerza    = (int) (calculador * fzaPorcentaje);
        agilidad  = (int) (calculador * agiPorcentaje);
        vitalidad = (int) (calculador * vitPorcentaje);
        energia   = (int) (calculador * enePorcentaje);
        comando   = (int) (calculador * cmdPorcentaje);

        if(numTitulo == 0){

            if(fuerza > 64982){

                agilidad  = (int) (agilidad + (fuerza - 64982) * 0.5);
                vitalidad = (int) (vitalidad + (fuerza - 64982) * 0.15);
                energia   = (int) (energia + (fuerza - 64982) * 0.35);
                fuerza = 64982;

                if(agilidad > 64982){

                    vitalidad = (int) (vitalidad + (agilidad - 64982) * 0.35);
                    energia   = (int) (energia + (agilidad - 64982) * 0.65);
                    agilidad  = 64982;

                    if(energia > 64970){

                        vitalidad = vitalidad + (energia - 64970);
                        energia = 64970;

                        if(vitalidad > 64985){

                            vitalidad = 64985;
                            Toast.makeText(getApplicationContext(), "PJ Full", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
            comando = 0;
        }

        if(numTitulo == 2){

            if(energia > 64970){


                agilidad  = (int) (agilidad + (energia - 64970) * 0.85);
                vitalidad = (int) (vitalidad + (energia - 64970) * 0.12);
                fuerza    = (int) (fuerza + (energia - 64970) * 0.03);
                energia = 64970;

                if(agilidad > 64982){

                    vitalidad = (int) (vitalidad + (agilidad - 64982) * 0.88);
                    fuerza    = (int) (fuerza + (agilidad - 64982) * 0.12);
                    agilidad  = 64982;

                    if(vitalidad > 64985){

                        fuerza = fuerza + (vitalidad - 64985);
                        vitalidad = 64985;

                        if(fuerza > 64982){

                            fuerza = 64982;
                            Toast.makeText(getApplicationContext(), "PJ Full", Toast.LENGTH_SHORT).show();

                        }
                    }
                }
            }
            comando = 0;

        }

        if(numTitulo == 3){

            if(energia > 64977){

                agilidad  = (int) (agilidad + (energia - 64977) * 0.7);
                vitalidad = (int) (vitalidad + (energia - 64977) * 0.15);
                fuerza    = (int) (fuerza + (energia - 64977) * 0.15);
                energia = 64977;

                if(agilidad > 64979){

                    vitalidad = (int) (vitalidad + (agilidad - 64979) * 0.5);
                    fuerza    = (int) (fuerza + (agilidad - 64979) * 0.5);
                    agilidad  = 64979;

                    if(fuerza > 64979){

                        vitalidad = vitalidad + (fuerza - 64979);
                        fuerza    = 64979;

                        if(vitalidad > 64982){

                            vitalidad = 64982;

                            Toast.makeText(getApplicationContext(), "PJ Full", Toast.LENGTH_SHORT).show();

                        }
                    }
                }
            }
            comando = 0;
        }

        if(numTitulo == 4){

            if(fuerza > 64968){

                agilidad  = (int) (agilidad + (fuerza - 64968) * 0.22);
                vitalidad = (int) (vitalidad + (fuerza - 64968) * 0.14);
                energia   = (int) (energia + (fuerza - 64986) * 0.64);
                fuerza  = 64968;

                if(energia > 64980){

                    agilidad  = (int) (agilidad + (energia - 64980) * 0.66);
                    vitalidad = (int) (vitalidad + (energia - 64980) * 0.34);
                    energia  = 64980;

                    if(agilidad > 64973){

                        vitalidad = vitalidad + (agilidad - 64973);
                        agilidad = 64973;

                        if(vitalidad > 64975){

                            vitalidad = 64975;
                            Toast.makeText(getApplicationContext(), "PJ Full", Toast.LENGTH_SHORT).show();

                        }
                    }
                }
            }
            comando = 0;
        }

        if(numTitulo == 6){

            if(fuerza > 64978){

                agilidad  = (int) (agilidad + (fuerza - 64978) * 0.25);
                vitalidad = (int) (vitalidad + (fuerza - 64978) * 0.19);
                energia   = (int) (energia + (fuerza - 64978) * 0.31);
                comando   = (int) (comando + (fuerza - 64978) * 0.25);
                fuerza = 64978;

                if(energia > 64985){

                    vitalidad = (int) (vitalidad + (energia - 64985) * 0.30);
                    agilidad  = (int) (energia + (energia - 64985) * 0.35);
                    comando   = (int) (comando + (energia - 64985) * 0.35);
                    energia  = 64985;

                    if(agilidad > 64975){

                        comando  = (int) (comando + (agilidad - 64975) * 0.55);
                        vitalidad = (int) (vitalidad + (agilidad - 64975) * 0.45);
                        agilidad = 64975;

                        if(comando > 65000){

                            vitalidad = vitalidad + (comando - 65000);
                            comando = 65000;

                            if(vitalidad > 64980)

                                vitalidad = 64980;
                                Toast.makeText(getApplicationContext(), "PJ Full", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        }

        fza = String.valueOf(fuerza);
        agi = String.valueOf(agilidad);
        vit = String.valueOf(vitalidad);
        ene = String.valueOf(energia);
        cmd = String.valueOf(comando);

    }


    private void elfaDialog(int numTitulo, final String[] elfa){

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);

        title = guerreros[numTitulo];

        builder.setTitle(title);

        builder.setItems(elfa, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int position) {

                if(position == 0){

                    fzaPorcentaje = 0.24;
                    agiPorcentaje = 0.64;
                    vitPorcentaje = 0.1;
                    enePorcentaje = 0.02;
                    cmdPorcentaje = 0.0;

                    fuerza    = (int) (calculador * fzaPorcentaje);
                    agilidad  = (int) (calculador * agiPorcentaje);
                    vitalidad = (int) (calculador * vitPorcentaje);
                    energia   = (int) (calculador * enePorcentaje);
                    comando   = (int) (calculador * cmdPorcentaje);

                    if(agilidad > 64980){

                        fuerza    = (int) (fuerza + (agilidad - 64980) * 0.73);
                        vitalidad = (int) (vitalidad + (agilidad - 64980) * 0.17);
                        energia   = (int) (energia + (agilidad - 64980) * 0.1);
                        agilidad = 64980;

                        if(fuerza > 64978){

                            vitalidad = (int) (vitalidad + (fuerza - 64978) * 0.78);
                            energia   = (int) (energia + (fuerza - 64978) * 0.22);
                            fuerza  = 64978;

                            if(vitalidad > 64975){

                                energia = energia + (vitalidad - 64975);
                                vitalidad = 64975;

                                if(energia > 64990){

                                    energia = 64990;
                                    Toast.makeText(getApplicationContext(), "PJ Full", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                    }
                    comando = 0;

                    fza = String.valueOf(fuerza);
                    agi = String.valueOf(agilidad);
                    vit = String.valueOf(vitalidad);
                    ene = String.valueOf(energia);
                    cmd = String.valueOf(comando);

                    createDialog(title + " (Agilidad)", fza, agi, vit, ene, cmd);

                }

                if(position == 1){

                    fzaPorcentaje = 0.02;
                    agiPorcentaje = 0.28;
                    vitPorcentaje = 0.1;
                    enePorcentaje = 0.6;
                    cmdPorcentaje = 0.0;

                    fuerza    = (int) (calculador * fzaPorcentaje);
                    agilidad  = (int) (calculador * agiPorcentaje);
                    vitalidad = (int) (calculador * vitPorcentaje);
                    energia   = (int) (calculador * enePorcentaje);
                    comando   = (int) (calculador * cmdPorcentaje);

                    if(energia > 64990){

                        fuerza     = (int) (fuerza + (energia - 64990) * 0.12);
                        vitalidad  = (int) (vitalidad + (energia - 64990) * 0.22);
                        agilidad   = (int) (agilidad + (energia - 64990) * 0.66);
                        energia = 64990;

                        if(agilidad > 64980){

                            vitalidad = (int) (vitalidad + (agilidad - 64980) * 0.73);
                            fuerza    = (int) (fuerza + (agilidad - 64980) * 0.27);
                            agilidad  = 64980;

                            if(vitalidad > 64975){

                                fuerza = fuerza + (vitalidad - 64975);
                                vitalidad = 64975;

                                if(fuerza > 64978){

                                    fuerza = 64978;
                                    Toast.makeText(getApplicationContext(), "PJ Full", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                    }
                    comando = 0;

                    fza = String.valueOf(fuerza);
                    agi = String.valueOf(agilidad);
                    vit = String.valueOf(vitalidad);
                    ene = String.valueOf(energia);
                    cmd = String.valueOf(comando);

                    createDialog(title + " (Energía)", fza, agi, vit, ene, cmd);

                }

            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();

    }

    private void magicGladiator(int numTitulo, final String[] mg){

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        title = guerreros[numTitulo];

        builder.setTitle(title);

        builder.setItems(mg, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int position) {

                if(position == 0){

                    fzaPorcentaje = 0.46;
                    agiPorcentaje = 0.4;
                    vitPorcentaje = 0.1;
                    enePorcentaje = 0.04;
                    cmdPorcentaje = 0.0;

                    fuerza    = (int) (calculador * fzaPorcentaje);
                    agilidad  = (int) (calculador * agiPorcentaje);
                    vitalidad = (int) (calculador * vitPorcentaje);
                    energia   = (int) (calculador * enePorcentaje);
                    comando   = (int) (calculador * cmdPorcentaje);

                    if(fuerza > 64972){

                        agilidad  = (int) (agilidad + (fuerza - 64972) * 0.64);
                        vitalidad = (int) (vitalidad + (fuerza - 64972) * 0.3);
                        energia   = (int) (energia + (fuerza - 64972) * 0.06);
                        fuerza = 64972;

                        if(agilidad > 64980){

                            vitalidad = (int) (vitalidad + (agilidad - 64980) * 0.87);
                            energia   = (int) (energia + (agilidad - 64980) * 0.13);
                            agilidad  = 64980;

                            if(vitalidad > 64975){

                                energia = energia + (vitalidad - 64975);
                                vitalidad = 64975;

                                if(energia > 64990){

                                    energia = 64990;
                                    Toast.makeText(getApplicationContext(), "PJ Full", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                    }
                    comando = 0;

                    fza = String.valueOf(fuerza);
                    agi = String.valueOf(agilidad);
                    vit = String.valueOf(vitalidad);
                    ene = String.valueOf(energia);
                    cmd = String.valueOf(comando);

                    createDialog(title + " (Caballero)", fza, agi, vit, ene, cmd);

                }

                if(position == 1){

                    fzaPorcentaje = 0.1;
                    agiPorcentaje = 0.3;
                    vitPorcentaje = 0.1;
                    enePorcentaje = 0.5;
                    cmdPorcentaje = 0.0;

                    fuerza    = (int) (calculador * fzaPorcentaje);
                    agilidad  = (int) (calculador * agiPorcentaje);
                    vitalidad = (int) (calculador * vitPorcentaje);
                    energia   = (int) (calculador * enePorcentaje);
                    comando   = (int) (calculador * cmdPorcentaje);

                    if(energia > 64990){

                        fuerza    = (int) (fuerza + (energia - 64990) * 0.2);
                        vitalidad = (int) (vitalidad + (energia - 64990) * 0.2);
                        agilidad  = (int) (agilidad + (energia - 64990) * 0.6);
                        energia = 64990;

                        if(agilidad > 64980){

                            vitalidad = (int) (vitalidad + (agilidad - 64980) * 0.5);
                            fuerza   = (int) (fuerza + (agilidad - 64980) * 0.5);
                            agilidad  = 64980;

                            if(fuerza > 64972){

                                vitalidad = vitalidad + (fuerza - 64972);
                                fuerza    = 64972;

                                if(vitalidad > 64975)

                                    vitalidad = 64975;
                                    Toast.makeText(getApplicationContext(), "PJ Full", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                    comando = 0;

                    fza = String.valueOf(fuerza);
                    agi = String.valueOf(agilidad);
                    vit = String.valueOf(vitalidad);
                    ene = String.valueOf(energia);
                    cmd = String.valueOf(comando);

                    createDialog(title + " (Mago)", fza, agi, vit, ene, cmd);

                }

                if(position == 2){

                    fzaPorcentaje = 0.3;
                    agiPorcentaje = 0.3;
                    vitPorcentaje = 0.1;
                    enePorcentaje = 0.3;
                    cmdPorcentaje = 0.0;

                    fuerza    = (int) (calculador * fzaPorcentaje);
                    agilidad  = (int) (calculador * agiPorcentaje);
                    vitalidad = (int) (calculador * vitPorcentaje);
                    energia   = (int) (calculador * enePorcentaje);
                    comando   = (int) (calculador * cmdPorcentaje);

                    if(fuerza > 64972){

                        agilidad  = (int) (agilidad +(fuerza - 64972) * 0.4);
                        vitalidad = (int) (vitalidad +(fuerza - 64972) * 0.2);
                        energia   = (int) (energia +(fuerza - 64972) * 0.4);

                        fuerza = 64972;

                        if(agilidad > 64980){

                            vitalidad = (int)(vitalidad +(agilidad - 64980) * 0.4);
                            energia   = (int)(energia +(agilidad - 64980) * 0.6);
                            agilidad = 64980;

                            if(energia > 64990){

                                vitalidad = vitalidad + (energia - 64990);
                                energia = 64990;

                                if(vitalidad > 64975){

                                    vitalidad = 64975;
                                    Toast.makeText(getApplicationContext(), "PJ Full", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                    }
                    comando = 0;

                    fza = String.valueOf(fuerza);
                    agi = String.valueOf(agilidad);
                    vit = String.valueOf(vitalidad);
                    ene = String.valueOf(energia);
                    cmd = String.valueOf(comando);

                    createDialog(title + " (Híbrido)", fza, agi, vit, ene, cmd);

                }

            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();

    }
}
