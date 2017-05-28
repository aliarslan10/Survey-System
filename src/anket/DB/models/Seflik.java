package anket.DB.models;

public class Seflik {

	private int id,uniteID;
	private String isim , unite;
	
	public Seflik() {
		
	}

	public Seflik(int id, int uniteID, String isim, String unite) {
		super();
		this.id = id;
		this.uniteID = uniteID;
		this.isim = isim;
		this.unite = unite;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUniteID() {
		return uniteID;
	}

	public void setUniteID(int uniteID) {
		this.uniteID = uniteID;
	}

	public String getIsim() {
		return isim;
	}

	public void setIsim(String isim) {
		this.isim = isim;
	}

	public String getUnite() {
		return unite;
	}

	public void setUnite(String unite) {
		this.unite = unite;
	}

}
