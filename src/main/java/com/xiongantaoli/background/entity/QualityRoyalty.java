package com.xiongantaoli.background.entity;

import java.math.BigDecimal;

public class QualityRoyalty {
    private Long id;

    private BigDecimal royalty;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getRoyalty() {
        return royalty;
    }

    public void setRoyalty(BigDecimal royalty) {
        this.royalty = royalty;
    }
}