package tech.pratham.findmyslot.dataClass;

public class Slots {

    String centerName, vaccineName, address, dose1, dose2, feeType, ageLimit;

   public Slots(String centerName, String vaccineName, String address, String dose1, String dose2,
                String feeType, String ageLimit){

       this.centerName = centerName;
       this.vaccineName = vaccineName;
       this.address = address;
       this.dose1 = dose1;
       this.dose2 = dose2;
       this.feeType = feeType;
       this.ageLimit = ageLimit;
    }

    public String getFeeType() {
        return feeType;
    }

    public String getAgeLimit() {
        return ageLimit;
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
