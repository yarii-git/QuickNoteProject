module QuickNoteProject {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.base;
	requires javafx.graphics;
	
	requires java.sql;
	requires java.desktop;
	
	opens model to javafx.fxml;
    
	exports application;
    exports model;
    
	opens application to javafx.graphics, javafx.fxml, javafx.media, javafx.base;
}
