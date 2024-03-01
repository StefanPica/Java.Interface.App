package com.example.javainterf;

import com.example.javainterf.Domain.Activitate;
import com.example.javainterf.Domain.ActivitateFizica;
import com.example.javainterf.Service.ActivitateService;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.util.List;

public class HelloController {
    private ActivitateService service;

    @FXML
    private TextField textFieldNrMinim;

    @FXML
    private TextField textFieldNrMaxim;

    @FXML
    private ListView lista;

    @FXML
    private TextField textFieldId;

    @FXML
    private TextField textFieldData;

    @FXML
    private TextField textFieldPasi;

    @FXML
    private TextField textFieldOre;

    @FXML
    private TextField textFieldDescriere;

    @FXML
    private TextField textFieldMinute;

    void setService(ActivitateService service) {
        this.service = service;
        init_list();
    }

    @FXML
    private void init_list() {
        lista.getItems().clear();

        List<Activitate> activitati = service.getActivitatiOrdonate();
        for (Activitate activitate : activitati) {
            lista.getItems().add("Data: " + activitate.getData() + ", Nr. pasi: " + activitate.getNrPasi() + ", Nr. ore somn: " + activitate.getNrOreSomn() + ", Activitati: ");
            List<ActivitateFizica> activitatiFizice = service.getActivitatiFizice(activitate.getId());
            for (ActivitateFizica af : activitatiFizice) {
                lista.getItems().add("Descriere text: " + af.getDescriere() + ", Nr. minute miscare: " + af.getMinuteMiscare());
            }
        }
    }


    @FXML
    private void filtreazaButtonOnClick() {
        lista.getItems().clear();

        if (textFieldNrMinim.getText().isEmpty()) {
            int minuteMaxim = Integer.parseInt(textFieldNrMaxim.getText());
            List<Activitate> activitatiFiltrate = service.filtreazaActivitatiDupaMinuteMax(minuteMaxim);

            for (Activitate activitate : activitatiFiltrate) {
                lista.getItems().add("Data: " + activitate.getData() + ", Nr. pasi: " + activitate.getNrPasi() + ", Nr. ore somn: " + activitate.getNrOreSomn() + ", Activitati: ");
                List<ActivitateFizica> activitatiFizice = service.getActivitatiFizice(activitate.getId());
                for (ActivitateFizica af : activitatiFizice) {
                    lista.getItems().add("Descriere text: " + af.getDescriere() + ", Nr. minute miscare: " + af.getMinuteMiscare());
                }
            }
        } else if (textFieldNrMaxim.getText().isEmpty()) {
            int minuteMinim = Integer.parseInt(textFieldNrMinim.getText());
            List<Activitate> activitatiFiltrate = service.filtreazaActivitatiDupaMinuteMin(minuteMinim);

            for (Activitate activitate : activitatiFiltrate) {
                lista.getItems().add("Data: " + activitate.getData() + ", Nr. pasi: " + activitate.getNrPasi() + ", Nr. ore somn: " + activitate.getNrOreSomn() + ", Activitati: ");
                List<ActivitateFizica> activitatiFizice = service.getActivitatiFizice(activitate.getId());
                for (ActivitateFizica af : activitatiFizice) {
                    lista.getItems().add("Descriere text: " + af.getDescriere() + ", Nr. minute miscare: " + af.getMinuteMiscare());
                }
            }
        } else {
            int minuteMinim = Integer.parseInt(textFieldNrMinim.getText());
            int minuteMaxim = Integer.parseInt(textFieldNrMaxim.getText());

            List<Activitate> activitatiFiltrate = service.filtreazaActivitatiDupaMinuteMinMax(minuteMinim, minuteMaxim);

            for (Activitate activitate : activitatiFiltrate) {
                lista.getItems().add("Data: " + activitate.getData() + ", Nr. pasi: " + activitate.getNrPasi() + ", Nr. ore somn: " + activitate.getNrOreSomn() + ", Activitati: ");
                List<ActivitateFizica> activitatiFizice = service.getActivitatiFizice(activitate.getId());
                for (ActivitateFizica af : activitatiFizice) {
                    lista.getItems().add("Descriere text: " + af.getDescriere() + ", Nr. minute miscare: " + af.getMinuteMiscare());
                }
            }
        }
    }

    @FXML
    private void adaugaButtonOnClick() {
        Integer id = Integer.parseInt(textFieldId.getText());
        String data = textFieldData.getText();
        int nrPasi = Integer.parseInt(textFieldPasi.getText());
        int nrOreSomn = Integer.parseInt(textFieldOre.getText());
        String descriere = textFieldDescriere.getText();
        int nrMinuteMiscare = Integer.parseInt(textFieldMinute.getText());

        service.add(id, data, nrPasi, nrOreSomn, descriere, nrMinuteMiscare);

        textFieldData.clear();
        textFieldPasi.clear();
        textFieldOre.clear();
        textFieldDescriere.clear();
        textFieldMinute.clear();
        init_list();
    }
}
