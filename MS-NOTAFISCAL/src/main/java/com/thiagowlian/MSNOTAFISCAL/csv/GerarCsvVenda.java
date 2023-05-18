package com.thiagowlian.MSNOTAFISCAL.csv;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Set;

import com.thiagowlian.MSNOTAFISCAL.dto.ProdutoDto;

public class GerarCsvVenda {

    private String arquivoLocal;
    private final String TMP_PATH = "./tmp";

    public GerarCsvVenda(String arquivoLocal) {
        this.arquivoLocal = TMP_PATH + "/" + arquivoLocal;
    }

    private CSVPrinter gerarCabecalho() {
        try {
            BufferedWriter writer = Files.newBufferedWriter(Paths.get(arquivoLocal));

            return new CSVPrinter(writer, CSVFormat.DEFAULT
                    .withHeader("Código", "Nome", "Quantidade", "Valor Unitário", "Valor Total"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private CSVPrinter gerarConteudo(CSVPrinter csvPrinter ,Set<ProdutoDto> produtos) {
        produtos.forEach(e -> {
            try {
                csvPrinter.printRecord(e.codigo());
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        return csvPrinter;
    }

    public String gerarArquivoCsv(Set<ProdutoDto> produtoDtos) throws IOException {
        CSVPrinter csvPrinter = gerarCabecalho();
        csvPrinter = gerarConteudo(csvPrinter, produtoDtos);
        csvPrinter.flush();
        return arquivoLocal;
    }
}
