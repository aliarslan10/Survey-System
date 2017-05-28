package anket.DB.models;

public class Secenek {

	int secenekID, secenekNO, soruID;
	String secenek;
	
	public Secenek(){
		
	}
	
	public Secenek(int secenekID, int secenekNO, int soruID, String secenek){
		
		this.secenekID = secenekID;
		this.secenekNO = secenekNO;
		this.soruID = soruID;
		this.secenek = secenek;
	}
	
	public int getSecenekID(){
		return secenekID;
	}
	
	public void setSecenekID(int secenekID){
		this.secenekID = secenekID;
	}
	
	public int getSecenekNO(){
		return secenekNO;
	}
	
	public void setSecenekNO(int secenekNO){
		this.secenekNO = secenekNO;
	}
	
	public int getSoruID(){
		return soruID;
	}
	
	public void setSoruID(int soruID){
		this.soruID = soruID;
	}
	
	public String getSecenek(){
		return secenek;
	}
	
	public void setSecenek(String secenek){
		this.secenek = secenek;
	}
}
