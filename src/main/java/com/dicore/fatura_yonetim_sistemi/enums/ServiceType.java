package com.dicore.fatura_yonetim_sistemi.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ServiceType {
    ELECTRICITY(BillingUnit.kWh),
    WATER(BillingUnit.CUBIC_METER),
    INTERNET(BillingUnit.GB);

    private final BillingUnit billingUnit;
}
