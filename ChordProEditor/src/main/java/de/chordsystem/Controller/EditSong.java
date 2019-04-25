package de.chordsystem.Controller;

import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.control.MenuButton;

public class EditSong {

    @FXML
    private JFXTextArea EnterSong;

    @FXML
    private JFXTextField txtSongTitle;

    @FXML
    private JFXTextField txtAlternativeTitle;

    @FXML
    private JFXTextField txtArtist;

    @FXML
    private MenuButton btnAusgabeart;

    @FXML
    private MenuButton btnFormat;

    @FXML
    private MenuButton btnAkkorde;

    @FXML
    private JFXTextField txtCapo;

    @FXML
    private JFXTextArea ChangedFormat;

}
