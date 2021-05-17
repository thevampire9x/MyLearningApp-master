package com.example.mylearningapp.JDBC;

import java.sql.Connection;

public class JDBCController {
    JDBCModel JdbcModel = new JDBCModel();

    public Connection ConnnectionData() {
        return JdbcModel.getConnectionOf();
    }
}
