package com.example.findmyslot.dataClass;

public class Slots {

    String centerName, date, vaccineName, address;
    int slotsAvailable;
    String block_name;

    public Slots(){}

   public Slots(String centerName, String date,
                String vaccineName, String block_name, String address){

       this.centerName = centerName;
       this.date = date;
       this.vaccineName = vaccineName;
       //this.slotsAvailable = slotsAvailable;
       this.block_name = block_name;
       this.address = address;
    }


    public String getBlock_name() {
        return block_name;
    }

    public void setBlock_name(String block_name) {
        this.block_name = block_name;
    }

    public String getCenterName() {
        return centerName;
    }

    public void setCenterName(String centerName) {
        this.centerName = centerName;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setVaccineName(String vaccineName) {
        this.vaccineName = vaccineName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setSlotsAvailable(int slotsAvailable) {
        this.slotsAvailable = slotsAvailable;
    }

    public String getDate() {
        return date;
    }

    public String getVaccineName() {
        return vaccineName;
    }

    public int getSlotsAvailable() {
        return slotsAvailable;
    }

    public String getAddress() {
        return address;
    }
}
