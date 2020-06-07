//Carl Dahlén cada7128

import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class ApplianceDialog extends Alert {
    private TextField nameField = new TextField();
    private TextField prisField = new TextField();
    private TextField skickField = new TextField();

    public ApplianceDialog(){
        super(AlertType.CONFIRMATION);
        setTitle("Ny apparat");
        GridPane grid = new GridPane();
        grid.addRow(0, new Label("Namn: "), nameField);
        grid.addRow(1, new Label("Pris: "), prisField);
        grid.addRow(2, new Label("Skick: "), skickField);
        getDialogPane().setContent(grid);
        setHeaderText(null);
    }
    public String getName(){
        return nameField.getText();
    }
    public double getPrice(){
        return Double.parseDouble(prisField.getText());
    }
    public int getWear(){
        return Integer.parseInt(skickField.getText());
    }
}
