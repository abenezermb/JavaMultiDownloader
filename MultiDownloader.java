// Multidownloader downloads multiple files at the same time cutting the time it takes to download multiple files sequentially significantly.
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class MultiDownloader {
    private static final int MYTHREADS = 5;
    public static void main(String[] args) throws Exception {
        // ExecutorService executor = Executors.newFixedThreadPool(MYTHREADS);
        // System.out.println("Copy and paste the URL below ");
        Scanner in = new Scanner(System.in);
        System.out.println("How many files do you want to download the same time?");
        int size = in.nextInt();
        ExecutorService executor = Executors.newFixedThreadPool(5);
        String urls [] = new String[size];
        System.out.println("Now enter all "+size+" URL(s) one after another by pressing `Enter`");
        for(var i=0;i<size;i++){
            urls[i] = in.next();
            System.out.println("Url Entered: "+urls[i]);
            System.out.println("\n");
        }
        // System.out.println(urlEntered);
        System.out.println("Finished reading all the URL(s). Download will being shortly");
        for(var i=0;i<urls.length;i++){
            String url = urls[i];
            String chunks[] = urls[i].split("/");
            String filename = chunks[chunks.length-1];
            Runnable worker = new MDRunnable(url,filename);
            executor.execute(worker);
        }
        executor.shutdown();
        // Wait until all threads are finished
        while(!executor.isTerminated()){}
        System.out.println("\nFinished all threads");
    }
    
    public static class MDRunnable implements Runnable{
    private  URL url;
    private  String filename;
    public MDRunnable(String url, String filename){
        try {
            this.url = new URL(url);
        } catch (Exception e) {
            //TODO: handle exception
            System.out.println("Incorrect URL");
        }
        this.filename = filename;
    }
    // Download file function here
    public  void DownloadFile() throws Exception{
        // expects a single url pared with the filename at once
        try(InputStream in = this.url.openStream()) {
            Files.copy(in,Paths.get(this.filename));
        } catch (Exception e) {
            // System.out.println("Error ocurred when downloading the file.");
            e.printStackTrace();
        }
    }
    @Override
    public void run(){
        try {
            DownloadFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    }
}



