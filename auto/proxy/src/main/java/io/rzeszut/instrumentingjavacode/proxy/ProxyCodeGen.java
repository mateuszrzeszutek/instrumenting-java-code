package io.rzeszut.instrumentingjavacode.proxy;

import java.lang.reflect.Proxy;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProxyCodeGen {

  public static void main(String[] args) throws SQLException {

    try (var connection = DriverManager.getConnection("jdbc:h2:mem:proxy")) {
      var statement = connection.prepareStatement("SELECT 'Hello World'");

      statement = instrument(statement);

      statement.execute();
      try (ResultSet rs = statement.getResultSet()) {
        if (!rs.next()) throw new AssertionError();
        System.out.println(rs.getString(1));
      }
    }
  }

  static PreparedStatement instrument(PreparedStatement ps) {
    return (PreparedStatement) Proxy.newProxyInstance(
        ps.getClass().getClassLoader(),
        new Class[]{PreparedStatement.class},
        new TimedStatementHandler(ps));
  }
}
