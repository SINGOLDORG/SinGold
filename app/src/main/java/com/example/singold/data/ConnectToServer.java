package com.example.singold.data;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.singold.MyActivities.PatientListActivity;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.SettableFuture;
import com.microsoft.windowsazure.mobileservices.MobileServiceClient;
import com.microsoft.windowsazure.mobileservices.http.NextServiceFilterCallback;
import com.microsoft.windowsazure.mobileservices.http.OkHttpClientFactory;
import com.microsoft.windowsazure.mobileservices.http.ServiceFilter;
import com.microsoft.windowsazure.mobileservices.http.ServiceFilterRequest;
import com.microsoft.windowsazure.mobileservices.http.ServiceFilterResponse;
import com.microsoft.windowsazure.mobileservices.table.MobileServiceTable;
import com.microsoft.windowsazure.mobileservices.table.TableQueryCallback;
import com.squareup.okhttp.OkHttpClient;

import java.net.MalformedURLException;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import static com.microsoft.windowsazure.mobileservices.table.query.QueryOperations.val;

/**
 * Created by samih on 05/03/2017.
 */

public  class ConnectToServer {

    /**
     * Mobile Service Client reference
     */
    private static MobileServiceClient azureDBClient;

    /**
     * Mobile Service Table used to access data
     */
    private static MobileServiceTable<MyUser> userTable;
    private static MobileServiceTable<Song> songTable;
    private static MobileServiceTable<PatientDetails> patientDetailsTable;
    private static MobileServiceTable<PatientProfile> patientProfileTable;
    private static MobileServiceTable<MatchingSurvey> HalfSurveyTable;



    private static MobileServiceTable<ToDoItem> mToDoTable;
    private  static  ProgressDialog dialog;


    public static Activity context;
    //  private static List<ToDoItem> results;

    public static void connect(Activity context) {

        ConnectToServer.context = context;
        if(azureDBClient ==null) {
            try {
                // Create the Mobile Service Client instance, using the provided

                // Mobile Service URL and key
                azureDBClient = new MobileServiceClient(
                        "https://singold2.azurewebsites.net",
                        context).withFilter(new ProgressFilter(context));

                // Extend timeout from default of 10s to 20s
                azureDBClient.setAndroidHttpClientFactory(new OkHttpClientFactory() {
                    @Override
                    public OkHttpClient createOkHttpClient() {
                        OkHttpClient client = new OkHttpClient();
                        client.setReadTimeout(20, TimeUnit.SECONDS);
                        client.setWriteTimeout(20, TimeUnit.SECONDS);
                        return client;
                    }
                });

            } catch (MalformedURLException e) {
                createAndShowDialog(new Exception("There was an error creating the Mobile Service. Verify the URL"), "Error");
            } catch (Exception e) {
                createAndShowDialog(e, "Error");
            }
        }

    }

