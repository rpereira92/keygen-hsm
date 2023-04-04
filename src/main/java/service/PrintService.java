package service;

import javafx.print.*;
import javafx.scene.Node;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class PrintService {

    public static void printPanel(Node panel) {
        PrinterJob job = configurePrinter();

        if (job != null && job.showPrintDialog(null)) {

            if (job.printPage(job.getJobSettings().getPageLayout(), panel)) {
                job.endJob();
                log.info("Key Component and KCV printed successfully.");
                log.info(HsmService.getHsmInformations());
            } else {
                log.warn("Printing failed.");
            }
            job.endJob();
        } else {
            log.warn("Impressão cancelada pelo usuário.");
        }
    }

    private static PrinterJob configurePrinter() {
        Printer printer = Printer.getDefaultPrinter();
        PrinterJob job = PrinterJob.createPrinterJob(printer);
        if (job != null) {
            PageLayout pageLayout = printer.createPageLayout(Paper.A4, PageOrientation.PORTRAIT, Printer.MarginType.DEFAULT);
            job.getJobSettings().setPageLayout(pageLayout);
            job.getJobSettings().setPrintQuality(PrintQuality.HIGH);
            job.getJobSettings().setCopies(1);
        }
        return job;
    }
}
