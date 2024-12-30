package com.tomzxy.webQuiz.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;


public class PhoneNumberValidator implements ConstraintValidator<PhoneNumber,String> {

    @Override
    public void initialize(PhoneNumber phoneNumberNo) {

    }

    @Override
    public boolean isValid(String phoneNo, ConstraintValidatorContext cxt) {
        if(phoneNo ==null){
            return false;
        }
        // format regex length of phone number = 10 character
        if(phoneNo.matches("\\d{10}")) return true;
        // format regex the phone number within -, .or space: 000-042-6534
        else if (phoneNo.matches("\\d{3}[-\\.\\s]\\d{3}[-\\.\\s]\\d{4}")) return true;
        else //return false if nothing matches the input
            if(phoneNo.matches("\\d{3}-\\d{3}-\\d{4}\\s(x|(ext))\\d{3,5}")) return true;
                //validating phone number where area code is in braces ()
            else return phoneNo.matches("\\(\\d{3}\\)-\\d{3}-\\d{4}");
    }
}
