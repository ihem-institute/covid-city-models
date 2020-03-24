package covid;

public class CityLink {
	
	
	private City end1;
	private City end2;
	
	private int openessStrategy = 100; 

	public CityLink(City end1, City end2) {
		this.end1 = end1;
		this.end2 = end2;
		this.end1.getLinks().add(this);
		this.end2.getLinks().add(this);
	}
	
	public City getOtherCity(City me) {
		if(this.end1 == me) {
			return this.end2;
		} else {
			return this.end1;
		}
	}
	public String toString() {
		return this.end1.getName() + '-' + this.end2.getName();
	}

	public int getOpenessStrategy() {
		return openessStrategy;
	}

	public void setOpenessStrategy(int openessStrategy) {
		this.openessStrategy = openessStrategy;
	}
}
