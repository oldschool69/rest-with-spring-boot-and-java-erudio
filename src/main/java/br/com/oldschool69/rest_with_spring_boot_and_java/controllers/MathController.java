package br.com.oldschool69.rest_with_spring_boot_and_java.controllers;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;




@RestController
@RequestMapping("/math")
public class MathController {
    //http://localhost:8080/math/sum/3/5
    @RequestMapping("/sum/{numberOne}/{numberTwo}")
    public Double sum(@PathVariable("numberOne") String numberOne,
                      @PathVariable("numberTwo") String numberTwo
    ) throws Exception {
        if(!isNumeric(numberOne) || !isNumeric(numberTwo))
            throw new UnsupportedOperationException("Please set a numeric value");
        return convertToDouble(numberOne) + convertToDouble(numberTwo);
    }

    @RequestMapping("/sub/{numberOne}/{numberTwo}")
    public Double sub(@PathVariable("numberOne") String numberOne,
                      @PathVariable("numberTwo") String numberTwo
    ) throws Exception {
        if(!isNumeric(numberOne) || !isNumeric(numberTwo))
            throw new UnsupportedOperationException("Please set a numeric value");
        return convertToDouble(numberOne) - convertToDouble(numberTwo);
    }

    @RequestMapping("/mult/{numberOne}/{numberTwo}")
    public Double mult(@PathVariable("numberOne") String numberOne,
                      @PathVariable("numberTwo") String numberTwo
    ) throws Exception {
        if(!isNumeric(numberOne) || !isNumeric(numberTwo))
            throw new UnsupportedOperationException("Please set a numeric value");
        return convertToDouble(numberOne) * convertToDouble(numberTwo);
    }

    @RequestMapping("/div/{numberOne}/{numberTwo}")
    public Double div(@PathVariable("numberOne") String numberOne,
                       @PathVariable("numberTwo") String numberTwo
    ) throws Exception {
        if(!isNumeric(numberOne) || !isNumeric(numberTwo))
            throw new UnsupportedOperationException("Please set a numeric value");
        if(convertToDouble(numberTwo) == 0)
            throw new UnsupportedOperationException("Invalid division by zero");
        return convertToDouble(numberOne) / convertToDouble(numberTwo);
    }

    @RequestMapping("/mean/{numberOne}/{numberTwo}")
    public Double mean(@PathVariable("numberOne") String numberOne,
                      @PathVariable("numberTwo") String numberTwo
    ) throws Exception {
        if(!isNumeric(numberOne) || !isNumeric(numberTwo))
            throw new UnsupportedOperationException("Please set a numeric value");
        return (convertToDouble(numberOne) + convertToDouble(numberTwo)) / 2;
    }

    @RequestMapping("/sqrt/{number}")
    public Double sqrt(@PathVariable("number") String number) throws Exception {
        if(!isNumeric(number))
            throw new UnsupportedOperationException("Please set a numeric value");
        return Math.sqrt(convertToDouble(number));
    }


    private Double convertToDouble(String strNumber) {
        if (strNumber == null || strNumber.isEmpty())
            throw new UnsupportedOperationException("Please set a numeric value");
        String number = strNumber.replace(",", ".");
        return Double.parseDouble(number);
    }

    private boolean isNumeric(String strNumber){
        if (strNumber == null || strNumber.isEmpty()) return false;
        String number = strNumber.replaceAll(",", ".");
        return number.matches("[-+]?[0-9]*\\.?[0-9]+");
    }

}
