package com.template.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.io.File;

public class ExtentManager {
    private static ExtentReports extent;
    private static ExtentTest test;
    private static String reportPath;

    public static ExtentReports getInstance() {
        if (extent == null) {
            String basePath = System.getProperty("user.dir");
            reportPath = basePath + "/target/reports/ExtentReport.html";

            File reportFile = new File(reportPath);
            if (!reportFile.getParentFile().exists()) {
                boolean created = reportFile.getParentFile().mkdirs();
                System.out.println("📂 Diretório criado? " + created + " | Caminho: " + reportFile.getParentFile().getAbsolutePath());
            }

            ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);
            sparkReporter.config().setTheme(Theme.DARK);
            sparkReporter.config().setDocumentTitle("Relatório de Testes Automatizados");
            sparkReporter.config().setReportName("Execução dos Testes - Magento");
            sparkReporter.config().setEncoding("UTF-8");

            extent = new ExtentReports();
            extent.attachReporter(sparkReporter);
            System.out.println("✅ ExtentReports inicializado com sucesso! Relatório será gerado em: " + reportPath);
        }
        return extent;
    }

    public static ExtentTest createTest(String testName) {
        test = getInstance().createTest(testName);
        return test;
    }

    public static ExtentTest getTest() {
        return test;
    }

    public static void flush() {
        if (extent != null) {
            extent.flush();
            System.out.println("📄 Relatório gerado com sucesso: " + reportPath);
        } else {
            System.out.println("❌ ERRO: ExtentReports não inicializado corretamente.");
        }
    }
}