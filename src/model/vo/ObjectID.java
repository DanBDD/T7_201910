package model.vo;

public class ObjectID implements Comparable <ObjectID>{

	private int id;

	public ObjectID(int pID) {
		id = pID;
	}

	public int getID() {
		return id;
	}

	@Override
	public int compareTo(ObjectID id) {
		int comp = 0;
		if((this.id - id.getID()) > 0 ) {
			comp = 1;
		}
		else if((this.id - id.getID()) < 0 ) {
			comp = -1;
		}
		else if((this.id - id.getID()) == 0) {
			comp = 0;
		}
		return comp;
	}

}