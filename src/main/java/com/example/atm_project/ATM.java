package com.example.atm_project;

import com.example.atm_project.classes.DB;
import com.example.atm_project.classes.Lists;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

public class ATM extends Application {
    @Override
    public void start(Stage stage) throws IOException, SQLException {
        //make stage global
        Lists.stage = stage;
        setAppIcon();
        loadScenes();
        //load customer data & transactions from DB
        DB.getData();
        DB.getTransactionData();
        showSceneIndex(0,"Please Insert Card");
    }

    public static void main(String[] args) {
        launch();
    }

    public void loadScenes() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ATM.class.getResource("frmLogin.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 400, 500);
        Lists.scenesArrayList.add(scene); //0 main

        FXMLLoader fxmlLoader1 = new FXMLLoader(ATM.class.getResource("frmCustomer.fxml"));
        Scene scene1 = new Scene(fxmlLoader1.load(), 900, 600);
        Lists.scenesArrayList.add(scene1); //1 customer

        FXMLLoader fxmlLoader2 = new FXMLLoader(ATM.class.getResource("frmCustomerRequestTransaction.fxml"));
        Scene scene2 = new Scene(fxmlLoader2.load(), 400, 500);
        Lists.scenesArrayList.add(scene2); //2 transaction

        FXMLLoader fxmlLoader3 = new FXMLLoader(ATM.class.getResource("frmCustomerRequestHistory.fxml"));
        Scene scene3 = new Scene(fxmlLoader3.load(), 900, 900);
        Lists.scenesArrayList.add(scene3); //3 requestHistory

        FXMLLoader fxmlLoader4 = new FXMLLoader(ATM.class.getResource("frmCustomerWithdraw.fxml"));
        Scene scene4 = new Scene(fxmlLoader4.load(), 400, 500);
        Lists.scenesArrayList.add(scene4); //4 withdraw

        FXMLLoader fxmlLoader5 = new FXMLLoader(ATM.class.getResource("frmCustomerRequestPinChange.fxml"));
        Scene scene5 = new Scene(fxmlLoader5.load(), 400, 500);
        Lists.scenesArrayList.add(scene5); //5 pinChange

        FXMLLoader fxmlLoader6 = new FXMLLoader(ATM.class.getResource("frmRequestNewCard.fxml"));
        Scene scene6 = new Scene(fxmlLoader6.load(), 400, 600);
        Lists.scenesArrayList.add(scene6); //6 request new card

        FXMLLoader fxmlLoader7 = new FXMLLoader(ATM.class.getResource("frmCustomerDeposit.fxml"));
        Scene scene7 = new Scene(fxmlLoader7.load(), 400, 500);
        Lists.scenesArrayList.add(scene7); //7 Deposit

        FXMLLoader fxmlLoader8 = new FXMLLoader(ATM.class.getResource("frmAdminPanel.fxml"));
        Scene scene8 = new Scene(fxmlLoader8.load(), 900, 900);
        Lists.scenesArrayList.add(scene8); //8 admin
    }

    public static void showSceneIndex(int index,String title){
        Scene scene = Lists.scenesArrayList.get(index);
        Lists.stage.setScene(scene);
        Lists.stage.setResizable(false);
        Lists.stage.setTitle(title);
        Lists.stage.show();
    }

    public static  void setAppIcon(){
        //set app icon
        try{
            Image icon = new Image("https://i.imgur.com/QPuK73U.png");
            Lists.stage.getIcons().add(icon);
        }catch (Exception e){
            System.out.println("couldn't load app icon");
        }
    }



}