    private static void createAndShowDialogFromTask(final Exception exception, String title) {
        exception.printStackTrace();
        context.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                createAndShowDialog(exception, "Error");
            }
        });
    }


    /**
     * Creates a dialog and shows it
     *
     * @param exception The exception to show in the dialog
     * @param title     The dialog title
     */
    private static void createAndShowDialog(Exception exception, String title) {
        Throwable ex = exception;
        if (exception.getCause() != null) {
            ex = exception.getCause();
        }
        createAndShowDialog(ex.getMessage(), title);
    }

    /**
     * Creates a dialog and shows it
     *
     * @param message The dialog message
     * @param title   The dialog title
     */
    private static void createAndShowDialog(final String message, final String title) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);

        builder.setMessage(message);
        builder.setTitle(title);
        builder.create().show();
    }

    private static void  showProProgressDialog(String msg)
    {
        if(((Activity) context).isFinishing())
        {
            return;
        }
        if(dialog==null)
        {
            dialog=new ProgressDialog(context);
        }
        dialog.setMessage(msg);
        dialog.show();
    }
    private static void  dismissProProgressDialog()
    {
        if(((Activity) context).isFinishing())
        {
            return;
        }
        if(dialog!=null)
        {
            dialog.dismiss();
        }

    }


    /**
     * Run an ASync task on the corresponding executor
     *
     * @param task
     * @return
     */
    public static AsyncTask<Void, Void, Void> runAsyncTask(AsyncTask<Void, Void, Void> task) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            return task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        } else {
            return task.execute();
        }
    }



    public static class ProgressFilter implements ServiceFilter {

        public ProgressFilter(Activity activity)
        {
            context=activity;
        }

        @Override
        public ListenableFuture<ServiceFilterResponse> handleRequest(ServiceFilterRequest request, NextServiceFilterCallback nextServiceFilterCallback) {

            final SettableFuture<ServiceFilterResponse> resultFuture = SettableFuture.create();




            context.runOnUiThread(new Runnable() {

                @Override
                public void run() {
                    //  if (mProgressBar != null) mProgressBar.setVisibility(ProgressBar.VISIBLE);
                    //showProProgressDialog("Connecting...Wait...");

                }
            });

            ListenableFuture<ServiceFilterResponse> future = nextServiceFilterCallback.onNext(request);

            Futures.addCallback(future, new FutureCallback<ServiceFilterResponse>() {
                @Override
                public void onFailure(Throwable e) {
                    resultFuture.setException(e);
                }

                @Override
                public void onSuccess(ServiceFilterResponse response) {
                    context.runOnUiThread(new Runnable() {

                        @Override
                        public void run() {
                            //    if (mProgressBar != null) mProgressBar.setVisibility(ProgressBar.GONE);
                            dismissProProgressDialog();
//                            Intent intent = new Intent(context, PatientListActivity.class);
//                            context.startActivity(intent);
                        }
                    });

                    resultFuture.set(response);
                }
            });

            return resultFuture;
        }
    }

    /**
     * Add an item to the Mobile Service Table
     *
     * @param item The item to Add
     */
    public static void addInTable(final MyUser item) throws ExecutionException, InterruptedException {
        final MyUser[] entity = {null};
        if (userTable == null)
            userTable = azureDBClient.getTable(MyUser.class);
        AsyncTask<Void, Void, Void> task = new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                try {
                    entity[0] = userTable.insert(item).get();

                    context.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if(dialog!=null){
                                dialog.setMessage("Saving...");
                                dialog.show();
                            }

                        }
                    });
                } catch (final Exception e) {
                    createAndShowDialogFromTask(e, "Error");
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                dismissProProgressDialog();
                context.finish();


            }
        };

        runAsyncTask(task);
    }

    public static  void login(final String user, String passw, final ProgressBar progressBar)
    {
        if (userTable == null)
            userTable = azureDBClient.getTable(MyUser.class);
        progressBar.setVisibility(View.VISIBLE);
        userTable.where().field("username").eq(user).and().field("EnterId").eq(passw).execute(new TableQueryCallback<MyUser>() {
            @Override
            public void onCompleted(List<MyUser> result, int count, Exception exception, ServiceFilterResponse response) {

                // it appears for me and error here, ** remember to ask about it
                if (result!=null && result.size() > 0) {
               //     DataBaseMngr.saveLogIn(result.get(0), getBaseContext());
                 //   signinDialog.dismiss();
                    Intent intent=new Intent(context, PatientListActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    PrefManager.setFirstTimeLaunch(context,true,user);
                    context.startActivity(intent);
                    progressBar.setVisibility(View.GONE);

                    Toast.makeText(context, "WELCOME " , Toast.LENGTH_SHORT).show();

                    //    finish();

                } else {
                    //signinDialog.dismiss();
                    createAndShowDialog("EMAIL OR PASSWORD WRONG", "");

                }
            }
        });
    }
    public static  void searchAndMatch(MatchingSurvey matchingSurvey)
    {
        if (patientProfileTable == null)
            patientProfileTable = azureDBClient.getTable(PatientProfile.class);

        patientProfileTable.where().field("country").eq(matchingSurvey.getCountry()).and().field("language").eq(matchingSurvey.getLanguage()).execute(new TableQueryCallback<PatientProfile>() {
            @Override
            public void onCompleted(List<PatientProfile> result, int count, Exception exception, ServiceFilterResponse response) {

                // it appears for me and error here, ** remember to ask about it
                if (result!=null && result.size() > 0) {
                    //     DataBaseMngr.saveLogIn(result.get(0), getBaseContext());
                    //   signinDialog.dismiss();
                    Intent intent=new Intent(context, PatientListActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    context.startActivity(intent);
                    Toast.makeText(context, "WELCOME, " , Toast.LENGTH_LONG).show();
                    //    finish();

                } else {
                    //signinDialog.dismiss();
                    createAndShowDialog("EMAIL OR PASSWORD WRONG", "");

                }
            }
        });
    }
    public static void refreshItemsFromTable(final SongAdapter adapter, final PatientProfile patientProfile, final ProgressBar progressBar) {
        // Get the items that weren't marked as completed and add them in the
        // adapter
        AsyncTask<Void, Void, Void> task = new AsyncTask<Void, Void, Void>(){
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                if (patientProfileTable == null)
                    patientProfileTable = azureDBClient.getTable(PatientProfile.class);
                if(progressBar!=null)
                    progressBar.setVisibility(View.VISIBLE);
//                showProProgressDialog("Downloading Details..");
            }

            @Override
            protected Void doInBackground(Void... params) {

                try {
                    //   final List<ToDoItem> results = refreshItemsFromMobileServiceTable();

                    final List<PatientProfile> results =
                            patientProfileTable.where().
                                    field("language").eq(patientProfile.getLanguage())
                                    //.and().field("culture").eq(patientProfile.getCulture())
                                    .execute().get();
                    //Offline Sync
                    //final List<ToDoItem> results = refreshItemsFromMobileServiceTableSyncTable();
                    for (PatientProfile item  : results) {
                        refreshItemsFromTable(adapter,item.getIdPatient(),progressBar);

                    }
                    context.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                           // adapter.clear();

                            for (PatientProfile item  : results) {
                               // adapter.add(item);

                            }
                        }
                    });
                } catch (final Exception e){
                    createAndShowDialogFromTask(e, "Error");
                }

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                if(progressBar!=null)
                    progressBar.setVisibility(View.GONE);
                //dismissProProgressDialog();
                        }
        };

        runAsyncTask(task);


    }
    public static void refreshItemsFromTable(final ToDoItemAdapter adapter) {

        // Get the items that weren't marked as completed and add them in the
        // adapter
        AsyncTask<Void, Void, Void> task = new AsyncTask<Void, Void, Void>(){
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                showProProgressDialog("ToDOItems..");
            }

            @Override
            protected Void doInBackground(Void... params) {

                try {
                 //   final List<ToDoItem> results = refreshItemsFromMobileServiceTable();
                    final List<ToDoItem> results = mToDoTable.where().execute().get();
                    //Offline Sync
                    //final List<ToDoItem> results = refreshItemsFromMobileServiceTableSyncTable();

                    context.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            adapter.clear();

                            for (ToDoItem item : results) {
                                adapter.add(item);
                            }
                        }
                    });
                } catch (final Exception e){
                    createAndShowDialogFromTask(e, "Error");
                }

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                dismissProProgressDialog();            }
        };

        runAsyncTask(task);
    }

    public static void refreshItemsFromTable(final UserAdapter adapter) {

        // Get the items that weren't marked as completed and add them in the
        // adapter
        AsyncTask<Void, Void, Void> task = new AsyncTask<Void, Void, Void>(){
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                showProProgressDialog("Downloading Users...");
            }

            @Override
            protected Void doInBackground(Void... params) {

                try {
                    //   final List<ToDoItem> results = refreshItemsFromMobileServiceTable();
                    final List<MyUser> results = userTable.where().execute().get();
                    //Offline Sync
                    //final List<ToDoItem> results = refreshItemsFromMobileServiceTableSyncTable();

                    context.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            adapter.clear();

                            for (MyUser item : results) {
                                adapter.add(item);
                            }
                        }
                    });
                } catch (final Exception e){
                    createAndShowDialogFromTask(e, "Error");
                }

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                dismissProProgressDialog();
            }
        };

        runAsyncTask(task);
    }
    public static void refreshItemsFromTable(final SongAdapter adapter, final String id, final ProgressBar progressBar) {
        if(progressBar!=null)
            progressBar.setVisibility(View.VISIBLE);
        // Get the items that weren't marked as completed and add them in the
        // adapter
        AsyncTask<Void, Void, Void> task = new AsyncTask<Void, Void, Void>(){
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
//                showProProgressDialog("Reading Songs");
                //Toast.makeText(context,"Reading Songs",Toast.LENGTH_LONG).show();
            }

            @Override
            protected Void doInBackground(Void... params) {

                try {
                    //   final List<ToDoItem> results = refreshItemsFromMobileServiceTable();
                    if (songTable == null)
                        songTable = azureDBClient.getTable(Song.class);
                    final List<Song> results = songTable.where().field("idPatient").eq(id).execute().get();
                    //Offline Sync
                    //final List<ToDoItem> results = refreshItemsFromMobileServiceTableSyncTable();

                    context.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            adapter.clear();

                            for (Song item  : results) {
                                adapter.add(item);
                            }
                        }
                    });
                } catch (final Exception e){
                    createAndShowDialogFromTask(e, "Error");
                }

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
               // dismissProProgressDialog();
               // Toast.makeText(context,"finished Reading Songs",Toast.LENGTH_LONG).show();
                if(progressBar!=null)
                    progressBar.setVisibility(View.GONE);
            }
        };

        runAsyncTask(task);
    }

    public static void refreshItemsFromTable(final PatientDetailsAdapter adapter, final String userId) {

        // Get the items that weren't marked as completed and add them in the
        // adapter
        AsyncTask<Void, Void, Void> task = new AsyncTask<Void, Void, Void>(){
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                if (patientDetailsTable == null)
                    patientDetailsTable = azureDBClient.getTable(PatientDetails.class);
//                showProProgressDialog("Downloading Details..");
            }

            @Override
            protected Void doInBackground(Void... params) {

                try {
                    //   final List<ToDoItem> results = refreshItemsFromMobileServiceTable();

                    final List<PatientDetails> results = patientDetailsTable.where().field("idUser").eq(userId).execute().get();
                    //Offline Sync
                    //final List<ToDoItem> results = refreshItemsFromMobileServiceTableSyncTable();

                    context.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            adapter.clear();

                            for (PatientDetails item  : results) {
                                adapter.add(item);
                            }
                        }
                    });
                } catch (final Exception e){
                    createAndShowDialogFromTask(e, "Error");
                }

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                dismissProProgressDialog();            }
        };

        runAsyncTask(task);
    }
    public static void refreshItemsFromTable(final PatientSurveyAdapter adapter) {

        // Get the items that weren't marked as completed and add them in the
        // adapter
        AsyncTask<Void, Void, Void> task = new AsyncTask<Void, Void, Void>(){
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
               // showProProgressDialog("Filling the questions..");
            }

            @Override
            protected Void doInBackground(Void... params) {

                try {
                    //   final List<ToDoItem> results = refreshItemsFromMobileServiceTable();
                    final List<PatientProfile> results = patientProfileTable.where().execute().get();
                    //Offline Sync
                    //final List<ToDoItem> results = refreshItemsFromMobileServiceTableSyncTable();

                    context.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            adapter.clear();

                            for (PatientProfile item  : results) {
                                adapter.add(item);
                            }
                        }
                    });
                } catch (final Exception e){
                    createAndShowDialogFromTask(e, "Error");
                }

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
               // dismissProProgressDialog();
            }
        };

        runAsyncTask(task);
    }
    public static void refreshItemsFromTable(final MatchingSurveyAdapter adapter) {

        // Get the items that weren't marked as completed and add them in the
        // adapter
        AsyncTask<Void, Void, Void> task = new AsyncTask<Void, Void, Void>(){
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
              //  showProProgressDialog("Matching..");
            }

            @Override
            protected Void doInBackground(Void... params) {

                try {
                    //   final List<ToDoItem> results = refreshItemsFromMobileServiceTable();
                    final List<MatchingSurvey> results = HalfSurveyTable.where().execute().get();
                    //Offline Sync
                    //final List<ToDoItem> results = refreshItemsFromMobileServiceTableSyncTable();

                    context.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            adapter.clear();

                            for (MatchingSurvey item  : results) {
                                adapter.add(item);
                            }
                        }
                    });
                } catch (final Exception e){
                    createAndShowDialogFromTask(e, "Error");
                }

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
              //  dismissProProgressDialog();
            }
        };

        runAsyncTask(task);
    }


    public  static void addInTable(final ToDoItem item) throws ExecutionException, InterruptedException {
        ;
        if (mToDoTable == null)
            mToDoTable = azureDBClient.getTable(ToDoItem.class);
        AsyncTask<Void, Void, Void> task = new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                try {
                    final ToDoItem entity = mToDoTable.insert(item).get();

                    context.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
//                            if(!entity.isComplete()){
//                                ///mAdapter.add(entity);
//                            }
                            showProProgressDialog("Saving TODOITEM");

                        }
                    });
                } catch (final Exception e) {
                  //  createAndShowDialogFromTask(e, "Error");
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
               // dismissProProgressDialog();

            }
        };

        runAsyncTask(task);
    }

    public static void addInTable(final Song item, final ProgressBar progressBar) throws ExecutionException, InterruptedException {
        if(progressBar!=null)
            progressBar.setVisibility(View.VISIBLE);
        if (songTable == null)
            songTable = azureDBClient.getTable(Song.class);
        AsyncTask<Void, Void, Void> task = new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                try {
                    final Song entity = songTable.insert(item).get();

                    context.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
//                            if(!entity.isComplete()){
//                                ///mAdapter.add(entity);
                    //showProProgressDialog("Saving Song");
//                            }
                        }
                    });
                } catch (final Exception e) {
                    createAndShowDialogFromTask(e, "Error");
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                //dismissProProgressDialog();
                if(progressBar!=null)
                    progressBar.setVisibility(View.GONE);
                context.finish();

            }
        };

        runAsyncTask(task);
    }
    public static void addInTable(final PatientProfile item, final ProgressBar progressBar) throws ExecutionException, InterruptedException {
        if(progressBar!=null)
            progressBar.setVisibility(View.VISIBLE);
        if (patientProfileTable == null)
            patientProfileTable = azureDBClient.getTable(PatientProfile.class);
        AsyncTask<Void, Void, Void> task = new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                try {
                    final PatientProfile entity = patientProfileTable.insert(item).get();

                    context.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
//                            if(!entity.isComplete()){
//                                ///mAdapter.add(entity);
//                            }
                         //   showProProgressDialog("Saving PatientProfile");

                        }
                    });
                } catch (final Exception e) {
                    createAndShowDialogFromTask(e, "Error");
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
               // dismissProProgressDialog();
                if(progressBar!=null)
                    progressBar.setVisibility(View.GONE);
                context.finish();

            }
        };

        runAsyncTask(task);
    }

    public static void addInTable(final PatientDetails item, final ProgressBar progressBar) throws ExecutionException, InterruptedException {
        if(progressBar!=null)
            progressBar.setVisibility(View.VISIBLE);
        if (patientDetailsTable == null)
            patientDetailsTable = azureDBClient.getTable(PatientDetails.class);
        AsyncTask<Void, Void, Void> task = new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                try {
                    final PatientDetails entity = patientDetailsTable.insert(item).get();

                    context.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
//                            if(!entity.isComplete()){
//                                ///mAdapter.add(entity);
//                            }
                            ///showProProgressDialog("Saving PatientDetails");

                        }
                    });
                } catch (final Exception e) {
                    createAndShowDialogFromTask(e, "Error");
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
               // dismissProProgressDialog();
                if(progressBar!=null)
                    progressBar.setVisibility(View.GONE);
                context.finish();
            }
        };

        runAsyncTask(task);
    }
    public static void addInTable(final MatchingSurvey item) throws ExecutionException, InterruptedException {
        ;
        if (HalfSurveyTable == null)
            HalfSurveyTable = azureDBClient.getTable(MatchingSurvey.class);
        AsyncTask<Void, Void, Void> task = new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                try {
                    final MatchingSurvey entity = HalfSurveyTable.insert(item).get();

                    context.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
//                            if(!entity.isComplete()){
//                                ///mAdapter.add(entity);
//                            }
                            showProProgressDialog("Saving MatchingSurvey");

                        }
                    });
                } catch (final Exception e) {
                    createAndShowDialogFromTask(e, "Error");
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                    dismissProProgressDialog();
            }
        };

        runAsyncTask(task);
    }


}
