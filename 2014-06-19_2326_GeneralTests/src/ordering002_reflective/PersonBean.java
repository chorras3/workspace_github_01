package ordering002_reflective;

public class PersonBean {
	public long id;
	public String name;
	public String surname;
	public String surname2;
	public double salary;
	
	PersonBean(){
		id=0;
		name="";
		surname="";
		surname2="";
		salary=0;
	}
	
	
	public PersonBean(long id, String name, String surname, String surname2,
			double salary) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.surname2 = surname2;
		this.salary = salary;
	}



	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getSurname2() {
		return surname2;
	}
	public void setSurname2(String surname2) {
		this.surname2 = surname2;
	}
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}


}
