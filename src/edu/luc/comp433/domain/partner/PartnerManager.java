package edu.luc.comp433.domain.partner;

public interface PartnerManager {
    public boolean create(String userName, String name, String address, String phone);
    public boolean updateName(String userName, String name);
    public boolean updateAddress(String userName, String address);
    public boolean updatePhone(String userName, String phone);
    public boolean delete(String userName);
}
