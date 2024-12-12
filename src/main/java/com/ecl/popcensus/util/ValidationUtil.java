package com.ecl.popcensus.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationUtil {
    public static boolean phoneNumberIsValid(String phoneNumber){
        String patterns
                = "^(\\+\\d{1,3}( )?)?((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$"
                + "|^(\\+\\d{1,3}( )?)?(\\d{3}[ ]?){2}\\d{3}$"
                + "|^(\\+\\d{1,3}( )?)?(\\d{3}[ ]?)(\\d{2}[ ]?){2}\\d{2}$";

        Pattern pattern = Pattern.compile(patterns);
            if(phoneNumber == null|| phoneNumber.isBlank())
                return false;
            Matcher matcher = pattern.matcher(phoneNumber);
            return matcher.matches();
    }

    public static boolean nameIsValid(String name){
        String reg = "^[a-zA-ZàáâäãåąčćęèéêëėįìíîïłńòóôöõøùúûüųūÿýżźñçčšžÀÁÂÄÃÅĄĆČĖĘÈÉÊËÌÍÎÏĮŁŃÒÓÔÖÕØÙÚÛÜŲŪŸÝŻŹÑßÇŒÆČŠŽ∂ð ,.'-]+$";
        Pattern pattern = Pattern.compile(reg);

        if(name == null || name.isBlank()) {
            return true; //to cater for other names
        }
        Matcher matcher = pattern.matcher(name);
        return matcher.matches();
    }
}
