package com.validation.demovalidation;


import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;


public class UserDTO {
   @NotBlank(message = "Must input your  First Name")
   @Size(min=2,max=50,message ="First Name must be between 2 and 50 characters" )
    private String firstName;

   @NotBlank(message = "Must input your Last Name")
   @Size(min=2,max=50,message="Last Name must be between 2 and 50 characters")
    private String lastName;


    @Email(message = "Please input email")
    @NotEmpty(message = "Email required")
    private String email;

      @DecimalMin(value = "20.0", inclusive = false, message = "Price must be greater than 20")
      @DecimalMax(value = "10000.0", inclusive = true, message = "Price must not exceed 10000")
       private Double price;


    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public Double getPrice() {
        return price;
    }
    public void setPrice(Double price) {
        this.price = price;
    }
}
