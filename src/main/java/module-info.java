module com.example.atm_project {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.dlsc.formsfx;
    requires java.sql;
    requires java.desktop;
    requires javafx.media;
    requires org.bytedeco.javacv;
    requires org.bytedeco.opencv;
    requires webcam.capture;
    requires org.bytedeco.ffmpeg;
    opens com.example.atm_project to javafx.fxml;
    exports com.example.atm_project;
}