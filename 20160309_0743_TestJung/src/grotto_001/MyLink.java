package grotto_001;

class MyLink_01 {
	double capacity; // should be private
	double weight; // should be private for good practice
	int id;
	
	//
	int edgeCount = 0;

	public MyLink_01(double weight, double capacity) {
		this.id = edgeCount++; // This is defined in the outer class.
		this.weight = weight;
		this.capacity = capacity;
	}

	public String toString() { // Always good for debugging
		return "E" + id;
	}
}
