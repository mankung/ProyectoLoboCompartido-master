package proyectolobo.apk;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {

	int num=0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	
	public void enviar(View view)
	{
		EditText texto=(EditText)findViewById(R.id.editText1);
		String numtexto=texto.getText().toString();
		num=Integer.parseInt(numtexto);
		Intent intento = new Intent(this, insertarNombres.class);
       	intento.putExtra("num", num);
   		startActivity(intento);
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
