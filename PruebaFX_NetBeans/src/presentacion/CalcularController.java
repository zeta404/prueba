/*
 * Copyright (c) 2012, 2013 Oracle and/or its affiliates.
 * All rights reserved. Use is subject to license terms.
 *
 * This file is available and licensed under the following license:
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *  - Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 *  - Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in
 *    the documentation and/or other materials provided with the distribution.
 *  - Neither the name of Oracle nor the names of its
 *    contributors may be used to endorse or promote products derived
 *    from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
 * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package presentacion;

import excepciones.DAOExcepcion;
import excepciones.DominioExcepcion;
import java.awt.Dialog;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import jfx.messagebox.MessageBox;
import logica.Acabado;
import logica.Aditivo;
import logica.Base;
import logica.Pigmento;
import logica.Controlador;
import logica.Pigmento;
import logica.Producto;
import static presentacion.ListadoProductosController.cargar;


public class CalcularController implements Initializable, ControlledScreen{
   ScreensController myController;
    @FXML private static TableView<Aditivo> tableView;
    private String cantidad;
    Producto producto;
    Acabado acabado;
    Pigmento pigmento;
    @FXML private TextField txtCantidad;
    @FXML private ComboBox comboProducto,comboAcabado,comboPigmento;
    @FXML
    public void calcular(ActionEvent event){
    pigmento=(Pigmento)comboPigmento.getSelectionModel().getSelectedItem();
    producto=(Producto)comboProducto.getSelectionModel().getSelectedItem();
    acabado=(Acabado)comboAcabado.getSelectionModel().getSelectedItem();
    cantidad=txtCantidad.getText();
    
    }
   

   
    
    @Override
    public void initialize (URL location,ResourceBundle resources){
       try {
           cargarPigmentos();
       } catch (DAOExcepcion ex) {
           Logger.getLogger(CalcularController.class.getName()).log(Level.SEVERE, null, ex);
       }
       try {
           cargarAcabados();
       } catch (DAOExcepcion ex) {
           Logger.getLogger(CalcularController.class.getName()).log(Level.SEVERE, null, ex);
       }
       try {
           cargarProductos();
       } catch (DAOExcepcion ex) {
           Logger.getLogger(CalcularController.class.getName()).log(Level.SEVERE, null, ex);
       }
        }
    
    
    public void cargarPigmentos() throws DAOExcepcion{
        
                    Controlador controlador = null;

  ArrayList<Pigmento> pigmentos=new ArrayList<Pigmento>();

        try {
            controlador = Controlador.dameControlador();
        } catch (DAOExcepcion ex) {
            Logger.getLogger(ListadoBasesController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DominioExcepcion ex) {
            Logger.getLogger(ListadoBasesController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            pigmentos=controlador.getPigmentos();
        } catch (DAOExcepcion ex) {
            Logger.getLogger(ListadoBasesController.class.getName()).log(Level.SEVERE, null, ex);
        }
          ObservableList<Pigmento> pigmento = FXCollections.observableList(pigmentos);
          comboPigmento.setItems(pigmento);
    }
     public void cargarAcabados() throws DAOExcepcion{
        
                    Controlador controlador = null;

  ArrayList<Acabado> acabados=new ArrayList<Acabado>();

        try {
            controlador = Controlador.dameControlador();
        } catch (DAOExcepcion ex) {
            Logger.getLogger(ListadoBasesController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DominioExcepcion ex) {
            Logger.getLogger(ListadoBasesController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            acabados=controlador.getAcabados();
        } catch (DAOExcepcion ex) {
            Logger.getLogger(ListadoBasesController.class.getName()).log(Level.SEVERE, null, ex);
        }
          ObservableList<Acabado> acabado = FXCollections.observableList(acabados);
          comboAcabado.setItems(acabado);
    }
      public void cargarProductos() throws DAOExcepcion{
        
                    Controlador controlador = null;

  ArrayList<Producto> productos=new ArrayList<Producto>();

        try {
            controlador = Controlador.dameControlador();
        } catch (DAOExcepcion ex) {
            Logger.getLogger(ListadoBasesController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DominioExcepcion ex) {
            Logger.getLogger(ListadoBasesController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            productos=controlador.getProductos();
        } catch (DAOExcepcion ex) {
            Logger.getLogger(ListadoBasesController.class.getName()).log(Level.SEVERE, null, ex);
        }
          ObservableList<Producto> producto = FXCollections.observableList(productos);
          comboProducto.setItems(producto);
    }
    

    @Override
    public void setScreenParent(ScreensController screenPage) {
        myController=screenPage;
    }
  
}