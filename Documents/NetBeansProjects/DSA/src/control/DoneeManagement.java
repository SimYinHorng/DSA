package control;

import adt.HashMap;
import boundary.DoneeManagementUI;
import dao.DoneeDAO;
import dao.DonorDAO;
import entity.Donee;
import utility.DoneeCategory;
import static utility.MessageUI.displayInvalidChoiceMessage;

public class DoneeManagement {
    
    private HashMap<Integer, Donee> doneeMap = new HashMap<>();
    private DoneeDAO doneeDAO = new DoneeDAO();
    private DoneeManagementUI doneeUI = new DoneeManagementUI();
    
    public DoneeManagement() {
        doneeMap = doneeDAO.retrieveFromFile();
    }
    
    
    
    
}
