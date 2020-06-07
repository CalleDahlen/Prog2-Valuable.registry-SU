//Carl Dahlén cada7128

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.Optional;

public class ValuableRegister extends Application {
    private static final int COMPARE_CONSTANT_LARGE = 1;
    private static final int COMPARE_CONSTANT_SMALL = -1;
    private static final int COMPARE_CONSTANT_EQUAL = 0;
    private static final int STAGE_HORIZONTAL_BOUNDARY = 800;
    private static final int STAGE_VERTICAL_BOUNDARY = 400;
    private ArrayList<Valuable> allValuables = new ArrayList<>();
    private TextArea display = new TextArea();
    private  Label heading = new Label("Värdesaker");
    private BorderPane root = new BorderPane();
    private FlowPane top = new FlowPane();
    private   VBox right = new VBox();
    private Label sorteringsLabel = new Label("Sortering");
    private RadioButton namnButton = new RadioButton("Namn");
    private RadioButton valueButton = new RadioButton("Värde");
    private ToggleGroup group = new ToggleGroup();
    private Label nyLabel = new Label("Ny: ");
    private  MenuButton menuButton = new MenuButton("Välj");
    private  MenuItem itemAktie = new MenuItem("Aktie");
    private  MenuItem itemApparat = new MenuItem("Apparat");
    private  MenuItem itemSmycke = new MenuItem("Smycke");
    private  Button visaButton = new Button("Visa");
    private Button stockCrashButton = new Button("Börskrasch");
    private FlowPane bottom = new FlowPane();
    private Scene scene = new Scene(root, STAGE_HORIZONTAL_BOUNDARY, STAGE_VERTICAL_BOUNDARY);

    @Override
    public void start(Stage primarystage) {
        primarystage.setTitle("Valuable Register");
        root.setTop(top);
        top.getChildren().add(heading);
        top.setAlignment(Pos.CENTER);
        top.setPadding(new Insets(10));

        root.setRight(right);
        right.setPadding(new Insets(10));
        right.setSpacing(5);
        right.getChildren().add(sorteringsLabel);
        namnButton.setOnAction(new SortByName());
        valueButton.setOnAction(new SortByValue());
        right.getChildren().addAll(namnButton, valueButton);
        group.getToggles().addAll(namnButton, valueButton);
        namnButton.setSelected(true);

        root.setCenter(display);
        display.setEditable(false);

        root.getChildren().add(menuButton);
        itemAktie.setOnAction(new AktieHandler());
        itemApparat.setOnAction(new ApparatHandler());
        itemSmycke.setOnAction(new SmyckeHandler());
        menuButton.getItems().addAll(itemAktie, itemApparat, itemSmycke);

        visaButton.setOnAction(new VisaHandler());
        stockCrashButton.setOnAction(new StockCrashHanlder());
        root.setBottom(bottom);
        bottom.getChildren().addAll(nyLabel, menuButton, visaButton, stockCrashButton);
        bottom.setAlignment(Pos.CENTER);
        bottom.setPadding(new Insets(5));
        bottom.setHgap(5);

        primarystage.setScene(scene);
        primarystage.show();
    }


    class VisaHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            display.clear();
            for (Valuable valuable : allValuables)
                display.appendText(valuable.toString() + "\n");
        }
    }

    class StockCrashHanlder implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            for (Valuable valuable : allValuables) {
                if (valuable instanceof Stock) {
                    ((Stock) valuable).setRate(0);
                }
            }
        }
    }

    class SmyckeHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            try {
                JewelleryDialog dialog = new JewelleryDialog();
                Optional<ButtonType> answer = dialog.showAndWait();
                if (answer.isPresent() && answer.get() == ButtonType.OK) {
                    String name = dialog.getName();
                    if (name.isEmpty()) {
                        Alert alert = new Alert(Alert.AlertType.ERROR, "Felaktig inmatning");
                        alert.showAndWait();
                        return;
                    }
                    int numberOfJewles = dialog.getNumberOfJewels();
                    String material = (dialog.isGold()) ? "Guld" : "Silver";
                    Jewellery newValuable = new Jewellery(name, numberOfJewles, material);
                    allValuables.add(newValuable);
                }
            } catch (NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Felaktig inmatning");
                alert.showAndWait();
            }
        }
    }

    class ApparatHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            try {
                ApplianceDialog dialog = new ApplianceDialog();
                Optional<ButtonType> answer = dialog.showAndWait();
                if (answer.isPresent() && answer.get() == ButtonType.OK) {
                    String name = dialog.getName();
                    if (name.isEmpty()) {
                        Alert alert = new Alert(Alert.AlertType.ERROR, "Felaktig inmatning");
                        alert.showAndWait();
                        return;
                    }
                    int wear = dialog.getWear();
                    if (wear < 1 || wear > 10) {
                        Alert alert = new Alert(Alert.AlertType.ERROR, "Skicket anges mellan 1 -10");
                        alert.showAndWait();
                        return;
                    }
                    double price = dialog.getPrice();
                    Appliance newAppliance = new Appliance(name, price, wear);
                    allValuables.add(newAppliance);
                }
            } catch (NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Felaktig inmatning");
                alert.showAndWait();
            }
        }
    }

    class AktieHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            try {
                StockDialog dialog = new StockDialog();
                Optional<ButtonType> answer = dialog.showAndWait();
                if (answer.isPresent() && answer.get() == ButtonType.OK) {
                    String name = dialog.getName();
                    if (name.isEmpty()) {
                        Alert alert = new Alert(Alert.AlertType.ERROR, "Felaktig inmatning");
                        alert.showAndWait();
                        return;
                    }
                    int quantity = dialog.getQuantity();
                    double rate = dialog.getRate();
                    Stock newStock = new Stock(name, quantity, rate);
                    allValuables.add(newStock);
                }

            } catch (NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Felaktig inmatning");
                alert.showAndWait();
            }

        }
    }

    class SortByValue implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            allValuables.sort((Valuable valuable1, Valuable valuable2) -> {
                if (valuable1.getValue() > valuable2.getValue()) {
                    return COMPARE_CONSTANT_SMALL;
                } else if (valuable1.getValue() < valuable2.getValue()) {
                    return COMPARE_CONSTANT_LARGE;
                } else
                    return COMPARE_CONSTANT_EQUAL;
            });
        }
    }

    class SortByName implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            allValuables.sort((Valuable valuable1, Valuable valuable2) -> {
                int compareName = valuable1.getName().compareTo(valuable2.getName());
                if (compareName > COMPARE_CONSTANT_EQUAL)
                    return COMPARE_CONSTANT_LARGE;
                if (compareName < COMPARE_CONSTANT_EQUAL)
                    return COMPARE_CONSTANT_SMALL;
                else
                    return COMPARE_CONSTANT_EQUAL;
            });
        }

    }
}





