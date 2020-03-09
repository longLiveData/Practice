package staffmanagement;

/**
 * class of employee, storage basic info
 */
public class Employee {
    
    // date define
    private String name;
    private String gender; // 0 for male, 1 for female, 2 for others
    private String IdCardNo;
    private String dateOfBirth;
    private String dateOfEntry;
    private String mobile;
    private String address;
    
    // set values
    public void setName(String cName){
        this.name = cName;
    }
    
    public void setGender(String cGender){
        if(cGender.equals("male")){
            this.gender = "0";
        }else if(cGender.equals("female")){
            this.gender = "1";
        }else{
            this.gender = "2";
        }
    }
    
    public void setIdCardNo(String cIdCardNo){
        this.IdCardNo = cIdCardNo;
    }
    
    public void setDateOfBirth(String cDateOfBirth){
        this.dateOfBirth = cDateOfBirth;
    }
    
    public void setDateOfEntry(String cDateOfExtry){
        this.dateOfEntry = cDateOfExtry;
    }
    
    public void setMobile(String cMobile){
        this.mobile = cMobile;
    }
    
    public void setAddress(String cAddress){
        this.address = cAddress;
    }
    
    // get values
    public String getName(){
        return this.name;
    }
    
    public String getGender(){
        String res = "notsure";
        switch (this.gender) {
            case "0":
                res = "male";
                break;
            case "1":
                res = "female";
                break;
        }
        return res;
    }
    
    public String getIdCardNo(){
        return this.IdCardNo;
    }
    
    public String getDateOfBirth(){
        return this.dateOfBirth;
    }
    
    public String getDateOfEntry(){
        return this.dateOfEntry;
    }
    public String getMobile(){
        return this.mobile;
    }
    public String getAddress(){
        return this.address;
    }
}
