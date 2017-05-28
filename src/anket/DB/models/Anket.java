package anket.DB.models;

public class Anket {

	private int anketID, seflikID, uniteID;
	private String anketAdi, unite, seflik;
	
	public Anket(){
		
	}
	
	public Anket(int anketID, int seflikID, int uniteID, String anketAdi, String unite, String seflik){
		
		super();
		this.anketID = anketID;
		this.seflikID = seflikID;
		this.uniteID = uniteID;
		this.anketAdi = anketAdi;
		this.unite = unite;
		this.seflik = seflik;
	}
	
	public int getAnketID()	{
		return anketID;
	}
	
	public void setAnketID(int anketID)	{
		this.anketID = anketID;
	}
	
	public int getSeflikID(){
		return seflikID;
	}
	
	public void setSeflikID(int seflikID){
		this.seflikID = seflikID;
	}
	
	public int getUniteID(){
		return uniteID;
	}
	
	public void setUniteID(int uniteID){
		this.uniteID = uniteID;
	}
	
	public String getAnketAdi(){
		return anketAdi;
	}
	
	public void setAnketAdi(String anketAdi){
		this.anketAdi = anketAdi;
	}
	
	public String getUnite(){
		return unite;
	}
	
	public void setUnite(String unite){
		this.unite = unite;
	}
	
	public String getSeflik(){
		return seflik;
	}
	
	public void setSeflik(String seflik){
		this.seflik = seflik;
	}
}
