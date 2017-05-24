package com.lesco.diccionario.helper;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;

/*
 * Copyright (c) 2012 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.googleapis.media.MediaHttpUploader;
import com.google.api.client.googleapis.media.MediaHttpUploaderProgressListener;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.InputStreamContent;
//import com.google.api.services.samples.youtube.cmdline.Auth;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.ResourceId;
import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.SearchResult;
import com.google.api.services.youtube.model.Thumbnail;
import com.google.api.services.youtube.model.Video;
import com.google.api.services.youtube.model.VideoListResponse;
import com.google.api.services.youtube.model.VideoSnippet;
import com.google.api.services.youtube.model.VideoStatus;
import com.google.common.collect.Lists;

/**
 * Upload a video to the authenticated user's channel. Use OAuth 2.0 to
 * authorize the request. Note that you must add your video files to the
 * project folder to upload them with this application.
 *
 * @author Jeremy Walker
 */
public class YoutubeHelper {

	//Log4J class logger instance
	private static final Logger logger = Logger.getLogger(YoutubeHelper.class);
		
    /**
     * Define a global instance of a Youtube object, which will be used
     * to make YouTube Data API requests.
     */
    private static YouTube youtube;
    
    /**
     * Define a global variable that identifies the name of a file that
     * contains the developer's API key.
     */

    private static final String PROPERTIES_FILENAME = "youtube.properties";
    
    private static final long NUMBER_OF_VIDEOS_RETURNED = 1;

    /**
     * Define a global variable that specifies the MIME type of the video
     * being uploaded.
     */
    private static final String VIDEO_FILE_FORMAT = "video/*";

    //private static final String SAMPLE_VIDEO_FILENAME = "sample-video.mp4";

