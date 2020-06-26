package it.polito.tdp.food;

import java.net.URL;
import java.util.ResourceBundle;

import it.polito.tdp.food.model.Model;
import it.polito.tdp.food.model.PorzioneAdiacente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {
	
	private Model model;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtCalorie;

    @FXML
    private TextField txtPassi;

    @FXML
    private Button btnAnalisi;

    @FXML
    private Button btnCorrelate;

    @FXML
    private Button btnCammino;

    @FXML
    private ComboBox<String> boxPorzioni;

    @FXML
    private TextArea txtResult;

    @FXML
    void doCammino(ActionEvent event) {

    }

    @FXML
    void doCorrelate(ActionEvent event) {
    	txtResult.clear();
    	if(boxPorzioni.getValue()==null)
    		txtResult.appendText("selezionare una porzione");
    	txtResult.appendText("Porzioni collegate a "+boxPorzioni.getValue()+"\n");
    	for(PorzioneAdiacente pa: model.getVicini(boxPorzioni.getValue())) {
    		txtResult.appendText(pa+"\n");
    	}
    }

    @FXML
    void doCreaGrafo(ActionEvent event) {
    	txtResult.clear();
    	boxPorzioni.getItems().clear();
    	int k=0;
    	try {
    		k= Integer.parseInt(txtCalorie.getText());
    	}catch(NumberFormatException nfe) {
    		txtResult.appendText("Inserire un numero intero positivo");
    		return;
    	}
    	if(k<0)
    		txtResult.appendText("inseire un numero maggiore di 0");
    	model.creaGrafo(k);
    	txtResult.appendText("Numero vertici "+model.getVertex().size()+"\nNemero Archi "+model.getEdge() );
    	boxPorzioni.getItems().addAll(model.getVertex());
    }

    @FXML
    void initialize() {
        assert txtCalorie != null : "fx:id=\"txtCalorie\" was not injected: check your FXML file 'Food.fxml'.";
        assert txtPassi != null : "fx:id=\"txtPassi\" was not injected: check your FXML file 'Food.fxml'.";
        assert btnAnalisi != null : "fx:id=\"btnAnalisi\" was not injected: check your FXML file 'Food.fxml'.";
        assert btnCorrelate != null : "fx:id=\"btnCorrelate\" was not injected: check your FXML file 'Food.fxml'.";
        assert btnCammino != null : "fx:id=\"btnCammino\" was not injected: check your FXML file 'Food.fxml'.";
        assert boxPorzioni != null : "fx:id=\"boxPorzioni\" was not injected: check your FXML file 'Food.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Food.fxml'.";

    }

	public void setModel(Model model) {
		this.model = model;
	}
}
