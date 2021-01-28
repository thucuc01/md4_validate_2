package com.codegym.model;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

//@Entity
//@Table(name = "Users")
public class User implements Validator {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
    private String name;

    private String number;

    @Min(18)
    @Max(100)
    private String age;

    private String email;

    public User(String name, String number, String age, String email) {
        this.name = name;
        this.number = number;
        this.age = age;
        this.email = email;
    }

    public User() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean supports(Class<?> clazz) {

        return User.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        User user = (User) target;
        String name = user.getName();
        ValidationUtils.rejectIfEmpty(errors, "name", "name.empty");
        if (name.length()<3){
            errors.rejectValue("name", "name.length");
        }

        if (!name.matches("(^$|[A-Z,a-z]*$)")){
            errors.rejectValue("name", "name.matches");
        }

        String number= user.getNumber();
        ValidationUtils.rejectIfEmpty(errors, "number", "number.empty");
        if(number.length()>11||number.length()<10){
            errors.rejectValue("number", "number.length");
        }
        if (!number.startsWith("0")){
            errors.rejectValue("number", "number.startsWith");
        }
        if (!number.matches("(^$|[0-9]*$)")){
            errors.rejectValue("number", "number.matches");
        }
//        String age= user.getAge();
//        ValidationUtils.rejectIfEmpty(errors,"age","age.empty");
//        if(age.length()<3){
//            errors.rejectValue("age","age.length");
//        }
//        if(!age.matches("(^[0-9]*$)")){
//            errors.rejectValue("age", "age.matches");
//        }
        String email= user.getEmail();
        ValidationUtils.rejectIfEmpty(errors,"email","email.empty");
        if(!email.matches("^[a-z0-9](\\.?[a-z0-9]){5,}@gmail.com$")){
            errors.rejectValue("email","email.matches");
        }

    }
}
