package com.szilardolah.mini.bean;


import com.szilardolah.mini.annotation.WebshopBean;
import com.szilardolah.mini.constraint.DateOfBirth;
import com.szilardolah.mini.constraint.Name;
import com.szilardolah.mini.constraint.Password;
import com.szilardolah.mini.enums.Sex;
import java.util.Calendar;
import java.util.Date;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.validation.constraints.Past;


/**
 *
 * @author Szilard <szilard.olah@yahoo.com>
 */
@WebshopBean
@Name 
@DateOfBirth
public class UserDTO implements Serializable{    
    @NotNull @Size(min = 6)
    private String username;
    
    @NotNull @Password
    private String password;
    
    private String firstname;
    
    private String lastname;
    
    @Pattern( regexp = "^\\d{4}.*" )
    private String address;
    
    @Pattern (regexp = "^\\+(36|06)\\d{9}")
    private String phone;
    
    @NotNull @Pattern (regexp = ".*@.*\\..{2,3}") 
    private String email;
    
    private Sex sex;
    
    @NotNull @Past()
    private Date registrationDate;
    
    private Date dateOfBirth;
    
    private boolean admin;

    
    public UserDTO() {
        //Default constructor for ObjectMapper
    }
    
    private UserDTO(Builder builder) {
        this.username = builder.username;
        this.password = builder.password;
        this.firstname = builder.firstname;
        this.lastname = builder.lastname;
        this.address = builder.address;
        this.phone = builder.phone;
        this.email = builder.email;
        this.sex = builder.sex;
        setRegistrationDate(builder.registrationDate);
        this.dateOfBirth = builder.dateOfBirth;
        this.admin = builder.admin;
    }            
    
    public String getUsername() {
        return username;
    }
 
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = (registrationDate!=null) ? registrationDate : new Date();
        try {
            Thread.sleep(10);
        } catch (InterruptedException ex) {
            Logger.getLogger(UserDTO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + Objects.hashCode(this.username);
        hash = 47 * hash + Objects.hashCode(this.password);
        hash = 47 * hash + Objects.hashCode(this.firstname);
        hash = 47 * hash + Objects.hashCode(this.lastname);
        hash = 47 * hash + Objects.hashCode(this.address);
        hash = 47 * hash + Objects.hashCode(this.phone);
        hash = 47 * hash + Objects.hashCode(this.email);
        hash = 47 * hash + Objects.hashCode(this.sex);
        hash = 47 * hash + Objects.hashCode(this.registrationDate);
        hash = 47 * hash + Objects.hashCode(this.dateOfBirth);
        hash = 47 * hash + (this.admin ? 1 : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final UserDTO other = (UserDTO) obj;
        if (this.admin != other.admin) {
            return false;
        }
        if (!Objects.equals(this.username, other.username)) {
            return false;
        }
        if (!Objects.equals(this.password, other.password)) {
            return false;
        }
        if (!Objects.equals(this.firstname, other.firstname)) {
            return false;
        }
        if (!Objects.equals(this.lastname, other.lastname)) {
            return false;
        }
        if (!Objects.equals(this.address, other.address)) {
            return false;
        }
        if (!Objects.equals(this.phone, other.phone)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        if (this.sex != other.sex) {
            return false;
        }
        if (!Objects.equals(this.registrationDate, other.registrationDate)) {
            return false;
        }
        return Objects.equals(this.dateOfBirth, other.dateOfBirth);
    }
    
    
    
    public static class Builder {
        private String username;
        private String password; 
        private String firstname;
        private String lastname;   
        private String address;   
        private String phone;   
        private String email;   
        private Sex sex;   
        private Date registrationDate;   
        private Date dateOfBirth;   
        private boolean admin;
        
        public Builder(String username, String password, String email) {
            this.username = username;
            this.password = password;
            this.email = email;
            this.registrationDate = Calendar.getInstance().getTime();
        }
        
        public Builder setUsername(String username) {
            this.username = username;
            return this;
        }
        
        public Builder setPassword(String password) {
            this.password = password;
            return this;
        }

        public Builder setFirstname(String firstname) {
            this.firstname = firstname;
            return this;
        }

        public Builder setLastname(String lastname) {
            this.lastname = lastname;
            return this;
        }

        public Builder setAddress(String address) {
            this.address = address;
            return this;
        }

        public Builder setPhone(String phone) {
            this.phone = phone;
            return this;
        }

        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder setRegistrationDate(Date registrationDate) {
            this.registrationDate = registrationDate;
            return this;
        }

        public Builder setDateOfBirth(Date dateOfBirth) {
            this.dateOfBirth = dateOfBirth;
            return this;
        }

        public Builder setAdmin(boolean admin) {
            this.admin = admin;
            return this;
        }
        
        public Builder setSex(Sex sex) {
            this.sex = sex;
            return this;
        }
        
        public UserDTO build() {
            return new UserDTO(this);
        }

    }    
}
