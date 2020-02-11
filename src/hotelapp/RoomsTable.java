package hotelapp;

/**
 *
 * @author YVON
 */
public class RoomsTable {

    String  NUMB, FIRSTNAME, LASTNAME, TYPENAME, INDATE, STATE;

    public RoomsTable(String NUMB, String FIRSTNAME, String LASTNAME, String TYPENAME, String INDATE, String STATE) {
        this.NUMB = NUMB;
        this.FIRSTNAME = FIRSTNAME;
        this.LASTNAME = LASTNAME;
        this.TYPENAME = TYPENAME;
        this.INDATE = INDATE;
        this.STATE = STATE;
    }

    public String getNUMB() {
        return NUMB;
    }

    public void setNUMB(String NUMB) {
        this.NUMB = NUMB;
    }

    public String getFIRSTNAME() {
        return FIRSTNAME;
    }

    public void setFIRSTNAME(String FIRSTNAME) {
        this.FIRSTNAME = FIRSTNAME;
    }

    public String getLASTNAME() {
        return LASTNAME;
    }

    public void setLASTNAME(String LASTNAME) {
        this.LASTNAME = LASTNAME;
    }

    public String getTYPENAME() {
        return TYPENAME;
    }

    public void setTYPENAME(String TYPENAME) {
        this.TYPENAME = TYPENAME;
    }

    public String getINDATE() {
        return INDATE;
    }

    public void setINDATE(String INDATE) {
        this.INDATE = INDATE;
    }

    public String getSTATE() {
        return STATE;
    }

    public void setSTATE(String STATE) {
        this.STATE = STATE;
    }

    
}
