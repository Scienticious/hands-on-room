package com.app.handsonroom;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.app.handsonroom.Model.Word;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText editText;
    private Button button;

    private RecyclerView recyclerViewName;

    private WordViewModel mWordViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        editText = findViewById(R.id.editTextName);
        button = findViewById(R.id.buttonName);
        recyclerViewName = findViewById(R.id.recyclerViewName);


        final WordListAdapter adapter = new WordListAdapter(this);
        recyclerViewName.setAdapter(adapter);
        recyclerViewName.setLayoutManager(new LinearLayoutManager(this));

        // Get a new or existing ViewModel from the ViewModelProvider.
        mWordViewModel = ViewModelProviders.of(this).get(WordViewModel.class);


        mWordViewModel.getAllWords().observe(this, new Observer<List<Word>>() {
            @Override
            public void onChanged(@Nullable List<Word> words) {
                adapter.setWords(words);
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Word word = new Word(editText.getText().toString());
                mWordViewModel.insert(word);
            }
        });
    }


}
