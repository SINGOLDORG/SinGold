package com.example.singold.data;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.AsyncTask;
import android.os.Build;

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
import com.squareup.okhttp.OkHttpClient;

import java.net.MalformedURLException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * Created by samih on 05/03/2017.
 */

public  class ConnectToServer {

    /**
     * Mobile Service Client reference
     */
    private static MobileServiceClient mClient;

    /**
     * Mobile Service Table used to access data
     */
    private static MobileServiceTable<User> userTable;
    private static MobileServiceTable<Song> songTable;
    private static MobileServiceTable<PatientDetails> patientDetailsTable;
    private static MobileServiceTable<PatientSurvey> patientSurveyTable;
    private static MobileServiceTable<halfSurvey> HalfSurveyTable;

    private MobileServiceTable<ToDoItem> mToDoTable;


    private static Activity context;

    public static void connet(Activity context) {
        ConnectToServer.context = context;
        try {
            // Create the Mobile Service Client instance, using the provided

            // Mobile Service URL and key
            mClient = new MobileServiceClient(
                    "https://singold2.azurewebsites.net",
                    context).withFilter(new ProgressFilter());

            // Extend timeout from default of 10s to 20s
            mClient.setAndroidHttpClientFactory(new OkHttpClientFactory() {
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

    private void createAndShowDialogFromTask(final Exception exception, String title) {
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

    /**
     * Run an ASync task on the corresponding executor
     *
     * @param task
     * @return
     */
    private AsyncTask<Void, Void, Void> runAsyncTask(AsyncTask<Void, Void, Void> task) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            return task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        } else {
            return task.execute();
        }
    }

    private static class ProgressFilter implements ServiceFilter {

        @Override
        public ListenableFuture<ServiceFilterResponse> handleRequest(ServiceFilterRequest request, NextServiceFilterCallback nextServiceFilterCallback) {

            final SettableFuture<ServiceFilterResponse> resultFuture = SettableFuture.create();


            context.runOnUiThread(new Runnable() {

                @Override
                public void run() {
                    //  if (mProgressBar != null) mProgressBar.setVisibility(ProgressBar.VISIBLE);
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
    public void addInTable(final User item) throws ExecutionException, InterruptedException {
        final User[] entity = {null};
        if (userTable == null)
            userTable = mClient.getTable(User.class);
        AsyncTask<Void, Void, Void> task = new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                try {
                    entity[0] = userTable.insert(item).get();

                    context.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
//                            if(!entity.isComplete()){
//                                ///mAdapter.add(entity);
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
            }
        };

        runAsyncTask(task);
    }

    public void addInTable(final ToDoItem item) throws ExecutionException, InterruptedException {
        ;
        if (mToDoTable == null)
            mToDoTable = mClient.getTable(ToDoItem.class);
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
                        }
                    });
                } catch (final Exception e) {
                    createAndShowDialogFromTask(e, "Error");
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
            }
        };

        runAsyncTask(task);
    }
    public void addInTable(final Song item) throws ExecutionException, InterruptedException {
        ;
        if (songTable == null)
            songTable = mClient.getTable(Song.class);
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
            }
        };

        runAsyncTask(task);
    }
    public void addInTable(final PatientSurvey item) throws ExecutionException, InterruptedException {
        ;
        if (patientSurveyTable == null)
            patientSurveyTable = mClient.getTable(PatientSurvey.class);
        AsyncTask<Void, Void, Void> task = new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                try {
                    final PatientSurvey entity = patientSurveyTable.insert(item).get();

                    context.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
//                            if(!entity.isComplete()){
//                                ///mAdapter.add(entity);
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
            }
        };

        runAsyncTask(task);
    }

    public void addInTable(final PatientDetails item) throws ExecutionException, InterruptedException {
        ;
        if (patientDetailsTable == null)
            patientDetailsTable = mClient.getTable(PatientDetails.class);
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
                        }
                    });
                } catch (final Exception e) {
                    createAndShowDialogFromTask(e, "Error");
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
            }
        };

        runAsyncTask(task);
    }
    public void addInTable(final halfSurvey item) throws ExecutionException, InterruptedException {
        ;
        if (HalfSurveyTable == null)
            HalfSurveyTable = mClient.getTable(halfSurvey.class);
        AsyncTask<Void, Void, Void> task = new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                try {
                    final halfSurvey entity = HalfSurveyTable.insert(item).get();

                    context.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
//                            if(!entity.isComplete()){
//                                ///mAdapter.add(entity);
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
            }
        };

        runAsyncTask(task);
    }


}
