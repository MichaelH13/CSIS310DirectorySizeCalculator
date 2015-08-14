import java.io.File;
import java.util.ArrayDeque;

/**
 * Program to compute the size of the contents of a given directory on the local
 * disk.
 *
 */
public class TotalSize
{
   public static final String NO_ARGS_ERROR      = "You must provide a file path to compute.";
   public static final String INVALID_PATH_ERROR = "You have entered an invalid file path. Can not compute";
   public static final String UNREADABLE_ERROR   = "Some files/directories were unreadable.";

   /**
    * Main program for counting the bytes in a given file path passed as the
    * first element in the args argument.
    * 
    * @param args
    *           the first element should contain the file path to output the
    *           size of, if the file path is not valid, an error message will be
    *           displayed.
    */
   public static void main(String[] args)
   {
      String filePath = null;
      File file;
      File[] files;
      long totalFileSize = 0L;
      boolean allReadable = true;
      ArrayDeque<File> filesToProcessStack = new ArrayDeque<File>();

      // If we have at least one argument, attempt to run the program, otherwise
      // display an error message
      if (args.length > 0)
      {
         filePath = args[0];
         file = new File(filePath);

         // If the file path given is valid compute its size, otherwise display
         // an
         // error message
         if (file.exists())
         {
            // Add the file given to us to the stack
            filesToProcessStack.push(file);

            // While we still have files to process
            while (!filesToProcessStack.isEmpty())
            {
               // Get a file from the files to process stack
               file = filesToProcessStack.pop();

               // If the current file is not null and it is a directory
               if (file != null && file.isDirectory())
               {
                  // The current file is a directory, so get a list of its
                  // contents, add its size to the total size and push them on
                  // to
                  // the files to process stack
                  files = file.listFiles();
                  totalFileSize += file.length();

                  // If there are files to add to our total size, then add them
                  // to the filesToProcessStack
                  if (files != null)
                  {
                     // Add each file to the files to process stack
                     for (int i = 0; i < files.length; i++)
                     {
                        filesToProcessStack.push(files[i]);
                     }
                  }
                  else
                  // If files is null, then something was read
                  // falsely as a directory
                  {
                     allReadable = false;
                  }
               }
               // If the current file is a file then add it to our total size
               else if (file != null && file.isFile())
               {
                  totalFileSize += file.length();
               }
            }

            // If the file is not null and the file path exists compute its
            // size, and display the result to the user
            System.out.println(totalFileSize + " bytes");
         }
         // If the file path given does not exist then display an error message
         // for the user informing them of the error
         else if (!file.exists())
         {
            System.out.println(INVALID_PATH_ERROR);
         }
      }
      // If we don't have any elements in args, display an error message
      else
      {
         System.out.println(NO_ARGS_ERROR);
      }

      // If some files were unreadable, then display a message to inform the
      // user
      if (!allReadable)
      {
         System.out.println(UNREADABLE_ERROR);
      }
   }
}
