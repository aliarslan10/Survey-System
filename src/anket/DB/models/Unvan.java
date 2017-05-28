package anket.DB.models;

public class Unvan {

	private int id,seflikID,uniteID;
	private String isim,seflikIsim,uniteIsim;
	
	public Unvan() {
		// TODO Auto-generated constructor stub
	}

	public Unvan(int id,String isim, int seflikID,String seflikIsim, int uniteID, 
			 String uniteIsim) {
		super();
		this.id = id;
		this.seflikID = seflikID;
		this.uniteID = uniteID;
		this.isim = isim;
		this.seflikIsim = seflikIsim;
		this.uniteIsim = uniteIsim;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSeflikID() {
		return seflikID;
	}

	public void setSeflikID(int seflikID) {
		this.seflikID = seflikID;
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

	public String getSeflikIsim() {
		return seflikIsim;
	}

	public void setSeflikIsim(String seflikIsim) {
		this.seflikIsim = seflikIsim;
	}

	public String getUniteIsim() {
		return uniteIsim;
	}

	public void setUniteIsim(String uniteIsim) {
		this.uniteIsim = uniteIsim;
	}

}
