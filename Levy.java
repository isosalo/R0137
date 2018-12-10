
public class Levy {
	
	private String nimi;
	private String artisti;
	private String julkaisuvuosi;
	
	
	public Levy (String nimi, String artisti, String julkaisuvuosi)
	{
		this.nimi = nimi;
		this.artisti = artisti;
		this.julkaisuvuosi = julkaisuvuosi;
	}



	public String getNimi() {
		return nimi;
		}	
	
	public String getArtisti() {
		return artisti;
		}	
	
	public String getJulkaisuvuosi() {
		return julkaisuvuosi;
		}	
}