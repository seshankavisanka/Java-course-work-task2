package com.example.course_work_sd2_task2;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import static com.example.course_work_sd2_task2.Main.*;

public class FoodieFaveQueueController implements Initializable {

    @FXML
    private Label BurgerStock;

    @FXML
    private TextField BurgersReq;

    @FXML
    private TextArea ErrorArea;

    @FXML
    private TextField FirstName;

    @FXML
    private TextField InputQueue;

    @FXML
    private TextField Input;

    @FXML
    private TableColumn<?, Integer> NoColumn;

    @FXML
    private Button SearchButton;

    @FXML
    private TextField SecondName;

    @FXML
    private TableView<Customer> WaitingCustomer;

    @FXML
    private TableColumn<Customer, Integer> burgersReq;

    @FXML
    private TableColumn<Customer, String> firstName;

    @FXML
    private TableColumn<Customer, String> secondName;

    @FXML private TextArea Cashier11Input;
    @FXML private ImageView Cashier11Image;

    @FXML private ImageView Cashier12Image;
    @FXML private TextArea Cashier12Input;

    @FXML private ImageView Cashier21Image;
    @FXML private TextArea Cashier21Input;

    @FXML private ImageView Cashier22Image;
    @FXML private TextArea Cashier22Input;

    @FXML private ImageView Cashier23Image;
    @FXML private TextArea Cashier23Input;

    @FXML private ImageView Cashier31Image;
    @FXML private TextArea Cashier31Input;

    @FXML private ImageView Cashier32Image;
    @FXML private TextArea Cashier32Input;

    @FXML private ImageView Cashier33Image;
    @FXML private TextArea Cashier33Input;

    @FXML private ImageView Cashier34Image;
    @FXML private TextArea Cashier34Input;

    @FXML private ImageView Cashier35Image;
    @FXML private TextArea Cashier35Input;

    @FXML private Label Cashier1Income;
    @FXML private Label Cashier2Income;
    @FXML private Label Cashier3Income;

    @FXML
    void OnClickSearch(ActionEvent event) {
        FirstName.clear();
        SecondName.clear();
        BurgersReq.clear();
        ErrorArea.clear();
        String queueName = InputQueue.getText();
        String firstName = Input.getText();
        switch (queueName) {
            case "1" -> {
                try {
                    for (Customer customer : cashier1.getCashier()) {
                        if (Objects.equals(customer.getFirstName(), firstName)) {
                            setCustomerDetails(customer);
                            setAdditionalDetails("This customer is in Cashier 1 queue");
                            return;
                        } else if (Objects.equals(firstName, "")) {
                            setAdditionalDetails("Please enter the customer first name");
                        } else {
                            setAdditionalDetails("No person found");
                        }
                    }
                } catch (Exception ignored) {}
            }
            case "2" -> {
                try {
                    for (Customer customer: cashier2.getCashier()) {
                        if (Objects.equals(customer.getFirstName(), firstName)) {
                            setCustomerDetails(customer);
                            setAdditionalDetails("This customer is in Cashier 2 queue");
                            return;
                        } else if (Objects.equals(firstName, "")) {
                            setAdditionalDetails("Please enter the customer first name");
                        } else {
                            setAdditionalDetails("No person found");
                        }
                    }
                } catch (Exception ignored) {}
            }
            case "3" -> {
                try {
                    for (Customer customer: cashier3.getCashier()) {
                        if (Objects.equals(customer.getFirstName(), firstName)) {
                            setCustomerDetails(customer);
                            setAdditionalDetails("This customer is in Cashier 3 queue");
                            return;
                        } else if (Objects.equals(firstName, "")) {
                            setAdditionalDetails("Please enter the customer first name");
                        } else {
                            setAdditionalDetails("No person found");
                        }
                    }
                } catch (Exception ignored) {}
            }
            case "waiting" -> {
                try {
                    for (Customer customer: waitingQueue) {
                        if(Objects.equals(customer.getFirstName(), firstName)) {
                            setCustomerDetails(customer);
                            setAdditionalDetails("This customer is in the waiting queue to go to the cashier queue.");
                            return;
                        } else if (Objects.equals(firstName, "")) {
                            setAdditionalDetails("Please enter the customer first name");
                        } else {
                            setAdditionalDetails("No person found");
                        }
                    }
                } catch (Exception ignored) {}
            }
            default -> setAdditionalDetails("Invalid input. Please try again.");
        }
    }

    private void setCustomerDetails (Customer customer) {
        FirstName.setText(customer.getFirstName());
        SecondName.setText(customer.getSecondName());
        BurgersReq.setText(String.valueOf(customer.getBurgersReq()));
    }

    private void setAdditionalDetails (String text) {
        ErrorArea.setText(String.format("Attention: %s", text));
    }

