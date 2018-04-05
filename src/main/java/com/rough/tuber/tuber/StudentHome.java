package com.rough.tuber.tuber;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StudentHome extends AppCompatActivity {

    Button bSubCourseSelect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_home);

        final Button bSubCourseSelect = (Button) findViewById(R.id.bSubCourseSelect);
       /* bSubCourseSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent SelectSubCourseIntent = new Intent(StudentHome.this, SelectSubjectCourse.class);
                StudentHome.this.startActivity(SelectSubCourseIntent);

            }
        });*/
    }
}
