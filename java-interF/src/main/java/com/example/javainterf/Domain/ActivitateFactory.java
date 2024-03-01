package com.example.javainterf.Domain;

import java.util.ArrayList;
import java.util.List;

public class ActivitateFactory implements IEntityFactory<Activitate> {
    @Override
    public String entityToString(Activitate entity) {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(entity.getId()).append(";");
        stringBuilder.append(entity.getData()).append(";");
        stringBuilder.append(entity.getNrPasi()).append(";");
        stringBuilder.append(entity.getNrOreSomn()).append(";");

        List<ActivitateFizica> activitati = entity.getActivitati();
        for (int i = 0; i < activitati.size(); i++) {
            stringBuilder.append(activitati.get(i).getDescriere()).append(",");
            stringBuilder.append(activitati.get(i).getMinuteMiscare());

            if (i < activitati.size() - 1) {
                stringBuilder.append(";");
            }
        }

        return stringBuilder.toString();
    }

    @Override
    public Activitate StringToEntity(String line) {
        String[] parts = line.split(";");
        Integer id = Integer.parseInt(parts[0]);
        String data = parts[1];
        int nrPasi = Integer.parseInt(parts[2]);
        int nrOreSomn = Integer.parseInt(parts[3]);
        List<ActivitateFizica> activitati = new ArrayList<>();
        for (int i = 4; i < parts.length; i++) {
            String[] parts2 = parts[i].split(",");
            activitati.add(new ActivitateFizica(parts2[0], Integer.parseInt(parts2[1])));
        }
        Activitate activitate = new Activitate(id, data, nrPasi, nrOreSomn);
        activitate.setActivitati(activitati);
        return activitate;
    }
}