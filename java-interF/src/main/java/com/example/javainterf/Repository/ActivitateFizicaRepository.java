package com.example.javainterf.Repository;

import com.example.javainterf.Domain.ActivitateFizica;

import java.util.List;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ActivitateFizicaRepository {
    private final JDBCUtils jdbcUtils;

    public ActivitateFizicaRepository(Properties properties) {
        jdbcUtils = new JDBCUtils(properties);
    }

    public void add(ActivitateFizica entity, Integer idActivitate) {
        String query = "INSERT INTO activitate_fizica(descriere,minute,id_activitate) VALUES(?,?,?)";
        try (Connection connection = jdbcUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)
        ) {
            statement.setString(1, entity.getDescriere());
            statement.setInt(2, entity.getMinuteMiscare());
            statement.setInt(3, idActivitate);

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(Integer id) {

    }

    public void update(Integer id, ActivitateFizica entity) {

    }

    public List<ActivitateFizica> findByActivitateId(Integer id_activitate) {
        String query = "SELECT * FROM activitate_fizica WHERE id_activitate=?";
        List<ActivitateFizica> activitati = new ArrayList<>();
        try (Connection connection = jdbcUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)
        ) {
            statement.setInt(1, id_activitate);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String descriere = resultSet.getString("descriere");
                int minute = resultSet.getInt("minute");
                ActivitateFizica activitate = new ActivitateFizica(descriere, minute);
                activitati.add(activitate);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return activitati;
    }
}
