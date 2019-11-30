package Controller.List;

import Controller.List.ProprietarioList;
import javafx.scene.control.ListCell;

public class ListViewCell extends ListCell<String>
{
    @Override
    public void updateItem(String string, boolean empty)
    {
        super.updateItem(string,empty);
        if(string != null) {
            ProprietarioList data = new ProprietarioList();
            data.setInfo(string);
            setGraphic(data.getBox());
        }
    }
}
