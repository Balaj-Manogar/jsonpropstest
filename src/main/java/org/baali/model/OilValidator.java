package org.baali.model;

public class OilValidator implements Validator
{

    @Override
    public void validate(OutModel dto) {
        System.out.println("PalmOilValidator validator");
    }

}
