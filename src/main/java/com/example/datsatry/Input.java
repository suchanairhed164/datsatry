package com.example.datsatry;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;


@Entity
public class Input {

    int sortid = 0;

    private static final String URL = "jdbc:postgresql://localhost/sort_db";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "root";
    private static Connection connection;

    //CONNECT
    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    //ЗАПРОСЫ

    public void addSorting(String input) {
        String sql = "INSERT INTO sorting (name) VALUES(?);";
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement(sql);

            preparedStatement.setString(1, input);
            //System.out.println("Новая сортировка добавлена");

            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public Sorting getNewSortingId() {
        Sorting sorting = null;
        String sql = "SELECT id FROM sorting ORDER BY id DESC LIMIT 1;";

        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement(sql);

            ResultSet resultSet = preparedStatement.executeQuery();

            resultSet.next();

            sorting = new Sorting();

            sortid = resultSet.getInt("id");
            //System.out.println("Последняя сортировка: "+sortid);

            sorting.setId(resultSet.getInt("id"));

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return sorting;
    }

    public void addArray(int id_sorting, int[] arr) {
        String sql = "INSERT INTO elements (id_sorting, element) VALUES(?, ?);";
        try {
            for (int i = 0; i < arr.length; i++) {

                PreparedStatement preparedStatement =
                        connection.prepareStatement(sql);

                preparedStatement.setInt(1, id_sorting);
                preparedStatement.setInt(2, arr[i]);

                preparedStatement.executeUpdate();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public Elements getArray(int sort, ArrayList<Integer> arr) {
        Elements elements = null;
        String sql = "SELECT * FROM elements WHERE id_sorting=?;";

        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement(sql);

            preparedStatement.setInt(1, sort);

            ResultSet resultSet = preparedStatement.executeQuery();

            //resultSet.next();
            while (resultSet.next()) {
                elements = new Elements();

                arr.add(resultSet.getInt("element"));
                //System.out.println("|id: " + resultSet.getInt("id") + " |sort_name: " + resultSet.getInt("id_sorting") + " |elem: " + resultSet.getInt("element"));

                elements.setId(resultSet.getInt("id"));
                elements.setSortId(resultSet.getInt("id_sorting"));
                elements.setName(resultSet.getInt("element"));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return elements;
    }


///////////////

    /////^^^^^^^^^запросы
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String content;

    public String getContent() {
        return content;
    }

    static String text2;

    public void setContent(String content) {
        String text1 = content;

        String[] items = text1.split(" ");

        int[] results = new int[items.length];

        for (int i = 0; i < items.length; i++) {
            try {
                results[i] = Integer.parseInt(items[i]);
            } catch (NumberFormatException nfe) {
            }
            ;
        }

        //--------------ВЫЗОВ ЗАПРОСОВ---------------//
        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        String strDate = dateFormat.format(date);

        addSorting(strDate);

        getNewSortingId();
        addArray(sortid, results);

        ArrayList<Integer> list = new ArrayList<Integer>();
        list.clear();
        getArray(sortid, list);
        //System.out.println("ArrayList из базы: "+Arrays.toString(list.toArray()));

        int[] arry = new int[list.size()];
        for (int i = 0; i < arry.length; i++) {
            arry[i] = list.get(i).intValue();
        }
        //System.out.println("ArrayList to integer array: "+Arrays.toString(arry));

        Functions.bubbleSort(arry);

        String textN = Arrays.toString(arry).trim().replaceAll("\\[", "").replaceAll("\\]", "");

        text2 = textN;

        this.content = textN;
    }

}






