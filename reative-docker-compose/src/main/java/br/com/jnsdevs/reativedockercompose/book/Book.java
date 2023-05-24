package br.com.jnsdevs.reativedockercompose.book;

import io.r2dbc.spi.Row;
import io.r2dbc.spi.RowMetadata;

/**
 * @Autor Jairo Nascimento
 * @Created 24/05/2023 - 09:33
 */

public record Book(Long id, String title, String isbn) {

    // Not used in this example
    public static Book fromRow(Row row, RowMetadata metadata) {
        return new Book(
                row.get("id", Long.class),
                row.get("title", String.class),
                row.get("isbn", String.class)
        );
    }
}
