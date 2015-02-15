/**
 * This program copies the contents of a file from one location to another.
 *
 * @author Kevin J James
 * @version 02.06.15
 */
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;



public class FileCopier {
    /**
     * This is the main method, and it calls the copy() method.
     *
     * Exit status 0 - successful
     *             1 - input file does not exist
     *             2 - output file already exists
     */
    public static void main(String[] args) {
        /* String variable to hold input filename */
        String inFile;
        /* String variable to hold output filename */
        String outFile;
        /* Scanner used for reading in from stdin */
        Scanner in = new Scanner(System.in);

        /* Prompt user for input filename */
        System.out.print("Enter input filename> ");
        /* Read in input filename */
        inFile = in.next();

        /* Prompt user for output filename */
        System.out.print("Enter output filename> ");
        /* Read in output filename */
        outFile = in.next();
        
        try {
            /* Perform copy operation */
            int status = copy(inFile, outFile);
            if (status != 0)
                System.exit(status);
        } catch (IOException e) {
            System.out.println("Copy error IOException thrown: " + e);
        }

        /* Exit with zero return value */
        System.exit(0);
    }
    
    /**
     * This method accepts a source filename and a desired destination
     * filename, and then copies the contents from the source file to the 
     * destination file.
     *
     * @param src  source filename
     * @param dest  destination filename
     * @return execution status
     *        0  - success
     *        1  - input file does NOT exist
     *        2  - output file DOES exist
     */
    public static int copy(String src, String dest) throws IOException {
        File outFile;
        File inFile;
        FileOutputStream ofs;
        try {
            /* File variable to hold source file */
            inFile = new File(src);
            /* Check to see if source file exists */
            if (!inFile.exists())
                /* Abort because input file doesn't exist */
                return 1;

            /* File variable to hold destination file */
            outFile = new File(dest);
            /* Check to see if destination file exists */
            if (outFile.exists())
                /* Abort because output file already exists */
                return 2;

            /* FileOutputStram used to write to given destination file */
            ofs = new FileOutputStream(outFile);
            /* Scanner used for reading in from source file */
            Scanner fScanner = new Scanner(inFile);
            /* Byte array to hold file contents */
            byte[] content;
            
            /* Loop until end of input file */
            while (fScanner.hasNextLine()) {
                content = fScanner.nextLine().getBytes();
                ofs.write(content);
                content = "\n".getBytes();
                ofs.write(content);
            }

            /* Close scanner */
            fScanner.close();

            /* Close output stream */
            ofs.flush();
            ofs.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        /* Print completion message to screen */
        System.out.println("File copied successfully!");
        /* Method ran successfully */
        return 0;
    }
}
