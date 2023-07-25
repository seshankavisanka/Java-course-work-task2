module com.example.course_work_sd2_task2 {
    requires javafx.controls;
    requires javafx.fxml;
            
                            
    opens com.example.course_work_sd2_task2 to javafx.fxml;
    exports com.example.course_work_sd2_task2;
}