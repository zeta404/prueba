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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import jfx.messagebox.MessageBox;
import logica.Acabado;
import logica.Aditivo;
import logica.Controlador;
import logica.Aditivo;


public class ListadoAditivosController implements Initializable, ControlledScreen{
    ScreensController myController;
    @FXML private static TableView<Aditivo> tableView;
    private String nombre;
    @FXML
    private TextField txtNombre;
    @FXML
    private Label lblErrorNombre;
      @FXML MenuItem listadoAcabado;
            @FXML MenuItem listadoPigmento;
                    @FXML MenuItem listadoAditivo;
                            @FXML MenuItem listadoBase;
                            @FXML MenuItem listadoProducto;
    @FXML MenuItem aditivoABase;
    @FXML MenuItem baseAPigmento;
    @FXML Menu calcular;
    @FXML Menu inicio;
    @FXML TextField txtNombreEliminar;
    @FXML Button botonEliminar;
    Stage stage;
    @FXML
    public void eliminarAditivo(ActionEvent event) throws DAOExcepcion, DominioExcepcion{
        
        Stage stage=new Stage();
        Controlador controlador=null;
        Aditivo aditivo= tableView.getSelectionModel().getSelectedItem();
        int answer= MessageBox.show(stage,
        "Estas seguro de que quieres eliminar este aditivo?",
        "Information dialog",
        MessageBox.ICON_INFORMATION | MessageBox.OK | MessageBox.CANCEL);
        System.out.println(answer);
        if(answer==65536){
            controlador=controlador.dameControlador();
            controlador.eliminarAditivo(aditivo);
            txtNombreEliminar.setText("");
            botonEliminar.setDisable(true);
            cargar();
        }
    }
    public void anyadirAditivo(ActionEvent event) throws IOException, DAOExcepcion, DominioExcepcion {
        Stage stage=new Stage();
        boolean error=false;
         Controlador controlador= Controlador.dameControlador();
       nombre=txtNombre.getText();
            if(!nombre.equals("")){
             Aditivo aditivo=controlador.getAditivo(nombre);
            if(aditivo!=null){
            lblErrorNombre.setText("Ese aditivo ya existe");
            txtNombre.setText("");
            error=true;
            }else{
              lblErrorNombre.setText("");  
            }
                
            }else{
                lblErrorNombre.setText("Debes rellenar el nombre");
                error=true;
            }
            if(!error){
           Aditivo aditivo=new Aditivo(nombre);
                controlador.insertarAditivo(aditivo);  
                lblErrorNombre.setText("");
                  txtNombre.setText("");     
                  int answer = MessageBox.show(stage,
						"El aditivo se ha creado correctamente",
						"Information dialog", 
						MessageBox.ICON_INFORMATION | MessageBox.OK);
                cargar();
            }
    }
    
