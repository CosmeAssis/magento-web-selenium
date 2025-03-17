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
            File reportDir = reportFile.getParentFile();
            if (!reportDir.exists()) {
                if (reportDir.mkdirs()) {
                    System.out.println("✅ Diretório de relatórios criado: " + reportDir.getAbsolutePath());
                } else {
                    System.out.println("❌ ERRO: Falha ao criar o diretório de relatórios!");
                }
            }

            ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);
            sparkReporter.config().setTheme(Theme.STANDARD);
            sparkReporter.config().setDocumentTitle("Relatório de Testes Automatizados");
            sparkReporter.config().setReportName("Execução dos Testes - Selenium");
            sparkReporter.config().setEncoding("UTF-8");

            extent = new ExtentReports();
            extent.attachReporter(sparkReporter);
            System.out.println("✅ ExtentReports inicializado: " + reportPath);
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
            System.out.println("📄 Relatório salvo com sucesso em: " + reportPath);
        } else {
            System.out.println("❌ ERRO: ExtentReports não foi inicializado corretamente.");
        }
    }
}