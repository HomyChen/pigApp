package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.jnetpcap.Pcap;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.Set;

/**
 * Created by Homy Chen on 2017-04-03.
 */
public class UsageStatController implements Initializable{
    private pcap currentPcap = Controller.getCurrentPcap();
    @FXML
    private TableView<UsageStatRow> tblViewUsageStats;
    @FXML
    private TableColumn<UsageStatRow, String> colIPAddress;
    @FXML
    private TableColumn<UsageStatRow, Long> colInboundUsageBytes;
    @FXML
    private TableColumn<UsageStatRow, Long> colOutboundUsageBytes;
    @FXML
    private TableColumn<UsageStatRow, Double> colInboundUsagePer;
    @FXML
    private TableColumn<UsageStatRow, Double> colOutboundUsagePer;

    public ObservableList<UsageStatRow> enteredUsageStatItems = FXCollections.observableArrayList();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            System.out.println("Test - Total Data: "+currentPcap.getTotalData());
        } catch (ExceptionReadingPcapFiles exceptionReadingPcapFiles) {
            exceptionReadingPcapFiles.printStackTrace();
        }
        try {
            HashMap<String, ArrayList<Long>> rows = currentPcap.getUsageStat();
            Set<String> ipAddresses = rows.keySet();
            long totalData = currentPcap.getTotalData();
            for(String ip : ipAddresses){
                long inbound = rows.get(ip).get(0);
                long outbound = rows.get(ip).get(1);
                double inboundPer = (((double)inbound)/(totalData))*100;
                double outboundPer = (((double)outbound)/(totalData))*100;
                enteredUsageStatItems.add(new UsageStatRow(ip, inbound, outbound, inboundPer, outboundPer));
            }
        } catch (ExceptionReadingPcapFiles exceptionReadingPcapFiles) {
            exceptionReadingPcapFiles.printStackTrace();
        }
        colIPAddress.setCellValueFactory(new PropertyValueFactory<>("ipAddress"));
        colInboundUsageBytes.setCellValueFactory(new PropertyValueFactory<>("inboundRate"));
        colOutboundUsageBytes.setCellValueFactory(new PropertyValueFactory<>("outboundRate"));
        colInboundUsagePer.setCellValueFactory(new PropertyValueFactory<>("inboundRatePer"));
        colOutboundUsagePer.setCellValueFactory(new PropertyValueFactory<>("outboundRatePer"));

        tblViewUsageStats.getColumns().clear();
        tblViewUsageStats.setItems(enteredUsageStatItems);
        tblViewUsageStats.getColumns().addAll(colIPAddress, colInboundUsageBytes, colOutboundUsageBytes, colInboundUsagePer, colOutboundUsagePer);
    }

    @FXML
    public void goBackToTable(ActionEvent e) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Main.window.setScene(new Scene(root, 600, 400));
    }
}