    /**
     * Upload the user-selected video to the user's YouTube channel. The code
     * looks for the video in the application's project folder and uses OAuth
     * 2.0 to authorize the API request.
     *
     * @param args command line args (not used).
     */
    public String uploadVideo(String title, String description, MultipartFile videoFile) {
    	
    	logger.debug("UploadVideo - upload() - Start");

        // This OAuth 2.0 access scope allows an application to upload files
        // to the authenticated user's YouTube channel, but doesn't allow
        // other types of access.
        List<String> scopes = Lists.newArrayList("https://www.googleapis.com/auth/youtube.upload");
        String response;

        try {
            // Authorize the request.
            Credential credential = AuthHelper.authorize(scopes, "uploadvideo");

            // This object is used to make YouTube Data API requests.
            youtube = new YouTube.Builder(AuthHelper.HTTP_TRANSPORT, AuthHelper.JSON_FACTORY, credential).setApplicationName(
                    "diccionario-lesco-youtube-channel").build();

            System.out.println("Uploading: " + videoFile.getName());

            // Add extra information to the video before uploading.
            Video videoObjectDefiningMetadata = new Video();

            // Set the video to be publicly visible. This is the default
            // setting. Other supporting settings are "unlisted" and "private."
            VideoStatus status = new VideoStatus();
            status.setPrivacyStatus("public");
            videoObjectDefiningMetadata.setStatus(status);

            // Most of the video's metadata is set on the VideoSnippet object.
            VideoSnippet snippet = new VideoSnippet();

            // This code uses a Calendar instance to create a unique name and
            // description for test purposes so that you can easily upload
            // multiple files. You should remove this code from your project
            // and use your own standard names instead.
            //Calendar cal = Calendar.getInstance();
            //snippet.setTitle("Upload via Diccionario Lesco on " + cal.getTime());
            //snippet.setDescription("Video uploaded via YouTube Data API V3 using the Java library " + "on " + cal.getTime());
            
//            snippet.setTitle(addTermForm.getWordName() + "Diccionario LESCO");
//            snippet.setDescription("Definición: " + addTermForm.getDefinition() + "\n" + 
//            		"Explicación: " + addTermForm.getExplanation() + "\n" + "Ejemplo: " + addTermForm.getExample());
            
            snippet.setTitle(title + "- Diccionario LESCO");
            snippet.setDescription(description);

            // Set the keyword tags that you want to associate with the video.
            List<String> tags = new ArrayList<String>();
            tags.add("LESCO");
            tags.add("Lenguaje de Señas Costarricense");
            tags.add("Diccionario");
            tags.add("Costa Rica");
            snippet.setTags(tags);

            // Add the completed snippet object to the video resource.
            videoObjectDefiningMetadata.setSnippet(snippet);

//            InputStreamContent mediaContent = new InputStreamContent(VIDEO_FILE_FORMAT,
//                    UploadVideo.class.getResourceAsStream("/sample-video.mp4"));
            
            
            InputStreamContent mediaContent =
                new InputStreamContent(VIDEO_FILE_FORMAT,
                    new BufferedInputStream(new FileInputStream(multipartToFile(videoFile))));
            //mediaContent.setLength(multipartToFile(videoFile).length());
            
            
            //InputStreamContent mediaContent = multipartToFile(videoFile);
            
            

            // Insert the video. The command sends three arguments. The first
            // specifies which information the API request is setting and which
            // information the API response should return. The second argument
            // is the video resource that contains metadata about the new video.
            // The third argument is the actual video content.
            YouTube.Videos.Insert videoInsert = youtube.videos()
                    .insert("snippet,statistics,status", videoObjectDefiningMetadata, mediaContent);

            // Set the upload type and add an event listener.
            MediaHttpUploader uploader = videoInsert.getMediaHttpUploader();

            // Indicate whether direct media upload is enabled. A value of
            // "True" indicates that direct media upload is enabled and that
            // the entire media content will be uploaded in a single request.
            // A value of "False," which is the default, indicates that the
            // request will use the resumable media upload protocol, which
            // supports the ability to resume an upload operation after a
            // network interruption or other transmission failure, saving
            // time and bandwidth in the event of network failures.
            uploader.setDirectUploadEnabled(false);

            MediaHttpUploaderProgressListener progressListener = new MediaHttpUploaderProgressListener() {
                public void progressChanged(MediaHttpUploader uploader) throws IOException {
                    switch (uploader.getUploadState()) {
                        case INITIATION_STARTED:
                            System.out.println("Initiation Started");
                            break;
                        case INITIATION_COMPLETE:
                            System.out.println("Initiation Completed");
                            break;
                        case MEDIA_IN_PROGRESS:
                            System.out.println("Upload in progress");
                            System.out.println("Upload percentage: " + uploader.getProgress());
                            break;
                        case MEDIA_COMPLETE:
                            System.out.println("Upload Completed!");
                            break;
                        case NOT_STARTED:
                            System.out.println("Upload Not Started!");
                            break;
                    }
                }
            };
            uploader.setProgressListener(progressListener);

            // Call the API and upload the video.
            Video returnedVideo = videoInsert.execute();
            
            response = returnedVideo.getId();

            // Print data about the newly inserted video from the API response.
            System.out.println("\n================== Returned Video ==================\n");
            System.out.println("  - Id: " + returnedVideo.getId());
            System.out.println("  - Title: " + returnedVideo.getSnippet().getTitle());
            System.out.println("  - Tags: " + returnedVideo.getSnippet().getTags());
            System.out.println("  - Privacy Status: " + returnedVideo.getStatus().getPrivacyStatus());
            System.out.println("  - Video Count: " + returnedVideo.getStatistics().getViewCount());

        } catch (GoogleJsonResponseException e) {
        	logger.error("UploadVideo - upload() - Error: ", e);
            System.err.println("GoogleJsonResponseException code: " + e.getDetails().getCode() + " : "
                    + e.getDetails().getMessage());
            e.printStackTrace();
            return "failure";
        } catch (IOException e) {
        	logger.error("UploadVideo - upload() - Error: ", e);
            System.err.println("IOException: " + e.getMessage());
            e.printStackTrace();
            return "failure";
        } catch (Throwable t) {
        	logger.error("UploadVideo - upload() - Error: ", t);
            System.err.println("Throwable: " + t.getMessage());
            t.printStackTrace();
            return "failure";
        }
        
        logger.debug("UploadVideo - upload() - End");
        
        return response;
    }
    
