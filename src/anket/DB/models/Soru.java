package anket.DB.models;

public class Soru {
	
	int soruID, soruNO, anketID;
	String soru, anket;
	
	public Soru(){
		
	}
	
	public Soru(int soruID, int soruNO, int anketID, String soru){
		
		this.soruID = soruID;
		this.soruNO = soruNO;
		this.anketID = anketID;
		this.soru = soru;
	}
	
	public int getSoruID(){
		return soruID;
	}
	
	public void setSoruID(int soruID){
		this.soruID = soruID;
	}
	
	public int getSoruNO(){
		return soruNO;
	}
	
	public void setSoruNO(int soruNO){
		this.soruNO = soruNO;
	}
	
	public int getAnketID(){
		return anketID;
	}
	
	public void setAnketID(int anketID){
		this.anketID = anketID;
	}
	
	public String getSoru(){
		return soru;
	}
	
	public void setSoru(String soru){
		this.soru = soru;
	}
	
	public String getAnket(){
		return anket;
	}
	
	public void setAnket(String anket){
		this.anket = anket;
	}
}

