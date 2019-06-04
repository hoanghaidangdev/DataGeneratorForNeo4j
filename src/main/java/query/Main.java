package query;

import connection.Neo4jConnection;
import de.vandermeer.asciitable.AsciiTable;
import de.vandermeer.skb.interfaces.transformers.textformat.TextAlignment;
import generator.RandomDataGenerator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static final Connection connection = new Neo4jConnection().getConnection(
            "jdbc:neo4j:bolt://localhost:7687",
            "neo4j", "12345678");

    public static void query(String query) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            long start = System.currentTimeMillis();
            ResultSet resultSet = preparedStatement.executeQuery();
            long end = System.currentTimeMillis();
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();
            AsciiTable asciiTable = new AsciiTable();
            asciiTable.addRule();
            ArrayList<String> columnNames = new ArrayList<String>();
            for (int i = 1; i <= columnCount; i++) {
                columnNames.add(metaData.getColumnName(i));
            }
            asciiTable.addRow(columnNames);
            asciiTable.addRule();
            ArrayList<String> columnContents = new ArrayList<String>();
            while (resultSet.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    columnContents.add(resultSet.getString(i));
                }
                asciiTable.addRow(columnContents);
                columnContents = new ArrayList<String>();
                asciiTable.addRule();
            }
            asciiTable.setTextAlignment(TextAlignment.CENTER);
            System.out.println(asciiTable.render());
            System.out.println("Thời gian thực thi truy vấn: " + (end - start) + " milliseconds");
            resultSet.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        RandomDataGenerator generator = new RandomDataGenerator();
        AsciiTable asciiTable = new AsciiTable();
        asciiTable.addRule();
        asciiTable.addRow("Lựa chọn", "Chức năng");
        asciiTable.addRule();
        asciiTable.addRow("1", "Sinh cơ sở dữ liệu mới");
        asciiTable.addRule();
        asciiTable.addRow("2", "Thực hiện truy vẫn cơ sở dữ liệu");
        asciiTable.addRule();
        System.out.println(asciiTable.render());
        int choice = -1;
        Scanner scanner = new Scanner(System.in);
        while ((choice != 1) && (choice != 2)) {
            System.out.print("Nhập lựa chọn: ");
            String choiceString = scanner.nextLine();
            try {
                choice = Integer.parseInt(choiceString);
            } catch (Exception e) {
                System.out.println("Lựa chọn không phù hợp, mời nhập lại");
            }
        }
        if (choice == 1) {
            while (true) {
                System.out.print("Nhập số lượng thực thể: ");
                String entitiesNumberString = scanner.nextLine();
                System.out.print("Nhập số lượng quan hệ: ");
                String relationsNumberString = scanner.nextLine();
                int entitiesNumber = 0, relationsNumber = 0;
                try {
                    entitiesNumber = Integer.parseInt(entitiesNumberString);
                    relationsNumber = Integer.parseInt(relationsNumberString);
                } catch (Exception e) {
                    System.out.println("Số lượng không phù hợp, mời nhập lại ");
                }
                if (entitiesNumber > 0 && relationsNumber > 0) {
                    generator.generate(entitiesNumber, relationsNumber);
                    break;
                }
            }
        }
        boolean check = false;
        while (true) {
            if (check) {
                System.out.println("Để thoát truy vấn, nhập exit()");
            }
            check = true;
            System.out.print("Nhập câu truy vấn: ");
            String query = scanner.nextLine();
            if (query.equals("exit()")) {
                break;
            } else {
                Main.query(query);
            }
        }
        try {
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
