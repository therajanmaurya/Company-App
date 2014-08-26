package in.msme.cic;



public class Service {

	
	private String Title;
	private String Des ;
	private String  image_url;
		
	public String getTitle() {
		return Title;
	}
	public void setTitle(String name) {
		this.Title = name;
	}
	public String getDes() {
		return Des;
	}
	public void setDes(String Dec) {
		this.Des = Dec;
	}
	public String getImage_url() {
		return image_url;
	}
	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Title :- "+Title+" Des :- "+Des+" Image_ url :- "+image_url  ;
	}
	
	
}
