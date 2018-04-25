package com.netcracker.model;

import java.math.BigDecimal;
import java.util.UUID;

public class CashBox {
    private UUID id;
    private UUID userId;
    private BigDecimal sum;//!rename

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    public BigDecimal getSum() {
        return sum;
    }

    public void setSum(String sum) {
        this.sum = new  BigDecimal(sum);
    }

    public void setSum(BigDecimal sum) {
        this.sum = sum;
    }

}
