package com.example.findmyslot.dataClass;

public class Slots {

    String centerName, date, vaccineName, address, slotsAvailable;
   // int slotsAvailable;

   public Slots(String centerName, String date,
                String vaccineName,  String address, String slotsAvailable){

       this.centerName = centerName;
       this.date = date;
       this.vaccineName = vaccineName;
       this.slotsAvailable = slotsAvailable;
       this.address = address;
    }

    public String getCenterName() {
        return centerName;
    }

    public String getDate() {
        return date;
    }

    public String getVaccineName() {
        return vaccineName;
    }

    public String getSlotsAvailable() {
        return slotsAvailable;
    }

    public String getAddress() {
        return address;
    }
}
