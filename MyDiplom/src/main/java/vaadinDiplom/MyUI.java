package vaadinDiplom;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.*;
import com.vaadin.ui.components.grid.MultiSelectionModel;
import com.vaadin.ui.renderers.ButtonRenderer;
import com.vaadin.ui.renderers.ImageRenderer;
import datasource.DataSource;
import objectmodel.RwField;
import reader.RecordReaderException;
import showWork.ShowWork;
import writer.RecordWriterException;
import writer.statementbuild.DatabaseException;
import writer.statementbuild.StatementBuilder;
import writer.statementbuild.StatementBuilderImpl;

import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of an HTML page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("mytheme")
public class MyUI extends UI {
    final VerticalLayout layout = new VerticalLayout();
    private Grid<ArrayList<RwField>> gridResult;
    private Panel panel;
    private FormLayout content;

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Where is your PostgreSQL JDBC Driver? " + "Include in your library path!");
            e.printStackTrace();
            return;
        }


        panel = new Panel("Security ETL");
      //  panel.setPrimaryStyleName("customStyle");
        panel.setSizeFull();
        layout.addComponent(panel);


        gridResult = new Grid<>();
        gridResult.setStyleName("v-grid-test");
        MultiSelectionModel<ArrayList<RwField>> selectionModel
                = (MultiSelectionModel<ArrayList<RwField>>) gridResult.setSelectionMode(Grid.SelectionMode.MULTI);

        selectionModel.addMultiSelectionListener(event -> {
            Set<ArrayList<RwField>> selected = event.getAllSelectedItems();
            Notification.show(selected.size() + " items selected");
        });

        gridResult.addItemClickListener(event ->
                Notification.show("Value: " + event.getItem()));

        gridResult.addContextClickListener(event -> Notification.show(
                ((Grid.GridContextClickEvent<ArrayList<RwField>>)event).getItem() + " Clicked")
        );

        gridResult.setVisible(false);

        content = new FormLayout();
        content.addStyleName("mypanelcontent");
        content.setSizeUndefined();
        content.setMargin(true);
        content.addComponent(addShowButton());
        content.addComponent(addResultButton());
        content.addComponent(gridResult);
//
//        //layout.addComponents(addShowButton(), addResultButton(), gridResult);

        panel.setContent(content);
        setContent(layout);
    }

    private Button addResultButton(){
        Button resultButton = new Button("Show Result");
        resultButton.addClickListener(e -> {
            try {
                prepareTable();
            } catch (RecordWriterException e1) {
                e1.printStackTrace();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        });
        resultButton.setEnabled(false);
        return resultButton;
    }

    private Button addShowButton(){
        Button buttonShowWork  = new Button("Show work");
        buttonShowWork.addClickListener(e -> {
            try {
                ShowWork.showWork();
                content.getComponent(1).setEnabled(true);
                e.getButton().setEnabled(false);
            } catch (RecordReaderException er) {
                er.printStackTrace();
            } catch (IOException er) {
                er.printStackTrace();
            } catch (DatabaseException er) {
                er.printStackTrace();
            }
        });

        return buttonShowWork;
    }


    private void prepareTable() throws RecordWriterException, SQLException {
        StatementBuilder statementBuilder = new StatementBuilderImpl();
        String select = statementBuilder.buildSelectStatement(ShowWork.getRwView());
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = DataSource.getConnection();
            statement = connection.createStatement();
            resultSet =  statement.executeQuery(select);
            if(resultSet.next() == false){
                gridResult.setVisible(false);
                System.out.println("empty");
                return;
            }
            ArrayList<ArrayList<RwField>> result = new ArrayList<>();

            final List<RwField> rwFields = new ArrayList<>( ShowWork.getRwRecord().getRwFields());

           // Grid.Column<ArrayList<RwField>, ThemeResource> imageColumn =
             gridResult.addColumn(p -> new ThemeResource("1.jpg"), new ImageRenderer());

            gridResult.addComponentColumn(rawInfo -> {
                Button button = new Button("Get info!");
                button.addClickListener(click ->
                        Notification.show("Security Info: " + rawInfo.toString()));
                return button;
            });

            Stream.iterate(0, i->i+1).limit(rwFields.size()).forEach(i->{
                gridResult.addColumn(rwFields1 -> rwFields1.get(i).getFieldValue()).setCaption(rwFields.get(i).getFieldName());
            });

            gridResult.addColumn(person -> "Delete",
                    new ButtonRenderer(clickEvent -> {
                        result.remove(clickEvent.getItem());
                        gridResult.setItems(result);
                    }));

            gridResult.setBodyRowHeight(40);
            //gridResult.sort(nameColumn, SortDirection.DESCENDING);


//            FooterRow footer = gridResult.prependFooterRow();
//            HeaderCell nameCell = footer.join(
//                    footer.getCell("s"),
//                    footer.getCell("s")).setText("<b>Total:<b>");

//            footer.getCell("month").setHtml("<b>Total:</b>");
//            provider.addDataProviderListener(event -> footer.getCell("value").setHtml(calculateTotal(provider)));


            int columns = resultSet.getMetaData().getColumnCount();
            while(resultSet.next()){
                ArrayList<RwField> rwFieldCopy = new ArrayList<>( rwFields.size());
                try {
                    for(RwField rwField : rwFields){
                        rwFieldCopy.add( rwField.clone() );
                    }
                } catch (CloneNotSupportedException e) {
                    System.out.println("Cloning is not supported by ArrayList element");
                    e.printStackTrace();
                }
                for (int i = 1; i <= columns; i++){
                    rwFieldCopy.get(i-1).setFieldValue(resultSet.getString(i));
                }
                result.add(rwFieldCopy);

            }

              gridResult.setItems(result);

//            gridResult.getColumns().forEach(col-> {
//                  String name = col.getCaption();
//                  if (name !=null || !"".equals(name)){
//                      gridResult.sort(name, SortDirection.DESCENDING);
//                  }
//               });

              gridResult.setVisible(true);

        }finally {
            if(resultSet != null)
                resultSet.close();
            if(statement != null)
                statement.close();
            if(connection != null)
                connection.close();
        }
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
