package com.mobintum.intentsamples;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import java.util.List;


public class MainActivity extends ActionBarActivity implements View.OnClickListener {

    ImageButton btnCall, btnEmail, btnShare, btnCamera, btnBrowser, btnActivity, btnFacebook;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnCall = (ImageButton) findViewById(R.id.btnCall);
        btnEmail = (ImageButton) findViewById(R.id.btnEmail);
        btnShare = (ImageButton) findViewById(R.id.btnShare);
        btnCamera = (ImageButton) findViewById(R.id.btnCamera);
        btnBrowser = (ImageButton) findViewById(R.id.btnBrowser);
        btnActivity = (ImageButton) findViewById(R.id.btnActivity);
        btnFacebook = (ImageButton) findViewById(R.id.btnFacebook);

        btnCall.setOnClickListener(this);
        btnEmail.setOnClickListener(this);
        btnShare.setOnClickListener(this);
        btnCamera.setOnClickListener(this);
        btnBrowser.setOnClickListener(this);
        btnActivity.setOnClickListener(this);
        btnFacebook.setOnClickListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public static boolean isAvailable(Context ctx, Intent intent) {
        final PackageManager mgr = ctx.getPackageManager();
        List<ResolveInfo> list =
                mgr.queryIntentActivities(intent,
                        PackageManager.MATCH_DEFAULT_ONLY);
        return list.size() > 0;
    }
    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.btnCall:
                Log.e("ENTER", "CALL");
                intent = new Intent(Intent.ACTION_DIAL,
                        Uri.parse("tel:(521) 5514382887"));
                startActivity(intent);
                break;

            case R.id.btnEmail:

                intent = new Intent(Intent.ACTION_SENDTO);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_SUBJECT, "Subject of email");
                intent.putExtra(Intent.EXTRA_TEXT, "Body of email");
                intent.setData(Uri.parse("mailto:ricardo.celj@gmail.com"));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                break;

            case R.id.btnBrowser:
                intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("http://www.github.com"));
                startActivity(intent);

                break;

            case R.id.btnCamera:
                intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                startActivity(intent);
                break;

            case R.id.btnShare:
                intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(android.content.Intent.EXTRA_SUBJECT, "ETSTSTS");
                intent.putExtra(android.content.Intent.EXTRA_TEXT, "Example text");
                startActivity(intent);
                break;

            case R.id.btnActivity:

                intent = new Intent(this, SecondActivity.class);
                startActivity(intent);

                break;

            case R.id.btnFacebook:
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse("twitter://user?screen_name=rickstart"));
                startActivity(intent);
                break;
        }
    }
}
