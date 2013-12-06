package proyectolobo.apk;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ScrollView;
import android.widget.TextView;

public class insertarNombres extends Activity {

	String[] nombres;
	String[] nombrescop;
	int cantidad;
	LinearLayout llfila;
	boolean poner=false;
	List<EditText> todoset = new ArrayList<EditText>();
	
	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Bundle bundle=getIntent().getExtras();
        cantidad=bundle.getInt("num");
        
        InputStreamReader flujo=null;
		BufferedReader lector=null;
		String nom;
		nombrescop = new String[cantidad];
		try
		{
			flujo= new InputStreamReader(getApplicationContext().openFileInput("nombres.txt"));
			lector= new BufferedReader(flujo);
		    nom = lector.readLine();
		    Log.i("","asd "+nom);
		    nombrescop=nom.split("/n");
		}
		catch (Exception ex)
		{
		    Log.e("ivan", "Error al leer fichero desde memoria interna ");
		    poner=true;
		}
		finally
		{
			try {
	    			if(flujo!=null)
	    				flujo.close();
				} catch (IOException e) {
				e.printStackTrace();
			}
		}
        
        LinearLayout lltodo=new LinearLayout(this);
        lltodo.setOrientation(LinearLayout.HORIZONTAL);
        
        LinearLayout llgeneral=new LinearLayout(this);
        llgeneral.setOrientation(LinearLayout.VERTICAL);
        llgeneral.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.WRAP_CONTENT));
        
        TextView titulo=new TextView(this);
        titulo.setText("Nombres");
        titulo.setGravity(Gravity.CENTER);
        titulo.setTextSize(40);
        
        llgeneral.addView(titulo);
        
        for(int i=0;i<cantidad;i++)
        {
        	llfila=new LinearLayout(this);
            llfila.setOrientation(LinearLayout.HORIZONTAL);
            
        	TextView texto=new TextView(this);
        	texto.setText("Nombre "+(i+1)+": ");
        	
        	EditText texto2=new EditText(this);
        	if(poner)
        	{
        		texto2.setText("Jugador "+(i+1));
        	}
        	else
        	{
        		texto2.setText(nombrescop[i]);
        	}
        	
            todoset.add(texto2);
            
        	llfila.addView(texto);
        	llfila.addView(texto2);
        	
        	llgeneral.addView(llfila);
        }
        
        Button boton=new Button(this);
        boton.setText("Enviar");
        boton.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				nombres = new String[todoset.size()];

				String text="";
				try {
					OutputStreamWriter fout=new OutputStreamWriter(getApplicationContext().openFileOutput("nombres.txt", Context.MODE_PRIVATE));
					
					for(int i=0; i < todoset.size(); i++){
					    nombres[i] = todoset.get(i).getText().toString();
					    Log.i("",""+nombres[i]);
					    text=text+nombres[i]+"/n";
					}
					fout.write(text);
					fout.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
        	
        });
        llgeneral.addView(boton);
        
        ScrollView sv = new ScrollView(this);
        sv.addView(llgeneral);
        lltodo.addView(sv);
        
        setContentView(lltodo);
	}

}