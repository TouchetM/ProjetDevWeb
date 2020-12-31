package bean;

public class LocationBean {

    private int id;
    private String name = "";
    private String address = "";

    public void setId(int id){ this.id = id;}
    public void setName(String name){ this.name = name;}
    public void setAddress(String address){ this.address = address;}

    public int getId(){ return this.id;}
    public String getName(){ return this.name;}
    public String getAddress(){ return this.address;}

    @Override
    public String toString() {
        return "LocationBean{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
