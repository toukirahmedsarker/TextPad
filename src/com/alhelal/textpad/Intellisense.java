/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alhelal.textpad;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;
import javafx.stage.Window;

/**
 *
 * @author alhelal
 */
public class Intellisense{
    
    private Popup stageIntelliSense;
    private VBox root;
    private ListView<String> lstIntelliSense;
    private ObservableList<String> keywords = FXCollections.observableArrayList(
            "mov", "jmp", "add", "sub", "adc", "sbb", "div", "idiv", "mul", "imul",
            "loop", "ret", "push", "pop"
            );
    private Boolean showing;
    public Intellisense(String s)
    {
        this();
        selectItem(s);
    }
    
    public Intellisense()
    {
        stageIntelliSense = new Popup();
        root = new VBox();
        showing = false;
        
        lstIntelliSense = new ListView(keywords);
        keywords.sort( (o1, o2) -> o1.compareToIgnoreCase(o2));
    
        
        root.getChildren().add(lstIntelliSense);
        stageIntelliSense.getContent().add(root);
       
    }
    
    public void selectItem(String s)
    {
        try{
            
            lstIntelliSense.setItems(keywords.filtered((String t) -> t.substring(0, s.length()).equals(s)));
            lstIntelliSense.getSelectionModel().select(0);
        }
       
        catch (Exception ex)
        {
           lstIntelliSense.setItems(keywords);
           ex.printStackTrace();
           
        }
    
        
    }
            
    
    public void goDown()
    {
        try{
            lstIntelliSense.getSelectionModel().selectNext();
        } catch(Exception ex)
        {
            
        }
        
    }
    
    public void goUp()
    {
        try{
            lstIntelliSense.getSelectionModel().selectPrevious();
        } catch(Exception ex)
        {
            
        }
        
    }
    
    public String getSelectedItem()
    {
        return lstIntelliSense.getSelectionModel().getSelectedItem();
        
    }
    
    public Popup getStage()
    {
        return stageIntelliSense;
    }
    
    public void show(Window owner)
    {
        stageIntelliSense.show(owner);
        showing = true;
    }
    
    public void hide()
    {
        stageIntelliSense.hide();
        showing = false;
    }
    
    public Boolean exists(String s)
    {
        if(s.length() > 0)
            return keywords.filtered((String t) -> t.substring(0, s.length()).equals(s)).size() >= 1;
        else 
            return false;
        
    }
    
    public Boolean isShowing()
    {
        return showing;
    }
    
   
    
}
