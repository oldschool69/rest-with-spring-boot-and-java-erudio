package br.com.oldschool69.rest_with_spring_boot_and_java.file.importer.factory;

import br.com.oldschool69.rest_with_spring_boot_and_java.exception.BadRequestException;
import br.com.oldschool69.rest_with_spring_boot_and_java.file.importer.contract.FileImporter;
import br.com.oldschool69.rest_with_spring_boot_and_java.file.importer.impl.CsvImporter;
import br.com.oldschool69.rest_with_spring_boot_and_java.file.importer.impl.XlsxImporter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class FileImporterFactory {
    private Logger logger = LoggerFactory.getLogger(FileImporterFactory.class);

    @Autowired
    private ApplicationContext context;

    public FileImporter getImporter(String fileName) throws Exception {
        if (fileName.endsWith(".xlsx")) {
            return context.getBean(XlsxImporter.class);
            //return new XlsxImporter();
        } else if(fileName.endsWith(".csv")) {
            return context.getBean(CsvImporter.class);
            //return new CsvImporter();
        } else {
            throw new BadRequestException("Invalid file format");
        }
    }
}
