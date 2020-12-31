package bean;

public class ActivityBean {

    private int id;
    private int id_user;
    private int id_location;
    private String name = "";
    private String date = "";
    private String start_at = "";
    private String end_at = "";

    public void setId(int id){ this.id = id;}
    public void setId_user(int id){ this.id_user = id;}
    public void setId_location(int id){ this.id_location = id;}
    public void setName(String name){ this.name = name;}
    public void setDate(String date){ this.date = date;}
    public void setStart_at(String start){ this.start_at = start;}
    public void setEnd_at(String end){ this.end_at = end;}

    public int getId(){ return this.id;}
    public int getId_user(){ return this.id_user;}
    public int getId_location(){ return this.id_location;}
    public String getName(){ return this.name;}
    public String getDate(){ return this.date;}
    public String getStart_at(){return this.start_at;}
    public String getEnd_at(){ return end_at;}

    @Override
    public String toString() {
        return "ActivityBean{" +
                "id=" + id +
                ", id_user='" + id_user + '\'' +
                ", id_location='" + id_location + '\'' +
                ", name='" + name + '\'' +
                ", date='" + date + '\'' +
                ", start_at='" + start_at + '\'' +
                ", end_at='" + end_at + '\'' +
                '}';
    }

}
