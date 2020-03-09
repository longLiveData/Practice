public class Aircrew
{
    private String name;
    private String callSign;
    private int missions;
    private String status;

    public Aircrew(String name, String callSign, int missions){
        this.name = name;
        this.callSign = callSign;
        this.missions = missions;
        this.status = "Rookie";
    }

    public Aircrew(String name, String callSign){
        this.name = name;
        this.callSign = callSign;
        this.missions = 0;
        this.status = "Rookie";
    }

    public String getName(){
        return this.name;
    }

    public String getCallSign(){
        return this.callSign;
    }

    public int getMissions(){
        return this.missions;
    }

    public String getStatus(){
        return this.status;
    }


    public void setName(String name){
            this.name = name;
    }

    public void setCallSign(String callSign){
            this.callSign = callSign;
    }

    public void setMissions(int missions){
            this.missions = missions;
    }

    public void setStatus(String status){
            this.status = status;
    }

    public void setStatusByMissions(int missions){
            if (missions > 16){
                this.status = "Ace pilot";
            } else if ( missions > 10 ){
                this.status = "Veteran";
            } else if (missions > 5 ){
                this.status = "Trained";
            } else {
                this.status = "Rookie";
            }
    }

    public void increMissions(){
            this.missions += 1;
            this.setStatusByMissions(this.missions);
    }

    public String toString(){
            String res = "   Aircrew\n" +
                         "   [\n" +
                         "     Name: " + this.getName() + "\n" +
                         "     Call Sign: " + this.getCallSign() + "\n" +
                         "     Missions: " + this.getMissions() + "\n" +
                         "     Status: " + this.getStatus() + "\n" +
                         "   ]";
            return res;
    }

    public boolean prepared(String status) {
        return stateNum(this.getStatus()) >= stateNum(status);
    }

    public int stateNum(String status){
        switch(status){
            case "Rookie": return 1;
            case "Trained": return 2;
            case "Veteran": return 3;
            case "Ace pilot":return 4;
            case "rookie": return 1;
            case "trained": return 2;
            case "veteran": return 3;
            case "ace pilot":return 4;
        }
        return 0;
    }
}
