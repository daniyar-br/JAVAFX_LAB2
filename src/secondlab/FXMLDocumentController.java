package secondlab;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;

public class FXMLDocumentController implements Initializable {

    @FXML
    private Label label1;
    @FXML
    private Label label2;
    @FXML
    private TableView<String[]> table;
    @FXML
    private Label label3;
    @FXML
    private Label label4;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Utils.buildTable(table, 4, 5, 24, 90, true, null);
//        Utils.buildTable(table, 8, 6, 24, 90, true, new String[] {"1","2","3","4","5","6"}); // Вариант с шапкой
        doRandom(null);
        //table.getItems().add(new String[6]); // Так можно добавить строку
        //table.getItems().remove(table.getItems().size()-1); // Так можно удалить строку
        //table.refresh(); // Обновить таблицу на экране
        label1.setText("Подсчитать количество отрицательных элементов в таблице и\n"
                + "увеличить на это значение минимальный и максимальный\n" + "элементы таблицы");
    }

    @FXML
    private void doRandom(ActionEvent event) {
        // Заполнение таблицы случайными числами
        for (int row = 0; row < table.getItems().size(); row++) {
            for (int col = 0; col < table.getColumns().size(); col++) {
                int rand = (int) Math.round(-5 + Math.random() * 9);
                table.getItems().get(row)[col] = String.valueOf(rand);
            }
        }
        table.refresh();
    }

    @FXML
    private void doSol(ActionEvent event) {
        double max, min, x;
        int max_row = 0, max_col = 0;
        int min_row = 0, min_col = 0;
        min = Double.parseDouble(table.getItems().get(max_row)[max_col]);
        max = min;
        for (int row = 0; row < table.getItems().size(); row++) {
            for (int col = 0; col < table.getColumns().size(); col++) {
                x = Double.parseDouble(table.getItems().get(row)[col]);
                if (x < min) {
                    min = x;
                    min_row = row;
                    min_col = col;
                }
                if (x > max) {
                    max = x;
                    max_row = row;
                    max_col = col;
                };
            }
        }
        label2.setText("Макс. элемент = " + String.valueOf(max));
        label3.setText("Мин. элемент = " + String.valueOf(min));

        int count = 0;
        for (int row = 0; row < table.getItems().size(); row++) {
            for (int col = 0; col < table.getColumns().size(); col++) {
                if (Double.parseDouble(table.getItems().get(row)[col]) < 0) {
                    count += 1;
                }
            }
        }
        label4.setText("Кол-во отрицательных элементов: " + String.valueOf(count));
        
        double new_min = Double.parseDouble(table.getItems().get(min_row)[min_col]);
        new_min = new_min + count;
        table.getItems().get(min_row)[min_col] = String.valueOf(new_min);
        
        double new_max = Double.parseDouble(table.getItems().get(max_row)[max_col]);
        new_max = new_max + count;
        table.getItems().get(max_row)[max_col] = String.valueOf(new_max);
          
        table.refresh();
    }
}
