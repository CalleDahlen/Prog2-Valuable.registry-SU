// Carl Dahlén  cada7128

import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class StockDialog extends Alert {
    private TextField nameField = new TextField();
    private TextField quantityField = new TextField();
    private TextField rateField = new TextField();

    public StockDialog(){
        super(AlertType.CONFIRMATION);
        setTitle("Ny aktie");
        GridPane grid = new GridPane();
        grid.addRow(0, new Label("Namn: "), nameField);
        grid.addRow(1,new Label("Antal: "), quantityField);
        grid.addRow(2,new Label("Pris: "), rateField);
        getDialogPane().setContent(grid);
        setHeaderText(null);

    }
    public String getName(){
        return nameField.getText();
    }
    public int getQuantity(){
        return Integer.parseInt(quantityField.getText());
    }
    public double getRate(){
        return Double.parseDouble(rateField.getText());
    }

}
