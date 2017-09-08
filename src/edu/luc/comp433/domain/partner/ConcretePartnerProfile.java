package edu.luc.comp433.domain.partner;

public class ConcretePartnerProfile implements PartnerProfile {

    private double id;
    private String name;
    
    public ConcretePartnerProfile() {}

    public double getId() {
        return id;
    }

    public void setId(double id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
