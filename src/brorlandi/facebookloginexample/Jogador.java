package brorlandi.facebookloginexample;

public class Jogador {

	private String mPrimeiroNome;
	private String mNomeCompleto;
	private String mFacebookId;

	public Jogador(String mPrimeiroNome, String mNomeCompleto,
			String mFacebookId) {
		this.mPrimeiroNome = mPrimeiroNome;
		this.mNomeCompleto = mNomeCompleto;
		this.mFacebookId = mFacebookId;
	}
	public String getPrimeiroNome() {
		return mPrimeiroNome;
	}
	public void setPrimeiroNome(String mPrimeiroNome) {
		this.mPrimeiroNome = mPrimeiroNome;
	}
	public String getNomeCompleto() {
		return mNomeCompleto;
	}
	public void setNomeCompleto(String mNomeCompleto) {
		this.mNomeCompleto = mNomeCompleto;
	}
	public String getFacebookId() {
		return mFacebookId;
	}
	public void setFacebookId(String mFacebookId) {
		this.mFacebookId = mFacebookId;
	}

	

}
