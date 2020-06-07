//Carl Dahlén cada7128

import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class JewelleryDialog extends Alert {
    private TextField nameField = new TextField();
    private TextField numberOfJewelsField = new TextField();
    private CheckBox avGuldBox = new CheckBox();

    public JewelleryDialog(){
        super(AlertType.CONFIRMATION);
        setTitle("Nytt smycke");
        GridPane grid = new GridPane();
        grid.addRow(0, new Label("Namn: "), nameField);
        grid.addRow(1, new Label("Stenar: "), numberOfJewelsField);
        grid.addRow(2, new Label("Av guld", avGuldBox));
        getDialogPane().setContent(grid);
        setHeaderText(null);
    }
    public String getName(){
        return nameField.getText();
    }
    public int getNumberOfJewels(){
        return Integer.parseInt(numberOfJewelsField.getText());
    }
    public boolean isGold(){
       return avGuldBox.isSelected();
    }

}
