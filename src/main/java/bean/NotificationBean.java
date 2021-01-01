package bean;

public class NotificationBean {

    int id;
    String message;
    boolean check;

    public void setId(int id){ this.id = id;}
    public void setMessage(String msg){ this.message = msg;}
    public void setCheck(boolean bool){ this.check = bool;}

    public int getId(){ return this.id;}
    public String getMessage(){ return this.message;}
    public boolean getCheck(){ return this.check;}

    @Override
    public String toString() {
        return "NotificationBean{" +
                "id=" + id +
                ", message='" + message + '\'' +
                ", check='" + check + '\'' +
                '}';
    }
}
