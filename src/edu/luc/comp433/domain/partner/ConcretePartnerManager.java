package edu.luc.comp433.domain.partner;

public class ConcretePartnerManager implements PartnerManager {
    
    public ConcretePartnerManager() {}
    
    @Override
    public boolean register(String name) {
	return false;
    }
    
    @Override
    public boolean delete(double id) {
	return false;
    }
}
