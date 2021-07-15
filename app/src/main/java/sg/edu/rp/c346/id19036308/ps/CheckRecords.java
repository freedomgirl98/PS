package sg.edu.rp.c346.id19036308.ps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

public class CheckRecords extends AppCompatActivity {

    Button btnRefresh;
    ListView lvRecords;
    TextView tvRecords;
    ArrayList<String> alRecords;
    ArrayAdapter<String> aaRecord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_records);
        btnRefresh = findViewById(R.id.btnRefresh);
        lvRecords = findViewById(R.id.lvRecords);
        tvRecords = findViewById(R.id.tvRecords);
        alRecords = new ArrayList<>();
        aaRecord = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, alRecords);
        lvRecords.setAdapter(aaRecord);

        // Reading internal Storage
        String folderLocation_I = getFilesDir().getAbsolutePath() + "/Folder";
        File targetFile_I = new File(folderLocation_I,"data.txt");
        if (targetFile_I.exists() == true){
            String data = "";
            try{
                FileReader reader = new FileReader(targetFile_I);
                BufferedReader br = new BufferedReader(reader);
                String line = br.readLine();
                int numOfRecord = 0;
                while (line != null){
//                    tvRecords.setText(line);
                    data += line + "\n";
                    line = br.readLine();
                    alRecords.add(line + "\n");
                    aaRecord.notifyDataSetChanged();
//                    tvRecords.setText("Number of records: "+alRecords.size());
                    numOfRecord ++;


//                    tv.setText("Data: " + data );
                }
//                tvRecords.setText(alRecords.size());
                tvRecords.setText("Number of records: "+alRecords.size());

                br.close();
                reader.close();
            } catch (Exception e){
                Toast.makeText(CheckRecords.this, "Failed to read!", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
            Log.d("Content", data);
        }

        btnRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alRecords.clear();
                // Reading internal Storage
                String folderLocation_I = getFilesDir().getAbsolutePath() + "/Folder";
                File targetFile_I = new File(folderLocation_I,"data.txt");
                if (targetFile_I.exists() == true){
                    String data = "";
                    try{
                        FileReader reader = new FileReader(targetFile_I);
                        BufferedReader br = new BufferedReader(reader);
                        String line = br.readLine();
                        while (line != null){
                            data += line + "\n";
                            line = br.readLine();
                            alRecords.add(line + "\n");
                            aaRecord.notifyDataSetChanged();

                        }
                        tvRecords.setText("Number of records: "+alRecords.size());
                        br.close();
                        reader.close();
                    } catch (Exception e){
                        Toast.makeText(CheckRecords.this, "Failed to read!", Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    }
                    Log.d("Content", data);
                }
            }
        });
    }


}