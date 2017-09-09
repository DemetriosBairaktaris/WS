package edu.luc.comp433.domain.partner;

public class ConcretePartnerProfile implements PartnerProfile {

    private double id;
    private String name;
    
    public ConcretePartnerProfile() {}

    @Override
    public double getId() {
        return id;
    }

    @Override
    public void setId(double id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }
}
