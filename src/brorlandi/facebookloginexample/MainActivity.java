package brorlandi.facebookloginexample;

import java.net.URL;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.Session;
import com.facebook.model.GraphUser;
import com.facebook.widget.LoginButton;
import com.facebook.widget.ProfilePictureView;

public class MainActivity extends Activity {
	
	private ProfilePictureView profilePictureView;
	private TextView userName;
    private GraphUser User;
    private ImageView image;
    
    private Jogador jogador;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		  
		LoginButton loginButton = (LoginButton) findViewById(R.id.authButton);
		  
        profilePictureView = (ProfilePictureView) findViewById(R.id.profilePicture);
        userName = (TextView) findViewById(R.id.hello);
        image = (ImageView) findViewById(R.id.imageView1);
        
        loginButton.setUserInfoChangedCallback(new LoginButton.UserInfoChangedCallback() {

			@Override
            public void onUserInfoFetched(GraphUser user) {
                User = user;
                if(user != null)
                	jogador = new Jogador(User.getFirstName(), User.getName(), User.getId());
                else
                	jogador = null;
                updateUi();
            }
        });
        
	}
	
	protected void updateUi() {
		if (User != null) {
			profilePictureView.setProfileId(User.getId()); // View especial para foto de perfil do facebook			
	        userName.setText("Nome: "+ jogador.getPrimeiroNome() + ", Nome completo: "+ jogador.getNomeCompleto());
        	String url = "http://graph.facebook.com/" + jogador.getFacebookId() + "/picture?type=normal"; // URL para pegar a imagem de perfil
        	Log.d("face",url);
        	
			new CarregaImagem().execute(url);

	        
        } else {
            profilePictureView.setProfileId(null);
            userName.setText("Nao logado! :(");
        }
	}
	
	class CarregaImagem extends AsyncTask<String, Void, Bitmap> {

	    
	    @Override
	    protected void onPreExecute() {
	    	super.onPreExecute();
	    	Toast.makeText(MainActivity.this, "Carregando imagem de perfil...", Toast.LENGTH_SHORT).show();
	    }

	    protected Bitmap doInBackground(String... urls) {
	        try {
	            URL image_value= new URL(urls[0]);
				Bitmap profPict=BitmapFactory.decodeStream(image_value.openConnection().getInputStream()); // carrega a imagem da URL em um Bitmap
	            return profPict;
	        } catch (Exception e) {
	            e.printStackTrace();
	            return null;
	        }
	    }

	    protected void onPostExecute(Bitmap pic) {
            image.setImageBitmap(pic);
	    }
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) { // deve ter este método implementado pois é onde o app recebe os dados do facebook quando faz login.
	  super.onActivityResult(requestCode, resultCode, data);
	  Log.d("FacebookExample", "onActivityResult");
	  Session.getActiveSession().onActivityResult(this, requestCode, resultCode, data);
	}

}
