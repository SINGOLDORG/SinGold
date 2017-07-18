package com.example.singold.MyActivities;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.singold.R;
import com.example.singold.data.VideoItem;
import com.example.singold.data.YoutubeConnector;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.ResourceId;
import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.SearchResult;
import com.google.api.services.youtube.model.Thumbnail;
import com.squareup.picasso.Picasso;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

public class SearchSongActivity extends AppCompatActivity {
private EditText search;
    private ImageButton sear;
    private ListView videosFound;

    private Handler handler;
    private List<VideoItem> searchResults;
//
//    /**
//     * Define a global variable that identifies the name of a file that
//     * contains the developer's API key.
//     */
//    private static final String PROPERTIES_FILENAME = "youtube.properties";
//
//    private static final long NUMBER_OF_VIDEOS_RETURNED = 5;
//
//    /**
//     * Define a global instance of a Youtube object, which will be used
//     * to make YouTube Data API requests.
//     */
//    private static YouTube youtube;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_song);
        search=(EditText)findViewById(R.id.search);
        sear=(ImageButton)findViewById(R.id.sear);



        videosFound = (ListView)findViewById(R.id.videos_found);
        addClickListener();
        handler = new Handler();

        search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId == EditorInfo.IME_ACTION_DONE){
                    searchOnYoutube(v.getText().toString());
                    return false;
                }
                return true;
            }
        });
    }
    private void searchOnYoutube(final String keywords){
        new Thread(){
            public void run(){
                YoutubeConnector yc = new YoutubeConnector(SearchSongActivity.this);
                searchResults = yc.search(keywords);
                handler.post(new Runnable(){
                    public void run(){
                        updateVideosFound();
                    }
                });
            }
        }.start();
    }
    private void updateVideosFound(){
        ArrayAdapter<VideoItem> adapter = new ArrayAdapter<VideoItem>(getApplicationContext(), R.layout.video_item, searchResults){
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                if(convertView == null){
                    convertView = getLayoutInflater().inflate(R.layout.video_item, parent, false);
                }
                ImageView thumbnail = (ImageView)convertView.findViewById(R.id.video_thumbnail);
                TextView title = (TextView)convertView.findViewById(R.id.video_title);
                TextView description = (TextView)convertView.findViewById(R.id.video_description);

                VideoItem searchResult = searchResults.get(position);

                Picasso.with(getApplicationContext()).load(searchResult.getThumbnailURL()).into(thumbnail);
                title.setText(searchResult.getTitle());
                description.setText(searchResult.getDescription());
                return convertView;
            }
        };

        videosFound.setAdapter(adapter);
    }
    private void addClickListener(){
        videosFound.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> av, View v, int pos,
                                    long id) {
                Intent intent = new Intent(getApplicationContext(),
                        PlayerActivity.class);
                intent.putExtra("VIDEO_ID", searchResults.get(pos).getId());
                startActivity(intent);
            }

        });
    }


    @Override
    protected void onResume() {
        super.onResume();
        Intent i=getIntent();
        if (i!=null) {
            String toSearch=i.getExtras().getString("toSearch");
            search.setText(toSearch);
            searchOnYoutube(toSearch);
           // search(toSearch);
        }

    }


