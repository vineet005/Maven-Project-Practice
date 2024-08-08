package Employee;

public class Address {
    private String street;
    private String city;
    private String zip_Code;

    public Address(String street, String city, String zip_Code) {
        this.street = street;
        this.city = city;
        this.zip_Code = zip_Code;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZip_Code() {
        return zip_Code;
    }

    public void setZip_Code(String zip_Code) {
        this.zip_Code = zip_Code;
    }

    @Override
    public String toString() {
        return "Address{" +
                "street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", zip_Code='" + zip_Code + '\'' +
                '}';
    }
}
