package grotto_002;
class MyNode {
	////int id; // good coding practice would have this as private
	int id; // good coding practice would have this as private
	int edgeCount=0;

	public MyNode(int id) {
		this.id = id;
	}

	public String toString() { // Always a good idea for debuging
		return "V" + id; // JUNG2 makes good use of these.
	}
}