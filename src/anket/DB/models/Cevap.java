package anket.DB.models;

public class Cevap {

	private int secenekID, sayac, sicilNO, anketID;
	
	public Cevap(){
		
	}
	
	public Cevap(int secenekID, int sayac){

		this.secenekID = secenekID;
		this.sayac = sayac;
	}
	
	public int getSecenekID(){
		return secenekID;
	}
	
	public void setSecenekID(int secenekID){
		this.secenekID = secenekID;
	}
	
	public int getSicilNO(){
		return this.sicilNO;
	}
	
	public void setSicilNO(int sicilNO){
		this.sicilNO = sicilNO;
	}
	
	public void setAnketID(int anketID){
		this.anketID = anketID;
	}
	
	public int getAnketID(){
		return this.anketID;
	}
	
	public void setSayac(int anketID){
		this.sayac = anketID;
	}
	
	public int getSayac(){
		return this.sayac;
	}
}
