package com.example.singold.MyActivities;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.singold.R;
import com.example.singold.data.VideoItem;
import com.example.singold.data.YoutubeConnector;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.ResourceId;
import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.SearchResult;
import com.google.api.services.youtube.model.Thumbnail;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SearchSongActivity extends AppCompatActivity {
private EditText etSearch;
    private ImageButton sear;
    private ListView videosFound;

    private List<VideoItem> searchResults;


    private static final long NUMBER_OF_VIDEOS_RETURNED = 5;

    /**
     * Define a global instance of a Youtube object, which will be used
     * to make YouTube Data API requests.
     */
    private static YouTube youtube;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_song);
        etSearch =(EditText)findViewById(R.id.search);
        sear=(ImageButton)findViewById(R.id.sear);
        videosFound = (ListView)findViewById(R.id.videos_found);
        addClickListener();

        etSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId == EditorInfo.IME_ACTION_DONE){
                   // searchOnYoutube(v.getText().toString());
                    searchOnYoutube(v.getText().toString());
                    return true;
                }
                return false;
            }
        });
    }


    private void addClickListener(){
        videosFound.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> av, View v, int pos,
                                    long id) {
//                Intent intent = new Intent(getApplicationContext(),
//                        PlayerYouTubeActivity.class);
//                intent.putExtra("VIDEO_ID", searchResults.get(pos).getId());
//                startActivity(intent);
                setResult(RESULT_OK);
                finish();
            }

        });

        sear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchOnYoutube(etSearch.getText().toString());
            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();
        Intent i=getIntent();
        if (i!=null && i.getExtras()!=null && i.getExtras().containsKey("toSearch")) {
            String toSearch=i.getExtras().getString("toSearch");
            etSearch.setText(toSearch);
           searchOnYoutube(toSearch);
        }

    }

    /**
     * Initialize a YouTube object to searchOnYoutube for videos on YouTube. Then
     * display the name and thumbnail image of each video in the result set.
     *
     * @param toSearch .
     */
    public  void searchOnYoutube(final String toSearch) {

        try {
            // This object is used to make YouTube Data API requests. The last
            // argument is required, but since we don't need anything
            // initialized when the HttpRequest is initialized, we override
            // the interface and provide a no-op function.
//            youtube = new YouTube.Builder(Auth.HTTP_TRANSPORT, Auth.JSON_FACTORY, new HttpRequestInitializer() {
//                public void initialize(HttpRequest request) throws IOException {
//                }
//            }).setApplicationName("youtube-cmdline-searchOnYoutube-sample").build();
            youtube = new YouTube.Builder(new NetHttpTransport(),
                    new JacksonFactory(), new HttpRequestInitializer() {
                @Override
                public void initialize(HttpRequest hr) throws IOException {}
            }).setApplicationName("singold").build();
            // Prompt the user to enter a query term.
            //String queryTerm = getInputQuery();

            // Define the API request for retrieving searchOnYoutube results.
            final YouTube.Search.List search = youtube.search().list("id,snippet");

            // Set your developer key from the {{ Google Cloud Console }} for
            // non-authenticated requests. See:
            // {{ https://cloud.google.com/console }}
           // String apiKey = "AIzaSyAtXYaHf2kjeGxwe7Tq_p8_hp6zREqeGMo";//properties.getProperty("youtube.apikey");
            search.setKey(YoutubeConnector.KEY);
            search.setQ(toSearch);

            // Restrict the searchOnYoutube results to only include videos. See:
            // https://developers.google.com/youtube/v3/docs/search/list#type
            search.setType("video");

            // To increase efficiency, only retrieve the fields that the
            // application uses.
            search.setFields("items(id/kind,id/videoId,snippet/title,snippet/thumbnails/default/url)");
            search.setMaxResults(NUMBER_OF_VIDEOS_RETURNED);

            // Call the API and print results.
            AsyncTask<Void,Integer,List<SearchResult>> asyncTask=new AsyncTask<Void, Integer, List<SearchResult>>() {
                @Override
                protected List<SearchResult> doInBackground(Void... params) {
                    try {
                        SearchListResponse searchResponse = search.execute();
                        List<SearchResult> searchResultList = searchResponse.getItems();
                        return  searchResultList;
                    } catch (IOException e) {
                        publishProgress();
                        e.printStackTrace();

                    }

                    return null;
                }

                @Override
                protected void onProgressUpdate(Integer... values) {
                    super.onProgressUpdate(values);
                    Toast.makeText(getApplicationContext(), "There was an IO error: " , Toast.LENGTH_LONG).show();

                }

                @Override
                protected void onPostExecute(List<SearchResult> searchResults) {
                    if (searchResults != null) {
                        updateListview(searchResults.iterator(), toSearch);
                    }
                }
            };
            asyncTask.execute();

        } catch (GoogleJsonResponseException e) {
            Toast.makeText(getApplicationContext(), "There was a service error: " + e.getDetails().getCode() + " : "
                    + e.getDetails().getMessage(), Toast.LENGTH_LONG).show();
            e.printStackTrace();

        } catch (IOException e) {
            Toast.makeText(getApplicationContext(), "There was an IO error: " + e.getCause() + " : " + e.getMessage(), Toast.LENGTH_LONG).show();
            e.printStackTrace();
        } catch (Throwable t) {
            Toast.makeText(getApplicationContext(), "Throwable: " + t.getCause() + " : " + t.getMessage(), Toast.LENGTH_LONG).show();

            t.printStackTrace();

        }
    }



    private  void updateListview(Iterator<SearchResult> iteratorSearchResults, String query) {


        if (!iteratorSearchResults.hasNext()) {
            Toast.makeText(getApplicationContext(), " There aren't any results for your query.", Toast.LENGTH_LONG).show();

            System.out.println(" There aren't any results for your query.");
        }

        List<VideoItem> items = new ArrayList<VideoItem>();

        if(searchResults== null) searchResults=new ArrayList<VideoItem>();
        searchResults.clear();
        while (iteratorSearchResults.hasNext()) {

            SearchResult singleVideo = iteratorSearchResults.next();
            ResourceId rId = singleVideo.getId();

            // Confirm that the result represents a video. Otherwise, the
            // item will not contain a video ID.
            if (rId.getKind().equals("youtube#video")) {
                Thumbnail thumbnail = singleVideo.getSnippet().getThumbnails().getDefault();
                VideoItem item = new VideoItem();
                item.setTitle(singleVideo.getSnippet().getTitle());
                item.setDescription(singleVideo.getSnippet().getDescription());
                item.setThumbnailURL(singleVideo.getSnippet().getThumbnails().getDefault().getUrl());
                item.setId(singleVideo.getId().getVideoId());
                searchResults.add(item);
//                System.out.println(" Video Id" + rId.getVideoId());
//                System.out.println(" Title: " + singleVideo.getSnippet().getTitle());
//                System.out.println(" Thumbnail: " + thumbnail.getUrl());
//                System.out.println("\n-------------------------------------------------------------\n");
            }
        }
        updateVideosFound();

    }
    private void updateVideosFound(){
        ArrayAdapter<VideoItem> adapter = new ArrayAdapter<VideoItem>(getApplicationContext(), R.layout.video_item, searchResults){
            @Override
            public View getView(final int position, View convertView, ViewGroup parent) {
                if(convertView == null){
                    convertView = getLayoutInflater().inflate(R.layout.video_item, parent, false);
                }
                ImageView thumbnail = (ImageView)convertView.findViewById(R.id.video_thumbnail);
                TextView title = (TextView)convertView.findViewById(R.id.video_title);
                TextView description = (TextView)convertView.findViewById(R.id.video_description);

                final VideoItem searchResult = searchResults.get(position);

                Picasso.with(getApplicationContext()).load(searchResult.getThumbnailURL()).into(thumbnail);
                title.setText(searchResult.getTitle());
                description.setText(searchResult.getDescription());


//                ImageButton btnPlay = (ImageButton)convertView.findViewById(R.id.itmBtnPlay);
//                Button btnChoise = (Button)convertView.findViewById(R.id.btnChoise);
//                btnPlay.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        Intent intent = new Intent(getApplicationContext(),
//                                PlayerYouTubeActivity.class);
//                        intent.putExtra("VIDEO_ID", searchResults.get(position).getId());
//                        startActivity(intent);
//                    }
//                });
//                btnChoise.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        Intent intent= new Intent();
//                        intent.putExtra("CHOISED",searchResult);
//                        setResult(RESULT_OK,intent);
//                        finish();
//                    }
//                });
                return convertView;
            }

            @Override
            public int getCount() {
                if(searchResults== null || searchResults.size()==0) {
                    Toast.makeText(getContext(), "No Results", Toast.LENGTH_LONG).show();
                    return 0;
                }
                return searchResults.size();
            }
        };

        videosFound.setAdapter(adapter);
    }
}

