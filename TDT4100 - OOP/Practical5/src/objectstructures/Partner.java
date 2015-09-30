package objectstructures;

public class Partner {
	private final String name;
	private Partner partner;
	
	public Partner(String name) {
		this.name = name;
	}
	public Partner getPartner() {
		return partner;
	}
	public void setPartner(Partner partner) {
		if (partner == this) {
			throw new IllegalArgumentException("Cannot be your own partner.");
		}
		Partner originalPartner = getPartner();
		this.partner = partner;
		if (originalPartner != null) {
			originalPartner.setPartner(null); // Set partner of partner to null
		}
		if (partner != null && partner.getPartner() != this) {
			partner.setPartner(this);
		}
	}
	public String getName() {
		return name;
	}
	public String toString() {
		return name;
	}
}
