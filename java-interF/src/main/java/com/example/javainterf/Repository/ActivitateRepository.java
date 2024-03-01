package com.example.javainterf.Repository;

import com.example.javainterf.Domain.Activitate;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ActivitateRepository implements IRepository<Activitate> {
    private final JDBCUtils jdbcUtils;

    public ActivitateRepository(Properties properties) {
        jdbcUtils = new JDBCUtils(properties);
    }

    @Override
    public void add(Activitate entity) {
        String query = "INSERT INTO activitate(id_activitate,data,nr_pasi,ore_somn) VALUES(?,?,?,?)";
        try (Connection connection = jdbcUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)
        ) {
            statement.setInt(1, entity.getId());
            statement.setString(2, entity.getData());
            statement.setInt(3, entity.getNrPasi());
            statement.setInt(4, entity.getNrOreSomn());

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public void update(Integer id, Activitate entity) {

    }

    @Override
    public Activitate findById(Integer id) {
        String query = "SELECT * FROM activitate WHERE id_activitate=?";
        Activitate activitate = null;
        try (Connection connection = jdbcUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)
        ) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String data = resultSet.getString("data");
                int nr_pasi = resultSet.getInt("nr_pasi");
                int ore_somn = resultSet.getInt("ore_somn");
                activitate = new Activitate(id, data, nr_pasi, ore_somn);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return activitate;
    }

    @Override
    public Iterable<Activitate> getAll() {
        List<Activitate> activitati = new ArrayList<>();

        String query = "SELECT * from activitate";
        try (Connection connection = jdbcUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()
        ) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id_activitate");
                String data = resultSet.getString("data");
                int nr_pasi = resultSet.getInt("nr_pasi");
                int ore_somn = resultSet.getInt("ore_somn");

                Activitate activitate = new Activitate(id, data, nr_pasi, ore_somn);
                activitati.add(activitate);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return activitati;
    }
}