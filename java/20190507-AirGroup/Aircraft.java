public class Aircraft
{
    private String tailCode;
    private String status;
    private Aircrew crew;

    public Aircraft(String tailCode)
    {
            this.tailCode = tailCode;
            this.status = "on deck";
    }

    public Aircraft(String tailCode, String status)
    {
            this.tailCode = tailCode;
            this.status = status;
    }

    public void addAircrew(String name, String callSign, int missions){
            this.crew = new Aircrew(name, callSign, missions);
    }

    public void addAircrew(String name, String callSign){
            this.crew = new Aircrew(name, callSign);
    }

    public String toString(){
            String res = "Aircraft\n" +
                         "[\n" +
                         "   Tail code: " + this.getTailCode() + "\n" + 
                         "   Status: " + this.getStatus() + "\n" +
                         this.getCrewString() + "\n" +
                         "]";
            return res;
    }

    public String getTailCode(){
        return this.tailCode;
    }

    public String getStatus(){
        return this.status;
    }

    public Aircrew getCrew(){
        return this.crew;
    }

    public String getCrewString(){
        if (this.crew != null) {
            return this.crew.toString();
        }
        return "\n" + "   This aircraft has no assigned air crew";
    }

    public boolean existCrew(){
        return this.crew != null;
    }

    public void start(){
        this.status = "airborne";
        this.crew.increMissions();
    }

    public void end(){
        this.status = "on deck";
    }
    
}
