//    /**
//     * Initialize a YouTube object to search for videos on YouTube. Then
//     * display the name and thumbnail image of each video in the result set.
//     *
//     * @param toSearch command line args.
//     */
//    public static void search(String toSearch) {
//        // Read the developer key from the properties file.
//        Properties properties = new Properties();
//        try {
//            InputStream in = YouTube.Search.class.getResourceAsStream("/" + PROPERTIES_FILENAME);
//            properties.load(in);
//
//        } catch (IOException e) {
//            System.err.println("There was an error reading " + PROPERTIES_FILENAME + ": " + e.getCause()
//                    + " : " + e.getMessage());
//            System.exit(1);
//        }
//
//        try {
//            // This object is used to make YouTube Data API requests. The last
//            // argument is required, but since we don't need anything
//            // initialized when the HttpRequest is initialized, we override
//            // the interface and provide a no-op function.
//            youtube = new YouTube.Builder(Auth.HTTP_TRANSPORT, Auth.JSON_FACTORY, new HttpRequestInitializer() {
//                public void initialize(HttpRequest request) throws IOException {
//                }
//            }).setApplicationName("youtube-cmdline-search-sample").build();
//
//            // Prompt the user to enter a query term.
//            //String queryTerm = getInputQuery();
//
//            // Define the API request for retrieving search results.
//            YouTube.Search.List search = youtube.search().list("id,snippet");
//
//            // Set your developer key from the {{ Google Cloud Console }} for
//            // non-authenticated requests. See:
//            // {{ https://cloud.google.com/console }}
//            String apiKey = "AIzaSyAtXYaHf2kjeGxwe7Tq_p8_hp6zREqeGMo";//properties.getProperty("youtube.apikey");
//            search.setKey(apiKey);
//            search.setQ(toSearch);
//
//            // Restrict the search results to only include videos. See:
//            // https://developers.google.com/youtube/v3/docs/search/list#type
//            search.setType("video");
//
//            // To increase efficiency, only retrieve the fields that the
//            // application uses.
//            search.setFields("items(id/kind,id/videoId,snippet/title,snippet/thumbnails/default/url)");
//            search.setMaxResults(NUMBER_OF_VIDEOS_RETURNED);
//
//            // Call the API and print results.
//            SearchListResponse searchResponse = search.execute();
//            List<SearchResult> searchResultList = searchResponse.getItems();
//            if (searchResultList != null) {
//                prettyPrint(searchResultList.iterator(), toSearch);
//            }
//        } catch (GoogleJsonResponseException e) {
//            System.err.println("There was a service error: " + e.getDetails().getCode() + " : "
//                    + e.getDetails().getMessage());
//        } catch (IOException e) {
//            System.err.println("There was an IO error: " + e.getCause() + " : " + e.getMessage());
//        } catch (Throwable t) {
//            t.printStackTrace();
//        }
//    }
//
//    /*
//     * Prompt the user to enter a query term and return the user-specified term.
//     */
//    private static String getInputQuery() throws IOException {
//
//        String inputQuery = "";
//
//        System.out.print("Please enter a search term: ");
//        BufferedReader bReader = new BufferedReader(new InputStreamReader(System.in));
//        inputQuery = bReader.readLine();
//
//        if (inputQuery.length() < 1) {
//            // Use the string "YouTube Developers Live" as a default.
//            inputQuery = "YouTube Developers Live";
//        }
//        return inputQuery;
//    }
//
//    /*
//     * Prints out all results in the Iterator. For each result, print the
//     * title, video ID, and thumbnail.
//     *
//     * @param iteratorSearchResults Iterator of SearchResults to print
//     *
//     * @param query Search query (String)
//     */
//    private static void prettyPrint(Iterator<SearchResult> iteratorSearchResults, String query) {
//
//        System.out.println("\n=============================================================");
//        System.out.println(
//                "   First " + NUMBER_OF_VIDEOS_RETURNED + " videos for search on \"" + query + "\".");
//        System.out.println("=============================================================\n");
//
//        if (!iteratorSearchResults.hasNext()) {
//            System.out.println(" There aren't any results for your query.");
//        }
//
//        while (iteratorSearchResults.hasNext()) {
//
//            SearchResult singleVideo = iteratorSearchResults.next();
//            ResourceId rId = singleVideo.getId();
//
//            // Confirm that the result represents a video. Otherwise, the
//            // item will not contain a video ID.
//            if (rId.getKind().equals("youtube#video")) {
//                Thumbnail thumbnail = singleVideo.getSnippet().getThumbnails().getDefault();
//
//                System.out.println(" Video Id" + rId.getVideoId());
//                System.out.println(" Title: " + singleVideo.getSnippet().getTitle());
//                System.out.println(" Thumbnail: " + thumbnail.getUrl());
//                System.out.println("\n-------------------------------------------------------------\n");
//            }
//        }
//    }
}

