package com.nasquicode.vitalcore.bukkit.objects;

import com.nasquicode.vitalcore.bukkit.utils.Console;
import lombok.Getter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    @Getter
    private final DatabaseInfo info;
    @Getter
    private Connection connection;
    public Database(DatabaseInfo info) throws SQLException {
        this.info = info;
        connection = DriverManager.getConnection(String.format("jdbc:mysql://%s/%s?autoReconnect=true", info.getHOST(), info.getDATABASE()), info.getUSER(), info.getPASSWORD());
    }
    public Boolean isConnected() {
        return connection != null;
    }
    public void closeConnection() {
        if(isConnected()) {
            try {
                connection.close();
                Console.log(String.format("&eDatabase connection closed. Database: &b%s", info.getKEY()));
            }catch(SQLException x) {
                Console.log(String.format("&cAn error occured while closing a database connection. Database: &e%s", info.getKEY()));
            }

        }
    }
}