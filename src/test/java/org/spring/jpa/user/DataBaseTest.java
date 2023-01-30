package org.spring.jpa.user;

import org.dbunit.database.DatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatDtdDataSet;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;
import java.io.FileOutputStream;

/**
 * database basic test: generate database.dtd
 *
 * @author linux_china
 */
public class DataBaseTest extends SpringBaseTest {

    @Test
    public void testDTDGeneration(@Autowired DataSource dataSource) throws Exception {
        DatabaseConnection databaseConnection = new DatabaseConnection(dataSource.getConnection());
        final IDataSet dataSet = databaseConnection.createDataSet();
        FlatDtdDataSet.write(dataSet, new FileOutputStream("database.dtd"));
        FlatDtdDataSet.write(dataSet, new FileOutputStream("src/test/resources/db/datasets/database.dtd"));
    }
}
