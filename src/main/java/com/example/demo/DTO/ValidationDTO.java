package com.example.demo.DTO;

import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.*;
import java.math.BigDecimal;

@Validated
public class ValidationDTO {
    @Null(message = "必须为null")
    private int id;
    @NotNull(message = "不能为null")
    @NotEmpty(message = "不能为空")
    private String notNullValidation;

    @Min(value = 100,message = "最小100")
    @Max(value = 1000,message = "最大1000")
    private int numValidation;

    @Digits(integer = 1,fraction = 2,message = "小数2位")
    private BigDecimal digits;

    public BigDecimal getDigits() {
        return digits;
    }

    public void setDigits(BigDecimal digits) {
        this.digits = digits;
    }

    public int getNumValidation() {
        return numValidation;
    }

    public void setNumValidation(int numValidation) {
        this.numValidation = numValidation;
    }

    public String getNotNullValidation() {
        return notNullValidation;
    }

    public void setNotNullValidation(String notNullValidation) {
        this.notNullValidation = notNullValidation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
