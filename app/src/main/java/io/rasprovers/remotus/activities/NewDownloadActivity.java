package io.rasprovers.remotus.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.webkit.URLUtil;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.rasprovers.remotus.R;
import io.rasprovers.remotus.model.Download;

public class NewDownloadActivity extends AppCompatActivity {
    
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.downloadLinkEditText)
    EditText downloadLinkEditText;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.warningTextView)
    TextView warningTextView;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_download);
        ButterKnife.bind(this);
        
        setSupportActionBar(toolbar);
    
        
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
                
                String s = downloadLinkEditText.getText().toString();
                if(!Patterns.WEB_URL.matcher(s).matches()) {
                    warningTextView.setVisibility(View.VISIBLE);
                }
                else {
                    warningTextView.setVisibility(View.INVISIBLE);
                    Toast.makeText(NewDownloadActivity.this, URLUtil.guessFileName(s,null,null), Toast.LENGTH_SHORT).show();
                }
                
                
            }
        });
        
        downloadLinkEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                
            }
            
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                /* URL Validation*/
                validateUrl(s.toString());
            }
            
            @Override
            public void afterTextChanged(Editable s) {
                validateUrl(s.toString());
            }
        });
    }
    
    public void validateUrl(String url){
        if (!Patterns.WEB_URL.matcher(url).matches()) {
            warningTextView.setVisibility(View.VISIBLE);
        }
        else {
            warningTextView.setVisibility(View.INVISIBLE);
        }
    }
    
}