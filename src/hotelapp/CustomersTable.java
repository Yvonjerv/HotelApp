
package hotelapp;

/**
 *
 * @author YVON
 */
public class CustomersTable {
    
String CustID, FirstName,LastName,Address,PostalCode,Mobile ,email,Nationality ,Dob ,IDtype,IdNumb,Gender,State;

    public String getCustID() {
        return CustID;
    }

    public void setCustID(String CustID) {
        this.CustID = CustID;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String FirstName) {
        this.FirstName = FirstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String LastName) {
        this.LastName = LastName;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public String getPostalCode() {
        return PostalCode;
    }

    public void setPostalCode(String PostalCode) {
        this.PostalCode = PostalCode;
    }

    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String Mobile) {
        this.Mobile = Mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNationality() {
        return Nationality;
    }

    public void setNationality(String Nationality) {
        this.Nationality = Nationality;
    }

    public String getDob() {
        return Dob;
    }

    public void setDob(String Dob) {
        this.Dob = Dob;
    }

    public String getIDtype() {
        return IDtype;
    }

    public void setIDtype(String IDtype) {
        this.IDtype = IDtype;
    }

    public String getIdNumb() {
        return IdNumb;
    }

    public void setIdNumb(String IdNumb) {
        this.IdNumb = IdNumb;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String Gender) {
        this.Gender = Gender;
    }

    public String getState() {
        return State;
    }

    public void setState(String State) {
        this.State = State;
    }

    public CustomersTable(String CustID, String FirstName, String LastName, String Address, String PostalCode, 
            String Mobile, String email, String Nationality, String Dob, String IDtype, String IdNumb, String Gender, String State) {
        this.CustID = CustID;
        this.FirstName = FirstName;
        this.LastName = LastName;
        this.Address = Address;
        this.PostalCode = PostalCode;
        this.Mobile = Mobile;
        this.email = email;
        this.Nationality = Nationality;
        this.Dob = Dob;
        this.IDtype = IDtype;
        this.IdNumb = IdNumb;
        this.Gender = Gender;
         this.State = State;
    }
    
}
