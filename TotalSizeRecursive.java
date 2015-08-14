import java.io.File;

/**
 * Program to compute the size of the contents of a given directory on the local
 * disk.
 *
 */
public class TotalSizeRecursive
{
   public static final String NO_ARGS_ERROR      = "You must provide a file path to compute.";
   public static final String INVALID_PATH_ERROR = "You have entered an invalid file path. Can not compute";
   public static final String UNREADABLE_ERROR   = "Some files/directories were unreadable.";
   private static boolean     allReadable        = true;

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
      long totalFileSize = 0L;

      // If we have at least one argument, attempt to run the program, otherwise
      // display an error message
      if (args.length > 0)
      {
         // Get the file path and create a new file object with it
         filePath = args[0];
         file = new File(filePath);

         // If the file is not null and the file path exists compute its size,
         // and display the result to the user
         if (file != null && file.exists())
         {
            totalFileSize = totalFileSize(file);
            System.out.println(totalFileSize + " bytes");
         }
         else
         // If the file path given does not exist or is null then display an
         // error message for the user informing them of the error
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

   /**
    * Computes the recursive sum of all directories and files within the file
    * passed as a parameter.
    * 
    * @param file
    *           the file to compute the recursive sum of all directories and
    *           files within the file passed as a parameter.
    * @return the sum of all the directories and files of the file passed as an
    *         argument.
    */
   private static long totalFileSize(File file)
   {
      long fileSize = 0L;
      File[] files;

      // If the current file is not null and it is a directory
      if (file != null && file.isDirectory())
      {
         // The current file is a directory, so get a list of its
         // contents, add its size to the total size
         files = file.listFiles();
         fileSize += file.length();

         // If there are files in the directory to add to our total size
         if (files != null)
         {
            // Compute the size of each file in the directory and add it to our
            // total size
            for (int i = 0; i < files.length; i++)
            {
               fileSize += totalFileSize(files[i]);
            }
         }
         // If files is null, then something was read
         // falsely as a directory
         else
         {
            allReadable = false;
         }
      }
      // If the current file is a file then add it to our total size
      else if (file != null && file.isFile())
      {
         fileSize += file.length();
      }

      return fileSize;
   }

}
