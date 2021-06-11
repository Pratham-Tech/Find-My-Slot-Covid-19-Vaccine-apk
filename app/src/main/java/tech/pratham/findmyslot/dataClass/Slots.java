package tech.pratham.findmyslot.dataClass;

public class Slots {

    String centerName, date, vaccineName, address, dose1, dose2;

   public Slots(String centerName, String vaccineName, String address, String dose1, String dose2){

       this.centerName = centerName;
       this.vaccineName = vaccineName;
       this.address = address;
       this.dose1 = dose1;
       this.dose2 = dose2;
    }

    public String getCenterName() {
        return centerName;
    }

    public String getVaccineName() {
        return vaccineName;
    }

    public String getAddress() {
        return address;
    }

    public String getDose1() {
        return dose1;
    }

    public String getDose2() {
        return dose2;
    }
}
