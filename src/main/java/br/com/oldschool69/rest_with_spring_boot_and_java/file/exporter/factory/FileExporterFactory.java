package br.com.oldschool69.rest_with_spring_boot_and_java.file.exporter.factory;

import br.com.oldschool69.rest_with_spring_boot_and_java.exception.BadRequestException;
import br.com.oldschool69.rest_with_spring_boot_and_java.file.exporter.MediaTypes;
import br.com.oldschool69.rest_with_spring_boot_and_java.file.exporter.contract.PersonExporter;
import br.com.oldschool69.rest_with_spring_boot_and_java.file.exporter.impl.CsvExporter;
import br.com.oldschool69.rest_with_spring_boot_and_java.file.exporter.impl.PdfExporter;
import br.com.oldschool69.rest_with_spring_boot_and_java.file.exporter.impl.XlsxExporter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class FileExporterFactory {
    private Logger logger = LoggerFactory.getLogger(FileExporterFactory.class);

    @Autowired
    private ApplicationContext context;

    public PersonExporter getExporter(String acceptHeader) throws Exception {
        if (acceptHeader.equalsIgnoreCase(MediaTypes.APPLICATION_XLSX_VALUE)) {
            return context.getBean(XlsxExporter.class);
        } else if(acceptHeader.endsWith(MediaTypes.APPLICATION_CSV_VALUE)) {
            return context.getBean(CsvExporter.class);
        }
        else if(acceptHeader.endsWith(MediaTypes.APPLICATION_PDF_VALUE)) {
            return context.getBean(PdfExporter.class);
        } else {
            throw new BadRequestException("Invalid file format");
        }
    }
}
