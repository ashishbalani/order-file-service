package com.interview.round1.ap123456.orderfileservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.camel.dataformat.bindy.annotation.CsvRecord;
import org.apache.camel.dataformat.bindy.annotation.DataField;

import java.io.Serializable;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@CsvRecord(separator = ",", skipFirstLine = true)
public class Order implements Serializable {

    @DataField(pos = 1)
    private Long orderId;
    @DataField(pos = 2)
    private String price;
    @DataField(pos = 3)
    private Long parentId;
    @DataField(pos = 4)
    private Integer executedQuantity;
    @DataField(pos = 5)
    private Integer quantity;
    @DataField(pos = 6)
    private String security;

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", price='" + price + '\'' +
                ", parentId=" + parentId +
                ", executedQuantity=" + executedQuantity +
                ", quantity=" + quantity +
                ", security='" + security + '\'' +
                '}';
    }
}