package com.joshbarrosweb.springsalesapi.controller;

import com.joshbarrosweb.springsalesapi.entity.Sale;
import com.joshbarrosweb.springsalesapi.repository.SaleItemRepository;
import com.joshbarrosweb.springsalesapi.repository.SaleRepository;
import com.joshbarrosweb.springsalesapi.service.SalesReportService;
import com.joshbarrosweb.springsalesapi.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.Date;

@RestController
@RequestMapping("/api/sales")
@CrossOrigin("*")
public class SaleController {

    @Autowired
    private SaleRepository repository;

    @Autowired
    private SaleItemRepository saleItemRepository;

    @Autowired
    private SalesReportService salesReportService;

    @PostMapping
    @Transactional
    public void createSale(@RequestBody Sale sale) {
        repository.save(sale);
        sale.getItems().stream().forEach(si -> si.setSale(sale));
        saleItemRepository.saveAll(sale.getItems());
    }

    @GetMapping("/sales-report")
    public ResponseEntity<byte[]> salesReport(
            @RequestParam(value = "clientId", required = false, defaultValue = "") Long clientId,
            @RequestParam(value = "startDate", required = false, defaultValue = "") String startDate,
            @RequestParam(value = "endDate", required = false, defaultValue = "") String endDate
    ) {
        Date fromDate = DateUtils.fromString(startDate, false);
        Date toDate = DateUtils.fromString(endDate, true);

        if(fromDate == null) {
            fromDate = DateUtils.DEFAULT_START_DATE;
        }

        if(toDate == null) {
            toDate = DateUtils.today(true);
        }

        byte[] generatedReport = salesReportService.generateReport(clientId, fromDate, toDate);
        HttpHeaders headers = new HttpHeaders();
        var fileName = "sales-report.pdf";

        headers.setContentDispositionFormData("inline; filename=\"" + fileName + "\"", fileName);
        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
        var responseEntity = new ResponseEntity<>(generatedReport, headers, HttpStatus.OK);

        return responseEntity;
    }
}