    ObservableList<Customer> initialData () {
        return FXCollections.observableArrayList(waitingQueue);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        firstName.setCellValueFactory(new PropertyValueFactory<Customer, String>("firstName"));
        secondName.setCellValueFactory(new PropertyValueFactory<Customer, String>("secondName"));
        burgersReq.setCellValueFactory(new PropertyValueFactory<Customer, Integer>("burgersReq"));
        WaitingCustomer.setItems(initialData());

        BurgerStock.setText(String.valueOf(burgersCount));

        Cashier1Income.setText(String.format("Income : Rs.%s.00",incomeCashier1));
        Cashier2Income.setText(String.format("Income : Rs.%s.00",incomeCashier2));
        Cashier3Income.setText(String.format("Income : Rs.%s.00", incomeCashier3));

        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("customer.png")));
        Image unoccupied = new Image(Objects.requireNonNull(getClass().getResourceAsStream("occupied.png")));

        Customer customer11 = cashier1.getCashier()[0];
        Cashier11Input.setVisible(false);
        if (customer11 != null) {
            Cashier11Image.setImage(image);
            Cashier11Input.setVisible(true);
            Cashier11Input.setText(String.format("""
                    Name:
                    %s
                    Burgers:
                    %s
                    """, customer11.getFirstName() + " " + customer11.getSecondName(), customer11.getBurgersReq()));
        } else {
            Cashier11Image.setImage(unoccupied);
        }

        Customer customer12 = cashier1.getCashier()[1];
        Cashier12Input.setVisible(false);
        if (customer12 != null) {
            Cashier12Image.setImage(image);
            Cashier12Input.setVisible(true);
            Cashier12Input.setText(String.format("""
                    Name:
                    %s
                    Burgers:
                    %s
                    """, customer12.getFirstName() + " " + customer12.getSecondName(), customer12.getBurgersReq()));
        } else {
            Cashier12Image.setImage(unoccupied);
        }

        Customer customer21 = cashier2.getCashier()[0];
        Cashier21Input.setVisible(false);
        if (customer21 != null) {
            Cashier21Image.setImage(image);
            Cashier21Input.setVisible(true);
            Cashier21Input.setText(String.format("""
                    Name:
                    %s
                    Burgers:
                    %s
                    """, customer21.getFirstName() + " " + customer21.getSecondName(), customer21.getBurgersReq()));
        } else {
            Cashier21Image.setImage(unoccupied);
        }

        Customer customer22 = cashier2.getCashier()[1];
        Cashier22Input.setVisible(false);
        if (customer22 != null) {
            Cashier22Image.setImage(image);
            Cashier22Input.setVisible(true);
            Cashier22Input.setText(String.format("""
                    Name:
                    %s
                    Burgers:
                    %s
                    """, customer22.getFirstName() + " " + customer22.getSecondName(), customer22.getBurgersReq()));
        } else {
            Cashier22Image.setImage(unoccupied);
        }

        Customer customer23 = cashier2.getCashier()[2];
        Cashier23Input.setVisible(false);
        if (customer23 != null) {
            Cashier23Image.setImage(image);
            Cashier23Input.setVisible(true);
            Cashier23Input.setText(String.format("""
                    Name:
                    %s
                    Burgers:
                    %s
                    """, customer23.getFirstName() + " " + customer23.getSecondName(), customer23.getBurgersReq()));
        } else {
            Cashier23Image.setImage(unoccupied);
        }

        Customer customer31 = cashier3.getCashier()[0];
        Cashier31Input.setVisible(false);
        if (customer31 != null) {
            Cashier31Image.setImage(image);
            Cashier31Input.setVisible(true);
            Cashier31Input.setText(String.format("""
                    Name:
                    %s
                    Burgers:
                    %s
                    """, customer31.getFirstName() + " " + customer31.getSecondName(), customer31.getBurgersReq()));
        } else {
            Cashier31Image.setImage(unoccupied);
        }

        Customer customer32 = cashier3.getCashier()[1];
        Cashier32Input.setVisible(false);
        if (customer32 != null) {
            Cashier32Image.setImage(image);
            Cashier32Input.setVisible(true);
            Cashier32Input.setText(String.format("""
                    Name:
                    %s
                    Burgers:
                    %s
                    """, customer32.getFirstName() + " " + customer32.getSecondName(), customer32.getBurgersReq()));
        } else {
            Cashier32Image.setImage(unoccupied);
        }

        Customer customer33 = cashier3.getCashier()[2];
        Cashier33Input.setVisible(false);
        if (customer33 != null) {
            Cashier33Image.setImage(image);
            Cashier33Input.setVisible(true);
            Cashier33Input.setText(String.format("""
                    Name:
                    %s
                    Burgers:
                    %s
                    """, customer33.getFirstName() + " " + customer33.getSecondName(), customer33.getBurgersReq()));
        } else {
            Cashier33Image.setImage(unoccupied);
        }

        Customer customer34 = cashier3.getCashier()[3];
        Cashier34Input.setVisible(false);
        if (customer34 != null) {
            Cashier34Image.setImage(image);
            Cashier34Input.setVisible(true);
            Cashier34Input.setText(String.format("""
                    Name:
                    %s
                    Burgers:
                    %s
                    """, customer34.getFirstName() + " " + customer34.getSecondName(), customer34.getBurgersReq()));
        } else {
            Cashier34Image.setImage(unoccupied);
        }
        Customer customer35 = cashier3.getCashier()[4];
        Cashier35Input.setVisible(false);
        if (customer35 != null) {
            Cashier35Image.setImage(image);
            Cashier35Input.setVisible(true);
            Cashier35Input.setText(String.format("""
                    Name:
                    %s
                    Burgers:
                    %s
                    """, customer35.getFirstName() + " " + customer35.getSecondName(), customer35.getBurgersReq()));
        } else {
            Cashier35Image.setImage(unoccupied);
        }
    }
}
