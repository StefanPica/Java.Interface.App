package com.example.javainterf.Service;

import com.example.javainterf.Domain.Activitate;
import com.example.javainterf.Domain.ActivitateFizica;
import com.example.javainterf.Repository.ActivitateFizicaRepository;
import com.example.javainterf.Repository.ActivitateRepository;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class ActivitateService {
    ActivitateRepository repoActivitate;
    ActivitateFizicaRepository repoActivitateFizica;

    public ActivitateService(ActivitateRepository repoActivitate, ActivitateFizicaRepository repoActivitateFizica) {
        this.repoActivitate = repoActivitate;
        this.repoActivitateFizica = repoActivitateFizica;
    }

    public void add(Integer id, String data, int nrPasi, int nrOreSomn, String descriereText, int minuteMiscare) {
        List<Activitate> activitati = (List<Activitate>) getAll();

        // Verifica daca exista deja o activitate pentru data specificata
        Activitate activitateExistenta = null;
        for (Activitate activitate : activitati) {
            if (activitate.getData().equals(data)) {
                activitateExistenta = activitate;
                break;
            }
        }

        if (activitateExistenta != null) {
            // Actualizeaza activitatea existenta cu valorile noi
            activitateExistenta.setNrPasi(nrPasi);
            activitateExistenta.setNrOreSomn(nrOreSomn);
        } else {
            List<String> descrieriText = citesteDescrieriText("com/example/javainterf/settings.properties");
            if (!descrieriText.contains(descriereText)) {
                throw new IllegalArgumentException("Descrierea text nu este valida.");
            }

            ActivitateFizica activitateFizica = new ActivitateFizica(descriereText, minuteMiscare);
            List<ActivitateFizica> activitatiFizice = List.of(activitateFizica);
            repoActivitateFizica.add(activitateFizica, id);

            Activitate activitateNoua = new Activitate(id, data, nrPasi, nrOreSomn);
            activitateNoua.setActivitati(activitatiFizice);
            repoActivitate.add(activitateNoua);
        }
    }

    public void delete(Integer id) {
        repoActivitate.delete(id);
    }

    public void update(Integer id, Activitate entity) {
        repoActivitate.update(id, entity);
    }

    public Iterable<Activitate> getAll() {
        return repoActivitate.getAll();
    }

    public List<Activitate> getActivitatiOrdonate() {
        List<Activitate> activitati = (List<Activitate>) getAll();
        Collections.sort(activitati, Comparator.comparing(Activitate::getData));
        return activitati;
    }

    public List<Activitate> filtreazaActivitatiDupaMinuteMinMax(int minMinute, int maxMinute) {
        List<Activitate> activitati = (List<Activitate>) getAll();
        List<Activitate> activitatiFiltrate = new ArrayList<>();

        for (Activitate activitate : activitati) {
            List<ActivitateFizica> activitatiFiziceFiltrate = getActivitatiFizice(activitate.getId())
                    .stream()
                    .filter(a -> a.getMinuteMiscare() >= minMinute && a.getMinuteMiscare() <= maxMinute)
                    .collect(Collectors.toList());

            if (!activitatiFiziceFiltrate.isEmpty()) {
                Activitate activitateNoua = new Activitate(
                        activitate.getId(),
                        activitate.getData(),
                        activitate.getNrPasi(),
                        activitate.getNrOreSomn()
                );
                activitateNoua.setActivitati(activitatiFiziceFiltrate);
                activitatiFiltrate.add(activitateNoua);
            }
        }

        return activitatiFiltrate;
    }

    public List<Activitate> filtreazaActivitatiDupaMinuteMin(int minMinute) {
        List<Activitate> activitati = (List<Activitate>) getAll();
        List<Activitate> activitatiFiltrate = new ArrayList<>();

        for (Activitate activitate : activitati) {
            List<ActivitateFizica> activitatiFiziceFiltrate = getActivitatiFizice(activitate.getId())
                    .stream()
                    .filter(a -> a.getMinuteMiscare() >= minMinute)
                    .collect(Collectors.toList());

            if (!activitatiFiziceFiltrate.isEmpty()) {
                Activitate activitateNoua = new Activitate(
                        activitate.getId(),
                        activitate.getData(),
                        activitate.getNrPasi(),
                        activitate.getNrOreSomn()
                );
                activitateNoua.setActivitati(activitatiFiziceFiltrate);
                activitatiFiltrate.add(activitateNoua);
            }
        }

        return activitatiFiltrate;
    }

    public List<Activitate> filtreazaActivitatiDupaMinuteMax(int maxMinute) {
        List<Activitate> activitati = (List<Activitate>) getAll();
        List<Activitate> activitatiFiltrate = new ArrayList<>();

        for (Activitate activitate : activitati) {
            List<ActivitateFizica> activitatiFiziceFiltrate = getActivitatiFizice(activitate.getId())
                    .stream()
                    .filter(a -> a.getMinuteMiscare() <= maxMinute)
                    .collect(Collectors.toList());

            if (!activitatiFiziceFiltrate.isEmpty()) {
                Activitate activitateNoua = new Activitate(
                        activitate.getId(),
                        activitate.getData(),
                        activitate.getNrPasi(),
                        activitate.getNrOreSomn()
                );
                activitateNoua.setActivitati(activitatiFiziceFiltrate);
                activitatiFiltrate.add(activitateNoua);
            }
        }

        return activitatiFiltrate;
    }

    private List<String> citesteDescrieriText(String filePath) {
        List<String> descrieriText = new ArrayList<>();

        try (FileInputStream input = new FileInputStream(filePath)) {
            Properties properties = new Properties();
            properties.load(input);

            String descrieriTextProperty = properties.getProperty("descrieri_text");
            String[] descrieriArray = descrieriTextProperty.split(",");
            descrieriText.addAll(Arrays.asList(descrieriArray));
        } catch (IOException e) {
            e.printStackTrace(); // sau gestionează eroarea în alt mod, excepție, etc.
        }

        return descrieriText;
    }

    public List<ActivitateFizica> getActivitatiFizice(Integer id_activitate) {
        return repoActivitateFizica.findByActivitateId(id_activitate);
    }
}
