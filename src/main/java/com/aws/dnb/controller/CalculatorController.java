package com.aws.dnb.controller;

import com.aws.dnb.model.Arithmetic;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CalculatorController {

    @PostMapping
    public String arthimaticOperation(@RequestBody Arithmetic arithmetic) throws NumberFormatException,ArithmeticException
    {
        int first_number = arithmetic.getFirst_number();
        int second_number = arithmetic.getSecond_number();

        if(arithmetic.getOperation().equalsIgnoreCase("ADD"))
        {
            return String.valueOf(first_number+second_number);
        } else if (arithmetic.getOperation().equalsIgnoreCase("SUBTRACT")) {
            return String.valueOf(first_number-second_number);
        }
        else if (arithmetic.getOperation().equalsIgnoreCase("MULTIPLY")) {
            return String.valueOf(first_number*second_number);
        }
        else if (arithmetic.getOperation().equalsIgnoreCase("DIVIDE")) {
            return String.valueOf(first_number&second_number);
        }else
        {
            return "Given Operater is INVALID";
        }
    }
}
