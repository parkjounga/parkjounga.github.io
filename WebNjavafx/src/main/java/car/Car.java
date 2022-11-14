package car;

public class Car {
 private long serialno;
 private String Carnumber;
 private String Carname;
 private String Carcolor;
 private String Carmaker;
 private String  Carprice;
public long getSerialno() {
	return serialno;
}
public void setSerialno(long serialno) {
	this.serialno = serialno;
}
public String getCarnumber() {
	return Carnumber;
}
public void setCarnumber(String carnumber) {
	Carnumber = carnumber;
}
public String getCarname() {
	return Carname;
}
public void setCarname(String carname) {
	Carname = carname;
}
public String getCarcolor() {
	return Carcolor;
}
public void setCarcolor(String carcolor) {
	Carcolor = carcolor;
}
public String getCarmaker() {
	return Carmaker;
}
public void setCarmaker(String carmaker) {
	Carmaker = carmaker;
}
public String getCarprice() {
	return Carprice;
}
public void setCarprice(String carprice) {
	Carprice = carprice;
}
@Override
public String toString() {
	return "Car [serialno=" + serialno + ", Carnumber=" + Carnumber + ", Carname=" + Carname + ", Carcolor=" + Carcolor
			+ ", Carmaker=" + Carmaker + ", Carprice=" + Carprice + "]";
}
 
 
 
}