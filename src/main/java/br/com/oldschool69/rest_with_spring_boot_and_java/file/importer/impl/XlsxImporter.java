package br.com.oldschool69.rest_with_spring_boot_and_java.file.importer.impl;

import br.com.oldschool69.rest_with_spring_boot_and_java.data.dto.v1.PersonDTO;
import br.com.oldschool69.rest_with_spring_boot_and_java.file.importer.contract.FileImporter;

import java.io.InputStream;
import java.util.List;

public class XlsxImporter implements FileImporter {
    @Override
    public List<PersonDTO> importFile(InputStream inputStream) throws Exception {
        return List.of();
    }
}