    @Override
    public void initialize (URL location,ResourceBundle resources){
        
         botonEliminar.setDisable(true);
      tableView.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent t) {
                Aditivo aditivo= tableView.getSelectionModel().getSelectedItem();
                if(aditivo!=null){
                    txtNombreEliminar.setText(aditivo.getNombre());
                    botonEliminar.setDisable(false);
                }
            }
        });
        
         stage = ObjetoCompartido.dameLo().getStage();
          
       listadoAcabado.setOnAction(new EventHandler<ActionEvent>() {

           @Override
           public void handle(ActionEvent t) {
               Parent root=null;
               try {
                   root = FXMLLoader.load(getClass().getResource("ListadoAcabados.fxml"));
               } catch (IOException ex) {
                   Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
               }
  
        stage.setTitle("Acabados");
        stage.setScene(new Scene(root));
        stage.show();
           }
       });
       
       listadoAditivo.setDisable(true);
       listadoPigmento.setOnAction(new EventHandler<ActionEvent>() {

           @Override
           public void handle(ActionEvent t) {
               Parent root=null;
               try {
                   root = FXMLLoader.load(getClass().getResource("ListadoPigmentos.fxml"));
               } catch (IOException ex) {
                   Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
               }
 
        stage.setTitle("Pigmentos");
        stage.setScene(new Scene(root));
        stage.show();
           }
       });
       listadoProducto.setOnAction(new EventHandler<ActionEvent>() {

           @Override
           public void handle(ActionEvent t) {
               Parent root=null;
               try {
                   root = FXMLLoader.load(getClass().getResource("ListadoProductos.fxml"));
               } catch (IOException ex) {
                   Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
               }

        stage.setTitle("Productos");
        stage.setScene(new Scene(root));
        stage.show();
           }
       });
       listadoBase.setOnAction(new EventHandler<ActionEvent>() {

           @Override
           public void handle(ActionEvent t) {
               Parent root=null;
               try {
                   root = FXMLLoader.load(getClass().getResource("ListadoBases.fxml"));
               } catch (IOException ex) {
                   Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
               }
 
        stage.setTitle("Bases");
        stage.setScene(new Scene(root));
        stage.show();
           }
       });
       baseAPigmento.setOnAction(new EventHandler<ActionEvent>() {

           @Override
           public void handle(ActionEvent t) {
               Parent root=null;
               try {
                   root = FXMLLoader.load(getClass().getResource("ListadoPigmento_ComboBox.fxml"));
               } catch (IOException ex) {
                   Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
               }
 
        stage.setTitle("Pigmento a Base");
        stage.setScene(new Scene(root));
        stage.show();
           }
       });
      aditivoABase.setOnAction(new EventHandler<ActionEvent>() {

           @Override
           public void handle(ActionEvent t) {
               Parent root=null;
               try {
                   root = FXMLLoader.load(getClass().getResource("ListadoBases_ComboBox.fxml"));
               } catch (IOException ex) {
                   Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
               }

        stage.setTitle("Aditivo a Base");
        stage.setScene(new Scene(root));
        stage.show();
           }
       });
      
      inicio.setOnShown(new EventHandler<Event>() {

          @Override
           public void handle(Event t) {
               Parent root=null;
               try {
                   root = FXMLLoader.load(getClass().getResource("Principal.fxml"));
               } catch (IOException ex) {
                   Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
               }
    
        stage.setTitle("Inicio");
        stage.setScene(new Scene(root));
        stage.show();
        inicio.hide();
           }
       });
 
      calcular.setOnShown(new EventHandler<Event>() {

          @Override
           public void handle(Event t) {

               Parent root=null;
               try {
                   root = FXMLLoader.load(getClass().getResource("Calcular.fxml"));
               } catch (IOException ex) {
                   Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
               }

        stage.setTitle("Calcular");
        stage.setScene(new Scene(root));
        stage.show();
        calcular.hide();
           }
       });
      
        tableView.setEditable(true);
        tableView.setMaxWidth(520);
        TableColumn Nombre=new TableColumn("Nombre");
        Nombre.setMinWidth(520);
      Nombre.setCellValueFactory(new PropertyValueFactory<Aditivo,String>("Nombre"));
      tableView.getColumns().addAll(Nombre);
      
      cargar();
   // Nombre.setCellFactory(TextFieldTableCell.forTableColumn());
   /* Nombre.setOnEditCommit(
    new EventHandler<CellEditEvent<Aditivo, String>>() {
        @Override
        public void handle(CellEditEvent<Aditivo, String> t) {
               Controlador controlador = null;
               Aditivo aditivo=null;
               Stage stage=new Stage();
        try {
            controlador=controlador.dameControlador();
        } catch (DAOExcepcion ex) {
            Logger.getLogger(ListadoAditivosController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DominioExcepcion ex) {
            Logger.getLogger(ListadoAditivosController.class.getName()).log(Level.SEVERE, null, ex);
        }
            try {
                aditivo=controlador.getAditivo(t.getNewValue());
            } catch (DAOExcepcion ex) {
                Logger.getLogger(ListadoAditivosController.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                this.finalize();
            } catch (Throwable ex) {
                Logger.getLogger(ListadoAditivosController.class.getName()).log(Level.SEVERE, null, ex);
            }
             if(t.getNewValue().trim().equals("")){
                int answer = MessageBox.show(stage,
						"El nombre del aditivo no  puede estar vacio",
						"Information dialog", 
						MessageBox.ICON_INFORMATION | MessageBox.OK);
                cargar();
            }
            if(aditivo==null && !t.getNewValue().trim().equals("")){
               ((Aditivo) t.getTableView().getItems().get(
                t.getTablePosition().getRow())
                ).setNombre(t.getNewValue());
               Aditivo aditivoA=  ((Aditivo) t.getTableView().getItems().get(
                t.getTablePosition().getRow()));
               aditivoA.setNombre(t.getNewValue().trim());
                   try {
                       controlador.modificarAditivo(aditivoA);
                   } catch (DAOExcepcion ex) {
                       Logger.getLogger(ListadoAditivosController.class.getName()).log(Level.SEVERE, null, ex);
                   }
               cargar();
            }
           
            if(aditivo!= null && !t.getNewValue().trim().equals("")){
                int answer = MessageBox.show(stage,
						"Ese aditivo ya existe, introduzca un nombre distinto",
						"Information dialog", 
						MessageBox.ICON_INFORMATION | MessageBox.OK);
                cargar();
    
            }
      
        }
    }
);*/
    
        }
    
    
    public static void cargar(){
        
                    Controlador controlador = null;

  ArrayList<Aditivo> aditivos=new ArrayList<Aditivo>();

        try {
            controlador = Controlador.dameControlador();
        } catch (DAOExcepcion ex) {
            Logger.getLogger(ListadoAditivosController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DominioExcepcion ex) {
            Logger.getLogger(ListadoAditivosController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            aditivos=controlador.getAditivos();
        } catch (DAOExcepcion ex) {
            Logger.getLogger(ListadoAditivosController.class.getName()).log(Level.SEVERE, null, ex);
        }
                  
       ObservableList<Aditivo> aditivo = FXCollections.observableList(aditivos);  
       tableView.setItems(aditivo);
    }
    

    @Override
    public void setScreenParent(ScreensController screenPage) {
        myController=screenPage;
    }
    
}