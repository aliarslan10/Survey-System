package anket.DB.models;

public class Personel {
    
	private int sicilNo , uniteID , seflikID , unvanID;

	private String isim,soyisim,unite,seflik,unvan;
	
	public Personel() {
		
	}

	public Personel(int sicilNo,String isim,String soyisim, int uniteID, String unite, int seflikID,
			String seflik,int unvanID,  String unvan) {
		super();
		this.sicilNo = sicilNo;
		this.uniteID = uniteID;
		this.seflikID = seflikID;
		this.unvanID = unvanID;
		this.isim = isim;
		this.soyisim = soyisim;
		this.unite = unite;
		this.seflik = seflik;
		this.unvan = unvan;
	}
	
	public int getSicilNo() {
		return sicilNo;
	}

	public void setSicilNo(int sicilNo) {
		this.sicilNo = sicilNo;
	}

	public int getUniteID() {
		return uniteID;
	}

	public void setUniteID(int uniteID) {
		this.uniteID = uniteID;
	}

	public int getSeflikID() {
		return seflikID;
	}

	public void setSeflikID(int seflikID) {
		this.seflikID = seflikID;
	}

	public int getUnvanID() {
		return unvanID;
	}

	public void setUnvanID(int unvanID) {
		this.unvanID = unvanID;
	}

	public String getIsim() {
		return isim;
	}

	public void setIsim(String isim) {
		this.isim = isim;
	}

	public String getSoyisim() {
		return soyisim;
	}

	public void setSoyisim(String soyisim) {
		this.soyisim = soyisim;
	}

	public String getUnite() {
		return unite;
	}

	public void setUnite(String unite) {
		this.unite = unite;
	}

	public String getSeflik() {
		return seflik;
	}

	public void setSeflik(String seflik) {
		this.seflik = seflik;
	}

	public String getUnvan() {
		return unvan;
	}

	public void setUnvan(String unvan) {
		this.unvan = unvan;
	}
}
