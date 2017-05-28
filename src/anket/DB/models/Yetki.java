package anket.DB.models;

public class Yetki {
	
	private int sicilNo , yetki;
	private String isim , soyisim , unite , seflik , unvan;

	public Yetki() {
		// TODO Auto-generated constructor stub
	}

	public Yetki(int sicilNo, int yetki, String isim, String soyisim,
			String unite, String seflik, String unvan) {
		super();
		this.sicilNo = sicilNo;
		this.yetki = yetki;
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

	public int getYetki() {
		return yetki;
	}

	public void setYetki(int yetki) {
		this.yetki = yetki;
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
