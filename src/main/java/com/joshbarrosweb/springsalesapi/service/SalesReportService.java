package com.joshbarrosweb.springsalesapi.service;

import net.sf.jasperreports.engine.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class SalesReportService {

    @Value("classpath:reports/sales-report.jrxml")
    private Resource salesReport;

    @Autowired
    private DataSource dataSource;

    public byte[] generateReport(Long clientId, Date startDate, Date endDate) {
        try (
           Connection connection = dataSource.getConnection()
        ){
           JasperReport compiledReport = JasperCompileManager.compileReport(salesReport.getInputStream());

           Map<String, Object> parameters = new HashMap<>();
           parameters.put("CLIENT_ID", clientId);
           parameters.put("START_DATE", startDate);
           parameters.put("END_DATE", endDate);

           JasperPrint print = JasperFillManager.fillReport(compiledReport, parameters, connection);

           return JasperExportManager.exportReportToPdf(print);

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JRException e) {
            e.printStackTrace();
        }

        return null;
    }
}
