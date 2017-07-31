package com.puigros.gemfire.model;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by gpuigros on 31/07/17.
 */
@Data
public class CustomerAddress implements Serializable {
    private String street;
    private Integer number;
}
