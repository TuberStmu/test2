


//Change this
package com.rough.tuber.tuber;

        import android.os.Bundle;
        import android.support.v7.app.AppCompatActivity;
        import android.view.View;
        import android.widget.AdapterView;
        import android.widget.ArrayAdapter;
        import android.widget.Spinner;

public class SelectSubjectCourse extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_subject_course);

      //  setSpinner1();

    }

    /*private void setSpinner1() {
        Spinner spinner1 = (Spinner) findViewById(R.id.spinner);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.subjects, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter);
        final String[] subjects = getResources().getStringArray(R.array.gender);
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                loadContentOnSpinner2(subjects[pos]);
            }

            public void onNothingSelected(AdapterView<?> parent) {

            }

        });
    }

    private void loadContentOnSpinner2(String subject) {
        Spinner spinner2 = (Spinner) findViewById(R.id.spinner2);

        int math =R.array.Math;
        int ComputerScience =R.array.Computer_Science;
        int Business =R.array.Business;
        int Biology=R.array.Biology;
        int Chemistry=R.array.Chemistry;
        int SMC=R.array.SMC;
        int English=R.array.English;
        int Physics=R.array.Physics;
        int Psychology=R.array.Psychology;
        int Engineering=R.array.Engineering;
        int EnvironmentalScience=R.array.Environmental_Science;



        ArrayAdapter<CharSequence> adapter=null;


        if (subject.equals("Math"))
        {
            adapter = ArrayAdapter.createFromResource(this, math, android.R.layout.simple_spinner_item);
        }
        else if(subject.equals("CS"))
        {
            adapter = ArrayAdapter.createFromResource(this, ComputerScience, android.R.layout.simple_spinner_item);
        }
        else if(subject.equals("Business"))
        {
            adapter = ArrayAdapter.createFromResource(this, Business, android.R.layout.simple_spinner_item);
        }
        else if(subject.equals("English"))
        {
            adapter = ArrayAdapter.createFromResource(this, English, android.R.layout.simple_spinner_item);
        }
        else if(subject.equals("Biology"))
        {
            adapter = ArrayAdapter.createFromResource(this, Biology, android.R.layout.simple_spinner_item);
        }
        else if(subject.equals("Physics"))
        {
            adapter = ArrayAdapter.createFromResource(this, Physics, android.R.layout.simple_spinner_item);
        }
        else if(subject.equals("Psychology"))
        {
            adapter = ArrayAdapter.createFromResource(this, Psychology, android.R.layout.simple_spinner_item);
        }
        else if(subject.equals("Engineering"))
        {
            adapter = ArrayAdapter.createFromResource(this, Engineering, android.R.layout.simple_spinner_item);
        }
        else if(subject.equals("SMC"))
        {
            adapter = ArrayAdapter.createFromResource(this, SMC, android.R.layout.simple_spinner_item);
        }
        else if(subject.equals("EnvironmentalScience"))
        {
            adapter = ArrayAdapter.createFromResource(this, EnvironmentalScience, android.R.layout.simple_spinner_item);
        }
        else if(subject.equals("Chemistry"))
        {
            adapter = ArrayAdapter.createFromResource(this, EnvironmentalScience, android.R.layout.simple_spinner_item);
        }


        if (adapter!=null) {
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner2.setAdapter(adapter);//adapting the second array spinner into drop list
        }else if (adapter==null ||  subject=="Choose a subject"){
            ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.Empty, android.R.layout.simple_spinner_item);
            spinner2.setAdapter(adapter1);
        }
    }*/
}
