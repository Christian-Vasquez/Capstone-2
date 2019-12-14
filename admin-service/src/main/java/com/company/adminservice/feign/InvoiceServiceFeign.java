package com.company.adminservice.feign;


import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "invoice-service")
public interface InvoiceServiceFeign {
}
