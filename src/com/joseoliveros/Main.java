package com.joseoliveros;

import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.database.QueryDataSet;
import org.dbunit.database.search.TablesDependencyHelper;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;

import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;

public class Main {

    public static void main(String[] args) throws Exception {
        
        Class driverClass = Class.forName("com.mysql.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/dbappwebagenda";
        Connection jdbcConnection = DriverManager.getConnection(url, "root", "root");
        IDatabaseConnection connection = new DatabaseConnection(jdbcConnection);

        // partial database export
        QueryDataSet partialDataSet = new QueryDataSet(connection);
//        partialDataSet.addTable("FOO", "SELECT * FROM message");
        partialDataSet.addTable("tactividad");
        FlatXmlDataSet.write(partialDataSet, new FileOutputStream("partial.xml"));

        // full database export
        IDataSet fullDataSet = connection.createDataSet();
        FlatXmlDataSet.write(fullDataSet, new FileOutputStream("full.xml"));

        // dependent tables database export: export table X and all tables that
        // have a PK which is a FK on X, in the right order for insertion
        String[] depTableNames =
                TablesDependencyHelper.getAllDependentTables( connection, "tactividad" );
        IDataSet depDataSet = connection.createDataSet( depTableNames );
        FlatXmlDataSet.write(depDataSet, new FileOutputStream("dependents.xml"));
    }
}
