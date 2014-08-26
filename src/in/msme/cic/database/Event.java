package in.msme.cic.database;



public class Event {

	
	private String Title;
	private String Des ;
	private String  image_url;
	private String  venue;
	private String  Date;
		
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
	public String getvenue() {
		return venue;
	}
	public void setvenue(String image_url) {
		this.venue = image_url;
	}
	public String getDate() {
		return Date;
	}
	public void setDate(String image_url) {
		this.Date = image_url;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Title :- "+Title+" Des :- "+Des+" Image_ url :- "+image_url  ;
	}
	
	
}
