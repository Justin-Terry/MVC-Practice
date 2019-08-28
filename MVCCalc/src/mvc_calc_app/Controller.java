package mvc_calc_app;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

public class Controller {
	
	private String operator = "";
	private long number1 = 0;
	private Model model = new Model();
	private boolean start = true;

	@FXML
	private Text output;
	
	@FXML
	private void processNumpad(ActionEvent event) {
		if(start) {
			output.setText("");
			start = false;
		}
		String value = ((Button) event.getSource()).getText();
		output.setText(output.getText() + value);
	}
	
	@FXML
	private void processOperator(ActionEvent event) {
		String value = ((Button) event.getSource()).getText();
		
		if(!"=".equals(value)) {
			if( !operator.isEmpty())
				return;
			operator = value;
			number1 = Long.parseLong(output.getText());
			output.setText("");
		} else {
			// Do the operations
			
			if(operator.isEmpty()) {
				// Do nothing, no operator
				return;
			}
			
			output.setText(String.valueOf(model.calculate(number1, Long.parseLong(output.getText()), operator)));
			operator = "";
			start = true;
		}
	}
}
