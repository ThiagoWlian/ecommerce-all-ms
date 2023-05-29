package com.thiagowlian.MSNOTAFISCAL.csv;

import com.thiagowlian.MSNOTAFISCAL.dto.ProdutoNfDto;
import lombok.NoArgsConstructor;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Set;

import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class GerarCsvVenda {

    private String arquivoLocal;
    private final String TMP_PATH = ".";

    private CSVPrinter gerarCabecalho() {
        try {
            BufferedWriter writer = Files.newBufferedWriter(Paths.get(arquivoLocal));
            return new CSVPrinter(writer, CSVFormat.DEFAULT
                    .withHeader("Código", "Nome", "Quantidade", "Valor Unitário", "Valor Total"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private CSVPrinter gerarConteudo(CSVPrinter csvPrinter ,Set<ProdutoNfDto> produtos) {
        produtos.forEach(e -> {
            try {
                csvPrinter.printRecord(e.codigoBarras());
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        return csvPrinter;
    }

    public String gerarArquivoCsv(String arquivoLocal ,Set<ProdutoNfDto> produtoNfDtos) throws IOException {
        this.arquivoLocal = TMP_PATH + "/" + arquivoLocal;
        CSVPrinter csvPrinter = gerarCabecalho();
        csvPrinter = gerarConteudo(csvPrinter, produtoNfDtos);
        csvPrinter.flush();
        return arquivoLocal;
    }
}
