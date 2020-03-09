package staffmanagement;

import java.util.ArrayList;

public class Staff {
   
    public static ArrayList<Employee> staff = new ArrayList<>();
    
    
    /**
     * adding
     * @param args name, gender, IdCardNo, dateOfBirth, dateOfEntry, mobile, address
     * @return 
     */
    public boolean add(String[] args){
        // id number is primary key
        if(ifExistEmployee(args[2])){
            return false;
        }
        Employee em = new Employee();
        em.setName(args[0]);
        em.setGender(args[1]);
        em.setIdCardNo(args[2]);
        em.setDateOfBirth(args[3]);
        em.setDateOfEntry(args[4]);
        em.setMobile(args[5]);
        em.setAddress(args[6]);
        staff.add(em);
        return true;
    }
    
    private boolean ifExistEmployee(String cIdCardNo){
        for (Employee em : staff) {
            if(em.getIdCardNo().equals(cIdCardNo)){
                return true;
            }
        }
        return false;
    }
    
    
    /**
     * retrieving
     * @param type
     * @param args
     * @return 
     */
    public String retriev(String type, String[] args){
        String resStr = "";
        switch (type){
            case "name":
                resStr = retrievByName(args[0]);
                break;
            case "gender":
                resStr = retrievByGender(args[0]);
                break;
            case "IdCardNo":
                resStr = retrievByIdCardNo(args[0]);
                break;
            case "dateOfBirth":
                resStr = retrievByDateOfBirth(args[0], args[1]);
                break;
            case "dateOfEntry":
                resStr = retriveByDateOfEntry(args[0], args[1]);
                break;
            case "mobile":
                resStr = retrievByMobile(args[0]);
                break;
            case "address":
                resStr = retrievByAddress(args[0]);
                break;
        }
        return resStr;
    }
    
    private String retrievByName(String cName){
        String resStr = "";
        for (Employee em : staff) {
            if(em.getName().equals(cName)){
                if(!resStr.equals("")){
                    resStr += ",";
                }
                resStr += getSingleInfo(em);
            }
        }
        return resStr;
    }
    
    private String retrievByGender(String cGender){
        String resStr = "";
        for (Employee em : staff) {
            if(em.getGender().equals(cGender)){
                if(!resStr.equals("")){
                    resStr += ",";
                }
                resStr += getSingleInfo(em);
            }
        }
        return resStr;
    }
    
    private String retrievByIdCardNo(String cIdCardNo){
        String resStr = "";
        for (Employee em : staff) {
            if(em.getIdCardNo().equals(cIdCardNo)){
                if(!resStr.equals("")){
                    resStr += ",";
                }
                resStr += getSingleInfo(em);
            }
        }
        return resStr;
    }
    
    private String retrievByDateOfBirth(String cStartTime, String cEndTime){
        String resStr = "";
        for (Employee em : staff) {
            String currTime = em.getIdCardNo();
            if(currTime.compareTo(cStartTime) >= 0 && currTime.compareTo(cEndTime) <= 0){
                if(!resStr.equals("")){
                    resStr += ",";
                }
                resStr += getSingleInfo(em);
            }
        }
        return resStr;
    }
    
    private String retriveByDateOfEntry(String cStartTime, String cEndTime){
        String resStr = "";
        for (Employee em : staff) {
            String currTime = em.getDateOfEntry();
            if(currTime.compareTo(cStartTime) >= 0 && currTime.compareTo(cEndTime) <= 0){
                if(!resStr.equals("")){
                    resStr += ",";
                }
                resStr += getSingleInfo(em);
            }
        }
        return resStr;
    }
    
    private String retrievByMobile(String cMobile){
        String resStr = "";
        for (Employee em : staff) {
            if(em.getMobile().equals(cMobile)){
                if(!resStr.equals("")){
                    resStr += ",";
                }
                resStr += getSingleInfo(em);
            }
        }
        return resStr;
    }
    
    private String retrievByAddress(String cAddress){
        String resStr = "";
        for (Employee em : staff) {
            if(em.getAddress().equals(cAddress)){
                if(!resStr.equals("")){
                    resStr += ",";
                }
                resStr += getSingleInfo(em);
            }
        }
        return resStr;
    }

    
    /**
     * updating
     * @param recordIdCardNo
     * @param args name, gender, IdCardNo, dateOfBirth, dateOfEntry, mobile, address
     * @return 
     */
    public boolean update(String recordIdCardNo, String[] args){
        // if change IdCardNo
        if(!recordIdCardNo.equals(args[2])){
            if(ifExistEmployee(args[2])){
                return false;
            }
        }
        for (Employee em : staff) {
            if(em.getIdCardNo().equals(recordIdCardNo)){
                em.setName(args[0]);
                em.setGender(args[1]);
                em.setIdCardNo(args[2]);
                em.setDateOfBirth(args[3]);
                em.setDateOfEntry(args[4]);
                em.setMobile(args[5]);
                em.setAddress(args[6]);
                break;
            }
        }
        return true;
    }
    

    /**
     * deleting
     * @param cIdCardNo 
     * @return  
     */
    public boolean delete(String cIdCardNo){
        for(int i=0; i< staff.size(); i++){
            if(staff.get(i).getIdCardNo().equals(cIdCardNo)){
                staff.remove(i);
                return true;
            }
        }
        return false;
    }
    
    
    /**
     * showing
     * @return 
     */
    public String getAllInfo(){
        String infoStr = "";
        for (Employee em : staff) {
            // "," to separate records 
            if(! infoStr.equals("")){
                infoStr += ",";
            }
            infoStr += getSingleInfo(em);
        }
        return infoStr;
    }
    
    // get info string of employee
    private String getSingleInfo(Employee em){
        // _ to separate field
        String singleInfoStr = "";
        singleInfoStr += em.getName() + "_";
        singleInfoStr += em.getGender() + "_";
        singleInfoStr += em.getIdCardNo() + "_";
        singleInfoStr += em.getDateOfBirth() + "_";
        singleInfoStr += em.getDateOfEntry() + "_";
        singleInfoStr += em.getMobile() + "_";
        singleInfoStr += em.getAddress();
        return singleInfoStr;
    }
}