    public Video getVideoMetadata(String videoID) {
    	// Read the developer key from the properties file.

        Properties properties = new Properties();
        
        Video youtubeVideo = null;

        try {
        	InputStream in = YoutubeHelper.class.getResourceAsStream("/" + PROPERTIES_FILENAME);
            properties.load(in);
        } catch (IOException e) {
            System.err.println("There was an error reading " + PROPERTIES_FILENAME + ": " + e.getCause()
                    + " : " + e.getMessage());
            System.exit(1);
        }
        
        try{
        	 // This object is used to make YouTube Data API requests. The last
            // argument is required, but since we don't need anything
            // initialized when the HttpRequest is initialized, we override
            // the interface and provide a no-op function.
            youtube = new YouTube.Builder(AuthHelper.HTTP_TRANSPORT, AuthHelper.JSON_FACTORY, new HttpRequestInitializer() {
                public void initialize(HttpRequest request) throws IOException {
                }
            }).setApplicationName("diccionario-lesco-youtube-channel").build();
            
            //API:https://developers.google.com/youtube/v3/docs/videos/list 
            YouTube.Videos.List listOfVideos = youtube.videos().list("id,snippet,statistics");
      
            // Set your developer key from the {{ Google Cloud Console }} for
            // non-authenticated requests. See:
            // {{ https://cloud.google.com/console }}
            String apiKey = properties.getProperty("youtube.apikey");
          
            listOfVideos.setKey(apiKey);
            listOfVideos.set("id", videoID);
                        
            listOfVideos.setMaxResults(NUMBER_OF_VIDEOS_RETURNED);
            
         // Call the API and print results.
            VideoListResponse videoResponse = listOfVideos.execute();
            
            List<Video> searchResultList = videoResponse.getItems();
            if (searchResultList != null) {
                //prettyPrint(searchResultList.iterator(), "Test");
            	
            	if(searchResultList.size() != 0) youtubeVideo= searchResultList.get(0);
            }
        } catch (GoogleJsonResponseException e) {
            System.err.println("There was a service error: " + e.getDetails().getCode() + " : "
                    + e.getDetails().getMessage());
        } catch (IOException e) {
            System.err.println("There was an IO error: " + e.getCause() + " : " + e.getMessage());
        } catch (Throwable t) {
            t.printStackTrace();
        }
       return youtubeVideo; 
    }
    
    /**
     * Converts a MultipartFile file to particular File type
     * @param multipart
     * @return
     * @throws IllegalStateException
     * @throws IOException
     */
    public File multipartToFile(MultipartFile multipart) throws IllegalStateException, IOException {
        
    	logger.debug("UploadVideo - multipartToFile() - Start");
    	
    	File convFile = new File( multipart.getOriginalFilename());
        multipart.transferTo(convFile);
        
        logger.debug("UploadVideo - multipartToFile() - End");
        
        return convFile;
    }
    
    
    /*
     * Prints out all results in the Iterator. For each result, print the
     * title, video ID, and thumbnail.
     *
     * @param iteratorSearchResults Iterator of SearchResults to print
     *
     * @param query Search query (String)
     */
    private static void prettyPrint(Iterator<SearchResult> iteratorSearchResults, String query) {

        System.out.println("\n=============================================================");
        System.out.println(
                "   First " + NUMBER_OF_VIDEOS_RETURNED + " videos for search on \"" + query + "\".");
        System.out.println("=============================================================\n");

        if (!iteratorSearchResults.hasNext()) {
            System.out.println(" There aren't any results for your query.");
        }

        while (iteratorSearchResults.hasNext()) {

            SearchResult singleVideo = iteratorSearchResults.next();
            ResourceId rId = singleVideo.getId();

            // Confirm that the result represents a video. Otherwise, the
            // item will not contain a video ID.
            if (rId.getKind().equals("youtube#video")) {
                Thumbnail thumbnail = singleVideo.getSnippet().getThumbnails().getDefault();

                System.out.println(" Video Id" + rId.getVideoId());
                System.out.println(" Title: " + singleVideo.getSnippet().getTitle());
                System.out.println(" Thumbnail: " + thumbnail.getUrl());
                System.out.println("\n-------------------------------------------------------------\n");
            }
        }
    }
}
