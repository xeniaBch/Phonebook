package model;

public class Contact {
    private String name;
    private String lastname;
    private String phone;
    private String email;
    private String city;
    private String comment;

    public Contact setName(String name) {
        this.name = name;
        return this;
    }

    public Contact setLastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public Contact setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public Contact setEmail(String email) {
        this.email = email;
        return this;
    }

    public Contact setCity(String city) {
        this.city = city;
        return this;
    }

    public Contact setComment(String comment) {
        this.comment = comment;
        return this;
    }

    public String getName() {
        return name;
    }

    public String getLastname() {
        return lastname;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getCity() {
        return city;
    }

    public String getComment() {
        return comment;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", comment='" + comment + '\'' +
                '}';
    }
}
