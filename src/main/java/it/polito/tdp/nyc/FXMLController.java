/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.nyc;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.nyc.model.Location;
import it.polito.tdp.nyc.model.Model;
import it.polito.tdp.nyc.model.VerticeVicini;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {

	private Model model;
	
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="btnAnalisi"
    private Button btnAnalisi; // Value injected by FXMLLoader

    @FXML // fx:id="btnCreaGrafo"
    private Button btnCreaGrafo; // Value injected by FXMLLoader

    @FXML // fx:id="btnPercorso"
    private Button btnPercorso; // Value injected by FXMLLoader

    @FXML // fx:id="cmbProvider"
    private ComboBox<String> cmbProvider; // Value injected by FXMLLoader

    @FXML // fx:id="txtDistanza"
    private TextField txtDistanza; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML // fx:id="txtStringa"
    private TextField txtStringa; // Value injected by FXMLLoader
    
    @FXML // fx:id="txtTarget"
    private ComboBox<Location> txtTarget; // Value injected by FXMLLoader

    @FXML
    void doAnalisiGrafo(ActionEvent event) {
    	List<VerticeVicini> l = model.getViciniMax();
    	this.txtResult.appendText("Vertici con più vicini:\n");
    	for(VerticeVicini v : l) {
    		this.txtResult.appendText(v.toString()+"\n");
    	}
    }

    @FXML
    void doCalcolaPercorso(ActionEvent event) {
    	String input = this.txtStringa.getText();
    	Location location = this.txtTarget.getValue();
    	if(input=="") {
    		this.txtResult.setText("Inserire una stringa.");
    		return;
    	}
    	if(location==null) {
    		this.txtResult.setText("Selezionare un target.");
    		return;
    	}
    	List<Location> percorso = model.calcolaPercorso(input, location);
    	this.txtResult.setText("Percorso creato.\n");
    	for(Location l : percorso) {
    		this.txtResult.appendText(l.toString()+"\n");
    	}
    }

    @FXML
    void doCreaGrafo(ActionEvent event) {
    	String provider = this.cmbProvider.getValue();
    	String input = this.txtDistanza.getText();
    	if(provider == null) {
    		this.txtResult.setText("Selezionare un provider.");
    		return;
    	}
    	if(input=="") {
    		this.txtResult.setText("Inserire una distanza.");
    		return;
    	}
    	try {
    		Double d = Double.parseDouble(input);
    		model.creaGrafo(provider,d);
    		this.txtResult.setText("Grafo creato!\n");
    		this.txtResult.appendText("#Vertici: "+model.getVerticiSize()+"\n");
    		this.txtResult.appendText("#Archi: "+model.getArchiSize()+"\n\n");
    		this.txtTarget.getItems().addAll(model.getVertici());
    	}catch(NumberFormatException e) {
    		this.txtResult.setText("La distanza inserita non è valida.");
    		return;
    	}
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert btnAnalisi != null : "fx:id=\"btnAnalisi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCreaGrafo != null : "fx:id=\"btnCreaGrafo\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnPercorso != null : "fx:id=\"btnPercorso\" was not injected: check your FXML file 'Scene.fxml'.";
        assert cmbProvider != null : "fx:id=\"cmbProvider\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtDistanza != null : "fx:id=\"txtDistanza\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtStringa != null : "fx:id=\"txtStringa\" was not injected: check your FXML file 'Scene.fxml'.";

    }

    public void setModel(Model model) {
    	this.model = model;
    	this.cmbProvider.getItems().addAll(model.getProvider());
    }
}
