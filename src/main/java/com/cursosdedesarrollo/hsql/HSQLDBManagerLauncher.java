package com.cursosdedesarrollo.hsql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by pepesan on 16/5/16.
 */
public class HSQLDBManagerLauncher {

    public static void main(String[] argv) {

        org.hsqldb.util.DatabaseManagerSwing manager=new org.hsqldb.util.DatabaseManagerSwing();
        manager.main();
    }
}